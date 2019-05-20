package com.guomz.MyMOOC.dao;

import org.apache.ibatis.annotations.Param;

public interface UserCollectionDao {

	//根据两个id查询用户是否收藏过课程，判断依据为查询结果的个数
	public int queryCollectionCountByUserIdAndCourseId(@Param("userId")Long userId,@Param("courseId")Long courseId);
	
	//添加收藏
	public void insertCollection(@Param("userId")Long userId,@Param("courseId")Long courseId);
	
	//取消收藏
	public void deleteCollection(@Param("userId")Long userId,@Param("courseId")Long courseId);
}
