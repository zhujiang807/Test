package com.friendship.teams.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.friendship.teams.dao.IUserTeamsDao;
import com.friendship.teams.extend.UserTeamsPaging;
import com.java.po.UserTeams;
import com.java.util.HibernateDaoBase;
import com.java.util.paging.AjaxPagingData;

@Repository("userTeamsDao")
public class UserTeamsDao extends HibernateDaoBase<UserTeams> implements IUserTeamsDao{

	@Override
	public List<UserTeamsPaging> getUserTeamsList(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select userteams.*,area.areaName,qqUser.userQQ,qqUser.userName,qqUser.userQQName,teams.teamsName from user_teams as userTeams left join qq_user as qqUser on qqUser.userId=userTeams.userId left join lol_teams as teams on teams.teamsId=userTeams.teamsId left join lol_area as area on area.areaId=teams.areaId ");
		return getList(sql, pagingData);
	}

	@Override
	public void del(UserTeams entity) {
		super.delete(super.get("from UserTeams where userTeamsid=?", entity.getUserTeamsId()));
	}

	@Override
	public Integer updateCheck(UserTeams entity) {
		return super.update("update user_teams set userTeamscheck="+entity.getUserTeamscheck()+" where userTeamsId="+entity.getUserTeamsId());
	}

	@Override
	public Integer updateCheckF(Integer teamsId) {
		return super.update("update user_teams set userTeamscheck=-1 where teamsId="+teamsId);
	}
	
	@Override
	public Integer getCount(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select count(*) from user_teams as userTeams left join qq_user as qqUser on qqUser.userId=userTeams.userId left join lol_teams as teams on teams.teamsId=userTeams.teamsId left join lol_area as area on area.areaId=teams.areaId ");
		return super.getCountSql(super.getPagingSql(sql, pagingData));
	}

	@Override
	public List<UserTeams> getF(Integer userId, Integer areaId) {
		return super.getListSql("select userTeamscheck from user_teams as ut left join lol_teams as t on t.teamsId=ut.teamsId where userTeamscheck <> -1 and t.areaId="+areaId+" and ut.userId="+userId, UserTeams.class);
	}

	@Override
	public List<UserTeamsPaging> getListF(Integer userId, String areaName, AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select ut.createTime,u.userQQ,u.userName from user_teams as ut left join qq_user as u on u.userId=ut.userId left join lol_teams as t on t.teamsId=ut.teamsId left join lol_area as a on a.areaId=t.areaId where t.userId=");
		sql.append(userId).append(" and a.areaName='").append(areaName).append("'");
		return getList(sql, pagingData);
	}

	@Override
	public List<UserTeamsPaging> getListF(Integer teamsId, AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select ut.createTime,u.userQQ,u.userName from user_teams as ut left join qq_user as u on u.userId=ut.userId left join lol_teams as t on t.teamsId=ut.teamsId left join lol_area as a on a.areaId=t.areaId where ut.teamsId=").append(teamsId);
		return getList(sql, pagingData);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<UserTeamsPaging> getList(StringBuffer sql, AjaxPagingData pagingData){
		final Integer item = pagingData.getItem();
		final Integer page = pagingData.getPage();
		final String s = getPagingSql(sql, pagingData);
		return super.getHibernateTemplate().execute( new HibernateCallback() {
			@Override
			public List<UserTeamsPaging> doInHibernate(Session session)  {
				Integer item2;
				if(item == null || item < 1)
					item2 = 10;
				else
					item2 = item;
				Query query = session.createSQLQuery(s).setResultTransformer(Transformers.aliasToBean(UserTeamsPaging.class));
				query.setFirstResult((page-1)*item2);
				query.setMaxResults(item2);
				List list = query.list();
				return list;
			}
		});	
	}

	@Override
	public List<UserTeams> getF(String userQQ, String areaName) {
		StringBuffer sql = new StringBuffer("select ut.userTeamsId,t.teamsId from user_teams as ut left join lol_teams as t on ut.teamsId=t.teamsid left join qq_user as u  on u.userId=ut.userId left join lol_area as a on a.areaId=t.areaId where u.userQQ='");
		sql.append(userQQ).append("' and a.areaName='").append(areaName).append("'");
		return super.getListSql(sql.toString(), UserTeams.class);
	}

	@Override
	public List<UserTeams> getListF(Integer userTeamsId, Integer userId) {
		return super.getListSql("select * from user_teams where userTeamsId="+userTeamsId+" and userId="+userId, UserTeams.class);
	}
	
	@Override
	public List<UserTeamsPaging> getJoinListF(Integer userId, AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select ut.userTeamsId,u.userQQ,u.userName,t.teamsName,ut.createTime,a.areaName,t.teamsIcon,t.teamsIntegral,t.teamsRemarks,t.teamsNumber from user_teams as ut left join lol_teams as t on t.teamsId=ut.teamsId left join lol_area as a on a.areaId=t.areaId left join qq_user as u on u.userId=t.userId where ut.userId=");
		sql.append(userId);
		List<UserTeams> list = super.getListSql("select teamsId from lol_teams where userId="+userId, UserTeams.class);
		if(list != null && list.size() > 0){
			sql.append(" and ut.teamsId not in (");
			for (UserTeams userTeams : list) {
				sql.append(userTeams.getTeamsId()).append(",");
			}
			sql = new StringBuffer(sql.substring(0, sql.length()-1)+")");
		}
		return getList(sql, pagingData);
	}

	@Override
	public List<UserTeams> getF2(Integer userId, Integer teamsId) {
		return super.getListSql("select * from user_teams where userId="+userId+" and teamsId="+teamsId, UserTeams.class);
	}
}
