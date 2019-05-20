package com.guomz.MyMOOC.dao;

import java.util.List;

import com.guomz.MyMOOC.entity.Plan;

public interface PlanDao {

	public List<Plan> queryPlanByCondition(Plan condition);
	
	public Plan queryPlanById(Long planId);
	
	public int insertPlanList(List<Plan> planList);
	
	public int updatePlan(Plan plan);
	
	public int deletePlan(Long planId);
}
