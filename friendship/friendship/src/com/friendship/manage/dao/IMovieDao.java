package com.friendship.manage.dao;

import java.util.List;

import com.friendship.manage.extend.MoviePaging;
import com.java.po.Movie;
import com.java.util.paging.AjaxPagingData;


public interface IMovieDao {
	public List<Movie> getMovieList(AjaxPagingData pagingData);
	
	public void save(Movie entity);
	
	public void del(Movie entity);
	
	public Integer getCount(AjaxPagingData pagingData);
	
	public void updateMovie(Movie entity);
	
	public Movie get(Integer movieId);
	
	public Integer get(String movieName);
	
	public List<MoviePaging> getMovieListF(AjaxPagingData pagingData);
}
