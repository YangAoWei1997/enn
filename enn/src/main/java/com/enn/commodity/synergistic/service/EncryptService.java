package com.enn.commodity.synergistic.service;

import net.sf.json.JSONObject;

public interface EncryptService {
	
	String Encrypt(JSONObject requestJson);
	
	String Decrypt(JSONObject requestJson);

}
