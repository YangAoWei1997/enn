package com.enn.commodity.synergistic.service;

import java.util.List;

import com.enn.commodity.synergistic.entity.User;

import net.sf.json.JSONObject;


public interface UserService {
	
	List<User> queryUser();
	
	JSONObject login(JSONObject userObject);
	
	JSONObject CreateUser(JSONObject userObject);
	
	JSONObject QueryUserDetail(JSONObject userObject);
	
	JSONObject QueryDriverInfoByPlateNo(JSONObject userObject);
	
	JSONObject UpdatePassword(JSONObject userObject);
	
	JSONObject UpdateUser(JSONObject userObject); 
	
	JSONObject BatchUpdateUser(JSONObject userObject); 
	
	JSONObject BatchDeleteUser(JSONObject userObject); 

}
