package com.guomz.MyMOOC.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guomz.MyMOOC.entity.Course;
import com.guomz.MyMOOC.entity.Room;
import com.guomz.MyMOOC.service.CourseService;
import com.guomz.MyMOOC.service.RoomService;

@RestController
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private CourseService courseService;
	@Autowired
	private RoomService roomService;
	
	/**
	 * 按照名称分页查询课程
	 * @param courseName
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/searchcourse",method=RequestMethod.GET)
	public Map<String ,Object> searchCourse(@RequestParam(value="courseName",required=false)String courseName,@RequestParam(value="pageIndex",required=false)Integer pageIndex,@RequestParam(value="pageSize",required=false)Integer pageSize)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		Course course=new Course();
		if(courseName!=null)
		{
			course.setCourseName(courseName);
		}
		try {
			List<Course> courseList = courseService.getCourseByCondition(course, pageIndex, pageSize);
			modelMap.put("success", true);
			modelMap.put("courseList", courseList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	
	/**
	 * 搜索会议室
	 * @param roomName
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/searchroom",method=RequestMethod.GET)
	public Map<String,Object> searchRoom(String roomName,Integer pageIndex,Integer pageSize)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		Room room=new Room();
		if(roomName!=null&&!roomName.equals(""))
		{
			room.setRoomName(roomName);
		}
		try {
			List<Room> roomList = roomService.getRoomList(room, pageIndex, pageSize);
			modelMap.put("success", true);
			modelMap.put("roomList", roomList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
}
