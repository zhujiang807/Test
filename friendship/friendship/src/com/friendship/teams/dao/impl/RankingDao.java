package com.friendship.teams.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.friendship.teams.dao.IRankingDao;
import com.friendship.teams.extend.RankingPaging;
import com.java.po.TeamsRanking;
import com.java.util.HibernateDaoBase;
import com.java.util.paging.AjaxPagingData;

@Repository("rankingDao")
public class RankingDao extends HibernateDaoBase<TeamsRanking> implements IRankingDao{

	@Override
	public TeamsRanking get(Integer activitiesId, Integer teamsId) {
		return super.get("from TeamsRanking where activitiesId=? and teamsId=?", activitiesId, teamsId);
	}

	@Override
	public List<RankingPaging> getRankingList(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select ranking.*,teams.teamsName,userr.userQQ,userr.userName,area.areaName,act.actName,act.actCode from teams_ranking as ranking left join lol_teams as teams on teams.teamsId=ranking.teamsId left join lol_area as area on area.areaId=teams.areaId left join qq_user as userr on userr.userId=teams.userId left join user_activities as act on act.actId=ranking.activitiesId ");
		return getList(sql, pagingData);
	}

	@Override
	public void del(TeamsRanking entity) {
		super.delete(super.get("from TeamsRanking where ranId=?", entity.getRanId()));
	}

	@Override
	public Integer getCount(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select count(*) from teams_ranking as ranking left join lol_teams as teams on teams.teamsId=ranking.teamsId left join lol_area as area on area.areaId=teams.areaId left join qq_user as userr on userr.userId=teams.userId left join user_activities as act on act.actId=ranking.activitiesId ");
		return super.getCountSql(getPagingSql(sql, pagingData));
	}

	@Override
	public Integer updateRanking(TeamsRanking entity) {
		return super.update("update teams_ranking set ranNumber= "+entity.getRanNumber()+",ranRemarks='"+entity.getRanRemarks()+"' where ranId="+entity.getRanId());
	}

	@Override
	public List<RankingPaging> getRankingListF(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select ranking.ranCreateTime,ranking.ranRemarks,teams.teamsName,userr.userName,area.areaName,act.actName from teams_ranking as ranking left join lol_teams as teams on teams.teamsId=ranking.teamsId left join lol_area as area on area.areaId=teams.areaId left join qq_user as userr on userr.userId=teams.userId left join user_activities as act on act.actId=ranking.activitiesId ");
		return getList(sql, pagingData);
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<RankingPaging> getList(StringBuffer sql, AjaxPagingData pagingData){
		final Integer item = pagingData.getItem();
		final Integer page = pagingData.getPage();
		final String s = getPagingSql(sql, pagingData);
		return super.getHibernateTemplate().execute( new HibernateCallback() {
			@Override
			public List<RankingPaging> doInHibernate(Session session)  {
				Integer item2;
				if(item == null || item < 1)
					item2 = 10;
				else
					item2 = item;
				Query query = session.createSQLQuery(s).setResultTransformer(Transformers.aliasToBean(RankingPaging.class));
				query.setFirstResult((page-1)*item2);
				query.setMaxResults(item2);
				List list = query.list();
				return list;
			}
		});
	}
}
