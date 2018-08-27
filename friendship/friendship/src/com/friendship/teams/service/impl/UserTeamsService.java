package com.friendship.teams.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.friendship.teams.dao.ITeamsDao;
import com.friendship.teams.dao.IUserTeamsDao;
import com.friendship.teams.extend.UserTeamsPaging;
import com.friendship.teams.service.IUserTeamsService;
import com.java.po.LolTeams;
import com.java.po.UserTeams;
import com.java.util.annotation.UserLogAnnotation;
import com.java.util.aop.UserLogConfiguration;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

@Service("userTeamsService")
public class UserTeamsService implements IUserTeamsService{
	private IUserTeamsDao userTeamsDao;
	private ITeamsDao teamsDao;
	@Resource(name="userTeamsDao")
	public void setUserTeamsDao(IUserTeamsDao userTeamsDao) {
		this.userTeamsDao = userTeamsDao;
	}
	@Resource(name="teamsDao")
	public void setTeamsDao(ITeamsDao teamsDao) {
		this.teamsDao = teamsDao;
	}

	@Override
	@Transactional(readOnly=true)
	public PagePagingData getUserTeamsList(AjaxPagingData pagingData) {
		PagePagingData data = new PagePagingData();
		data.setData(userTeamsDao.getUserTeamsList(pagingData));
		data.setTotal(userTeamsDao.getCount(pagingData));
		return data;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void delete(UserTeams entity) {
		userTeamsDao.del(entity);
		LolTeams teams = new LolTeams();
		teams.setTeamsId(entity.getTeamsId());
		teams.setTeamsNumber(-1);
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "加入战队", type = "前", method = UserLogConfiguration.MethodName.USERTEAMS_SAVE)
	public void save(UserTeams entity) {
		userTeamsDao.save(entity);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	@UserLogAnnotation(content = "审核战队", type = "前", method = UserLogConfiguration.MethodName.USERTEAMS_CHECK)
	public void updateCheckF(UserTeamsPaging entity, String type) {
		List<UserTeams> list = userTeamsDao.getF(entity.getUserQQ(), entity.getAreaName());
		if(list != null && list.size() > 0){
			UserTeams userTeams = list.get(0);
			userTeams.setUserTeamscheck(entity.getUserTeamscheck());
			userTeamsDao.updateCheck(userTeams);
			
			LolTeams teams = new LolTeams();
			if(entity.getUserTeamscheck() > 0){
				teams.setTeamsNumber(1);
			}else if(entity.getUserTeamscheck() == -1 && type.equals("teams")){
				teams.setTeamsNumber(-1);
			}else{
				teams.setTeamsNumber(0);
			}
			teams.setTeamsId(userTeams.getTeamsId());
			teamsDao.updateTeamsNumber(teams);
		}
	}
	
	@Override
	@UserLogAnnotation(content = "退出战队", type = "前", method = UserLogConfiguration.MethodName.USERTEAMS_CHECK2)
	public String updateCheckF(Integer userTeamsId, Integer userId) {
		List<UserTeams> list = userTeamsDao.getListF(userTeamsId, userId);
		if(list != null && list.size() > 0){
			UserTeams userTeams = list.get(0);
			userTeams.setUserTeamscheck(-1);
			userTeamsDao.updateCheck(userTeams);
			
			LolTeams teams = new LolTeams();
			teams.setTeamsNumber(-1);
			teams.setTeamsId(userTeams.getTeamsId());
			teamsDao.updateTeamsNumber(teams);
			return "成功！";
		}
		return "失败！";
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<UserTeamsPaging> getListF(Integer userId, String areaName, AjaxPagingData pagingData) {
		return userTeamsDao.getListF(userId, areaName, pagingData);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<UserTeamsPaging> getJoinListF(Integer userId, AjaxPagingData pagingData) {
		return userTeamsDao.getJoinListF(userId, pagingData);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<UserTeamsPaging> getListF(Integer userId, Integer userTeamsId,
			AjaxPagingData pagingData) {
		List<UserTeams> list = userTeamsDao.getListF(userTeamsId, userId);
		if(list != null && list.size() > 0){
			return userTeamsDao.getListF(list.get(0).getTeamsId(), pagingData);
		}
		return new ArrayList<UserTeamsPaging>();
	}

}
