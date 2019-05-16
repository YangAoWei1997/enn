package com.enn.commodity.synergistic.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.enn.commodity.synergistic.entity.Plan;
import com.enn.commodity.synergistic.entity.Queuing;
import com.enn.commodity.synergistic.service.PlanService;
import com.enn.commodity.synergistic.util.ErrorHandler;
import com.enn.commodity.synergistic.util.RestUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/Plan")
@CrossOrigin
public class PlanController {
	
	@Autowired
	PlanService planService;
	
	@ResponseBody
	@RequestMapping(value = "/CreatePlan", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject CreatePlan(@RequestBody JSONObject planJson) {
		
		//定义访问区块链接口所需要的json
				JSONObject requestJson=new JSONObject();
				JSONObject reqplanJson=new JSONObject();
				//组装json
				String contentStr="time:"+planJson.getString("time")+";"+"enorder:"+planJson.getString("enorder")+";"+"ordered:"+planJson.getString("ordered");
				reqplanJson.put("content", contentStr);
				reqplanJson.put("date", planJson.getString("date"));
				
				requestJson.put("Plan", reqplanJson.toString());
				requestJson.put("UserOrgId", planJson.getString("orgid"));

				
				
				
				//result:区块链接口返回的json字符串
				String resultJson=null;
				try {
					resultJson = new RestUtil().httpPost("http://192.168.206.133:8080/commodity/service/IP/CreatePlan", requestJson);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//2.定义返回前台的json，用于组装
//			        { { [ { } ] } }
				//responseObject:方法最终返回的返回
				JSONObject responseObject=new JSONObject();
				//从区块链获取的jsonObject
				System.out.println(resultJson);
				JSONObject jsonObject=JSONObject.fromObject(resultJson);
				
				
				
//		      2.判断返回状态码
				//获取error信息
				
				if(jsonObject.get("error") instanceof JSONNull) {
					responseObject.put("errno", 0);
					responseObject.put("error", "success");
					responseObject.put("data", new JSONObject());
				}else {
					
					JSONArray errorArray=jsonObject.getJSONArray("error");
					JSONObject errorMap=errorArray.getJSONObject(0);

					responseObject=new ErrorHandler().errornoHandler(errorMap.getString("code"),responseObject);
					responseObject.put("data", new JSONObject());
					
				}
				

				return responseObject;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/QueryPlan", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryPlan(@RequestBody JSONObject planJson) {
		
//		JSONObject responseObject=new JSONObject();
		
		String orgid=planJson.getString("orgid");
		if(orgid.equals("")) {
			orgid=planJson.getString("organizationId");
		}
		
		Plan plan=new Plan();
		plan.setOrgid(orgid);
		plan.setOrderdate(planJson.getString("orderdate"));
		JSONObject planObject=planService.QueryPlan(plan);
		
		return planObject;
		
	}

}
