package com.enn.commodity.synergistic.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enn.commodity.synergistic.util.ErrorHandler;
import com.enn.commodity.synergistic.util.RestUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

public class BillTest {
	
	@ResponseBody
	@RequestMapping(value = "/QueryBillOfLading", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryBillOfLading(@RequestBody JSONObject billOfLadingJson) {
		
		
		
//      1.调区块链接口
		  //区块链返回的json字符串
//		String resultJson="{\"Eid\": \"//\",\r\n" + 
//				"  \"id\": \"mesg001\",\r\n" + 
//				"  \"channel\": \"WEB\",\r\n" + 
//				"\"tranCode\": \"CreateContainer\",\r\n" + 
//				"  \"type\": \"0\",\r\n" + 
//				"  \"dataType\": \"Container\",\r\n" + 
//				"  \"tranDate\": \"201706081123\",\r\n" + 
//				"\"error\": [\r\n" + 
//				"{\r\n" + 
//				"\"code\": \"\", \r\n" + 
//				"\"function\": \"\",\r\n" + 
//				"\"statement\": \"\"\r\n" + 
//				"}\r\n" + 
//				"],\r\n" + 
//				"  \"args\": [\r\n" + 
//				"{\"name\":\"driverOrderId\",\"value\":\"PL001\"}\r\n" + 
//				"],\r\n" + 
//				"  \"data\":[\r\n" + 
//				"{\"content\": {\r\n" + 
//				"	\"billno\":\"bb00001\",\r\n" + 
//				"  \"productname\":\"name1\",\r\n" + 
//				"  \"salesorderno\":\"ss00001\",\r\n" + 
//				"  \"sellername\":\"seller1\",\r\n" + 
//				"  \"buyername\":\"buyer1\",\r\n" + 
//				"  \"weightbyorder\":\"1000\",\r\n" + 
//				"  \"packinginfolist\":[{\r\n" + 
//				"    \"platenumber\":\"pl000001\",\r\n" + 
//				"    \"packingno\":\"pa000001\",\r\n" + 
//				"    \"transportname\":\"trans1\",\r\n" + 
//				"    \"entrytime\":\"2018-1-1\",\r\n" + 
//				"    \"leavefirsttime\":\"2018-2-1\",\r\n" + 
//				"    \"leavefirstweight\":\"900\",\r\n" + 
//				"    \"leavesecondtime\":\"2018-2-2\",\r\n" + 
//				"\"leavesecondweight\":\"1000\",\r\n" + 
//				"\"weight\":\"100\",\r\n" + 
//				"\"leavetime\":\"2018-2-2\"}]\r\n" + 
//				"}}\r\n" + 
//				"]}";
		
		//获取
		JSONObject requestJson=new JSONObject();
		requestJson.put("BillOfLadingId", billOfLadingJson.getString("billno"));
		String resultJson=null;
		try {
			resultJson = new RestUtil().httpPost("http://10.39.28.20:8080/commodity/service/BillOfLading/QueryBillOfLading", requestJson);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//2.定义返回前台的json，用于组装
//	        { { [ { } ] } }
		//总json
		JSONObject responseObject=new JSONObject();
		//从区块链获取的jsonObject
		JSONObject jsonObject=JSONObject.fromObject(resultJson);
		
		System.out.println(resultJson);
		
//      2.判断返回状态码
		//获取error信息
		
		if(jsonObject.get("error") instanceof JSONNull) {
			responseObject.put("errorno", 0);
			responseObject.put("error", "success");
		}else {
			
			JSONArray errorArray=jsonObject.getJSONArray("error");
			JSONObject errorMap=errorArray.getJSONObject(0);
//			if(errorMap.get("code").equals("")||errorMap.get("code")instanceof JSONNull) {
//				//返回结果正常，组装返回json字符串
//				responseObject.put("errorno", 0);
//				responseObject.put("error", "success");
//				
//			}else {
				//解析报错信息
			responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);

//			}	
			
		}
		
		
		//获取content信息(contentMap)
		
		
		if(jsonObject.get("data") instanceof JSONNull) {
			
			responseObject.put("data", new HashMap());
			
		}else {
			
			JSONArray contentArray=jsonObject.getJSONArray("data");
//			JSONObject contentMap=contentArray.getJSONObject(0);
//			JSONObject dataObject=contentMap.getJSONObject("content");
			//从区块链层获取的content对应的值是string类型，需要反序列化成jsonobject
			JSONObject dataMap=contentArray.getJSONObject(0);
			String contentStr=dataMap.getString("content");
			JSONObject dataObject=JSONObject.fromObject(contentStr);
			responseObject.put("data", dataObject);
		}

		return responseObject;
		
	}

}
