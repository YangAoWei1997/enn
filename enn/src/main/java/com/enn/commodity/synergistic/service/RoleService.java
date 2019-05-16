package com.enn.commodity.synergistic.service;

import java.util.List;

import com.enn.commodity.synergistic.entity.Role;

import net.sf.json.JSONObject;

public interface RoleService {
	
	JSONObject QueryRoleDetail(JSONObject roleObject);
	
	JSONObject CreateRole(JSONObject roleObject);
	
	JSONObject UpdateRole(JSONObject roleObject);
	
	JSONObject RoleAuthorization(JSONObject authObject);
	
	List<Role> QueryRole(Role role,Integer page, Integer limit);

}
