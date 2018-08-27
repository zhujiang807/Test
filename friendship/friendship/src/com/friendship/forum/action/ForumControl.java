package com.friendship.forum.action;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.friendship.forum.service.IForumService;
import com.java.po.LolForum;
import com.java.util.ActionBase;
import com.java.util.annotation.ActionVerifyAnnotation;
import com.java.util.annotation.ManageVerifyAnnotation;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.EntityFields;

@Controller
@RequestMapping(value="/forum")
@Scope
public class ForumControl extends ActionBase{
	private IForumService forumService;
	@Resource(name="forumService")
	public void setForumService(IForumService forumService) {
		this.forumService = forumService;
	}

	/**
	 * 
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="getForumList.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void getForumList(HttpServletResponse response, AjaxPagingData pagingData)throws Exception{
		if(pagingData.getFieldsString() != null && pagingData.getFieldsString().length() > 0){
			List<EntityFields> fields = new ObjectMapper().readValue(pagingData.getFieldsString(), new TypeReference<List<EntityFields>>() {});
			pagingData.setFields(fields);
		}
		super.writeString(response, forumService.getForumList(pagingData));
	}
	
	/**
	 * 
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="deleteForum.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void deleteForum(HttpServletResponse response, LolForum entity)throws Exception{
		try {
			forumService.delete(entity);
			super.writeString(response, "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			super.writeString(response, "删除失败！");
		}
	}
	
	/**
	 * 
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="addForum.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void addForum(HttpServletResponse response, LolForum entity)throws Exception{
		try{
			entity.setForumCreateTime(super.getDate());
			forumService.save(entity);
			super.writeString(response, "论坛添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 前台主页论坛信息
	 * @param response
	 * @param entity
	 * @throws Exception
	 */
	@RequestMapping(value="forumHomepage.htm",method=RequestMethod.POST)
	@ResponseBody
	public void forumHomepage(HttpServletResponse response)throws Exception{
		super.writeStringNotNull(response, forumService.forumHomepage());
	}
	
	@RequestMapping(value="forumPageF.htm",method=RequestMethod.POST)
	public String forumPageF(HttpServletResponse response, AjaxPagingData pagingData)throws Exception{
		return "foreground/page/forum";
	}
	
	/**
	 * 前台论坛信息
	 * @param response
	 * @param entity
	 * @throws Exception
	 */
	@RequestMapping(value="forumListF.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	public void forumListF(HttpServletResponse response, Integer item, Integer page)throws Exception{
		if(page == null ||page < 1)
			return;
		super.writeStringNotNull(response, forumService.getForumListF(item, page));
	}
}
