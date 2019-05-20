package com.guomz.MyMOOC.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guomz.MyMOOC.dao.PlanDao;
import com.guomz.MyMOOC.entity.Plan;
/**
 * 用于MyPlan
 * @author 12587
 *
 */
@Service
public class PlanService {

	@Autowired
	private PlanDao planDao;
	
	public List<Plan> getPlanListByCondition(Plan condition)
	{
		return planDao.queryPlanByCondition(condition);
	}
	
	public Plan getPlanById(Long planId)
	{
		return planDao.queryPlanById(planId);
	}
	
	@Transactional
	public void insertPlanList(List<Plan> planList)
	{
		planDao.insertPlanList(planList);
	}
	
	@Transactional
	public void updatePlan(Plan plan)
	{
		planDao.updatePlan(plan);
	}
	
	@Transactional
	public void deletePlan(Long planId)
	{
		planDao.deletePlan(planId);
	}
}
