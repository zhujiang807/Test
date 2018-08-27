package com.friendship.manage.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.friendship.manage.dao.IUserLogDao;
import com.friendship.manage.service.IUserLogService;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

@Service("userLogService")
public class UserLogService implements IUserLogService{
	private IUserLogDao userLogDao;
	@Resource(name="userLogDao")
	public void setUserLogDao(IUserLogDao userLogDao) {
		this.userLogDao = userLogDao;
	}

	@Override
	public PagePagingData getUserLogList(AjaxPagingData pagingData) {
		PagePagingData data = new PagePagingData();
		data.setData(userLogDao.getUserLogList(pagingData));
		data.setTotal(userLogDao.getCount(pagingData));
		return data;
	}

}
