package com.friendship.user.service;


import com.java.po.QqUser;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;


public interface IUserService {
	/**
	 * 显示user信息
	 * @param pagingData
	 * @return
	 */
	public PagePagingData getUserList(AjaxPagingData pagingData);
	/**
	 * 修改用户信息
	 * @param eneity
	 * @return
	 */
	public QqUser userUpdate(QqUser entity);
	
	public QqUser loginF(String userQQ);
}
