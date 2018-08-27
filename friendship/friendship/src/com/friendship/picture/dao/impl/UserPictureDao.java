package com.friendship.picture.dao.impl;

import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.friendship.picture.dao.IUserPictureDao;
import com.friendship.picture.extend.UserPicturePaging;
import com.java.po.UserPicture;
import com.java.util.HibernateDaoBase;
import com.java.util.paging.AjaxPagingData;

@Repository("userPictureDao")
public class UserPictureDao extends HibernateDaoBase<UserPicture> implements IUserPictureDao{

	@Override
	public List<UserPicturePaging> getPictureList(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select picture.*,user.userQQ,user.userName,user.userQQName from user_picture as picture left join qq_user as user on user.userId=picture.userId ");
		return getPictureList(pagingData, sql);
	}

	@Override
	public Integer getCount(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select count(*) from user_picture as picture left join qq_user as user on user.userId=picture.userId ");
		return super.getCountSql(getPagingSql(sql, pagingData));
	}

	@Override
	public List<UserPicture> getHomepage(Integer page, Integer item) {
		String hql = "select pictureName,pictureUrl from user_picture order by rand(),pictureType";
		return super.pagingSql(hql, page, item, UserPicture.class);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<UserPicturePaging> getPictureList(AjaxPagingData pagingData, StringBuffer sql){
		final Integer item = pagingData.getItem();
		final Integer page = pagingData.getPage();
		final String s = super.getPagingSql(sql, pagingData);
		return super.getHibernateTemplate().execute( new HibernateCallback() {
			@Override
			public List<UserPicturePaging> doInHibernate(Session session)  {
				Integer item2;
				if(item == null || item < 1)
					item2 = 10;
				else
					item2 = item;
				Query query = session.createSQLQuery(s).setResultTransformer(Transformers.aliasToBean(UserPicturePaging.class));
				query.setFirstResult((page-1)*item2);
				query.setMaxResults(item2);
				List list = query.list();
				return list;
			}
		});
	}

	@Override
	public void del(UserPicture entity) {
		super.delete(super.get("from UserPicture where userPictureId=?", entity.getUserPictureId()));
	}

	@Override
	public UserPicture get(Integer userId, Date pictureCreateTime) {
		return super.get("from UserPicture where userId=? and pictureCreateTime=?", userId, pictureCreateTime);
	}

	@Override
	public List<UserPicturePaging> getPictureListF(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select picture.pictureUrl,user.userName from user_picture as picture left join qq_user as user on user.userId=picture.userId ");
		return getPictureList(pagingData, sql);
	}
}
