package com.friendship.user.dao;

import java.util.List;

import com.java.po.QqUser;
import com.java.util.paging.AjaxPagingData;

public interface IUserDao {
	public List<QqUser> getUserList(AjaxPagingData pagingData);
	
	public Integer getUserCount(AjaxPagingData pagingData);
	
	
	public void userUpdate(QqUser eneity);
	
	
	public QqUser getUser(Integer id);
	
	
	public QqUser getUser(String userQQ);
}
