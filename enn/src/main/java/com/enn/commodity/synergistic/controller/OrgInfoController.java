package com.enn.commodity.synergistic.controller;

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
import com.enn.commodity.synergistic.entity.OrgInfo;
import com.enn.commodity.synergistic.entity.SystemOrg;
import com.enn.commodity.synergistic.service.IPService;
import com.enn.commodity.synergistic.service.OrgInfoService;
import com.enn.commodity.synergistic.util.ErrorHandler;
import com.enn.commodity.synergistic.util.JsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/OrgInfo")
@CrossOrigin
public class OrgInfoController {
	
	public static Logger logger = Logger.getLogger(ArrangementController.class);
	
	@Autowired
	private JsonUtil jsonUtil;
	
	@Autowired
	private OrgInfoService orgInfoService;
	
	@Autowired
	private IPService ipService;
	
	@ResponseBody
	@RequestMapping(value = "/SynchronInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject SynchronInfo(@RequestBody JSONObject synchronInfoJson) {
		
		//responseObject:方法最终返回的返回
		JSONObject responseObject=new JSONObject();
		//判断前端传的json是否为空
		if(synchronInfoJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(synchronInfoJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+synchronInfoJson.toString());
		
		//定义访问区块链接口所需要的json
				JSONObject requestJson=new JSONObject();

//				
				if(synchronInfoJson.optString("id").equals("")) {
					throw new MyException(201,"登陆用户缺失机构信息");
				}
				
				int result=0;
				//查询ip
				IP ip=new IP();
				ip.setId(synchronInfoJson.optString("id"));
				List<IP> ipList=ipService.SelectIPByObject(ip);
				
				OrgInfo orgInfo=(OrgInfo)JSONObject.toBean(synchronInfoJson, OrgInfo.class);
				List<OrgInfo> orgList=orgInfoService.SelectOrgInfoByObject(orgInfo);

				JSONObject dataObject=new JSONObject();
				
				if(ipList.size()<1) {
					//非化plus用户
					//查询orginfo表，新增orginfo数据
					if(orgList.size()<1) {
						orgInfo.setIdentify("0");
						result=orgInfoService.CreateOrgInfo(orgInfo);
						if(result<1) {
							throw new MyException(202,"创建机构信息失败");
						}
						//返回非化plus用户
						dataObject.put("userIdentify", "0");
						responseObject.put("errno", 0);
						responseObject.put("error", "success");
						responseObject.put("data", dataObject);
					}else {
						//返回非化plus用户
						dataObject.put("userIdentify", "0");
						responseObject.put("errno", 0);
						responseObject.put("error", "success");
						responseObject.put("data", dataObject);
					}
					
				}else {
					//化plus用户
					//新增orginfo数据
					//返回化plus用户
					if(orgList.size()<1) {
						orgInfo.setIdentify("1");
						result=orgInfoService.CreateOrgInfo(orgInfo);
						if(result<1) {
							throw new MyException(202,"创建机构信息失败");
						}
						//返回化plus用户
						dataObject.put("userIdentify", "1");
						responseObject.put("errno", 0);
						responseObject.put("error", "success");
						responseObject.put("data", dataObject);
					}else {
						//返回化plus用户
						dataObject.put("userIdentify", "1");
						responseObject.put("errno", 0);
						responseObject.put("error", "success");
						responseObject.put("data", dataObject);
					}
				}
				    
				
				logger.info("返回给前端的json数据:"+responseObject.toString());	

				return responseObject;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/QueryOrgInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryOrgInfo(@RequestBody JSONObject orgInfoJson) {
		
		if(orgInfoJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(orgInfoJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+orgInfoJson.toString());
		
		JSONObject responseObject=new JSONObject();
		
		OrgInfo orgInfoForQuery=(OrgInfo)JSONObject.toBean(orgInfoJson, OrgInfo.class);
		
		
		List <OrgInfo> orgList = orgInfoService.SelectOrgInfoByObject(orgInfoForQuery);
		
		JSONObject dataObject=new JSONObject();
		JSONArray dataArray=new JSONArray();
		if(orgList==null||orgList.size()==0) {
			responseObject.put("errno", 25);
			responseObject.put("error", "自定义查询结果为空");
			responseObject.put("data", new JSONObject());
		}else {
			
			for (OrgInfo orgInfo : orgList) {
				
				dataArray.add(JSONObject.fromObject(orgInfo));
				
			}
			dataObject.put("orgList", dataArray);
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", dataObject);
		}
		
		return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/UpdateOrgInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject UpdateOrgInfo(@RequestBody JSONObject orgInfoJson) {
		
		if(orgInfoJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(orgInfoJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		if(orgInfoJson.optString("id")==null&&("").equals(orgInfoJson.optString("id"))) {
			throw new MyException(102,"ID为必传项");
		}
		logger.info("接收前端或外围系统的json数据:"+orgInfoJson.toString());
		
		JSONObject responseObject=new JSONObject();
		
		OrgInfo orgInfo=(OrgInfo)JSONObject.toBean(orgInfoJson, OrgInfo.class);
		
		
		int result =orgInfoService.UpdateOrgInfo(orgInfo);
		if(result==0) {
			responseObject.put("errno", 203);
			responseObject.put("error", "修改机构信息失败");
			responseObject.put("data", new JSONObject());
		}else {
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", new JSONObject());
		}
		
		return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/DeleteOrgInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject DeleteSystemOrg(@RequestBody JSONObject orgInfoJson) {
		
		if(orgInfoJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(orgInfoJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		if(orgInfoJson.optString("id")==null&&("").equals(orgInfoJson.optString("id"))) {
			throw new MyException(102,"ID为必传项");
		}
		logger.info("接收前端或外围系统的json数据:"+orgInfoJson.toString());
		
		JSONObject responseObject=new JSONObject();
		
		OrgInfo orgInfo=(OrgInfo)JSONObject.toBean(orgInfoJson, OrgInfo.class);
		
		
		int result =orgInfoService.DeleteOrgInfo(orgInfo);
		if(result==0) {
			responseObject.put("errno", 204);
			responseObject.put("error", "删除机构信息失败");
			responseObject.put("data", new JSONObject());
		}else {
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", new JSONObject());
		}
		
		return responseObject;
	}
	

}
