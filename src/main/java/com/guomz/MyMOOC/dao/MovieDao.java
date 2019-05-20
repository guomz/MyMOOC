package com.guomz.MyMOOC.dao;

import java.util.List;

import com.guomz.MyMOOC.entity.Movie;

public interface MovieDao {

	public List<Movie> queryMovieByCondition(Movie movie);
	
	public Movie queryMovieByMovieId(Long movieId);
}
