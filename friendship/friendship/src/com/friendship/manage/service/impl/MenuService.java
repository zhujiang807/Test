package com.friendship.manage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.friendship.manage.dao.IMenuDao;
import com.friendship.manage.service.IMenuService;
import com.java.po.Menu;
import com.java.util.annotation.UserLogAnnotation;
import com.java.util.aop.UserLogConfiguration;

@Service("menuService")
public class MenuService implements IMenuService{

	private IMenuDao menuDao;
	@Resource(name="menuDao")
	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "管理员登录", type = "后", method = UserLogConfiguration.MethodName.MANAGE_LOGIN)
	public List<Object> homepageDataB() {
		List<Menu> menuList = menuDao.homepageData(0);
		List<Menu> menus = new ArrayList<Menu>();
		List<Object> menuData = new ArrayList<Object>();
		Map<String, Object> menusMap;
		if(menuList == null || menuList.size() == 0) return null;
		for (Menu menu : menuList) {
			if(menu.getFatherMenuId().equals(0)){
				menusMap = new HashMap<String, Object>();
				menus.add(menu);
				menusMap.put("menuid", menu.getMenuId());
				menusMap.put("icon", "icon-sys");
				menusMap.put("menuname", menu.getMenuName());
				
				List<Object> menuSonData = new ArrayList<Object>();
				Map<String, Object> menuMap;
				for (Menu men : menuList) {
					if(men.getFatherMenuId().equals(menu.getMenuId())){
						menuMap = new HashMap<String, Object>();
						menuMap.put("menuid", men.getMenuId());
						menuMap.put("icon", "icon-sys");
						menuMap.put("menuname", men.getMenuName());
						menuMap.put("url", men.getMenuUrl());
						menuSonData.add(menuMap);
					}
				}
				menusMap.put("menus", menuSonData);
				menuData.add(menusMap);
			}
		}
		return menuData;
	}

	@Override
	@Transactional(readOnly=true)
	public Map<String, Object> getMenuList() {
		List<Menu> menuList = menuDao.getMenuList();
		List<Menu> menus = null ;
		List<Object> menuData = new ArrayList<Object>();
		Map<String, Object> menusMap = null;
		Map<String, Object> headMenu = new HashMap<String, Object>();
		if(menuList == null || menuList.size() == 0) return null;
		for (Menu menu : menuList) {
			if(menu.getFatherMenuId().equals(0) && menu.getMenuType().equals(0)){
				menusMap = new HashMap<String, Object>();
				menusMap.put("menu", menu);
				
				menus = new ArrayList<Menu>();
				for (Menu m : menuList) {
					if(m.getFatherMenuId().equals(menu.getMenuId()) && m.getMenuType().equals(0)){
						menus.add(m);
					}
				}
				menusMap.put("subsetMenu", menus);
				menuData.add(menusMap);
			}
		}
		headMenu.put("bMenu", menuData);
		
		menuData = new ArrayList<Object>();
		for (Menu menu : menuList) {
			if(menu.getFatherMenuId().equals(0) && menu.getMenuType().equals(1)){
				menusMap = new HashMap<String, Object>();
				menusMap.put("menu", menu);
				
				menus = new ArrayList<Menu>();
				for (Menu m : menuList) {
					if(m.getFatherMenuId().equals(menu.getMenuId()) && menu.getMenuType().equals(1)){
						menus.add(m);
					}
				}
				menusMap.put("subsetMenu", menus);
				menuData.add(menusMap);
			}
		}
		headMenu.put("fMenu", menuData);
		
		return headMenu;	
	}

	@Override
	@Transactional
	public Integer update(Menu entity) {
		return menuDao.updateMenu(entity);
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "删除一个菜单", type = "后", method = UserLogConfiguration.MethodName.MENU_DELETE)
	public void delete(Menu entity) {
		menuDao.del(entity);
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "添加一个菜单", type = "后", method = UserLogConfiguration.MethodName.MENU_SAVE)
	public void add(Menu entity) {
		menuDao.save(entity);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Map<String, Object>> homepageDataF() {
		List<Menu> menuList = menuDao.homepageData(1);
		List<Map<String, Object>> menus = new ArrayList<Map<String, Object>>() ;
		List<Menu> subsetMenu;
		Map<String, Object> menuMap;
		if(menuList == null || menuList.size() == 0) return null;
		for (Menu menu : menuList) {
			if(menu.getFatherMenuId().equals(0)){
				menuMap = new HashMap<String, Object>();
				menuMap.put("menuName", menu.getMenuName());
				menuMap.put("menuUrl", menu.getMenuUrl());
				subsetMenu = new ArrayList<Menu>();
				for (Menu m : menuList) {
					if(m.getFatherMenuId().equals(menu.getMenuId())){
						m.setMenuId(null);
						m.setMenuShow(null);
						m.setMenuType(null);
						m.setMenuCreateTime(null);
						m.setMenuNumber(null);
						m.setMenuShow(null);
						subsetMenu.add(m);
					}
				}
				menuMap.put("subsetMenu", subsetMenu);
				menus.add(menuMap);
			}
		}
		return menus;
	}

}
