package com.friendship.manage.dao;


import java.util.List;

import com.java.po.Manage;


public interface IManageDao {
	public Manage loginVerify(String qq);

	public Integer updateManage(String newPassword, Integer manageId);
	
	public void save(Manage entity);
	
	public void del(Manage entity);
	
	public List<Manage> getManageList();

}
