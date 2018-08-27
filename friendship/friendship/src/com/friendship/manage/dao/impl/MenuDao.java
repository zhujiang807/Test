package com.friendship.manage.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.friendship.manage.dao.IMenuDao;
import com.java.po.Menu;
import com.java.util.HibernateDaoBase;

@Repository("menuDao")
public class MenuDao extends HibernateDaoBase<Menu> implements IMenuDao{

	@Override
	public List<Menu> homepageData(Integer menuType) {
		return super.find("from Menu where menuType="+menuType+" and menuShow=1 order by menuNumber asc");
	}

	@Override
	public List<Menu> getMenuList() {
		return super.find("from Menu order by menuNumber,menuType asc");
	}

	@Override
	public Integer updateMenu(Menu entity) {
		StringBuffer sql = new StringBuffer("update menu set fatherMenuId=").append(entity.getFatherMenuId());
		sql.append(",menuName='").append(entity.getMenuName()).append("',menuNumber=").append(entity.getMenuNumber());
		sql.append(",menuShow=").append(entity.getMenuShow()).append(",menuShowType=").append(entity.getMenuShowType()).append(",menuType=").append(entity.getMenuType());
		sql.append(",menuUrl='").append(entity.getMenuUrl()).append("' where menuId=").append(entity.getMenuId());
		return super.update(sql.toString());
	}

	@Override
	public void del(Menu entity) {
		super.delete(super.get("from Menu where menuId=?", entity.getMenuId()));
	}
}
