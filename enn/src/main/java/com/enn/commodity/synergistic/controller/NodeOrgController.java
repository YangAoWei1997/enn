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
import com.enn.commodity.synergistic.entity.NodeOrg;
import com.enn.commodity.synergistic.service.NodeOrgService;
import com.enn.commodity.synergistic.util.JsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/NodeOrg")
@CrossOrigin
public class NodeOrgController {
	
    public static Logger logger = Logger.getLogger(NodeOrgController.class);
	
	@Autowired
	private NodeOrgService nodeOrgService;
			
	@Autowired
	private JsonUtil jsonUtil;
	
	@ResponseBody
	@RequestMapping(value = "/CreateNodeOrg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject CreateNodeOrg(@RequestBody JSONObject nodeOrgJson) {
		
		if(nodeOrgJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(nodeOrgJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+nodeOrgJson.toString());
		
		JSONObject responseObject=new JSONObject();
		
		NodeOrg nodeOrg=(NodeOrg)JSONObject.toBean(nodeOrgJson, NodeOrg.class);
		
		
		int result =nodeOrgService.CreateNodeOrg(nodeOrg);
		if(result==0) {
			responseObject.put("errno", 121);
			responseObject.put("error", "创建NodeOrg失败");
			responseObject.put("data", new JSONObject());
		}else {
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", new JSONObject());
		}
		
		return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/QueryNodeOrg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryNodeOrg(@RequestBody JSONObject nodeOrgJson) {
		
		if(nodeOrgJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(nodeOrgJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		logger.info("接收前端或外围系统的json数据:"+nodeOrgJson.toString());
		
		JSONObject responseObject=new JSONObject();
		
		NodeOrg nodeOrgForQuery=(NodeOrg)JSONObject.toBean(nodeOrgJson, NodeOrg.class);
		
		
		List <NodeOrg> nodeOrgList = nodeOrgService.SelectNodeOrgByObject(nodeOrgForQuery);
		
		JSONObject dataObject=new JSONObject();
		JSONArray dataArray=new JSONArray();
		if(nodeOrgList==null||nodeOrgList.size()==0) {
			responseObject.put("errno", 25);
			responseObject.put("error", "自定义查询结果为空");
			responseObject.put("data", new JSONObject());
		}else {
			
			for (NodeOrg nodeOrg : nodeOrgList) {
				
				dataArray.add(JSONObject.fromObject(nodeOrg));
				
			}
			dataObject.put("nodeOrgList", dataArray);
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", dataObject);
		}
		
		return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/UpdateNodeOrg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject UpdateNodeOrg(@RequestBody JSONObject nodeOrgJson) {
		
		if(nodeOrgJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(nodeOrgJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		if(nodeOrgJson.optString("ID")==null&&("").equals(nodeOrgJson.optString("ID"))) {
			throw new MyException(102,"ID为必传项");
		}
		logger.info("接收前端或外围系统的json数据:"+nodeOrgJson.toString());
		
		JSONObject responseObject=new JSONObject();
		
		NodeOrg nodeOrg=(NodeOrg)JSONObject.toBean(nodeOrgJson, NodeOrg.class);
		
		
		int result =nodeOrgService.UpdateNodeOrg(nodeOrg);
		if(result==0) {
			responseObject.put("errno", 122);
			responseObject.put("error", "修改NodeOrg失败");
			responseObject.put("data", new JSONObject());
		}else {
			responseObject.put("errno", 0);
			responseObject.put("error", "success");
			responseObject.put("data", new JSONObject());
		}
		
		return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/DeleteNodeOrg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject DeleteNodeOrg(@RequestBody JSONObject nodeOrgJson) {
		
		if(nodeOrgJson==null) {
			throw new MyException(80,"前端传值为null");
		}
		if(!jsonUtil.isJson(nodeOrgJson.toString())) {
			throw new MyException(81,"前端传值不是json格式");
		}
		if(nodeOrgJson.optString("ID")==null&&("").equals(nodeOrgJson.optString("ID"))) {
			throw new MyException(102,"ID为必传项");
		}
		logger.info("接收前端或外围系统的json数据:"+nodeOrgJson.toString());
		
		JSONObject responseObject=new JSONObject();
		
		NodeOrg nodeOrg=(NodeOrg)JSONObject.toBean(nodeOrgJson, NodeOrg.class);
		
		
		int result =nodeOrgService.DeleteNodeOrg(nodeOrg);
		if(result==0) {
			responseObject.put("errno", 123);
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
