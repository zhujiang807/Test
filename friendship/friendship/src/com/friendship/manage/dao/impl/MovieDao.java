package com.friendship.manage.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.friendship.manage.dao.IMovieDao;
import com.friendship.manage.extend.MoviePaging;
import com.java.po.Movie;
import com.java.util.DateBase;
import com.java.util.HibernateDaoBase;
import com.java.util.paging.AjaxPagingData;

@Repository("movieDao")
public class MovieDao extends HibernateDaoBase<Movie> implements IMovieDao{

	@Override
	public List<Movie> getMovieList(AjaxPagingData pagingData) {
		StringBuffer hql = new StringBuffer("from Movie ");
		return super.pagingHql(getPagingSql(hql, pagingData), pagingData.getPage(), pagingData.getItem());
	}

	@Override
	public void del(Movie entity) {
		super.delete(super.get("from Movie where movieId=?", entity.getMovieId()));
	}

	@Override
	public Integer getCount(AjaxPagingData pagingData) {
		StringBuffer hql = new StringBuffer("select count(*) from Movie ");
		return super.getCountHql(getPagingSql(hql, pagingData));
	}

	@Override
	public Movie get(Integer mvoieId) {
		return super.get("from Movie where movieId=?", mvoieId);
	}

	@Override
	public void updateMovie(Movie entity) {
		StringBuffer sql = new StringBuffer("update movie set ");
		sql.append("movieName='").append(entity.getMovieName()).append("',movieShow=").append(entity.getMovieShow());
		sql.append(",movieRemarks='").append(entity.getMovieRemarks()).append("'");
		if(entity.getMovieGnosis() != null && entity.getMovieGnosis().length() > 0)
			sql.append(",movieGnosis='").append(entity.getMovieGnosis()).append("'");
		if(entity.getMovieShowTime() != null)
			sql.append(",movieShowTime='").append(entity.getMovieShowTime()).append("'");
		sql.append(" where movieId=").append(entity.getMovieId());
		super.update(sql.toString());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<MoviePaging> getMovieListF(AjaxPagingData pagingData) {
		final StringBuffer sql = new StringBuffer("select movieName,moviePicture,movieGnosis,movieRemarks,movieShowTime,(select count(*) from user_movie as um where um.movieId=mvoie.movieId and createTime='"+DateBase.getDateStringYMD()+"') as total from Movie as mvoie where 0=0");
		final Integer item = pagingData.getItem();
		final Integer page = pagingData.getPage();
		final String s = getPagingSql(sql, pagingData);
		return super.getHibernateTemplate().execute( new HibernateCallback() {
			@Override
			public List<MoviePaging> doInHibernate(Session session)  {
				Integer item2;
				if(item == null || item < 1)
					item2 = 10;
				else
					item2 = item;
				Query query = session.createSQLQuery(s).setResultTransformer(Transformers.aliasToBean(MoviePaging.class));
				query.setFirstResult((page-1)*item2);
				query.setMaxResults(item2);
				List list = query.list();
				return list;
			}
		});
	}

	@Override
	public Integer get(String movieName) {
		return Integer.parseInt(super.getField("select movieId from movie where movieName='"+movieName+"'").toString());
	}
}
