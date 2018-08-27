package com.friendship.teams.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.friendship.teams.service.IAreaService;
import com.java.po.LolArea;
import com.java.util.ActionBase;
import com.java.util.JSONFormat;
import com.java.util.annotation.ActionVerifyAnnotation;
import com.java.util.annotation.ManageVerifyAnnotation;

/**
 * lol大区
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/area")
@Scope
public class AreaControl extends ActionBase{
	private JSONFormat format;
	private IAreaService areaService;

	public JSONFormat getFormat() {
		return format;
	}
	@Resource(name="areaService")
	public void setAreaService(IAreaService areaService) {
		this.areaService = areaService;
	}

	@RequestMapping(value="getAreaList.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void getAreaList(HttpServletResponse response)throws Exception{
		format = new JSONFormat();
		format.setData(areaService.getAreaList());
		super.writeString(response, format);
	} 

	@RequestMapping(value="addArea.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void addArea(HttpServletResponse response, LolArea entity)throws Exception{	
		try {
			entity.setAreaCreateTime(super.getDate());
			areaService.save(entity);
		} catch (Exception e) {
			super.writeString(response, "大区名称不能重复！");
		}
		super.writeString(response, "添加成功！");
	} 

	@RequestMapping(value="deleteArea.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void deleteArea(HttpServletResponse response, LolArea entity)throws Exception{		
		try {
			areaService.delete(entity);
		} catch (Exception e) {
			super.writeString(response, "删除失败！");
		}
		super.writeString(response, "删除成功！");
	} 

	@RequestMapping(value="updateArea.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void updateArea(HttpServletResponse response, LolArea entity)throws Exception{		
		try {
			areaService.update(entity);
			super.writeString(response, "修改成功！");
		} catch (Exception e) {
			super.writeString(response, "大区名称不能重复！");
		}
	} 
	
	
	@RequestMapping(value="areaListF.htm",method=RequestMethod.POST)
	@ResponseBody
	public void areaListF(HttpServletResponse response)throws Exception{
		super.writeStringNotNull(response, areaService.getAreaListF());
	}
}
