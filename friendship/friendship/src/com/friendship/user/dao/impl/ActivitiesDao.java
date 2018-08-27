package com.friendship.user.dao.impl;


import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.friendship.user.dao.IActivitiesDao;
import com.friendship.user.extend.ActivitiesPaging;
import com.java.po.UserActivities;
import com.java.util.HibernateDaoBase;
import com.java.util.paging.AjaxPagingData;

@Repository("activitiesDao")
public class ActivitiesDao extends HibernateDaoBase<UserActivities> implements IActivitiesDao{

	@Override
	public List<ActivitiesPaging> getActivitiesList(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select act.*,area.areaName from user_activities as act left join lol_area as area on area.areaId=act.areaId ");
		return getList(sql, pagingData);
	}

	@Override
	public Integer getCount(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select count(*) from user_activities as act left join lol_area as area on area.areaId=act.areaId ");
		return super.getCountSql(getPagingSql(sql, pagingData));
	}
	

	@Override
	public void del(UserActivities entity) {
		super.delete(super.get("from UserActivities where actId=?", entity.getActId()));
	}

	@Override
	public List<UserActivities> getList(Integer actCode) {
		return super.pagingHql("from UserActivities where actCode="+actCode, 1, 1);
	}

	@Override
	public UserActivities actIdGet(Integer actId) {
		return super.pagingHql("from UserActivities where actId="+actId, 1, 1).get(0);
	}

	@Override
	public Integer updateAct(UserActivities entity) {
		StringBuffer sql = new StringBuffer("update user_activities set areaId=").append(entity.getAreaId());
		sql.append(",actCode=").append(entity.getActCode()).append(",actName='").append(entity.getActName()).append("'");
		if(entity.getActPictureAddress() != null && entity.getActPictureAddress().length() > 0)
			sql.append(",actPictureAddress='").append(entity.getActPictureAddress()).append("'");
		sql.append(",actUrl='").append(entity.getActUrl()).append("'").append(",actBeginTime='").append(entity.getActBeginTime()).append("'");
		sql.append(",actEndTime='").append(entity.getActEndTime()).append("'").append(",actRemarks='").append(entity.getActRemarks()).append("'");
		sql.append(",actShow=").append(entity.getActShow()).append(",actAmount=").append(entity.getActAmount());
		sql.append(" where actId=").append(entity.getActId());
		return super.update(sql.toString());
	}

	@Override
	public List<UserActivities> actHomepage() {
		return super.pagingSql("select actName,actUrl,actPictureAddress,actCode from user_activities where actShow=1 order by actCreateTime desc ", 1, 4, UserActivities.class);
	}

	@Override
	public List<ActivitiesPaging> getActivitiesListF(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select act.actPictureAddress,act.actName,act.actCode,act.actBeginTime,act.actEndTime,act.actRemarks,act.actAmount,area.areaName from user_activities as act left join lol_area as area on area.areaId=act.areaId ");
		return getList(sql, pagingData);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<ActivitiesPaging> getList(StringBuffer sql, AjaxPagingData pagingData){
		final Integer item = pagingData.getItem();
		final Integer page = pagingData.getPage();
		final String s = getPagingSql(sql, pagingData);
		return super.getHibernateTemplate().execute( new HibernateCallback() {
			@Override
			public List<ActivitiesPaging> doInHibernate(Session session)  {
				Integer item2;
				if(item == null || item < 1)
					item2 = 10;
				else
					item2 = item;
				Query query = session.createSQLQuery(s).setResultTransformer(Transformers.aliasToBean(ActivitiesPaging.class));
				query.setFirstResult((page-1)*item2);
				query.setMaxResults(item2);
				List list = query.list();
				return list;
			}
		});
	}

	@Override
	public UserActivities getF(Integer actCode, String actName) {
		return get("from UserActivities where actCode=? and actName=?", actCode, actName);
	}
}
