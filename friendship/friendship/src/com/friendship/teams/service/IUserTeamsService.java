package com.friendship.teams.service;

import java.util.List;

import com.friendship.teams.extend.UserTeamsPaging;
import com.java.po.UserTeams;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

public interface IUserTeamsService {
	public PagePagingData getUserTeamsList(AjaxPagingData pagingData);
	
	public void delete(UserTeams entity);
	
	public void save(UserTeams entity);
	
	
	
	public List<UserTeamsPaging> getListF(Integer userId, String areaName, AjaxPagingData pagingData);
	
	public List<UserTeamsPaging> getListF(Integer userId, Integer userTeamsId, AjaxPagingData pagingData);

	public List<UserTeamsPaging> getJoinListF(Integer userId, AjaxPagingData pagingData);
	
	public String updateCheckF(Integer userTeamsId, Integer userId);
	
	public void updateCheckF(UserTeamsPaging entity, String type);
}
