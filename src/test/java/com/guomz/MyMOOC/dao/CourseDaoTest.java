package com.guomz.MyMOOC.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.guomz.MyMOOC.BaseTest;
import com.guomz.MyMOOC.entity.Course;

public class CourseDaoTest extends BaseTest{

	@Autowired
	private CourseDao courseDao;
	
	@Test
	public void queryCourse()
	{
		Course course=new Course();
		course.setCourseName("SSM");
		System.out.println(courseDao.queryCourseByCondition(course,0,5));
	}
	
	@Test
	public void getMyCourse()
	{
		System.out.println(courseDao.queryCourseByUserId(1L));
	}
}
