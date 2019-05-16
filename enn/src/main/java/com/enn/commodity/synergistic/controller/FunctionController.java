package com.enn.commodity.synergistic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.enn.commodity.synergistic.entity.Function;
import com.enn.commodity.synergistic.entity.MineralResources;
import com.enn.commodity.synergistic.entity.Role;
import com.enn.commodity.synergistic.entity.User;
import com.enn.commodity.synergistic.service.FunctionService;
import com.enn.commodity.synergistic.service.RoleService;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("/Function")
@CrossOrigin
public class FunctionController {
	
	@Autowired
	FunctionService functionService;
	
	@Autowired
	RoleService roleService;
	
	@ResponseBody
	@RequestMapping(value = "/CreateFunction", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject CreateFunction(@RequestBody JSONObject functionJson) {
		
		JSONObject responseObject=new JSONObject();
//		Function function=new Function();
//		function.setFunid(functionJson.getString("funid"));
//		function.setFunname(functionJson.getString("funname"));
//		function.setFunurl(functionJson.getString("funurl"));
//		function.setPoweridfy(functionJson.getString("poweridfy"));
//		function.setFundetail(functionJson.getString("fundetail"));
		responseObject =functionService.insertFunction(functionJson);
		
		
		return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/UpdateFunction", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject UpdateFunction(@RequestBody JSONObject functionJson) {
		
		JSONObject responseObject=new JSONObject();
//		Function function=new Function();
//		function.setFunid(functionJson.getString("funid"));
//		function.setFunname(functionJson.getString("funname"));
//		function.setFunurl(functionJson.getString("funurl"));
//		function.setPoweridfy(functionJson.getString("poweridfy"));
//		function.setFundetail(functionJson.getString("fundetail"));
		responseObject =functionService.updateFunction(functionJson);
		
		
		return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/QueryFunctionDetail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryFunction(@RequestBody JSONObject functionJson) {
		
		JSONObject responseObject=new JSONObject();
//		Function function=new Function();
//		function.setFunname(functionJson.getString("funname"));
		
		responseObject =functionService.SelectFunctionsByFunname(functionJson);
		
		
		return responseObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/QueryRoleFunction", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	private JSONObject QueryRoleFunction(@RequestBody JSONObject roleJson) {
		
		JSONObject responseObject=new JSONObject();
		
		JSONObject rolePermissionList=null; 
		JSONObject userPermissionList=null; 
		JSONObject roleInfo=null;
		JSONObject dataObject=new JSONObject();
//		Role role=new Role();
//		User user=new User();
//		
//		role.setRoleId(roleJson.optString("roleId"));
//		
//		user.setUserId(roleJson.optString("userId"));
		rolePermissionList =functionService.SelectFunctionsByRoleId(roleJson);
		userPermissionList=functionService.SelectFunctionsByUserId(roleJson);
		
		
		dataObject.put("userpermissionlist", userPermissionList.optJSONArray("userfunctionlist"));
		dataObject.put("rolepermissionlist", rolePermissionList.optJSONArray("rolefunctionlist"));
		responseObject.put("errno", 0);
		responseObject.put("error", "success");
		responseObject.put("data", dataObject);
		return responseObject;
	}
	

}
