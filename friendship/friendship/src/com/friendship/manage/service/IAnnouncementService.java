package com.friendship.manage.service;

import com.java.po.Announcement;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

public interface IAnnouncementService {
	public void save(Announcement entity);
	
	public PagePagingData getList(AjaxPagingData pagingData);
	
	public Integer update(Announcement entity);
	
	public void del(Announcement entity);
	
	public Announcement get();
}
