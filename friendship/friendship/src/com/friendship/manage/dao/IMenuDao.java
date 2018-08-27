package com.friendship.manage.dao;

import java.util.List;

import com.java.po.Menu;

public interface IMenuDao {
	public List<Menu> homepageData(Integer menuType);
	
	public List<Menu> getMenuList();
	
	public Integer updateMenu(Menu entity);
	
	public void del(Menu entity);
	
	public void save(Menu entity);
}
