package com.friendship.user.dao;

import java.util.List;

import com.friendship.user.extend.ActivitiesPaging;
import com.java.po.UserActivities;
import com.java.util.paging.AjaxPagingData;

public interface IActivitiesDao {
	public List<ActivitiesPaging> getActivitiesList(AjaxPagingData pagingData);
	
	public Integer getCount(AjaxPagingData pagingData);
	
	public void save(UserActivities entity);
	
	public void del(UserActivities entity);
	
	public List<UserActivities> getList(Integer actCode);
	
	public UserActivities actIdGet(Integer actId);
	
	public Integer updateAct(UserActivities entity);
	
	public void update(UserActivities entity);

	
	public List<UserActivities> actHomepage();
	
	public List<ActivitiesPaging> getActivitiesListF(AjaxPagingData pagingData);
	
	public UserActivities getF(Integer actCode, String actName);
}
