package com.friendship.user.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.friendship.user.dao.IUserDao;
import com.java.po.QqUser;
import com.java.util.HibernateDaoBase;
import com.java.util.paging.AjaxPagingData;

@Repository
public class UserDao extends HibernateDaoBase<QqUser> implements IUserDao{

	public List<QqUser> getUserList(AjaxPagingData pagingData) {
		StringBuffer hql = new StringBuffer("from QqUser ");
		return super.pagingHql(getPagingSql(hql, pagingData), pagingData.getPage(), pagingData.getItem());
	}

	@Override
	public Integer getUserCount(AjaxPagingData pagingData) {
		StringBuffer hql = new StringBuffer("select count(*) from QqUser ");
		return super.getCountHql(getPagingSql(hql, pagingData));
	}

	@Override
	public void userUpdate(QqUser eneity) {
		super.update(eneity);
	}

	@Override
	public QqUser getUser(Integer id) {
		return super.get("from QqUser where userId=?", id);
	}
	
	public QqUser getUser(String userQQ){
		return super.get("from QqUser where userQQ=?", userQQ);
	}
}
