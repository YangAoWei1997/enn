package com.enn.commodity.synergistic.dao;

import java.util.List;

import com.enn.commodity.synergistic.entity.Plan;

public interface PlanDao {
	
	List<Plan> QueryPlanByOrgid(Plan plan);

}
