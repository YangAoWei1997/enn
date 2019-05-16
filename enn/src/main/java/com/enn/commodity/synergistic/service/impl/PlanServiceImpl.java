package com.enn.commodity.synergistic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enn.commodity.synergistic.dao.PlanDao;
import com.enn.commodity.synergistic.dao.QueuingDao;
import com.enn.commodity.synergistic.entity.Plan;
import com.enn.commodity.synergistic.service.PlanService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class PlanServiceImpl implements PlanService{
	
	@Autowired
	private PlanDao planDao;

	@Override
	public JSONObject QueryPlan(Plan plan) {
		
		JSONObject responseJSON=new JSONObject();
		
		List<Plan> planList=planDao.QueryPlanByOrgid(plan);
		
		if(planList==null||planList.size()<1) {
			responseJSON.put("errno", 25);
			responseJSON.put("error", "自定义查询结果为空");
			responseJSON.put("data", new JSONObject());
			return responseJSON;
		}
		
		JSONArray planinfo=new JSONArray();
		
		for(int i=0;i<planList.size();i++) {
			
			JSONObject planObject=new JSONObject();
			planObject.put("ordertime", planList.get(i).getOrdertime());
			planObject.put("enorder", planList.get(i).getEnorder());
			planObject.put("ordered", planList.get(i).getOrdered());
			planObject.put("orderdate", planList.get(i).getOrderdate());
			planObject.put("orgid", planList.get(i).getOrgid());
			
			planinfo.add(planObject);
			
		}
		JSONObject dataObject=new JSONObject();
		dataObject.put("planinfo", planinfo);
		
		responseJSON.put("errno", 0);
		responseJSON.put("error", "success");
		responseJSON.put("data", dataObject);
		return responseJSON;
		
		
		
	}
	
	

}
