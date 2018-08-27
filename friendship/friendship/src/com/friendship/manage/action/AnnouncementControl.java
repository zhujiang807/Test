package com.friendship.manage.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.friendship.manage.service.IAnnouncementService;
import com.java.po.Announcement;
import com.java.util.ActionBase;
import com.java.util.JSONFormat;
import com.java.util.annotation.ActionVerifyAnnotation;
import com.java.util.annotation.ManageVerifyAnnotation;
import com.java.util.paging.AjaxPagingData;

@Controller
@RequestMapping(value="/announcement")
@Scope
public class AnnouncementControl extends ActionBase{
	private IAnnouncementService announcementService;
	private JSONFormat format;
	@Resource(name="announcementService")
	public void setAnnouncementService(IAnnouncementService announcementService) {
		this.announcementService = announcementService;
	}
	public JSONFormat getFormat() {
		return format;
	}
	public void setFormat(JSONFormat format) {
		this.format = format;
	}

	@RequestMapping(value="getAnnList.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void getAnnList(HttpServletResponse response, AjaxPagingData pagingData)throws Exception{
		format = new JSONFormat();
		format.setData(announcementService.getList(pagingData));
		super.writeString(response, format);
	} 
	
	@RequestMapping(value="deleteAnn.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void deleteAnn(HttpServletResponse response, Announcement entity)throws Exception{
		try {
			announcementService.del(entity);
		} catch (Exception e) {
			super.writeString(response, "id错误");
		}
		super.writeString(response, "删除成功！");
	} 
	
	@RequestMapping(value="updateAnn.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void updateAnn(HttpServletResponse response, Announcement entity)throws Exception{
		try {
			announcementService.update(entity);
		} catch (Exception e) {
			super.writeString(response, "修改失败！");
		}
		super.writeString(response, "修改成功！");
	} 
	
	@RequestMapping(value="addAnn.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void addAnn(HttpServletResponse response, Announcement entity)throws Exception{
		try {
			entity.setAnnCreateTime(super.getDate());
			announcementService.save(entity);		} catch (Exception e) {
			super.writeString(response, "添加失败！");
		}
		super.writeString(response, "添加成功！");
	} 
	
	@RequestMapping(value="getAnnouncement.htm",method=RequestMethod.POST)
	@ResponseBody
	public void getAnnouncement(HttpServletResponse response)throws Exception{
		Announcement entity = announcementService.get();
		entity.setAnnId(null);
		super.writeStringNotNull(response, entity);
	}
	
	@RequestMapping(value="annHomepage.htm",method=RequestMethod.POST)
	@ResponseBody
	public void annHomepage(HttpServletResponse response, AjaxPagingData pagingData)throws Exception{
		pagingData.setItem(10);
		pagingData.setPage(1);
		super.writeStringNotNull(response, announcementService.getList(pagingData));
	}
	
	
	
	
	@RequestMapping(value="annPageF.htm",method=RequestMethod.POST)
	public String annPageF(HttpServletResponse response)throws Exception{				
		return "foreground/page/announcement";
	} 
	
	@RequestMapping(value="annListF.htm",method=RequestMethod.POST)
	@ResponseBody
	public void annListF(HttpServletResponse response, Integer page, Integer item)throws Exception{
		AjaxPagingData pagingData = new AjaxPagingData();
		pagingData.setItem(item);
		pagingData.setPage(page);
		super.writeStringNotNull(response, announcementService.getList(pagingData));
	}
}
