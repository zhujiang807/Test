package com.friendship.user.service;


import com.friendship.user.extend.JoinPaging;
import com.java.po.UserActivities;
import com.java.po.UserJoin;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

public interface IJoinService {
	public PagePagingData getJoinList(AjaxPagingData pagingData);
	
	public void delete(UserJoin entity);
	
	public String save(Integer actCode, String actName, Integer userId);
	
	/**
	 * 批量安排比赛
	 * @param entity
	 * @return
	 */
	public String arrangeMatch(UserActivities entity);
	
	/**
	 * 修改user_join中joinResult结果，并插入teams_ranking结果表
	 * @param entity
	 */
	public void updateJoin(JoinPaging entity);
	
	
	/**
	 * 战队参加活动历史
	 * @param pagingData
	 * @return
	 */
	public PagePagingData joinListF(AjaxPagingData pagingData);
	
	/**
	 * 战队参加活动历史
	 * @param pagingData
	 * @return
	 */
	public PagePagingData joinListF(AjaxPagingData pagingData, Integer userTeamsId, Integer userId);
}
