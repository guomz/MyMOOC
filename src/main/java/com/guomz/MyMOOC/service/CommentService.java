package com.guomz.MyMOOC.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guomz.MyMOOC.dao.CommentDao;
import com.guomz.MyMOOC.entity.Comment;

@Service
public class CommentService {

	@Autowired
	private CommentDao commentDao;
	
	/**
	 * 按条件查询评论，根据查询数量可以判断用户是否评论过该课程
	 * @param comment
	 * @return
	 */
	public List<Comment> getCommentList(Comment comment)
	{
		return commentDao.queryCommentByCondition(comment);
	}
	
	/**
	 * 插入评论
	 * @param comment
	 */
	@Transactional
	public void insertComment(Comment comment)
	{
		commentDao.insertComment(comment);
	}
}
