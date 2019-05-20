package com.guomz.MyMOOC.dao;

import java.util.List;

import com.guomz.MyMOOC.entity.Article;

public interface ArticleDao {

	public Article queryArticleById(Long articleId);
	
	public List<Article> queryArticleByCondition(Article article);
	
}
