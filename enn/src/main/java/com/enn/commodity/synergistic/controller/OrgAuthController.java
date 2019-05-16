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
import com.enn.commodity.synergistic.entity.OrgAuth;

import com.enn.commodity.synergistic.service.OrgAuthService;
import com.enn.commodity.synergistic.util.JsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/OrgAuth")
@CrossOrigin
public class OrgAuthController {
	
	private static Logger logger = Logger.getLogger(BillOfLadingController.class);
	
	@Autowired
	private JsonUtil jsonUtil;
	
	@Autowired
	private OrgAuthService orgAuthService;
	
	@ResponseBody
	@RequestMapping(value = "/QueryOrgAuth", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryPO(@RequestBody JSONObject orgauthJson) {
		
		if(orgauthJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(orgauthJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+orgauthJson.toString());
		
		JSONObject responseObject=new JSONObject();
		
		OrgAuth orgauthForQuery=(OrgAuth)JSONObject.toBean(orgauthJson, OrgAuth.class);
		
		
		List <OrgAuth> orgauthList = orgAuthService.SelectOrgAuthByObject(orgauthForQuery);
		
		JSONObject dataObject=new JSONObject();
		JSONArray dataArray=new JSONArray();
		if(orgauthList==null||orgauthList.size()==0) {
			responseObject.put("errno", 25);
			responseObject.put("error", "自定义查询结果为空");
			responseObject.put("data", new JSONObject());
		}else {
			
			for (OrgAuth orgauth : orgauthList) {
				
				dataArray.add(JsonUtil.fromObject(orgauth));
				
			}
			dataObject.put("orgauthList", dataArray);
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", dataObject);
		}
		logger.info("返回给前端的json数据:"+responseObject.toString());
		
		return responseObject;
	}
	
	

}
