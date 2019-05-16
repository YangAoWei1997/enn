package com.enn.commodity.synergistic.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.enn.commodity.synergistic.entity.IP;
import com.enn.commodity.synergistic.entity.MyException;

import com.enn.commodity.synergistic.entity.RelationInfo;
import com.enn.commodity.synergistic.service.IPService;
import com.enn.commodity.synergistic.service.RelationInfoService;
import com.enn.commodity.synergistic.util.JsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/RelationInfo")
@CrossOrigin
public class RelationInfoController {
	
	public static Logger logger = Logger.getLogger(ArrangementController.class);
	
	@Autowired
	private RelationInfoService relationInfoService;
	
	@Autowired
	private IPService ipService;
	
	@Autowired
	private JsonUtil jsonUtil;
	
	@ResponseBody
	@RequestMapping(value = "/CreateRelationInfoForOwner", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject CreateRelationInfoForOwner(@RequestBody JSONObject relationInfoJson) {
		
		if(relationInfoJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(relationInfoJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+relationInfoJson.toString());
		
		relationInfoJson.put("id", UUID.randomUUID().toString().replace("-", "").toLowerCase());
		
		relationInfoJson.put("ownerId", relationInfoJson.optString("organizationId"));
		relationInfoJson.put("ownerName", relationInfoJson.optString("organizationName"));
		relationInfoJson.put("status", "已通过");
		
		Date date=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar gc =new GregorianCalendar();
		
		relationInfoJson.put("issuedDate", df.format(date));
		gc.setTime(date);
		gc.add(5,+30);
		gc.set(gc.get(Calendar.YEAR),gc.get(Calendar.MONTH),gc.get(Calendar.DATE));
		relationInfoJson.put("expiredDate", df.format(gc.getTime()));
		
		relationInfoJson.put("reason", "");
		JSONObject responseObject=new JSONObject();
		
		RelationInfo relationInfo=(RelationInfo)JSONObject.toBean(relationInfoJson, RelationInfo.class);
		
		
		int result =relationInfoService.CreateRelationInfo(relationInfo);
		if(result==0) {
			responseObject.put("errno", 171);
			responseObject.put("error", "创建RelationInfo失败");
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
	@RequestMapping(value = "/UpdateRelationInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject UpdateRelationInfo(@RequestBody JSONObject relationInfoJson) {
		
		if(relationInfoJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(relationInfoJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+relationInfoJson.toString());
		
		
		JSONObject responseObject=new JSONObject();
		
		RelationInfo relationInfo=(RelationInfo)JSONObject.toBean(relationInfoJson, RelationInfo.class);
		
		
		int result =relationInfoService.UpdateRelationInfo(relationInfo);
		if(result==0) {
			responseObject.put("errno", 171);
			responseObject.put("error", "修改RelationInfo失败");
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
	@RequestMapping(value = "/QueryRelationInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryRelationInfo(@RequestBody JSONObject relationInfoJson) {
		
		if(relationInfoJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(relationInfoJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+relationInfoJson.toString());
		
		JSONObject responseObject=new JSONObject();
		
		String organizationId=relationInfoJson.optString("organizationId");
		IP ip=new IP();
		ip.setId(organizationId);
		List<IP> ipList=ipService.SelectIPByObject(ip);
		List <RelationInfo> relationInfoList=null;
		
		if(ipList.size()<1) {
			//化多多用户
			relationInfoJson.put("friendId", relationInfoJson.optString("organizationId"));
			relationInfoJson.put("status", relationInfoJson.optString("已通过"));
			RelationInfo relationInfoForQuery=(RelationInfo)JSONObject.toBean(relationInfoJson, RelationInfo.class);
			relationInfoList = relationInfoService.SelectRelationInfoByObject(relationInfoForQuery);
			
		}else {
			//化plus用户
			relationInfoJson.put("ownerId", relationInfoJson.optString("organizationId"));
			relationInfoJson.put("status", relationInfoJson.optString("已通过"));
			RelationInfo relationInfoForQuery=(RelationInfo)JSONObject.toBean(relationInfoJson, RelationInfo.class);
			relationInfoList = relationInfoService.SelectRelationInfoByObject(relationInfoForQuery);
		}
		
		
		JSONObject dataObject=new JSONObject();
		JSONArray dataArray=new JSONArray();
		if(relationInfoList==null||relationInfoList.size()==0) {
			responseObject.put("errno", 25);
			responseObject.put("error", "自定义查询结果为空");
			responseObject.put("data", new JSONObject());
		}else {
			
			for (RelationInfo relationInfo : relationInfoList) {
				
				dataArray.add(JsonUtil.fromObject(relationInfo));
				
			}
			dataObject.put("relationInfoList", dataArray);
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", dataObject);
		}
		
		logger.info("返回给前端的json数据:"+responseObject.toString());	
		
		return responseObject;
	}

}
