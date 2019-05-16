package com.enn.commodity.synergistic.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.enn.commodity.synergistic.entity.Arrangement;
import com.enn.commodity.synergistic.entity.IP;
import com.enn.commodity.synergistic.entity.MyException;
import com.enn.commodity.synergistic.entity.NodeOrg;
import com.enn.commodity.synergistic.entity.RelationInfo;
import com.enn.commodity.synergistic.entity.RequestInfo;
import com.enn.commodity.synergistic.service.IPService;
import com.enn.commodity.synergistic.service.RelationInfoService;
import com.enn.commodity.synergistic.service.RequestInfoService;
import com.enn.commodity.synergistic.util.JsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/RequestInfo")
@CrossOrigin
public class RequestInfoController {
	
	public static Logger logger = Logger.getLogger(ArrangementController.class);
	
	@Autowired
	private RequestInfoService requestInfoService;
	
	@Autowired
	private IPService ipService;
	
	@Autowired
	private JsonUtil jsonUtil;
	
	
	@ResponseBody
	@RequestMapping(value = "/CreateRequestInfoForFriend", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject CreateRequestInfoForFriend(@RequestBody JSONObject requestInfoJson) {
		
		if(requestInfoJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(requestInfoJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+requestInfoJson.toString());
		
		requestInfoJson.put("id", UUID.randomUUID().toString().replace("-", "").toLowerCase());
		requestInfoJson.put("friendId", requestInfoJson.optString("organizationId"));
		requestInfoJson.put("friendName", requestInfoJson.optString("organizationName"));
		requestInfoJson.put("status", "待验证");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		requestInfoJson.put("issuedDate", df.format(new Date()));
		
		JSONObject responseObject=new JSONObject();
		
		RequestInfo requestInfo=(RequestInfo)JSONObject.toBean(requestInfoJson, RequestInfo.class);
		
		
		int result =requestInfoService.CreateRequestInfo(requestInfo);
		
		if(result==0) {
			responseObject.put("errno", 181);
			responseObject.put("error", "创建RequestInfo失败");
			responseObject.put("data", new JSONObject());
		}else {
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", new JSONObject());
		}
		
		logger.info("返回给前端的json数据:"+responseObject.toString());
		
		return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/UpdateStatusOfRequestInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject UpdateStatusOfRequestInfo(@RequestBody JSONObject requestInfoJson) {
		
		if(requestInfoJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(requestInfoJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+requestInfoJson.toString());
		
		
		JSONObject responseObject=new JSONObject();
		
		RequestInfo requestInfo=(RequestInfo)JSONObject.toBean(requestInfoJson, RequestInfo.class);
		
		
		int result =requestInfoService.UpdateRquestInfo(requestInfo);
		if(result==0) {
			responseObject.put("errno", 182);
			responseObject.put("error", "修改RequestInfo失败");
			responseObject.put("data", new JSONObject());
		}else {
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", new JSONObject());
		}
		
		logger.info("返回给前端的json数据:"+responseObject.toString());
		
		return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/QueryRequestInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryRequestInfo(@RequestBody JSONObject requestInfoJson) {
		
		if(requestInfoJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(requestInfoJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+requestInfoJson.toString());
		
		JSONObject responseObject=new JSONObject();
		
		String organizationId=requestInfoJson.optString("organizationId");
		IP ip=new IP();
		ip.setId(organizationId);
		List<IP> ipList=ipService.SelectIPByObject(ip);
		System.out.println(ipList.size());
		List <RequestInfo> requestInfoList=null;
		
		if(ipList.size()<1) {
			//化多多用户
			requestInfoJson.put("friendId", requestInfoJson.optString("organizationId"));
			//requestInfoJson.put("status","待验证");
			RequestInfo requestInfoForQuery=(RequestInfo)JSONObject.toBean(requestInfoJson, RequestInfo.class);
			requestInfoList = requestInfoService.SelectRequestInfoByObject(requestInfoForQuery);
			
		}else {
			//化plus用户
			requestInfoJson.put("ownerId", requestInfoJson.optString("organizationId"));
			requestInfoJson.put("status","待验证");
			RequestInfo requestInfoForQuery=(RequestInfo)JSONObject.toBean(requestInfoJson, RequestInfo.class);
			requestInfoList = requestInfoService.SelectRequestInfoByObject(requestInfoForQuery);
		}
		
		
		JSONObject dataObject=new JSONObject();
		JSONArray dataArray=new JSONArray();
		if(requestInfoList==null||requestInfoList.size()==0) {
			responseObject.put("errno", 25);
			responseObject.put("error", "自定义查询结果为空");
			responseObject.put("data", new JSONObject());
		}else {
			
			for (RequestInfo requestInfo : requestInfoList) {
				
				dataArray.add(JsonUtil.fromObject(requestInfo));
				
			}
			dataObject.put("requestInfoList", dataArray);
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", dataObject);
		}
		
		logger.info("返回给前端的json数据:"+responseObject.toString());	
		
		return responseObject;
	}

}
