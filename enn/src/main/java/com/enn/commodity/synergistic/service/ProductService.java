package com.enn.commodity.synergistic.service;

import net.sf.json.JSONObject;

public interface ProductService {
	
	public String CreateProductInformation(JSONObject requestJson);
	
	public String QueryProductDetail(JSONObject requestJson);
	
	public String QueryProduct(JSONObject requestJson);
	
	public String UpdateProduct(JSONObject requestJson);

}
