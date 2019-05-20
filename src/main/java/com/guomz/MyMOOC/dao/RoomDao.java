package com.guomz.MyMOOC.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.guomz.MyMOOC.entity.Room;

public interface RoomDao {

	public List<Room> queryRoom(@Param(value="room")Room room,@Param(value="pageIndex")Integer pageIndex,@Param(value="pageSize")Integer pageSize);
	
	public List<Room> queryRoomByDate(String eventDate);
}
