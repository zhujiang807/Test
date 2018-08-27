package com.friendship.user.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.friendship.user.dao.IJoinDao;
import com.friendship.user.extend.JoinPaging;
import com.java.po.UserJoin;
import com.java.util.HibernateDaoBase;
import com.java.util.paging.AjaxPagingData;

@Repository("joinDao")
public class JoinDao extends HibernateDaoBase<UserJoin> implements IJoinDao{

	@Override
	public List<JoinPaging> getJoinList(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select uj.*,act.actName,act.actCode,user.userName,user.userQQ,tea.teamsName from user_join as uj left join user_activities as act on uj.activitiesId=actId left join lol_teams as tea on tea.teamsId=uj.teamsid left join qq_user as user on user.userId=uj.userId ");
		return getList(sql, pagingData);
	}

	@Override
	public void deleteJoin(UserJoin entity) {
		super.delete(super.get("from UserJoin where joinId=?", entity.getJoinId()));
	}

	@Override
	public Integer getCount(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select count(*) from user_join as uj left join user_activities as act on uj.activitiesId=actId left join lol_teams as tea on tea.teamsId=uj.teamsid left join qq_user as user on user.userId=uj.userId ");
		return super.getCountSql(super.getPagingSql(sql,pagingData).toString());
	}

	@Override
	public List<UserJoin> getJoinList(Integer actCode) {
		return super.getListSql("select uj.* from user_join as uj left join user_activities as act on act.actId=uj.activitiesId where joinResult=0 and act.actCode="+actCode, UserJoin.class);
	}
	
	@Override
	public Integer updateJoin(Integer joinId, Integer joinResult) {
		return super.update("update user_join set joinResult="+joinResult+" where joinId="+joinId);
	}

	@Override
	public List<JoinPaging> joinListF(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select tr.ranRemarks,ua.actName,a.areaName,t.teamsName,uj.joinResult,uj.joinCreateTime from user_join as uj left join lol_teams as t on t.teamsId=uj.teamsid left join lol_area as a on a.areaId=t.areaId left join user_activities as ua on ua.actId=uj.activitiesId left join qq_user as u on u.userId=t.userId left join teams_ranking as tr on tr.teamsId=uj.teamsId and ua.actId=tr.activitiesId");
		return getList(sql, pagingData);
	}
	

	@Override
	public Integer getCountF(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select count(*) from user_join as uj left join lol_teams as t on t.teamsId=uj.teamsid left join lol_area as a on a.areaId=t.areaId left join user_activities as ua on ua.actId=uj.activitiesId left join qq_user as u on u.userId=t.userId left join teams_ranking as tr on tr.teamsId=uj.teamsId and ua.actId=tr.activitiesId");
		return super.getCountSql(super.getPagingSql(sql,pagingData).toString());
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<JoinPaging> getList(StringBuffer sql, AjaxPagingData pagingData){
		final Integer item = pagingData.getItem();
		final Integer page = pagingData.getPage();
		final String s = super.getPagingSql(sql, pagingData);
		return super.getHibernateTemplate().execute( new HibernateCallback() {
			@Override
			public List<JoinPaging> doInHibernate(Session session)  {
				Integer item2;
				if(item == null || item < 1)
					item2 = 10;
				else
					item2 = item;
				Query query = session.createSQLQuery(s).setResultTransformer(Transformers.aliasToBean(JoinPaging.class));
				query.setFirstResult((page-1)*item2);
				query.setMaxResults(item2);
				List list = query.list();
				return list;
			}
		});
	}

	@Override
	public UserJoin get(Integer userId, Integer teamsId, Integer actId) {
		return get("from UserJoin where userId=? and teamsId=? and activitiesId=?", userId, teamsId, actId);
	}
}
