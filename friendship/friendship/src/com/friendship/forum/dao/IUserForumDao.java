package com.friendship.forum.dao;

import java.util.List;
import com.friendship.forum.extend.UserForumPaging;
import com.java.po.UserForum;
import com.java.util.paging.AjaxPagingData;

public interface IUserForumDao {
	public List<UserForumPaging> getUserForumList(AjaxPagingData pagingData);
	
	public void save(UserForum entity);
	
	public void del(UserForum entity);
	
	public Integer getCount(AjaxPagingData pagingData);
	
	
	
	public List<UserForumPaging> getUserForumInfoF(AjaxPagingData pagingData);
}
