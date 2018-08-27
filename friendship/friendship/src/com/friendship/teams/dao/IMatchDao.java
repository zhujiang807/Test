package com.friendship.teams.dao;

import java.util.List;
import com.friendship.teams.extend.MatchPaging;
import com.java.po.ActivitiesMatch;
import com.java.util.paging.AjaxPagingData;

public interface IMatchDao {
	public void saveList(List<ActivitiesMatch> list);
	
	public List<MatchPaging> getMatchList(AjaxPagingData pagingData);
	
	public Integer updateMatch(ActivitiesMatch entity);
	
	public Integer getCount(AjaxPagingData pagingData);
	
	public void del(ActivitiesMatch entity);
	
	public void save(ActivitiesMatch entity);

	
	
	public List<MatchPaging> matchHomepageF(AjaxPagingData pagingData);
	
	public Integer getCountF(AjaxPagingData pagingData);

}
