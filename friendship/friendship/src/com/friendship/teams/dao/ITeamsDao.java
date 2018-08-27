package com.friendship.teams.dao;


import java.util.List;
import com.friendship.teams.extend.TeamsPaging;
import com.java.po.LolTeams;
import com.java.util.paging.AjaxPagingData;

public interface ITeamsDao {
	public List<TeamsPaging> getTeamsList(AjaxPagingData pagingData);
	
	public Integer getTeamssCount(AjaxPagingData pagingData);
	
	public void deleteTeams(LolTeams entity);
	
	public void delete(LolTeams entity);
	
	public LolTeams get(Integer teamsId);
	
	public Integer updateTeamsIntegral(LolTeams entity);
	
	public Integer updateTeamsNumber(LolTeams entity);
	
	public void save(LolTeams entity);

	/**
	 * 前台主页信息
	 * @param pagingData
	 * @return
	 */
	public List<TeamsPaging> getHomepage(AjaxPagingData pagingData);
	
	/**
	 * 前台战队信息
	 * @param pagingData
	 * @return
	 */
	public List<TeamsPaging> getTeamsListF(AjaxPagingData pagingData);
	
	public LolTeams getF(Integer userId, String areaName);
	
	public Integer getF(String teamsName, Integer areaId);
	
	public LolTeams getF(Integer userId, Integer areaId);
	
	public Integer update(Integer teamsId, String teamsName, String teamsIcon, String teamsRemarks);
}
