package com.guomz.MyMOOC.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guomz.MyMOOC.dao.MovieDao;
import com.guomz.MyMOOC.entity.Movie;
/**
 * MyMOOC小程序
 * @author 12587
 *
 */
@Service
public class MovieService {

	@Autowired
	private MovieDao movieDao;
	
	public List<Movie> getMovieByCondition(Movie movie)
	{
		return movieDao.queryMovieByCondition(movie);
	}
	
	public Movie getMovieByMovieId(Long movieId)
	{
		return movieDao.queryMovieByMovieId(movieId);
	}
}
