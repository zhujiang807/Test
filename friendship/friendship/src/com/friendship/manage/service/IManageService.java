package com.friendship.manage.service;

import java.util.List;

import com.java.po.Manage;
import com.java.util.JSONFormat;

/**
 * 管理员service接口
 * @author Administrator
 *
 */
public interface IManageService {
	/**
	 * 判定用户是否有权限登录后台
	 * @param qq
	 * @return
	 */
	public Boolean loginVerify(String qq);
	
	/**
	 * 管理员登录
	 * @return
	 */
	public JSONFormat login(Manage entity);
	
	public Integer updateManage(String newPassword, Integer manageId);
	
	public void save(Manage entity);

	public void delete(Manage entity);
	
	public List<Manage> getManageList();

}
