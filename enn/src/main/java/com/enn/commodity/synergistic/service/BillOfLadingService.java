package com.enn.commodity.synergistic.service;



import java.util.List;
import java.util.Map;

import com.enn.commodity.synergistic.entity.BillOfLading;


import net.sf.json.JSONObject;

public interface BillOfLadingService {
	
	
	public String CreateBillOfLading(JSONObject requestJsonOfBill,JSONObject requestJsonOfPack);
	
	public String QueryBillOfLading(JSONObject requestJson);

	public String SelectBillOfLadingByPlateNoAndIssDate(JSONObject requestJson);
	
	public String SelectBillOfLadingBySalesCompanyAndBillId(JSONObject requestJson);
	
	public String SelectBillOfLadingByPlateNoAndIssDateOfBuyerId(JSONObject requestJson);
	
	public String SelectBillOfLadingBySalesPurchaseAndId(JSONObject requestJson);
	
	public String UpdateBillOfLading(JSONObject requestJson);
	
	public String SelectCar(JSONObject requestJson);
	
	List<BillOfLading>SelectBillOfLadingByObject(BillOfLading billOfLading);
	
	JSONObject CreateBillOfLading(BillOfLading billOfLading);
	
	List<BillOfLading>SelectBillOfLadingByObjectIn(Map<String,String> map);
	
}
