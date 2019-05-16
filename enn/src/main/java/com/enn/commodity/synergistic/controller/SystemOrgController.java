package com.enn.commodity.synergistic.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.enn.commodity.synergistic.entity.MyException;

import com.enn.commodity.synergistic.entity.SystemOrg;
import com.enn.commodity.synergistic.service.SystemOrgService;
import com.enn.commodity.synergistic.util.JsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/SystemOrg")
@CrossOrigin
public class SystemOrgController {
	
    public static Logger logger = Logger.getLogger(SystemOrgController.class);
	
	@Autowired
	private SystemOrgService systemOrgService;
			
	@Autowired
	private JsonUtil jsonUtil;
	
	@ResponseBody
	@RequestMapping(value = "/CreateSystemOrg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject CreateSystemOrg(@RequestBody JSONObject systemOrgJson) {
		
		if(systemOrgJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(systemOrgJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+systemOrgJson.toString());
		
		JSONObject responseObject=new JSONObject();
		
		SystemOrg systemOrg=(SystemOrg)JSONObject.toBean(systemOrgJson, SystemOrg.class);
		
		
		int result =systemOrgService.CreateSystemOrg(systemOrg);
		if(result==0) {
			responseObject.put("errno", 141);
			responseObject.put("error", "创建SystemOrg失败");
			responseObject.put("data", new JSONObject());
		}else {
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", new JSONObject());
		}
		
		return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/QuerySystemOrg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QuerySystemOrg(@RequestBody JSONObject systemOrgJson) {
		
		if(systemOrgJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(systemOrgJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+systemOrgJson.toString());
		
		JSONObject responseObject=new JSONObject();
		
		SystemOrg systemOrgForQuery=(SystemOrg)JSONObject.toBean(systemOrgJson, SystemOrg.class);
		
		
		List <SystemOrg> systemOrgList = systemOrgService.SelectSystemOrgByObject(systemOrgForQuery);
		
		JSONObject dataObject=new JSONObject();
		JSONArray dataArray=new JSONArray();
		if(systemOrgList==null||systemOrgList.size()==0) {
			responseObject.put("errno", 25);
			responseObject.put("error", "自定义查询结果为空");
			responseObject.put("data", new JSONObject());
		}else {
			
			for (SystemOrg systemOrg : systemOrgList) {
				
				dataArray.add(JSONObject.fromObject(systemOrg));
				
			}
			dataObject.put("systemOrgList", dataArray);
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", dataObject);
		}
		
		return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/UpdateSystemOrg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject UpdateSystemOrg(@RequestBody JSONObject systemOrgJson) {
		
		if(systemOrgJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(systemOrgJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		if(systemOrgJson.optString("ID")==null&&("").equals(systemOrgJson.optString("ID"))) {
			throw new MyException(102,"ID为必传项");
		}
		logger.info("接收前端或外围系统的json数据:"+systemOrgJson.toString());
		
		JSONObject responseObject=new JSONObject();
		
		SystemOrg systemOrg=(SystemOrg)JSONObject.toBean(systemOrgJson, SystemOrg.class);
		
		
		int result =systemOrgService.UpdateSystemOrg(systemOrg);
		if(result==0) {
			responseObject.put("errno", 142);
			responseObject.put("error", "修改SystemOrg失败");
			responseObject.put("data", new JSONObject());
		}else {
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", new JSONObject());
		}
		
		return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/DeleteSystemOrg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject DeleteSystemOrg(@RequestBody JSONObject systemOrgJson) {
		
		if(systemOrgJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(systemOrgJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		if(systemOrgJson.optString("ID")==null&&("").equals(systemOrgJson.optString("ID"))) {
			throw new MyException(102,"ID为必传项");
		}
		logger.info("接收前端或外围系统的json数据:"+systemOrgJson.toString());
		
		JSONObject responseObject=new JSONObject();
		
		SystemOrg systemOrg=(SystemOrg)JSONObject.toBean(systemOrgJson, SystemOrg.class);
		
		
		int result =systemOrgService.DeleteSystemOrg(systemOrg);
		if(result==0) {
			responseObject.put("errno", 143);
			responseObject.put("error", "删除NodeOrg失败");
			responseObject.put("data", new JSONObject());
		}else {
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", new JSONObject());
		}
		
		return responseObject;
	}

}
