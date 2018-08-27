package com.friendship.manage.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.friendship.manage.dao.IMovieDao;
import com.friendship.manage.dao.IUserMovieDao;
import com.friendship.manage.service.IUserMovieService;
import com.java.po.UserMovie;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

@Service("userMovieService")
public class UserMovieService implements IUserMovieService{
	private IUserMovieDao userMovieDao;
	private IMovieDao movieDao;
	@Resource(name="userMovieDao")
	public void setUserMovieDao(IUserMovieDao userMovieDao) {
		this.userMovieDao = userMovieDao;
	}
	@Resource(name="movieDao")
	public void setMovieDao(IMovieDao movieDao) {
		this.movieDao = movieDao;
	}

	@Override
	@Transactional(readOnly=true)
	public PagePagingData getUserMovieList(AjaxPagingData pagingData) {
		PagePagingData data = new PagePagingData();
		data.setData(userMovieDao.getUserMovieList(pagingData));
		data.setTotal(userMovieDao.getCount(pagingData));
		return data;
	}

	@Override
	@Transactional
	public String add(UserMovie entity, String movieName) {
		Integer movieId = userMovieDao.getF(entity.getUserId(), movieName);
		if(movieId != null){
			return "你已经投过票了,只能投一次！";
		}
		entity.setMovieId(movieDao.get(movieName));
		userMovieDao.save(entity);
		return "投票成功！";
	}
	
	@Override
	@Transactional
	public Integer updateGnosis(UserMovie entity, String movieName) {
		entity.setMovieId(movieDao.get(movieName));
		return userMovieDao.updateGnosis(entity);
	}
	
	@Override
	@Transactional(readOnly=true)
	public PagePagingData getUserMovieListF(AjaxPagingData pagingData) {
		PagePagingData data = new PagePagingData();
		data.setData(userMovieDao.getUserMovieListF(pagingData));
		data.setTotal(userMovieDao.getCount(pagingData));
		return data;
	}

}
