package com.guomz.MyMOOC.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.guomz.MyMOOC.entity.Course;
import com.guomz.MyMOOC.entity.Room;
import com.guomz.MyMOOC.service.CourseService;
import com.guomz.MyMOOC.service.RoomService;

@RestController
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private CourseService courseService;
	@Autowired
	private RoomService roomService;
	
	/**
	 * 首页信息，展示五条
	 * @return
	 */
	@RequestMapping(value="/getcourselist",method=RequestMethod.GET)
	public Map<String ,Object> getCourseList()
	{
		Map<String,Object> modelMap=new HashMap<String ,Object>();
		try {
			List<Course> courseList = courseService.getCourseByCondition(new Course(), 0, 5);
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
	 * 展示首页会议室
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/getroomlist" ,method=RequestMethod.GET)
	public Map<String,Object> getRoomList(Integer pageIndex,Integer pageSize)
	{
		Map<String,Object> modelMap=new HashMap<String ,Object>();
		try {
			List<Room> roomList = roomService.getRoomList(new Room(), pageIndex, pageSize);
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
