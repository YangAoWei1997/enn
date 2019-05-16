package com.enn.commodity.synergistic.service;

import java.util.List;

import com.enn.commodity.synergistic.entity.ShareData;

import net.sf.json.JSONObject;

public interface ShareDataService {
	
    String SelectShareDataByObject(JSONObject requestJson);
	
	String CreateShareData(JSONObject requestJson);
	
	String CreateShareDataTemplate(JSONObject requestJson);
	
	String DeleteShareDataTemplate(JSONObject requestJson);
	
	String UpdateShareDataTemplate(JSONObject requestJson);
	
	String DeleteShareData(JSONObject requestJson);
	
	String UpdateShareData(JSONObject requestJson);
	
	String CreateGroup(JSONObject requestJson);
	
	String DeleteGroup(JSONObject requestJson);
	
	String UpdateGroup(JSONObject requestJson);
	
	String AddOrgUserToGroup(JSONObject requestJson);
	
	String QueryShareDataTemplate(JSONObject requestJson);
	
	String QueryShareDataById(JSONObject requestJson);
	
	String QueryGroup(JSONObject requestJson);
	
	String QueryGroupByOrgId(JSONObject requestJson);
	
	String QueryShareDataTemplateByOrgId(JSONObject requestJson);
	
	String QueryShareDataByOrgId(JSONObject requestJson);

}
