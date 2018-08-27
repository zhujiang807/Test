package com.friendship.teams.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.friendship.teams.dao.IMatchDao;
import com.friendship.teams.extend.MatchPaging;
import com.java.po.ActivitiesMatch;
import com.java.util.HibernateDaoBase;
import com.java.util.paging.AjaxPagingData;

@Repository("matchDao")
public class MatchDao extends HibernateDaoBase<ActivitiesMatch> implements IMatchDao{

	@Override
	public void saveList(List<ActivitiesMatch> list) {
		super.save(list);
	}

	@Override
	public List<MatchPaging> getMatchList(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select mat.*,act.actCode,act.actName,tea1.teamsName as teamsName1,tea2.teamsName as teamsName2,area.areaName from activities_match as mat left join user_activities as act on act.actId=mat.activitiesId left join lol_teams as tea1 on tea1.teamsId=mat.teamsId1 left join lol_teams as tea2 on tea2.teamsId=mat.teamsId2 left join lol_area as area on area.areaId=act.areaId ");
		return getList(sql, pagingData);
	}

	@Override
	public Integer updateMatch(ActivitiesMatch entity) {
		StringBuffer sql = new StringBuffer("update activities_match set activitiesId=").append(entity.getActivitiesId());
		sql.append(",teamsId1=").append(entity.getTeamsId1()).append(",teamsId2=").append(entity.getTeamsId2());
		sql.append(",teamsVictory1=").append(entity.getTeamsVictory1()).append(",teamsVictory2=").append(entity.getTeamsVictory2());
		sql.append(",matchBeginTime='").append(entity.getMatchBeginTime()).append("',matchEndTime='").append(entity.getMatchEndTime());
		sql.append("',matchType=").append(entity.getMatchType()).append(",matchShow=").append(entity.getMatchShow()).append(" where matchId=").append(entity.getMatchId());
		return super.update(sql.toString());
	}

	@Override
	public Integer getCount(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select count(*) from activities_match as mat left join user_activities as act on act.actId=mat.activitiesId left join lol_teams as tea1 on tea1.teamsId=mat.teamsId1 left join lol_teams as tea2 on tea2.teamsId=mat.teamsId2 left join lol_area as area on area.areaId=act.areaId ");
		return super.getCountSql(super.getPagingSql(sql, pagingData));
	}

	@Override
	public void del(ActivitiesMatch entity) {
		super.delete(super.get("from ActivitiesMatch where matchId=?", entity.getMatchId()));
	}

	@Override
	public List<MatchPaging> matchHomepageF(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select tea1.teamsIcon as teamsIcon1,tea2.teamsIcon as teamsIcon2,mat.teamsVictory1,mat.teamsVictory2,mat.matchBeginTime,mat.matchEndTime,mat.matchType,act.actName,tea1.teamsName as teamsName1,ifnull(tea2.teamsName,0) as teamsName2,area.areaName from activities_match as mat left join user_activities as act on act.actId=mat.activitiesId left join lol_teams as tea1 on tea1.teamsId=mat.teamsId1 left join lol_teams as tea2 on tea2.teamsId=mat.teamsId2 left join lol_area as area on area.areaId=act.areaId where mat.matchShow=1 ");
		return getList(sql, pagingData);
	}

	@SuppressWarnings({"rawtypes", "unchecked" })
	private List<MatchPaging> getList(StringBuffer sql, AjaxPagingData pagingData){
		final Integer item = pagingData.getItem();
		final Integer page = pagingData.getPage();
		final String s = super.getPagingSql(sql, pagingData);
		return super.getHibernateTemplate().execute( new HibernateCallback() {
			@Override
			public List<MatchPaging> doInHibernate(Session session)  {
				Integer item2;
				if(item == null || item < 1)
					item2 = 10;
				else
					item2 = item;
				Query query = session.createSQLQuery(s).setResultTransformer(Transformers.aliasToBean(MatchPaging.class));
				query.setFirstResult((page-1)*item2);
				query.setMaxResults(item2);
				List list = query.list();
				return list;
			}
		});
	}

	@Override
	public Integer getCountF(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select count(*) from activities_match as mat left join user_activities as act on act.actId=mat.activitiesId left join lol_teams as tea1 on tea1.teamsId=mat.teamsId1 left join lol_teams as tea2 on tea2.teamsId=mat.teamsId2 left join lol_area as area on area.areaId=act.areaId where mat.matchShow=1 ");
		return super.getCountSql(super.getPagingSql(sql, pagingData));
	}

}
