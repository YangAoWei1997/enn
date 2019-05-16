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
import com.enn.commodity.synergistic.entity.RequestOfOrg;
import com.enn.commodity.synergistic.service.IPService;
import com.enn.commodity.synergistic.service.RelationInfoService;
import com.enn.commodity.synergistic.service.RequestInfoService;
import com.enn.commodity.synergistic.service.RequestOfOrgService;
import com.enn.commodity.synergistic.util.JsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/RequestOfOrg")
@CrossOrigin
public class RequestOfOrgController {
	
	public static Logger logger = Logger.getLogger(RequestOfOrgController.class);
	
	@Autowired
	private RequestOfOrgService requestOfOrgService;
	
	@Autowired
	private IPService ipService;
	
	@Autowired
	private JsonUtil jsonUtil;
	
	
	@ResponseBody
	@RequestMapping(value = "/CreateRequestOfOrg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject CreateRequestOfOrg(@RequestBody JSONObject requestOfOrgJson) {
		
		if(requestOfOrgJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(requestOfOrgJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+requestOfOrgJson.toString());
		
		requestOfOrgJson.put("id", UUID.randomUUID().toString().replace("-", "").toLowerCase());
		requestOfOrgJson.put("requestOrgId", requestOfOrgJson.optString("organizationId"));
		requestOfOrgJson.put("requestOrgName", requestOfOrgJson.optString("organizationName"));
		requestOfOrgJson.put("status", "待验证");
		requestOfOrgJson.put("dataSource", requestOfOrgJson.optString("source"));
		requestOfOrgJson.put("contact", requestOfOrgJson.optString("contact"));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		requestOfOrgJson.put("issuedDate", df.format(new Date()));
		
		JSONObject responseObject=new JSONObject();
		
		RequestOfOrg requestOfOrg=(RequestOfOrg)JSONObject.toBean(requestOfOrgJson, RequestOfOrg.class);
		
		
		int result =requestOfOrgService.CreateRequestOfOrg(requestOfOrg);
		
		if(result==0) {
			responseObject.put("errno", 211);
			responseObject.put("error", "创建加盟验证信息失败");
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
	@RequestMapping(value = "/UpdateStatusOfRequestOfOrg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject UpdateStatusOfRequestOfOrg(@RequestBody JSONObject requestOfOrgJson) {
		
		if(requestOfOrgJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(requestOfOrgJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		if(requestOfOrgJson.optString("id")==null&&("").equals(requestOfOrgJson.optString("id"))) {
			throw new MyException(102,"ID为必传项");
		}
		logger.info("接收前端或外围系统的json数据:"+requestOfOrgJson.toString());
		
		
		JSONObject responseObject=new JSONObject();
		
		
		
		
		RequestOfOrg requestOfOrg=(RequestOfOrg)JSONObject.toBean(requestOfOrgJson, RequestOfOrg.class);
		
		
		
		int resultOfUpdate =requestOfOrgService.UpdateRequestOfOrg(requestOfOrg);
		if(resultOfUpdate<1) {
			throw new MyException(212,"修改加盟验证信息失败");
		}
		
		if(requestOfOrgJson.optString("status").equals("已通过")) {
			//
			IP ipInfo=new IP();
			ipInfo.setId(requestOfOrgJson.optString("requestOrgId"));
			ipInfo.setName(requestOfOrgJson.optString("requestOrgName"));
			JSONObject resultOfCreate=ipService.CreateIP(ipInfo);
			if(!resultOfCreate.optString("error").equals("success")) {
				throw new MyException(resultOfCreate.optInt("errno"),resultOfCreate.optString("error"));
			}
			
		}
		
		
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", new JSONObject());
		
		
		logger.info("返回给前端的json数据:"+responseObject.toString());
		
		return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/QueryRequestOfOrg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryRequestOfOrg(@RequestBody JSONObject requestOfOrgJson) {
		
		if(requestOfOrgJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(requestOfOrgJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+requestOfOrgJson.toString());
		
		JSONObject responseObject=new JSONObject();
		
//		String organizationId=requestInfoJson.optString("organizationId");
//		IP ip=new IP();
//		ip.setId(organizationId);
//		List<IP> ipList=ipService.SelectIPByObject(ip);
//		System.out.println(ipList.size());
		List <RequestOfOrg> requestOfOrgList=null;
		
		
		requestOfOrgJson.put("requestOrgId", requestOfOrgJson.optString("organizationId"));
		requestOfOrgJson.put("requestOrgName", requestOfOrgJson.optString("organizationName"));
//		    requestOfOrgJson.put("status","待验证");
			RequestOfOrg requestOfOrgForQuery=(RequestOfOrg)JSONObject.toBean(requestOfOrgJson, RequestOfOrg.class);
			requestOfOrgList = requestOfOrgService.SelectRequestOfOrgByObject(requestOfOrgForQuery);
		
		
		
		JSONObject dataObject=new JSONObject();
		JSONArray dataArray=new JSONArray();
		if(requestOfOrgList==null||requestOfOrgList.size()==0) {
			responseObject.put("errno", 25);
			responseObject.put("error", "自定义查询结果为空");
			responseObject.put("data", new JSONObject());
		}else {
			
			for (RequestOfOrg requestOfOrg : requestOfOrgList) {
				
				dataArray.add(JsonUtil.fromObject(requestOfOrg));
				
			}
			dataObject.put("requestOfOrgList", dataArray);
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", dataObject);
		}
		
		logger.info("返回给前端的json数据:"+responseObject.toString());	
		
		return responseObject;
	}

}
