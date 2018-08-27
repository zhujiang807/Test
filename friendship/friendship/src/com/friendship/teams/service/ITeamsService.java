package com.friendship.teams.service;


import java.util.List;
import com.friendship.teams.extend.TeamsPaging;
import com.java.po.LolTeams;
import com.java.po.QqUser;
import com.java.util.JSONFormat;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;


public interface ITeamsService {
	/**
	 * 战队信息
	 * @param result
	 * @return
	 */
	public PagePagingData getTeamsList(AjaxPagingData pagingData);
	
	public void delete(LolTeams entity);
	
	
	//前台主页获取信息
	public List<TeamsPaging> getHomepage(AjaxPagingData pagingData);
	
	//战队信息
	public PagePagingData getTeamsListF(AjaxPagingData pagingData);

	//加入战队
	public JSONFormat joinTeamsF(LolTeams entity, QqUser user);
	
	//自己战队信息
	public List<TeamsPaging> getUserTeamsListF(AjaxPagingData pagingData);
	
	//创建战队
	public String saveF(LolTeams entity);
	//删除战队
	public String deleteF(Integer userId, String areaName);
	//修改战队
	public String updateF(LolTeams entity);
}
