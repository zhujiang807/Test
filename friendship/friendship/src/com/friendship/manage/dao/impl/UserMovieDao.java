package com.friendship.manage.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.friendship.manage.dao.IUserMovieDao;
import com.friendship.manage.extend.UserMoviePaging;
import com.java.po.UserMovie;
import com.java.util.DateBase;
import com.java.util.HibernateDaoBase;
import com.java.util.paging.AjaxPagingData;

@Repository("userMovieDao")
public class UserMovieDao extends HibernateDaoBase<UserMovie> implements IUserMovieDao{

	@Override
	public List<UserMoviePaging> getUserMovieList(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select um.*,user.userQQ,user.userQQName,user.userName,movie.movieName from user_movie as um left join qq_user as user on user.userId=um.userId left join movie as movie on movie.movieId=um.movieId ");
		return getList(sql, pagingData);
	}

	@Override
	public Integer getCount(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select count(*) from user_movie as um left join qq_user as user on user.userId=um.userId left join movie as movie on movie.movieId=um.movieId ");
		return super.getCountSql(getPagingSql(sql, pagingData));
	}

	@Override
	public Integer getF(Integer userId, String movieName) {
		Object movieId = super.getSql("select m.movieId from user_movie as um left join movie as m on m.movieId=um.movieId where um.userId="+userId+" and m.movieName='"+movieName+"' and um.createTime='"+DateBase.getDateStringYMD()+"'");
		if(movieId == null)
			return null;
		return Integer.parseInt(movieId.toString());
	}

	@Override
	public Integer updateGnosis(UserMovie entity) {
		return super.update("update user_movie set createTime='"+DateBase.getDateStringYMD()+"',movieGnosis='"+entity.getMovieGnosis()+"' where userId="+entity.getUserId()+" and movieId="+entity.getMovieId());
	}

	@Override
	public List<UserMoviePaging> getUserMovieListF(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select um.movieGnosis,um.createTime,CONCAT(SUBSTRING(user.userName,1,2),'###') as userName,movie.movieName from user_movie as um left join qq_user as user on user.userId=um.userId left join movie as movie on movie.movieId=um.movieId");
		return getList(sql, pagingData);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<UserMoviePaging> getList(StringBuffer sql, AjaxPagingData pagingData) {
		final Integer item = pagingData.getItem();
		final Integer page = pagingData.getPage();
		final String s = getPagingSql(sql, pagingData);
		return super.getHibernateTemplate().execute( new HibernateCallback() {
			@Override
			public List<UserMoviePaging> doInHibernate(Session session)  {
				Integer item2;
				if(item == null || item < 1)
					item2 = 10;
				else
					item2 = item;
				Query query = session.createSQLQuery(s).setResultTransformer(Transformers.aliasToBean(UserMoviePaging.class));
				query.setFirstResult((page-1)*item2);
				query.setMaxResults(item2);
				List list = query.list();
				return list;
			}
		});
	}
}
