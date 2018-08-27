package com.friendship.manage.dao;

import java.util.List;

import com.java.po.Announcement;
import com.java.util.paging.AjaxPagingData;

public interface IAnnouncementDao {
	public void save(Announcement entity);
	
	public List<Announcement> getList(AjaxPagingData pagingData);
	
	public Integer updateAnnouncement(Announcement entity);
	
	public void del(Announcement entity);
	
	public Integer getCount();
	
	public Announcement get();
}
