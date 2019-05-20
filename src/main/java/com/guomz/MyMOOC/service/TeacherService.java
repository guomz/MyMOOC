package com.guomz.MyMOOC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guomz.MyMOOC.dao.TeacherDao;
import com.guomz.MyMOOC.entity.Teacher;

@Service
public class TeacherService {

	@Autowired
	private TeacherDao teacherDao;
	
	/**
	 * 根据教师id精确查询教师信息
	 * @param teacherId
	 * @return
	 */
	public Teacher getTeacherById(Long teacherId)
	{
		return teacherDao.queryTeacherById(teacherId);
	}
}
