package com.friendship.teams.service;

import com.java.po.TeamsRanking;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

public interface IRankingService {
	public PagePagingData getRankingList(AjaxPagingData pagingData);
	
	public void delete(TeamsRanking entity);
	
	public Integer update(TeamsRanking entity);
	
	
	/**
	 * 前台战队排名
	 * @param pagingData
	 * @return
	 */
	public PagePagingData getRankingListF(AjaxPagingData pagingData);

}
