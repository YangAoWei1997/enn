package com.enn.commodity.synergistic.service;

import com.enn.commodity.synergistic.entity.Function;
import com.enn.commodity.synergistic.entity.Role;
import com.enn.commodity.synergistic.entity.User;

import net.sf.json.JSONObject;

public interface FunctionService {
	
	JSONObject SelectFunctionsByFunname(JSONObject functionJson);
	
	JSONObject SelectFunctionsByUserId(JSONObject userJson);
	
	JSONObject SelectFunctionsByRoleId(JSONObject roleJson);
	
	JSONObject insertFunction(JSONObject functionJson);
	
	JSONObject updateFunction(JSONObject functionJson);
	
	

}
