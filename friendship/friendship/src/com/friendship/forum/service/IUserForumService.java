package com.friendship.forum.service;

import com.java.po.UserForum;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

public interface IUserForumService {
	public PagePagingData getUserForumList(AjaxPagingData pagingData);
	
	public void delete(UserForum entity);
	
	
	public PagePagingData getUserForumInfoF(AjaxPagingData pagingData, String forumTitle);
	
	public String saveF(UserForum entity, String forumTitle);

}
