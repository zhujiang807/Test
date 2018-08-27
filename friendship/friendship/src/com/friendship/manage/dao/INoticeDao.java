package com.friendship.manage.dao;

import java.util.List;

import com.friendship.manage.extend.NoticePaging;
import com.java.po.UserNotice;
import com.java.util.paging.AjaxPagingData;

public interface INoticeDao {
	public List<NoticePaging> getNoticeList(AjaxPagingData pagingData);
	
	public Integer getCount(AjaxPagingData pagingData);
	
	public void save(List<UserNotice> entity);
	
	public void save(UserNotice entity);

	public void del(UserNotice entity);
	
	
	
	public List<NoticePaging> getNoticeListF(AjaxPagingData pagingData);
}
