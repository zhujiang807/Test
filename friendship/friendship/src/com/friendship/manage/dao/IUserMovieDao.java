package com.friendship.manage.dao;

import java.util.List;

import com.friendship.manage.extend.UserMoviePaging;
import com.java.po.UserMovie;
import com.java.util.paging.AjaxPagingData;

public interface IUserMovieDao {
	public List<UserMoviePaging> getUserMovieList(AjaxPagingData pagingData);
	
	public Integer getCount(AjaxPagingData pagingData);
	
	public void save(UserMovie entity);
	
	
	
	public Integer getF(Integer userId, String movieName);
	
	public Integer updateGnosis(UserMovie entity);
	
	public List<UserMoviePaging> getUserMovieListF(AjaxPagingData pagingData);

}
