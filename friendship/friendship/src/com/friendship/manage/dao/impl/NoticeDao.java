package com.friendship.manage.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.friendship.manage.dao.INoticeDao;
import com.friendship.manage.extend.NoticePaging;
import com.java.po.UserNotice;
import com.java.util.HibernateDaoBase;
import com.java.util.paging.AjaxPagingData;

@Repository("noticeDao")
public class NoticeDao extends HibernateDaoBase<UserNotice> implements INoticeDao{

	@Override
	public List<NoticePaging> getNoticeList(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select notice.*,user.userName,userQQ,manage.manName from user_notice as notice left join manage as manage on manage.manId=notice.manageId left join qq_user as user on user.userId=notice.userId ");
		return getList(sql, pagingData);
	}

	@Override
	public Integer getCount(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select count(*) from user_notice as notice left join manage as manage on manage.manId=notice.manageId left join qq_user as user on user.userId=notice.userId ");
		return super.getCountSql(super.getPagingSql(sql, pagingData));
	}
	
	@SuppressWarnings({"unchecked", "rawtypes" })
	private List<NoticePaging> getList(StringBuffer sql, AjaxPagingData pagingData){
		final Integer item = pagingData.getItem();
		final Integer page = pagingData.getPage();
		final String s = getPagingSql(sql, pagingData);
		return super.getHibernateTemplate().execute( new HibernateCallback() {
			@Override
			public List<NoticePaging> doInHibernate(Session session)  {
				Integer item2;
				if(item == null || item < 1)
					item2 = 10;
				else
					item2 = item;
				
				Query query = session.createSQLQuery(s).setResultTransformer(Transformers.aliasToBean(NoticePaging.class));
				query.setFirstResult((page-1)*item2);
				query.setMaxResults(item2);
				List list = query.list();
				return list;
			}
		});
	}

	@Override
	public List<NoticePaging> getNoticeListF(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select notice.noticeContent,notice.noticeCreateTime,user.userName from user_notice as notice left join manage as manage on manage.manId=notice.manageId left join qq_user as user on user.userId=notice.userId ");
		return getList(sql, pagingData);
	}

	@Override
	public void del(UserNotice entity) {
		super.delete(get("from UserNotice where noticeId=?", entity.getNoticeId()));
	}
}
