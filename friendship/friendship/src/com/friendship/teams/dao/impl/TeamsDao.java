package com.friendship.teams.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.friendship.teams.dao.ITeamsDao;
import com.friendship.teams.extend.TeamsPaging;
import com.java.po.LolTeams;
import com.java.util.HibernateDaoBase;
import com.java.util.paging.AjaxPagingData;

@Repository("teamsDao")
public class TeamsDao extends HibernateDaoBase<LolTeams> implements ITeamsDao{

	@Override
	public List<TeamsPaging> getTeamsList(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select t.*,u.userQQ,a.areaName from lol_teams as t left join qq_user as u on u.userid=t.userId left join lol_area as a on a.areaId=t.areaId ");
		return getTeamsList(sql, pagingData);
	}

	@Override
	public Integer getTeamssCount(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select count(*) from lol_teams as t left join qq_user as u on u.userid=t.userId left join lol_area as a on a.areaId=t.areaId ");
		return super.getCountSql(getPagingSql(sql, pagingData));
	}

	@Override
	public void deleteTeams(LolTeams entity) {
		super.delete(super.get("from LolTeams where teamsId=?", entity.getTeamsId()));
	}

	@Override
	public LolTeams get(Integer teamsId) {
		return super.pagingHql("from LolTeams where teamsId="+teamsId, 1, 1).get(0);
	}

	@Override
	public Integer updateTeamsIntegral(LolTeams entity) {
		StringBuffer sql = new StringBuffer("update lol_teams set teamsIntegral=teamsIntegral+").append(entity.getTeamsIntegral());
		sql.append(" where teamsId=").append(entity.getTeamsId());
		return super.update(sql.toString());
	}

	@Override
	public Integer updateTeamsNumber(LolTeams entity) {
		return super.update("update lol_teams set teamsNumber=teamsNumber+"+entity.getTeamsNumber()+" where teamsId="+entity.getTeamsId());
	}

	@Override
	public List<TeamsPaging> getHomepage(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select t.teamsId,t.teamsCreateTime,u.userName,t.teamsIcon,t.teamsName,t.teamsNumber,a.areaName,t.teamsRemarks,t.teamsIntegral from lol_teams as t left join qq_user as u on u.userid=t.userId left join lol_area as a on a.areaId=t.areaId ");
		return getTeamsList(sql, pagingData);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<TeamsPaging> getTeamsList(StringBuffer sql, AjaxPagingData pagingData) {
		final Integer item = pagingData.getItem();
		final Integer page = pagingData.getPage();
		final String s = getPagingSql(sql, pagingData);
		return super.getHibernateTemplate().execute( new HibernateCallback() {
			@Override
			public List<TeamsPaging> doInHibernate(Session session)  {
				Integer item2;
				if(item == null || item < 1)
					item2 = 10;
				else
					item2 = item;
				Query query = session.createSQLQuery(s).setResultTransformer(Transformers.aliasToBean(TeamsPaging.class));
				query.setFirstResult((page-1)*item2);
				query.setMaxResults(item2);
				List list = query.list();
				return list;
			}
		});
	}

	@Override
	public List<TeamsPaging> getTeamsListF(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select t.teamsId,u.userName,t.teamsIcon,t.teamsName,t.teamsIntegral,t.teamsRemarks,t.teamsNumber,t.teamsCreateTime,a.areaName from lol_teams as t left join qq_user as u on u.userid=t.userId left join lol_area as a on a.areaId=t.areaId ");
		return getTeamsList(sql, pagingData);	
	}

	@Override
	public LolTeams getF(Integer userId, String areaName) {
		StringBuffer sql =  new StringBuffer("select t.* from lol_teams as t left join lol_area as a on a.areaId=t.areaId where");
		sql.append(" t.userId=").append(userId).append(" and a.areaName='").append(areaName).append("'");
		List<LolTeams> list = super.getListSql(sql.toString(), LolTeams.class);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Integer getF(String teamsName, Integer areaId) {
		StringBuffer sql =  new StringBuffer("select count(*) from lol_teams where");
		sql.append(" areaId=").append(areaId).append(" and teamsName='").append(teamsName).append("'");
		return super.getCountSql(sql.toString());
	}

	@Override
	public LolTeams getF(Integer userId, Integer areaId) {
		StringBuffer sql =  new StringBuffer("select teamsId,teamsName from lol_teams where");
		sql.append(" areaId=").append(areaId).append(" and userId=").append(userId).append("");
		List<LolTeams> list = super.getListSql(sql.toString(), LolTeams.class);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Integer update(Integer teamsId, String teamsName, String teamsIcon, String teamsRemarks) {
		StringBuffer sql = new StringBuffer("update lol_teams set teamsName='").append(teamsName);
		if(teamsIcon != null){
			sql.append("',teamsIcon ='").append(teamsIcon);
		}
		sql.append("',teamsRemarks='").append(teamsRemarks).append("' where teamsId=").append(teamsId);
		return super.update(sql.toString());
	}
}
