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
import com.enn.commodity.synergistic.entity.ShareData;
import com.enn.commodity.synergistic.entity.SystemOrg;
import com.enn.commodity.synergistic.service.EncryptService;
import com.enn.commodity.synergistic.service.ShareDataService;
import com.enn.commodity.synergistic.service.SystemOrgService;
import com.enn.commodity.synergistic.util.ErrorHandler;
import com.enn.commodity.synergistic.util.JsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/ShareData")
@CrossOrigin
public class ShareDataController {
	
	    public static Logger logger = Logger.getLogger(ShareDataController.class);
		
		@Autowired
		private ShareDataService shareDataService;
		
		@Autowired
		private EncryptService encryptService;
				
		@Autowired
		private JsonUtil jsonUtil;
		
		@ResponseBody
		@RequestMapping(value = "/CreateShareData", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		private JSONObject CreateShareData(@RequestBody JSONObject shareDataJson) {
			
			if(shareDataJson==null) {
				throw new MyException(80,"前端传值为null");
			}
			if(!jsonUtil.isJson(shareDataJson.toString())) {
				throw new MyException(81,"前端传值不是json格式");
			}
			logger.info("接收前端或外围系统的json数据:"+shareDataJson.toString());
			
			JSONObject responseObject=new JSONObject();
			shareDataJson.put("dataType", "ShareData");
			
			JSONObject requestJson=new JSONObject();
			JSONObject requestJsonForEncrypt=new JSONObject();
			
			String orgId=shareDataJson.optString("organizationId");
			String detail=shareDataJson.optString("detail");
			requestJsonForEncrypt.put("orgId", orgId);
			requestJsonForEncrypt.put("detail", detail);
			
			String rj=encryptService.Encrypt(requestJsonForEncrypt);
			
			
			
			
			
            JSONObject jsonObjectForEncrypt=JSONObject.fromObject(rj);
			
			logger.info("管理控制台反返回数据反序列化结果:"+jsonObjectForEncrypt.toString());
			
			//解密
//	      2.判断返回状态码
			//获取error信息
			
			if(jsonObjectForEncrypt.getJSONArray("error").size()==0) {
				logger.info("管理控制台返回数据正常");
			}else {
				
				JSONArray errorArray=jsonObjectForEncrypt.getJSONArray("error");
				//判断errorArray.size是否为0  2019-1-2 refine
				if(errorArray.size()==0) {
					throw new MyException(71,"error数组长度为0");
				}
				
				JSONObject errorMap=errorArray.getJSONObject(0);

				responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
				return responseObject;
			}
			//获取content信息(contentMap)

				
			//判断dataMap.getString是否为空 1-2 refine 为""或 null
			if(!jsonObjectForEncrypt.has("content")) {
				logger.info("缺失content信息");
            	throw new MyException(73,"data中缺失content信息");
			}
					JSONObject contentObject=jsonObjectForEncrypt.getJSONObject("content");
					
					String detailAfterEncrypt=contentObject.getString("detailAfterEncrypt");
					String aesKeyAfterEncrypt=contentObject.getString("aesKeyAfterEncrypt");
					

					shareDataJson.put("detail", detailAfterEncrypt);
			
			
			
			
			
			
			
			
			
			
			requestJson.put("ShareDataJson", shareDataJson.toString());
			requestJson.put("aesKeyAfterEncrypt", aesKeyAfterEncrypt);
			//获取detail并加密
			
			String result =shareDataService.CreateShareData(requestJson);
			
			if(result==null) {
				responseObject.put("errno", 43);
				responseObject.put("error", "区块链service层返回数据为空");
				responseObject.put("data", new JSONObject());
			}
			
			
			JSONObject jsonObject=JSONObject.fromObject(result);
			
			logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
			
			if(jsonObject.get("error") instanceof JSONNull) {
				responseObject.put("errno", 0);
				responseObject.put("error", "success");
				responseObject.put("data", new JSONObject());
			}else {
				
				JSONArray errorArray=jsonObject.getJSONArray("error");
				//判断errorArray.size是否为0  2019-1-2 refine
				if(errorArray.size()==0) {
					throw new MyException(71,"error数组长度为0");
				}
				
				JSONObject errorMap=errorArray.getJSONObject(0);

				responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
				responseObject.put("data", new JSONObject());
				
			}
			
			logger.info("返回给前端的json数据:"+responseObject.toString());	

			return responseObject;
		}
		
		@ResponseBody
		@RequestMapping(value = "/CreateShareDataTemplate", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		private JSONObject CreateShareDataTemplate(@RequestBody JSONObject shareDataTemplateJson) {
			
			if(shareDataTemplateJson==null) {
				throw new MyException(80,"前端传值为null");
			}
			if(!jsonUtil.isJson(shareDataTemplateJson.toString())) {
				throw new MyException(81,"前端传值不是json格式");
			}
			logger.info("接收前端或外围系统的json数据:"+shareDataTemplateJson.toString());
			
			JSONObject responseObject=new JSONObject();
			
			shareDataTemplateJson.put("dataType", "ShareDataTemplate");
			JSONObject requestJson=new JSONObject();
			requestJson.put("ShareDataTemplateJson", shareDataTemplateJson.toString());
			//获取detail并加密
			
			String result =shareDataService.CreateShareDataTemplate(requestJson);
			
			if(result==null) {
				responseObject.put("errno", 43);
				responseObject.put("error", "区块链service层返回数据为空");
				responseObject.put("data", new JSONObject());
			}
			
			
			JSONObject jsonObject=JSONObject.fromObject(result);
			
			logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
			
			if(jsonObject.get("errors") instanceof JSONNull) {
				responseObject.put("errno", 0);
				responseObject.put("error", "success");
				responseObject.put("data", new JSONObject());
			}else {
				
				JSONArray errorArray=jsonObject.getJSONArray("error");
				//判断errorArray.size是否为0  2019-1-2 refine
				if(errorArray.size()==0) {
					throw new MyException(71,"error数组长度为0");
				}
				
				JSONObject errorMap=errorArray.getJSONObject(0);

				responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
				responseObject.put("data", new JSONObject());
				
			}
			
			logger.info("返回给前端的json数据:"+responseObject.toString());	

			return responseObject;
		}
		
		@ResponseBody
		@RequestMapping(value = "/CreateGroup", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		private JSONObject CreateGroup(@RequestBody JSONObject groupJson) {
			
			if(groupJson==null) {
				throw new MyException(80,"前端传值为null");
			}
			if(!jsonUtil.isJson(groupJson.toString())) {
				throw new MyException(81,"前端传值不是json格式");
			}
			logger.info("接收前端或外围系统的json数据:"+groupJson.toString());
			
			JSONObject responseObject=new JSONObject();
			
			groupJson.put("dataType", "Group");
			JSONObject requestJson=new JSONObject();
			requestJson.put("GroupJson", groupJson.toString());
			//获取detail并加密
			
			String result =shareDataService.CreateGroup(requestJson);
			
			if(result==null) {
				responseObject.put("errno", 43);
				responseObject.put("error", "区块链service层返回数据为空");
				responseObject.put("data", new JSONObject());
			}
			
			
			JSONObject jsonObject=JSONObject.fromObject(result);
			
			logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
			
			JSONArray errorArray=jsonObject.getJSONArray("errors");
			if(errorArray ==null||errorArray.size()==0) {
				responseObject.put("errno", 0);
				responseObject.put("error", "success");
				responseObject.put("data", new JSONObject());
			}else {
				
//				JSONArray errorArray=jsonObject.getJSONArray("errors");
//				//判断errorArray.size是否为0  2019-1-2 refine
//				if(errorArray.size()==0) {
//					throw new MyException(71,"error数组长度为0");
//				}
				
				JSONObject errorMap=errorArray.getJSONObject(0);

				responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
				responseObject.put("data", new JSONObject());
				
			}
			
			logger.info("返回给前端的json数据:"+responseObject.toString());	

			return responseObject;
		}
		
		@ResponseBody
		@RequestMapping(value = "/QueryShareData", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		private JSONObject QueryShareData(@RequestBody JSONObject shareDataJson) {
			
			if(shareDataJson==null) {
				throw new MyException(80,"前端传值为null");
			}
			if(!jsonUtil.isJson(shareDataJson.toString())) {
				throw new MyException(81,"前端传值不是json格式");
			}
			logger.info("接收前端或外围系统的json数据:"+shareDataJson.toString());
			
			JSONObject responseObject=new JSONObject();

			
			JSONObject requestJson=new JSONObject();
			//组装json
			requestJson.put("ShareDataId", shareDataJson.optString("id"));
			
			
			//result:区块链接口返回的json字符串
			String resultJson=null;
//			try {
//				resultJson = new RestUtil().httpPost(path+"/Arrangement/QueryArrangement", requestJson);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			resultJson=shareDataService.QueryShareDataById(requestJson);
			
			JSONObject jsonObject=JSONObject.fromObject(resultJson);
			
			logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
			
			//解密
//	      2.判断返回状态码
			//获取error信息
			
			if(jsonObject.get("error") instanceof JSONNull) {
				responseObject.put("errno", 0);
				responseObject.put("error", "success");
			}else {
				
				JSONArray errorArray=jsonObject.getJSONArray("error");
				//判断errorArray.size是否为0  2019-1-2 refine
				if(errorArray.size()==0) {
					throw new MyException(71,"error数组长度为0");
				}
				
				JSONObject errorMap=errorArray.getJSONObject(0);

				responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
				return responseObject;
			}
			//获取content信息(contentMap)
			if(jsonObject.get("data") instanceof JSONNull) {
				responseObject.put("errno", 12);
				responseObject.put("error", "查询结果为空");
				responseObject.put("data", new JSONObject());
				
			}else {
				
				JSONArray shareDataList=new JSONArray();
				JSONArray contentArray=jsonObject.getJSONArray("data");
				
				if(contentArray.size()==0) {
	            	logger.info("data数组长度为0");
	            	throw new MyException(72,"data数组长度为0");
	            }
				for(int i=0;i<contentArray.size();i++) {
					JSONObject shareDataMap=contentArray.getJSONObject(i);
					
					//判断dataMap.getString是否为空 1-2 refine 为""或 null
					if(!shareDataMap.has("content")) {
						logger.info("data中缺失content信息");
		            	throw new MyException(73,"data中缺失content信息");
					}
					String contentStr=shareDataMap.getString("content");
					//将json中的null转换为""
					contentStr=jsonUtil.fromObject(contentStr);
					if(!jsonUtil.isJson(contentStr)) {
						logger.info("格式不是json");
						throw new MyException(74,"格式不是json");
					}
					
					JSONObject shareDataObject=JSONObject.fromObject(contentStr);
					shareDataList.add(shareDataObject);
				}
                JSONObject packlistObject=new JSONObject();
                packlistObject.put("ShareDataList", shareDataList);
				responseObject.put("data", packlistObject);

			}

			logger.info("返回给前端的json数据:"+responseObject.toString());
			return responseObject;
		}
		
		@ResponseBody
		@RequestMapping(value = "/QueryShareDataTemplate", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		private JSONObject QueryShareDataTemplate(@RequestBody JSONObject shareDataTemplateJson) {
			
			if(shareDataTemplateJson==null) {
				throw new MyException(80,"前端传值为null");
			}
			if(!jsonUtil.isJson(shareDataTemplateJson.toString())) {
				throw new MyException(81,"前端传值不是json格式");
			}
			logger.info("接收前端或外围系统的json数据:"+shareDataTemplateJson.toString());
			
			JSONObject responseObject=new JSONObject();

			
			JSONObject requestJson=new JSONObject();
			//组装json
			requestJson.put("ShareDataTemplateId", shareDataTemplateJson.optString("id"));
			
			
			//result:区块链接口返回的json字符串
			String resultJson=null;
//			try {
//				resultJson = new RestUtil().httpPost(path+"/Arrangement/QueryArrangement", requestJson);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			resultJson=shareDataService.QueryShareDataTemplate(requestJson);
			
			JSONObject jsonObject=JSONObject.fromObject(resultJson);
			
			logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
			
			//解密
//	      2.判断返回状态码
			//获取error信息
			
			if(jsonObject.get("error") instanceof JSONNull) {
				responseObject.put("errno", 0);
				responseObject.put("error", "success");
			}else {
				
				JSONArray errorArray=jsonObject.getJSONArray("error");
				//判断errorArray.size是否为0  2019-1-2 refine
				if(errorArray.size()==0) {
					throw new MyException(71,"error数组长度为0");
				}
				
				JSONObject errorMap=errorArray.getJSONObject(0);

				responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
				return responseObject;
			}
			//获取content信息(contentMap)
			if(jsonObject.get("data") instanceof JSONNull) {
				responseObject.put("errno", 12);
				responseObject.put("error", "查询结果为空");
				responseObject.put("data", new JSONObject());
				
			}else {
				
				JSONArray shareDataList=new JSONArray();
				JSONArray contentArray=jsonObject.getJSONArray("data");
				
				if(contentArray.size()==0) {
	            	logger.info("data数组长度为0");
	            	throw new MyException(72,"data数组长度为0");
	            }
				for(int i=0;i<contentArray.size();i++) {
					JSONObject shareDataMap=contentArray.getJSONObject(i);
					
					//判断dataMap.getString是否为空 1-2 refine 为""或 null
					if(!shareDataMap.has("content")) {
						logger.info("data中缺失content信息");
		            	throw new MyException(73,"data中缺失content信息");
					}
					String contentStr=shareDataMap.getString("content");
					//将json中的null转换为""
					contentStr=jsonUtil.fromObject(contentStr);
					if(!jsonUtil.isJson(contentStr)) {
						logger.info("格式不是json");
						throw new MyException(74,"格式不是json");
					}
					
					JSONObject shareDataObject=JSONObject.fromObject(contentStr);
					shareDataList.add(shareDataObject);
				}
                JSONObject packlistObject=new JSONObject();
                packlistObject.put("ShareDataTemplateList", shareDataList);
				responseObject.put("data", packlistObject);

			}

			logger.info("返回给前端的json数据:"+responseObject.toString());
			return responseObject;
		}
		
		@ResponseBody
		@RequestMapping(value = "/QueryGroup", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		private JSONObject QueryGroup(@RequestBody JSONObject groupJson) {
			
			if(groupJson==null) {
				throw new MyException(80,"前端传值为null");
			}
			if(!jsonUtil.isJson(groupJson.toString())) {
				throw new MyException(81,"前端传值不是json格式");
			}
			logger.info("接收前端或外围系统的json数据:"+groupJson.toString());
			
			JSONObject responseObject=new JSONObject();

			
			JSONObject requestJson=new JSONObject();
			//组装json
			requestJson.put("GroupId", groupJson.optString("id"));
			
			
			//result:区块链接口返回的json字符串
			String resultJson=null;
//			try {
//				resultJson = new RestUtil().httpPost(path+"/Arrangement/QueryArrangement", requestJson);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			resultJson=shareDataService.QueryGroup(requestJson);
			
			JSONObject jsonObject=JSONObject.fromObject(resultJson);
			
			logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
			
			//解密
//	      2.判断返回状态码
			//获取error信息
			
			JSONArray errorArray=jsonObject.getJSONArray("errors");
			if(errorArray==null||errorArray.size()==0) {
				responseObject.put("errno", 0);
				responseObject.put("error", "success");
			}else {
				
//				errorArray=jsonObject.getJSONArray("error");
//				//判断errorArray.size是否为0  2019-1-2 refine
//				if(errorArray.size()==0) {
//					throw new MyException(71,"error数组长度为0");
//				}
				
				JSONObject errorMap=errorArray.getJSONObject(0);

				responseObject=new ErrorHandler().errornoHandler(errorMap.optString("code"),responseObject);
				return responseObject;
			}
			//获取content信息(contentMap)
			JSONArray contentArray=jsonObject.getJSONArray("data");
			if(contentArray==null||contentArray.size()==0) {
				responseObject.put("errno", 12);
				responseObject.put("error", "查询结果为空");
				responseObject.put("data", new JSONObject());
				
			}else {
				
				JSONArray shareDataList=new JSONArray();
				
				
				if(contentArray.size()==0) {
	            	logger.info("data数组长度为0");
	            	throw new MyException(72,"data数组长度为0");
	            }
				for(int i=0;i<contentArray.size();i++) {
					JSONObject shareDataMap=contentArray.getJSONObject(i);
					
					//判断dataMap.getString是否为空 1-2 refine 为""或 null
					if(!shareDataMap.has("content")) {
						logger.info("data中缺失content信息");
		            	throw new MyException(73,"data中缺失content信息");
					}
					String contentStr=shareDataMap.getString("content");
					//将json中的null转换为""
					contentStr=jsonUtil.fromObject(contentStr);
					if(!jsonUtil.isJson(contentStr)) {
						logger.info("格式不是json");
						throw new MyException(74,"格式不是json");
					}
					
					JSONObject shareDataObject=JSONObject.fromObject(contentStr);
					shareDataList.add(shareDataObject);
				}
                JSONObject packlistObject=new JSONObject();
                packlistObject.put("GroupList", shareDataList);
				responseObject.put("data", packlistObject);

			}

			logger.info("返回给前端的json数据:"+responseObject.toString());
			return responseObject;
		}
		
		
		
		@ResponseBody
		@RequestMapping(value = "/UpdateShareData", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		private JSONObject UpdateShareData(@RequestBody JSONObject shareDataJson) {
			
			if(shareDataJson==null) {
				throw new MyException(80,"前端传值为null");
			}
			if(!jsonUtil.isJson(shareDataJson.toString())) {
				throw new MyException(81,"前端传值不是json格式");
			}
			if(shareDataJson.optString("id")==null||("").equals(shareDataJson.optString("id"))) {
				throw new MyException(102,"ID为必传项");
			}
			logger.info("接收前端或外围系统的json数据:"+shareDataJson.toString());
			
			JSONObject requestJson=new JSONObject();
			//组装json
			requestJson.put("ShareDataJson", shareDataJson.toString());
			
			JSONObject responseObject=new JSONObject();
			
//			ShareData shareData=(ShareData)JSONObject.toBean(shareDataJson, ShareData.class);
			String result=null;
			
			result =shareDataService.UpdateShareData(requestJson);
			if(result==null) {
				responseObject.put("errno", 43);
				responseObject.put("error", "区块链service层返回数据为空");
				responseObject.put("data", new JSONObject());
			}
			
			
			JSONObject jsonObject=JSONObject.fromObject(result);
			
			logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
			
			if(jsonObject.get("error") instanceof JSONNull) {
				responseObject.put("errno", 0);
				responseObject.put("error", "success");
				responseObject.put("data", new JSONObject());
			}else {
				
				JSONArray errorArray=jsonObject.getJSONArray("error");
				//判断errorArray.size是否为0  2019-1-2 refine
				if(errorArray.size()==0) {
					throw new MyException(71,"error数组长度为0");
				}
				
				JSONObject errorMap=errorArray.getJSONObject(0);

				responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
				responseObject.put("data", new JSONObject());
				
			}
			
			logger.info("返回给前端的json数据:"+responseObject.toString());	

			return responseObject;
		}
		
		@ResponseBody
		@RequestMapping(value = "/UpdateShareDataTemplate", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		private JSONObject UpdateShareDataTemplate(@RequestBody JSONObject shareDataTemplateJson) {
			
			if(shareDataTemplateJson==null) {
				throw new MyException(80,"前端传值为null");
			}
			if(!jsonUtil.isJson(shareDataTemplateJson.toString())) {
				throw new MyException(81,"前端传值不是json格式");
			}
			if(shareDataTemplateJson.optString("id")==null||("").equals(shareDataTemplateJson.optString("id"))) {
				throw new MyException(102,"ID为必传项");
			}
			logger.info("接收前端或外围系统的json数据:"+shareDataTemplateJson.toString());
			
			JSONObject requestJson=new JSONObject();
			//组装json
			requestJson.put("ShareDataTemplateJson", shareDataTemplateJson.toString());
			
			JSONObject responseObject=new JSONObject();
			
			
			String result =shareDataService.UpdateShareDataTemplate(requestJson);
			if(result==null) {
				responseObject.put("errno", 43);
				responseObject.put("error", "区块链service层返回数据为空");
				responseObject.put("data", new JSONObject());
			}
			
			
			JSONObject jsonObject=JSONObject.fromObject(result);
			
			logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
			
			if(jsonObject.get("error") instanceof JSONNull) {
				responseObject.put("errno", 0);
				responseObject.put("error", "success");
				responseObject.put("data", new JSONObject());
			}else {
				
				JSONArray errorArray=jsonObject.getJSONArray("error");
				//判断errorArray.size是否为0  2019-1-2 refine
				if(errorArray.size()==0) {
					throw new MyException(71,"error数组长度为0");
				}
				
				JSONObject errorMap=errorArray.getJSONObject(0);

				responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
				responseObject.put("data", new JSONObject());
				
			}
			
			logger.info("返回给前端的json数据:"+responseObject.toString());	

			return responseObject;
		}
		
		@ResponseBody
		@RequestMapping(value = "/UpdateGroup", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		private JSONObject UpdateGroup(@RequestBody JSONObject groupJson) {
			
			if(groupJson==null) {
				throw new MyException(80,"前端传值为null");
			}
			if(!jsonUtil.isJson(groupJson.toString())) {
				throw new MyException(81,"前端传值不是json格式");
			}
			if(groupJson.optString("id")==null||("").equals(groupJson.optString("id"))) {
				throw new MyException(102,"ID为必传项");
			}
			logger.info("接收前端或外围系统的json数据:"+groupJson.toString());
			
			JSONObject requestJson=new JSONObject();
			//组装json
			requestJson.put("GroupId", groupJson.toString());
			
			JSONObject responseObject=new JSONObject();
			
			
			String result =shareDataService.UpdateGroup(requestJson);
			if(result==null) {
				responseObject.put("errno", 43);
				responseObject.put("error", "区块链service层返回数据为空");
				responseObject.put("data", new JSONObject());
			}
			
			
			JSONObject jsonObject=JSONObject.fromObject(result);
			
			logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
			
			if(jsonObject.get("error") instanceof JSONNull) {
				responseObject.put("errno", 0);
				responseObject.put("error", "success");
				responseObject.put("data", new JSONObject());
			}else {
				
				JSONArray errorArray=jsonObject.getJSONArray("error");
				//判断errorArray.size是否为0  2019-1-2 refine
				if(errorArray.size()==0) {
					throw new MyException(71,"error数组长度为0");
				}
				
				JSONObject errorMap=errorArray.getJSONObject(0);

				responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
				responseObject.put("data", new JSONObject());
				
			}
			
			logger.info("返回给前端的json数据:"+responseObject.toString());	

			return responseObject;
		}
		
		@ResponseBody
		@RequestMapping(value = "/DeleteShareData", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		private JSONObject DeleteShareData(@RequestBody JSONObject shareDataJson) {
			
			if(shareDataJson==null) {
				throw new MyException(80,"前端传值为null");
			}
			if(!jsonUtil.isJson(shareDataJson.toString())) {
				throw new MyException(81,"前端传值不是json格式");
			}
			if(shareDataJson.optString("id")==null||("").equals(shareDataJson.optString("id"))) {
				throw new MyException(102,"ID为必传项");
			}
			logger.info("接收前端或外围系统的json数据:"+shareDataJson.toString());
			
			JSONObject responseObject=new JSONObject();
			
			JSONObject requestJson=new JSONObject();
			//组装json
			requestJson.put("ShareDataId", shareDataJson.optString("id"));
			
			
			String result =shareDataService.DeleteShareData(requestJson);
			if(result==null) {
				responseObject.put("errno", 43);
				responseObject.put("error", "区块链service层返回数据为空");
				responseObject.put("data", new JSONObject());
			}
			
			
			JSONObject jsonObject=JSONObject.fromObject(result);
			
			logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
			
			if(jsonObject.get("error") instanceof JSONNull) {
				responseObject.put("errno", 0);
				responseObject.put("error", "success");
				responseObject.put("data", new JSONObject());
			}else {
				
				JSONArray errorArray=jsonObject.getJSONArray("error");
				//判断errorArray.size是否为0  2019-1-2 refine
				if(errorArray.size()==0) {
					throw new MyException(71,"error数组长度为0");
				}
				
				JSONObject errorMap=errorArray.getJSONObject(0);

				responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
				responseObject.put("data", new JSONObject());
				
			}
			
			logger.info("返回给前端的json数据:"+responseObject.toString());	

			return responseObject;
		}
		
		@ResponseBody
		@RequestMapping(value = "/AddOrgUserToGroup", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		private JSONObject AddOrgUserToGroup(@RequestBody JSONObject orgJson) {
			
			if(orgJson==null) {
				throw new MyException(80,"前端传值为null");
			}
			if(!jsonUtil.isJson(orgJson.toString())) {
				throw new MyException(81,"前端传值不是json格式");
			}
			logger.info("接收前端或外围系统的json数据:"+orgJson.toString());
			
			JSONObject responseObject=new JSONObject();
			
			
			JSONObject requestJson=new JSONObject();
			requestJson.put("GroupId", orgJson.optString("GroupId"));
			requestJson.put("OrgId", orgJson.optString("OrgId"));
			//获取detail并加密
			
			String result =shareDataService.AddOrgUserToGroup(requestJson);
			
			if(result==null) {
				responseObject.put("errno", 43);
				responseObject.put("error", "区块链service层返回数据为空");
				responseObject.put("data", new JSONObject());
			}
			
			
			JSONObject jsonObject=JSONObject.fromObject(result);
			
			logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
			
			if(jsonObject.get("error") instanceof JSONNull) {
				responseObject.put("errno", 0);
				responseObject.put("error", "success");
				responseObject.put("data", new JSONObject());
			}else {
				
				JSONArray errorArray=jsonObject.getJSONArray("error");
				//判断errorArray.size是否为0  2019-1-2 refine
				if(errorArray.size()==0) {
					throw new MyException(71,"error数组长度为0");
				}
				
				JSONObject errorMap=errorArray.getJSONObject(0);

				responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
				responseObject.put("data", new JSONObject());
				
			}
			
			logger.info("返回给前端的json数据:"+responseObject.toString());	

			return responseObject;
		}
		
		@ResponseBody
		@RequestMapping(value = "/QueryShareDataTemplateByOrgId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		private JSONObject QueryShareDataTemplateByOrgId(@RequestBody JSONObject orgJson) {
			
			if(orgJson==null) {
				throw new MyException(80,"前端传值为null");
			}
			if(!jsonUtil.isJson(orgJson.toString())) {
				throw new MyException(81,"前端传值不是json格式");
			}
			logger.info("接收前端或外围系统的json数据:"+orgJson.toString());
			
			JSONObject responseObject=new JSONObject();

			
			JSONObject requestJson=new JSONObject();
			//组装json
			requestJson.put("OrgId", orgJson.optString("OrgId"));
			
			
			//result:区块链接口返回的json字符串
			String resultJson=null;
//			try {
//				resultJson = new RestUtil().httpPost(path+"/Arrangement/QueryArrangement", requestJson);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			resultJson=shareDataService.QueryShareDataTemplateByOrgId(requestJson);
			
			JSONObject jsonObject=JSONObject.fromObject(resultJson);
			
			logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
			
			//解密
//	      2.判断返回状态码
			//获取error信息
			
			if(jsonObject.get("error") instanceof JSONNull) {
				responseObject.put("errno", 0);
				responseObject.put("error", "success");
			}else {
				
				JSONArray errorArray=jsonObject.getJSONArray("error");
				//判断errorArray.size是否为0  2019-1-2 refine
				if(errorArray.size()==0) {
					throw new MyException(71,"error数组长度为0");
				}
				
				JSONObject errorMap=errorArray.getJSONObject(0);

				responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
				return responseObject;
			}
			//获取content信息(contentMap)
			if(jsonObject.get("data") instanceof JSONNull) {
				responseObject.put("errno", 12);
				responseObject.put("error", "查询结果为空");
				responseObject.put("data", new JSONObject());
				
			}else {
				
				JSONArray shareDataList=new JSONArray();
				JSONArray contentArray=jsonObject.getJSONArray("data");
				
				if(contentArray.size()==0) {
	            	logger.info("data数组长度为0");
	            	throw new MyException(72,"data数组长度为0");
	            }
				for(int i=0;i<contentArray.size();i++) {
					JSONObject shareDataMap=contentArray.getJSONObject(i);
					
					//判断dataMap.getString是否为空 1-2 refine 为""或 null
					if(!shareDataMap.has("content")) {
						logger.info("data中缺失content信息");
		            	throw new MyException(73,"data中缺失content信息");
					}
					String contentStr=shareDataMap.getString("content");
					//将json中的null转换为""
					contentStr=jsonUtil.fromObject(contentStr);
					if(!jsonUtil.isJson(contentStr)) {
						logger.info("格式不是json");
						throw new MyException(74,"格式不是json");
					}
					
					JSONObject shareDataObject=JSONObject.fromObject(contentStr);
					shareDataList.add(shareDataObject);
				}
                JSONObject packlistObject=new JSONObject();
                packlistObject.put("ShareDataTemplateList", shareDataList);
				responseObject.put("data", packlistObject);

			}

			logger.info("返回给前端的json数据:"+responseObject.toString());
			return responseObject;
		}
		
		@ResponseBody
		@RequestMapping(value = "/QueryShareDataByOrgId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		private JSONObject QueryShareDataByOrgId(@RequestBody JSONObject orgJson) {
			
			if(orgJson==null) {
				throw new MyException(80,"前端传值为null");
			}
			if(!jsonUtil.isJson(orgJson.toString())) {
				throw new MyException(81,"前端传值不是json格式");
			}
			logger.info("接收前端或外围系统的json数据:"+orgJson.toString());
			
			JSONObject responseObject=new JSONObject();

			
			JSONObject requestJson=new JSONObject();
			//组装json
			requestJson.put("OrgId", orgJson.optString("OrgId"));
			
			
			//result:区块链接口返回的json字符串
			String resultJson=null;
//			try {
//				resultJson = new RestUtil().httpPost(path+"/Arrangement/QueryArrangement", requestJson);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			resultJson=shareDataService.QueryShareDataByOrgId(requestJson);
			
			JSONObject jsonObject=JSONObject.fromObject(resultJson);
			
			logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
			
			//解密
//	      2.判断返回状态码
			//获取error信息
			
			if(jsonObject.get("error") instanceof JSONNull) {
				responseObject.put("errno", 0);
				responseObject.put("error", "success");
			}else {
				
				JSONArray errorArray=jsonObject.getJSONArray("error");
				//判断errorArray.size是否为0  2019-1-2 refine
				if(errorArray.size()==0) {
					throw new MyException(71,"error数组长度为0");
				}
				
				JSONObject errorMap=errorArray.getJSONObject(0);

				responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
				return responseObject;
			}
			//获取content信息(contentMap)
			if(jsonObject.get("data") instanceof JSONNull) {
				responseObject.put("errno", 12);
				responseObject.put("error", "查询结果为空");
				responseObject.put("data", new JSONObject());
				
			}else {
				
				JSONArray shareDataList=new JSONArray();
				JSONArray contentArray=jsonObject.getJSONArray("data");
				
				if(contentArray.size()==0) {
	            	logger.info("data数组长度为0");
	            	throw new MyException(72,"data数组长度为0");
	            }
				for(int i=0;i<contentArray.size();i++) {
					JSONObject shareDataMap=contentArray.getJSONObject(i);
					
					//判断dataMap.getString是否为空 1-2 refine 为""或 null
					if(!shareDataMap.has("content")) {
						logger.info("data中缺失content信息");
		            	throw new MyException(73,"data中缺失content信息");
					}
					String contentStr=shareDataMap.getString("content");
					//将json中的null转换为""
					contentStr=jsonUtil.fromObject(contentStr);
					if(!jsonUtil.isJson(contentStr)) {
						logger.info("格式不是json");
						throw new MyException(74,"格式不是json");
					}
					
					JSONObject shareDataObject=JSONObject.fromObject(contentStr);
					shareDataList.add(shareDataObject);
				}
                JSONObject packlistObject=new JSONObject();
                packlistObject.put("ShareDataList", shareDataList);
				responseObject.put("data", packlistObject);

			}

			logger.info("返回给前端的json数据:"+responseObject.toString());
			return responseObject;
		}
		
		@ResponseBody
		@RequestMapping(value = "/QueryGroupByOrgId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		private JSONObject QueryGroupByOrgId(@RequestBody JSONObject orgJson) {
			
			if(orgJson==null) {
				throw new MyException(80,"前端传值为null");
			}
			if(!jsonUtil.isJson(orgJson.toString())) {
				throw new MyException(81,"前端传值不是json格式");
			}
			logger.info("接收前端或外围系统的json数据:"+orgJson.toString());
			
			JSONObject responseObject=new JSONObject();

			
			JSONObject requestJson=new JSONObject();
			//组装json
			requestJson.put("OrgId", orgJson.optString("OrgId"));
			
			
			//result:区块链接口返回的json字符串
			String resultJson=null;
//			try {
//				resultJson = new RestUtil().httpPost(path+"/Arrangement/QueryArrangement", requestJson);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			resultJson=shareDataService.QueryGroupByOrgId(requestJson);
			
			JSONObject jsonObject=JSONObject.fromObject(resultJson);
			
			logger.info("区块链层反返回数据反序列化结果:"+jsonObject.toString());
			
			//解密
//	      2.判断返回状态码
			//获取error信息
			
			if(jsonObject.get("error") instanceof JSONNull) {
				responseObject.put("errno", 0);
				responseObject.put("error", "success");
			}else {
				
				JSONArray errorArray=jsonObject.getJSONArray("error");
				//判断errorArray.size是否为0  2019-1-2 refine
				if(errorArray.size()==0) {
					throw new MyException(71,"error数组长度为0");
				}
				
				JSONObject errorMap=errorArray.getJSONObject(0);

				responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
				return responseObject;
			}
			//获取content信息(contentMap)
			if(jsonObject.get("data") instanceof JSONNull) {
				responseObject.put("errno", 12);
				responseObject.put("error", "查询结果为空");
				responseObject.put("data", new JSONObject());
				
			}else {
				
				JSONArray shareDataList=new JSONArray();
				JSONArray contentArray=jsonObject.getJSONArray("data");
				
				if(contentArray.size()==0) {
	            	logger.info("data数组长度为0");
	            	throw new MyException(72,"data数组长度为0");
	            }
				for(int i=0;i<contentArray.size();i++) {
					JSONObject shareDataMap=contentArray.getJSONObject(i);
					
					//判断dataMap.getString是否为空 1-2 refine 为""或 null
					if(!shareDataMap.has("content")) {
						logger.info("data中缺失content信息");
		            	throw new MyException(73,"data中缺失content信息");
					}
					String contentStr=shareDataMap.getString("content");
					//将json中的null转换为""
					contentStr=jsonUtil.fromObject(contentStr);
					if(!jsonUtil.isJson(contentStr)) {
						logger.info("格式不是json");
						throw new MyException(74,"格式不是json");
					}
					
					JSONObject shareDataObject=JSONObject.fromObject(contentStr);
					shareDataList.add(shareDataObject);
				}
                JSONObject packlistObject=new JSONObject();
                packlistObject.put("GroupList", shareDataList);
				responseObject.put("data", packlistObject);

			}

			logger.info("返回给前端的json数据:"+responseObject.toString());
			return responseObject;
		}

}
