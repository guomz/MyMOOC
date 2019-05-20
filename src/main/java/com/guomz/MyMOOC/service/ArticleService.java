package com.guomz.MyMOOC.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guomz.MyMOOC.dao.ArticleDao;
import com.guomz.MyMOOC.entity.Article;
/**
 * MyMOOC小程序
 * @author 12587
 *
 */
@Service
public class ArticleService {

	@Autowired
	private ArticleDao articleDao;
	
	public Article getArticleById(Long articleId)
	{
		return articleDao.queryArticleById(articleId);
	}
	
	public List<Article> getArticleByCondition(Article article)
	{
		return articleDao.queryArticleByCondition(article);
	}
	
}
