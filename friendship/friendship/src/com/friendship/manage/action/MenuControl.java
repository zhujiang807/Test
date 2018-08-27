package com.friendship.manage.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.friendship.manage.service.IMenuService;
import com.java.po.Menu;
import com.java.util.ActionBase;
import com.java.util.annotation.ActionVerifyAnnotation;
import com.java.util.annotation.ManageVerifyAnnotation;

@Controller
@Scope
@RequestMapping(value="/menu")
public class MenuControl extends ActionBase{
	private IMenuService menuService;

	@Resource(name="menuService")
	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	/**
	 * 后台主页提供数据
	 * @throws Exception
	 */
	@RequestMapping(value="frameDate.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void frameDate(HttpSession session,HttpServletResponse response)throws Exception{
		List<Object> menuData = menuService.homepageDataB();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("menus", menuData);
		writeString(response, data);
	}

	/**
	 * 后台主页提供数据
	 * @throws Exception
	 */
	@RequestMapping(value="getMenuList.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void getMenuList(HttpServletResponse response)throws Exception{
		writeString(response, menuService.getMenuList());
	}

	/**
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value="updateMenu.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void updateMenu(HttpServletResponse response, Menu entity)throws Exception{
		try {
			menuService.update(entity);	
		} catch (Exception e) {
			e.printStackTrace();
			writeString(response, "修改失败！");
		}
		writeString(response, "修改成功");	
	}

	/**
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value="addMenu.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void addMenu(HttpServletResponse response, Menu entity)throws Exception{
		try {
			entity.setMenuCreateTime(super.getDate());
			menuService.add(entity);		
		} catch (Exception e) {
			e.printStackTrace();
			writeString(response, "添加失败！");
		}
		writeString(response, "添加成功");		
	}

	/**
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value="deleteMenu.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void deleteMenu(HttpServletResponse response, Menu entity)throws Exception{
		try {
			menuService.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
			writeString(response, "删除失败！");
		}
		writeString(response, "删除成功");
	}
	
	/**
	 * 前台主页数据
	 * @param session
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="homepageMenu.htm",method=RequestMethod.POST)
	@ResponseBody
	public void homepageMenu(HttpServletResponse response)throws Exception{
		writeStringNotNull(response, menuService.homepageDataF());
	}
}
