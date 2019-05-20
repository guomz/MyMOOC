package com.guomz.MyMOOC.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guomz.MyMOOC.dao.RoomDao;
import com.guomz.MyMOOC.entity.Room;
/**
 * MyEvent小程序
 * @author 12587
 *
 */
@Service
public class RoomService {

	@Autowired
	private RoomDao roomDao;
	
	/**
	 * 按照条件分页查询会议室
	 * @param room
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<Room> getRoomList(Room room,Integer pageIndex,Integer pageSize)
	{
		return roomDao.queryRoom(room, pageIndex, pageSize);
	}
	
	/**
	 * 按照日期查询可用的会议室
	 * @param eventDate
	 * @return
	 */
	public List<Room> getRoomByDate(String eventDate)
	{
		return roomDao.queryRoomByDate(eventDate);
	}
}
