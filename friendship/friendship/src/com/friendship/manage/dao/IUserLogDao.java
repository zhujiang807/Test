package com.friendship.manage.dao;

import java.util.List;

import com.friendship.manage.extend.UserLogPaging;
import com.java.po.UserLog;
import com.java.util.paging.AjaxPagingData;

public interface IUserLogDao {
	public void save(UserLog entity);
	
	public List<UserLogPaging> getUserLogList(AjaxPagingData pagingData);
	
	public Integer getCount(AjaxPagingData pagingData);
}
