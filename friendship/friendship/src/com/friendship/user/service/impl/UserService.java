package com.friendship.user.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.friendship.user.dao.IUserDao;
import com.friendship.user.service.IUserService;
import com.java.po.QqUser;
import com.java.util.annotation.UserLogAnnotation;
import com.java.util.aop.UserLogConfiguration;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

@Service("userService")
public class UserService implements IUserService{
	
	private IUserDao userDao;
	
	@Resource(name="userDao")
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	@Transactional(readOnly=true)
	public PagePagingData getUserList(AjaxPagingData pagingData) {
		PagePagingData ppd = new PagePagingData();
		ppd.setData(userDao.getUserList(pagingData));
		ppd.setTotal(userDao.getUserCount(pagingData));
		return ppd;
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "用户修改信息", type = "前", method = UserLogConfiguration.MethodName.USER_USERUPDATE)
	public QqUser userUpdate(QqUser entity) {
		userDao.userUpdate(entity);
		return userDao.getUser(entity.getUserId());
	}

	@Override
	@Transactional(readOnly=true)
	public QqUser loginF(String userQQ) {
		return userDao.getUser(userQQ);
	}

}
