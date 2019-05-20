package com.guomz.MyMOOC.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.guomz.MyMOOC.entity.Course;

public interface CourseDao {

	public Course queryCourseById(Long courseId);
	
	public List<Course> queryCourseByCondition(@Param(value="course")Course course,@Param(value="pageIndex")Integer pageIndex,@Param(value="pageSize")Integer pageSize);
	
	/**
	 * 查询用户看过的课程
	 * @param userId
	 * @return
	 */
	public List<Course> queryCourseByUserId(Long userId);
	
	//增加课程优先级
	public void addPriority(Long courseId);
	
	//查询用户收藏的课程
	public List<Course> queryCollectionByUserId(Long userId);
}
