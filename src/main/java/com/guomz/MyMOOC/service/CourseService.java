package com.guomz.MyMOOC.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guomz.MyMOOC.dao.CourseDao;
import com.guomz.MyMOOC.dao.CourseHistoryDao;
import com.guomz.MyMOOC.dao.UserCollectionDao;
import com.guomz.MyMOOC.entity.Course;
import com.guomz.MyMOOC.entity.CourseHistory;
/**
 * MyMOOC小程序
 * @author 12587
 *
 */
@Service
public class CourseService {

	@Autowired
	private CourseDao courseDao;
	@Autowired
	private CourseHistoryDao courseHistoryDao;
	@Autowired
	private UserCollectionDao userCollectionDao;
	
	/**
	 * 精确查询课程信息
	 * @param courseId
	 * @return
	 */
	public Course getCourseById(Long courseId)
	{
		return courseDao.queryCourseById(courseId);
	}
	
	/**
	 * 条件组合查询课程
	 * @param course
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<Course> getCourseByCondition(Course course,Integer pageIndex,Integer pageSize)
	{
		return courseDao.queryCourseByCondition(course,pageIndex,pageSize);
	}
	
	/**
	 * 记录用户学习过的课程，并返回该课程的学习记录
	 * @param userId
	 * @param courseId
	 */
	@Transactional
	public CourseHistory handleHistory(Long userId,Long courseId)
	{
		CourseHistory courseHistory = courseHistoryDao.queryHistoryByUserIdAndCourseId(userId, courseId);
		if(courseHistory==null)
		{
			courseHistory=new CourseHistory();
			courseHistory.setUserId(userId);
			courseHistory.setCourseId(courseId);
			courseHistory.setChapter("1-1");
			courseHistory.setCurrentPos(0.0);
			courseHistoryDao.insertHistory(courseHistory);
			return courseHistory;
		}
		else
		{
			return courseHistory;
		}
	}
	
	/**
	 * 查询用户学习过的课程
	 * @param userId
	 * @return
	 */
	public List<Course> getCourseByUserId(Long userId)
	{
		return courseDao.queryCourseByUserId(userId);
	}
	
	/**
	 * 更新观看视频历史
	 * @param movieHistory
	 */
	@Transactional
	public void updateHistory(CourseHistory courseHistory)
	{
		courseHistoryDao.updateHistory(courseHistory);
	}
	
	/**
	 * 处理点击收藏操作
	 * @param userId
	 * @param courseId
	 * @return
	 */
	@Transactional
	public void handleCollection(Long userId,Long courseId)
	{
		int result = userCollectionDao.queryCollectionCountByUserIdAndCourseId(userId, courseId);
		if(result>0)
		{
			userCollectionDao.deleteCollection(userId, courseId);
			//return false;
		}
		else
		{
			userCollectionDao.insertCollection(userId, courseId);
			//课程的优先级加1
			courseDao.addPriority(courseId);
			//return true;
		}
	}
	
	/**
	 * 判断课程是否被收藏
	 * @param userId
	 * @param courseId
	 * @return
	 */
	public boolean isCollected(Long userId,Long courseId)
	{
		int result = userCollectionDao.queryCollectionCountByUserIdAndCourseId(userId, courseId);
		if(result>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * 获取用户收藏的课程
	 * @param userId
	 * @return
	 */
	public List<Course> getCollectionList(Long userId)
	{
		return courseDao.queryCollectionByUserId(userId);
	}
}
