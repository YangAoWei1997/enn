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

import com.enn.commodity.synergistic.entity.BusinessArea;
import com.enn.commodity.synergistic.entity.MyException;
import com.enn.commodity.synergistic.service.BusinessAreaService;
import com.enn.commodity.synergistic.util.JsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/BusinessArea")
@CrossOrigin
public class BusinessAreaController {
	
    public static Logger logger = Logger.getLogger(BusinessAreaController.class);
	
	@Autowired
	private BusinessAreaService businessAreaService;
			
	@Autowired
	private JsonUtil jsonUtil;
	
	@ResponseBody
	@RequestMapping(value = "/CreateBusinessArea", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject CreateBusinessArea(@RequestBody JSONObject businessAreaJson) {
		
		if(businessAreaJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(businessAreaJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+businessAreaJson.toString());
		
		JSONObject responseObject=new JSONObject();
		
		BusinessArea businessArea=(BusinessArea)JSONObject.toBean(businessAreaJson, BusinessArea.class);
		
		
		int result =businessAreaService.CreateBusinessArea(businessArea);
		if(result==0) {
			responseObject.put("errno", 101);
			responseObject.put("error", "创建商圈失败");
			responseObject.put("data", new JSONObject());
		}else {
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", new JSONObject());
		}
		
		return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/QueryBusinessArea", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private String QueryBusinessArea(@RequestBody JSONObject businessAreaJson) {
		
		if(businessAreaJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(businessAreaJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+businessAreaJson.toString());
		
		JSONObject responseObject=new JSONObject();
		
		BusinessArea businessAreaForQuery=(BusinessArea)JSONObject.toBean(businessAreaJson, BusinessArea.class);
		
		
		List <BusinessArea> businessAreaList = businessAreaService.SelectBusinessAreaByObject(businessAreaForQuery);
		
		JSONObject dataObject=new JSONObject();
		JSONArray dataArray=new JSONArray();
		if(businessAreaList==null||businessAreaList.size()==0) {
			responseObject.put("errno", 25);
			responseObject.put("error", "自定义查询结果为空");
			responseObject.put("data", new JSONObject());
		}else {
			
			for (BusinessArea businessArea : businessAreaList) {
				
				dataArray.add(JSONObject.fromObject(businessArea));
				
			}
			dataObject.put("businessAreaList", dataArray);
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", dataObject);
		}
		logger.info("返回给前端的数据:"+responseObject.toString());
		return responseObject.toString();
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/UpdateBusinessArea", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject UpdateBusinessArea(@RequestBody JSONObject businessAreaJson) {
		
		if(businessAreaJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(businessAreaJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		if(businessAreaJson.optString("ID")==null&&("").equals(businessAreaJson.optString("ID"))) {
			throw new MyException(102,"ID为必传项");
		}
		logger.info("接收前端或外围系统的json数据:"+businessAreaJson.toString());
		
		JSONObject responseObject=new JSONObject();
		
		BusinessArea businessArea=(BusinessArea)JSONObject.toBean(businessAreaJson, BusinessArea.class);
		
		
		int result =businessAreaService.UpdateBusinessArea(businessArea);
		if(result==0) {
			responseObject.put("errno", 103);
			responseObject.put("error", "修改商圈失败");
			responseObject.put("data", new JSONObject());
		}else {
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", new JSONObject());
		}
		
		return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/DeleteBusinessArea", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject DeleteBusinessArea(@RequestBody JSONObject businessAreaJson) {
		
		if(businessAreaJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(businessAreaJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		if(businessAreaJson.optString("ID")==null&&("").equals(businessAreaJson.optString("ID"))) {
			throw new MyException(102,"ID为必传项");
		}
		logger.info("接收前端或外围系统的json数据:"+businessAreaJson.toString());
		
		JSONObject responseObject=new JSONObject();
		
		BusinessArea businessArea=(BusinessArea)JSONObject.toBean(businessAreaJson, BusinessArea.class);
		
		
		int result =businessAreaService.DeleteBusinessArea(businessArea);
		if(result==0) {
			responseObject.put("errno", 104);
			responseObject.put("error", "删除商圈失败");
			responseObject.put("data", new JSONObject());
		}else {
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", new JSONObject());
		}
		
		return responseObject;
	}
	
	
	

}
