package com.guomz.MyMOOC.dao;

import org.apache.ibatis.annotations.Param;

import com.guomz.MyMOOC.entity.CourseHistory;

public interface CourseHistoryDao {

	//根据用户与课程id查询该用户在本课程的学习记录
	public CourseHistory queryHistoryByUserIdAndCourseId(@Param("userId")Long userId,@Param("courseId")Long courseId);
	
	//插入用户的学习记录
	public void insertHistory(CourseHistory courseHistory);
	
	//更新用户学习记录
	public void updateHistory(CourseHistory courseHistory);
}
