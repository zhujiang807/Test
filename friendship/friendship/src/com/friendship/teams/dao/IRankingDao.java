package com.friendship.teams.dao;

import java.util.List;
import com.friendship.teams.extend.RankingPaging;
import com.java.po.TeamsRanking;
import com.java.util.paging.AjaxPagingData;

public interface IRankingDao {
	public void saveOrUpdate(TeamsRanking entity);
	
	public TeamsRanking get(Integer activitiesId, Integer teamsId);
	
	public Integer updateRanking(TeamsRanking entity);
	
	public void update(TeamsRanking entity);

	public List<RankingPaging> getRankingList(AjaxPagingData pagingData);
	
	public void del(TeamsRanking entity);
	
	public Integer getCount(AjaxPagingData pagingData);
	
	
	
	public List<RankingPaging> getRankingListF(AjaxPagingData pagingData);

}
