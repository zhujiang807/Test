package com.friendship.manage.service;

import java.util.List;
import java.util.Map;

import com.java.po.Menu;


public interface IMenuService {
	/**
	 * 获取后台主页信息 
	 */
	public List<Object> homepageDataB();
	
	public Map<String, Object> getMenuList();
	
	public Integer update(Menu entity);
	
	public void delete(Menu entity);
	
	public void add(Menu entity);
	
	public List<Map<String, Object>> homepageDataF();
}
