package com.friendship.forum.service;

import java.util.List;

import com.java.po.LolForum;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

public interface IForumService {
	public PagePagingData getForumList(AjaxPagingData pagingData);
	
	public void save(LolForum entity);
	
	public void delete(LolForum entity);
	
	/**
	 * 前台主页论坛信息
	 */
	public List<LolForum> forumHomepage();
	
	/**
	 * 论坛信息
	 */
	public PagePagingData getForumListF(Integer item, Integer page);
}
