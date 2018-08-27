package com.friendship.manage.service;

import com.java.po.UserMovie;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

public interface IUserMovieService {
	public PagePagingData getUserMovieList(AjaxPagingData pagingData);
	
	public String add(UserMovie entity, String movieName);
	
	public Integer updateGnosis(UserMovie entity, String movieName);
	
	
	
	public PagePagingData getUserMovieListF(AjaxPagingData pagingData);

}
