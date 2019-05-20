package com.guomz.MyMOOC.dao;

import java.util.List;

import com.guomz.MyMOOC.entity.Comment;

public interface CommentDao {

	public List<Comment> queryCommentByCondition(Comment comment);
	
	public void insertComment(Comment comment);
}
