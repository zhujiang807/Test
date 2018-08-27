package com.friendship.manage.service;

import java.util.List;

import com.friendship.manage.extend.MoviePaging;
import com.java.po.Movie;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

public interface IMovieService {
	public PagePagingData getMovieList(AjaxPagingData pagingData);
	
	public void save(Movie entity);
	
	public void delete(Movie entity);
	
	public void update(Movie entity);
	
	public List<MoviePaging> getMovieListF(AjaxPagingData pagingData);
}
