package com.friendship.manage.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.friendship.manage.dao.IUserLogDao;
import com.friendship.manage.extend.UserLogPaging;
import com.java.po.UserLog;
import com.java.util.HibernateDaoBase;
import com.java.util.paging.AjaxPagingData;

@Repository("userLogDao")
public class UserLogDao extends HibernateDaoBase<UserLog> implements IUserLogDao{

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<UserLogPaging> getUserLogList(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select ul.*,m.manName,qu.userQQ,qu.userName,qu.userQQName from user_log as ul left join qq_user as qu on qu.userId=ul.userId left join manage as m on m.manId=ul.manageId ");
		final Integer item = pagingData.getItem();
		final Integer page = pagingData.getPage();
		final String s = getPagingSql(sql, pagingData);
		return super.getHibernateTemplate().execute( new HibernateCallback() {
			@Override
			public List<UserLogPaging> doInHibernate(Session session)  {
				Integer item2;
				if(item == null || item < 1)
					item2 = 10;
				else
					item2 = item;
				Query query = session.createSQLQuery(s).setResultTransformer(Transformers.aliasToBean(UserLogPaging.class));
				query.setFirstResult((page-1)*item2);
				query.setMaxResults(item2);
				List list = query.list();
				return list;
			}
		});
	}

	@Override
	public Integer getCount(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select count(*) from user_log as ul left join qq_user as qu on qu.userId=ul.userId left join manage as m on m.manId=ul.manageId ");
		return super.getCountSql(super.getPagingSql(sql, pagingData));
	}

}
