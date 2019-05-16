package com.enn.commodity.synergistic.service;

import java.util.List;
import java.util.Map;

import com.enn.commodity.synergistic.entity.Arrangement;
import com.enn.commodity.synergistic.entity.MaterialDetail;

import net.sf.json.JSONObject;

public interface ArrangementService {
	
	public String CreateArrangement(JSONObject requestJson);
	
	public String QueryArrangement(JSONObject requestJson);
	
	List<Arrangement>SelectArrangementByObject(Arrangement arrangement);
	
	JSONObject CreateArrangement(Arrangement arrangement);
	
	List<Arrangement> SelectArrangementByObjectIn(Map<String,String> map);

}
