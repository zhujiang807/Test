package com.friendship.manage.dao.impl;

import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.friendship.manage.dao.ILetterDao;
import com.friendship.manage.extend.LetterPaging;
import com.java.po.PrivateLetter;
import com.java.util.HibernateDaoBase;
import com.java.util.paging.AjaxPagingData;

@Repository("letterDao")
public class LetterDao extends HibernateDaoBase<PrivateLetter> implements ILetterDao{

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<LetterPaging> getLetterList(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select letter.*,user.userQQ,user.userQQName from private_letter as letter left join qq_user as user on user.userId=letter.userId ");
		final Integer item = pagingData.getItem();
		final Integer page = pagingData.getPage();
		final String s = getPagingSql(sql, pagingData);
		return super.getHibernateTemplate().execute( new HibernateCallback() {
			@Override
			public List<LetterPaging> doInHibernate(Session session)  {
				Integer item2;
				if(item == null || item < 1)
					item2 = 10;
				else
					item2 = item;
				Query query = session.createSQLQuery(s).setResultTransformer(Transformers.aliasToBean(LetterPaging.class));
				query.setFirstResult((page-1)*item2);
				query.setMaxResults(item2);
				List list = query.list();
				return list;
			}
		});
	}

	@Override
	public void updateCheck(PrivateLetter entity) {
		super.update("update private_letter set letterCheck=1 where letterId="+entity.getLetterId());
	}

	@Override
	public Integer getCount(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select count(*) from private_letter as letter left join qq_user as user on user.userId=letter.userId ");
		return super.getCountSql(super.getPagingSql(sql, pagingData));
	}

	@Override
	public void del(PrivateLetter entity) {
		super.delete(super.get("from PrivateLetter where letterId=?", entity.getLetterId()));
	}

	@Override
	public PrivateLetter getF(Integer userId, Date createTime) {
		return super.get("from PrivateLetter where userId=? and letterCreateTime=?", userId, createTime);
	}

}
