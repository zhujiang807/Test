package com.friendship.teams.service;


import java.util.List;

import com.friendship.teams.extend.MatchPaging;
import com.java.po.ActivitiesMatch;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

public interface IMatchService {
	public PagePagingData getMatchList(AjaxPagingData pagingData);
	
	public Integer update(ActivitiesMatch entity);
	
	public void delete(ActivitiesMatch entity);
	
	public void save(ActivitiesMatch entity);
	
	
	
	public List<MatchPaging> matchHomepage(AjaxPagingData pagingData);
	
	/**
	 * 战队比赛记录表
	 * @param pagingData
	 * @return
	 */
	public PagePagingData teamsMatchListF(AjaxPagingData pagingData, Integer userId, String areaName);
	
	/**
	 * 战队比赛记录表
	 * @param pagingData
	 * @return
	 */
	public PagePagingData teamsMatchListF(AjaxPagingData pagingData, Integer userId, Integer userTeamsId);
	
	public Integer getCountF(AjaxPagingData pagingData);
}
