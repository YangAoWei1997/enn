package com.enn.commodity.synergistic.service;

import net.sf.json.JSONObject;

public interface OrganizationService {
	
	public String CreateIPInformation(JSONObject requestJson);
	
	public String GetAllIPInformation(JSONObject requestJson);

}
