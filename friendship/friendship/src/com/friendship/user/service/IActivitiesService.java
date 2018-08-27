package com.friendship.user.service;


import java.util.List;

import com.java.po.UserActivities;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

public interface IActivitiesService {
	public PagePagingData getActivitiesList(AjaxPagingData pagingData);
	
	public void save(UserActivities entity);
	
	public void delete(UserActivities entity);
	
	public Integer update(UserActivities entity);
	
	
	public List<UserActivities> actHomepage();
	
	public PagePagingData getActivitiesListF(AjaxPagingData pagingData);
	
}
