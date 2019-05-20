package com.guomz.MyMOOC.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guomz.MyMOOC.dao.EventDao;
import com.guomz.MyMOOC.entity.Event;
/**
 * MyEvent小程序
 * @author 12587
 *
 */
@Service
public class EventService {

	@Autowired
	private EventDao eventDao;
	
	
	
	/**
	 * 预约会议室
	 * @param event
	 */
	@Transactional
	public boolean orderEvent(Event event)
	{
		if(getEventList(event)!=null&&getEventList(event).size()==0)
		{
			synchronized (eventDao) {
				if(getEventList(event)!=null&&getEventList(event).size()==0)
				{
					eventDao.insertEvent(event);
					return true;
				}
				else
				{
					return false;
				}
			}
		}
		return false;
		
	}
	
	/**
	 * 变更预约
	 * @param event
	 */
	@Transactional
	public void updateEvent(Event event)
	{
		eventDao.updateEvent(event);
	}
	
	/**
	 * 删除预约
	 * @param event
	 */
	@Transactional
	public void deleteEvent(Long eventId)
	{
		eventDao.deleteEvent(eventId);
	}
	
	/**
	 * 按条件查询会议室
	 * @param event
	 * @return
	 */
	public List<Event> getEventList(Event event)
	{
		return eventDao.queryEventByConditions(event);
	}
	
}
