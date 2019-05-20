package com.guomz.MyMOOC.dao;

import java.util.List;

import com.guomz.MyMOOC.entity.Event;

public interface EventDao {

	public void insertEvent(Event event);
	
	public void updateEvent(Event event);
	
	public void deleteEvent(Long eventId);
	
	//按照条件查询
	public List<Event> queryEventByConditions(Event event);
}
