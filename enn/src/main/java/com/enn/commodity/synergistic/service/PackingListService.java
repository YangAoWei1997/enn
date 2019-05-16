package com.enn.commodity.synergistic.service;

import java.util.List;
import java.util.Map;

import com.enn.commodity.synergistic.entity.PackingList;
import com.enn.commodity.synergistic.entity.QualityInfo;

import net.sf.json.JSONObject;

public interface PackingListService {
	
	public String CreatePackingList(JSONObject requestJson);
	
	public String QueryPackingList(JSONObject requestJson);
	
	public String CreateArrivalList(JSONObject requestJson);
	
	public String QueryArrivalList(JSONObject requestJson);
	
	public String CompareInOutFactory(JSONObject requestJson);
	
	public String CreateQualityInfo(JSONObject requestJson);
	
	public String CompareQualityInfo(JSONObject requestJson);
	
	List<PackingList>SelectPackingListByObject(PackingList packingList);
	
	List<QualityInfo>SelectQualityInfoByObject(QualityInfo qualityInfo);
	
    JSONObject CreatePackingList(PackingList packingList);
	
	JSONObject CreateQualityInfo(QualityInfo qualityInfo);
	
	List<PackingList> SelectPackingListByObjectIn(Map<String,String> map);
	

}
