package com.guomz.MyMOOC.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.guomz.MyMOOC.BaseTest;
import com.guomz.MyMOOC.entity.Comment;
import com.guomz.MyMOOC.entity.UserInfo;

public class CommentDaoTest extends BaseTest{

	@Autowired
	private CommentDao commentDao;
	
	@Test
	public void testQuery()
	{
		Comment comment=new Comment();
		comment.setCourseId(1L);
		UserInfo user=new UserInfo();
		user.setUserId(1L);
		comment.setUser(user);
		System.out.println(commentDao.queryCommentByCondition(comment));
	}
}
