package com.friendship.manage.service;

import com.java.po.UserNotice;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

public interface INoticeService {
	public PagePagingData getNoticeList(AjaxPagingData pagingData);
	
	public void delete(UserNotice entity);
	
	public String save(UserNotice entity, String userQQ);
	
	public void save(String[] teamsId, Integer manId, String noticeContent);
	
	/**
	 * 通知报名活动的用户
	 * @param manId
	 */
	public void saveJoin(Integer manId, Integer actCode, String noticeContent);


	public PagePagingData getNoticeListF(AjaxPagingData pagingData);
}
