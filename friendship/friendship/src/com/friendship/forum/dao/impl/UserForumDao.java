package com.friendship.forum.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.friendship.forum.dao.IUserForumDao;
import com.friendship.forum.extend.UserForumPaging;
import com.java.po.UserForum;
import com.java.util.HibernateDaoBase;
import com.java.util.paging.AjaxPagingData;

@Repository("userForumDao")
public class UserForumDao extends HibernateDaoBase<UserForum> implements IUserForumDao{

	@Override
	public List<UserForumPaging> getUserForumList(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select uf.*,forum.forumTitle,user.userName,user.userQQ,user.userQQName from user_forum as uf left join lol_forum as forum on uf.forumId=forum.forumId left join qq_user as user on user.userId=uf.userId ");
		return getList(sql, pagingData);
	}

	@Override
	public void del(UserForum entity) {
		super.delete(super.get("from UserForum where userForumId=?", entity.getUserForumId()));
	}

	@Override
	public Integer getCount(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select count(*) from user_forum as uf left join lol_forum as forum on uf.forumId=forum.forumId left join qq_user as user on user.userId=uf.userId ");
		return super.getCountSql(super.getPagingSql(sql, pagingData));
	}

	@Override
	public List<UserForumPaging> getUserForumInfoF(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select uf.content,concat(substring(user.userName,1,2),'###') as userName,uf.createTime from user_forum as uf left join lol_forum as forum on uf.forumId=forum.forumId left join qq_user as user on user.userId=uf.userId");
		return getList(sql, pagingData);
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<UserForumPaging> getList(StringBuffer sql, AjaxPagingData pagingData) {
		final Integer item = pagingData.getItem();
		final Integer page = pagingData.getPage();
		final String s = getPagingSql(sql, pagingData);
		return super.getHibernateTemplate().execute( new HibernateCallback() {
			@Override
			public List<UserForumPaging> doInHibernate(Session session)  {
				Integer item2;
				if(item == null || item < 1)
					item2 = 10;
				else
					item2 = item;
				Query query = session.createSQLQuery(s).setResultTransformer(Transformers.aliasToBean(UserForumPaging.class));
				query.setFirstResult((page-1)*item2);
				query.setMaxResults(item2);
				List list = query.list();
				return list;
			}
		});	
	}
}
