package com.friendship.manage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.friendship.manage.dao.IMovieDao;
import com.friendship.manage.extend.MoviePaging;
import com.friendship.manage.service.IMovieService;
import com.java.po.Movie;
import com.java.util.annotation.UserLogAnnotation;
import com.java.util.aop.UserLogConfiguration;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

@Service("movieService")
public class MovieService implements IMovieService{
	private IMovieDao movieDao;
	@Resource(name="movieDao")
	public void setMovieDao(IMovieDao movieDao) {
		this.movieDao = movieDao;
	}

	@Override
	@Transactional(readOnly=true)
	public PagePagingData getMovieList(AjaxPagingData pagingData) {
		PagePagingData data = new PagePagingData();
		data.setData(movieDao.getMovieList(pagingData));
		data.setTotal(movieDao.getCount(pagingData));
		return data;
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "添加电影", type = "后", method = UserLogConfiguration.MethodName.MANAGE_LOGIN)
	public void save(Movie entity) {
		movieDao.save(entity);
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "删除电影", type = "后", method = UserLogConfiguration.MethodName.NULL)
	public void delete(Movie entity) {
		movieDao.del(entity);
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "修改电影", type = "后", method = UserLogConfiguration.MethodName.NULL)
	public void update(Movie entity) {
		movieDao.updateMovie(entity);
	}

	@Override
	public List<MoviePaging> getMovieListF(AjaxPagingData pagingData) {
		return movieDao.getMovieListF(pagingData);
	}

}
