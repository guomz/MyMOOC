package com.guomz.MyMOOC.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guomz.MyMOOC.entity.Event;
import com.guomz.MyMOOC.entity.Plan;
import com.guomz.MyMOOC.entity.Room;
import com.guomz.MyMOOC.service.EventService;
import com.guomz.MyMOOC.service.PlanService;
import com.guomz.MyMOOC.service.RoomService;

@RestController
@RequestMapping("/calendar")
public class CalendarController {

	@Autowired
	private EventService eventService;
	@Autowired
	private PlanService planService;
	@Autowired
	private RoomService roomService;
	
	/**
	 * 获取一个会议室的预约事件
	 * @param roomId
	 * @return
	 */
	@RequestMapping(value="/geteventlist",method=RequestMethod.GET)
	public Map<String ,Object> getEventList(Long roomId)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		try {
			Event event=new Event();
			event.setRoomId(roomId);
			List<Event> eventList = eventService.getEventList(event);
			modelMap.put("success", true);
			modelMap.put("eventList", eventList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	
	/**
	 * 预约
	 * @param event
	 * @return
	 */
	@RequestMapping(value="/orderevent",method=RequestMethod.GET)
	public Map<String,Object> orderEvent(Long roomId,String eventName,String eventDetail,String groupName,String eventDate,Long userId)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		Event event =new Event();
		event.setEventDate(eventDate);
		event.setEventDetail(eventDetail);
		event.setEventName(eventName);
		event.setGroupName(groupName);
		event.setRoomId(roomId);
		event.setUserId(userId);
		//System.out.println(event);
		try {
			boolean result=eventService.orderEvent(event);
			if(result)
			{
				modelMap.put("success", true);
			}
			else
			{
				modelMap.put("success", false);
				modelMap.put("errMsg", "该时间已被预订");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	
	/**
	 * 修改预约
	 * @param event
	 * @return
	 */
	@RequestMapping(value="/changeevent",method=RequestMethod.GET)
	public Map<String,Object> changeEvent(Event event)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		try {
			eventService.updateEvent(event);
			modelMap.put("success", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	
	/**
	 * 删除预约
	 * @return
	 */
	@RequestMapping(value="/cancleevent",method=RequestMethod.GET)
	public Map<String ,Object> cancleEvent(Long eventId)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		try {
			eventService.deleteEvent(eventId);
			modelMap.put("success", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	
	/**
	 * 获得自己的预约
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/getmyeventlist",method=RequestMethod.GET)
	public Map<String,Object> getMyEventList(Long userId)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		try {
			Event event=new Event();
			event.setUserId(userId);
			List<Event> eventList = eventService.getEventList(event);
			modelMap.put("success", true);
			modelMap.put("eventList", eventList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
		}
		return modelMap;
	}
	
	/**
	 * 根据日期获取可用的会议室
	 * @param eventDate
	 * @return
	 */
	@RequestMapping(value="/getroombydate",method=RequestMethod.GET)
	public Map<String,Object> getRoomByDate(@RequestParam(value="eventDate",required=false)String eventDate)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		try {
			List<Room> roomList = roomService.getRoomByDate(eventDate);
			modelMap.put("success", true);
			modelMap.put("roomList", roomList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
		}
		
		return modelMap;
	}
	
	/**
	 * 获取计划列表，查询条件为用户id、日期
	 * @return
	 */
	@RequestMapping(value="/getplanlist",method=RequestMethod.GET)
	public Map<String,Object> getPlanList(@RequestParam(value="userId",required=false)Long userId,@RequestParam(value="planDate",required=false)String planDate)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		Plan plan=new Plan();
		if(userId!=null)
		{
			plan.setUserId(userId);
		}
		if(planDate!=null)
		{
			plan.setPlanDate(planDate);
		}
		
		try {
			List<Plan> planList = planService.getPlanListByCondition(plan);
			modelMap.put("planList", planList);
			modelMap.put("success", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
		}
		
		return modelMap;
	}
	
	/**
	 * 添加计划
	 * @param plan
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/addplan",method=RequestMethod.GET)
	public Map<String,Object> addPlan(@RequestParam(value="planListStr",required=false)String planListStr)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		ObjectMapper om=new ObjectMapper();
		try {
			List<Plan> planList=(List<Plan>) om.readValue(planListStr, new TypeReference<List<Plan>>(){});
			//System.out.println(planList);
			planService.insertPlanList(planList);
			modelMap.put("success", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
		}
		return modelMap;
	}
	
	/**
	 * 修改计划
	 * @param plan
	 * @return
	 */
	@RequestMapping(value="/updateplan",method=RequestMethod.GET)
	public Map<String,Object> updatePlan(Plan plan)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		//System.out.println(plan);
		try {
			planService.updatePlan(plan);
			modelMap.put("success", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
		}
		return modelMap;
	}
	
	/**
	 * 删除计划
	 * @param planId
	 * @return
	 */
	@RequestMapping(value="/deleteplan",method=RequestMethod.GET)
	public Map<String,Object> deletePlan(Long planId)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		try {
			planService.deletePlan(planId);
			modelMap.put("success", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
		}
		return modelMap;
	}
}
