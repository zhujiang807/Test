package com.friendship.forum.action;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.friendship.forum.service.IUserForumService;
import com.java.po.UserForum;
import com.java.util.ActionBase;
import com.java.util.JSONFormat;
import com.java.util.annotation.ActionVerifyAnnotation;
import com.java.util.annotation.ManageVerifyAnnotation;
import com.java.util.annotation.UserVerifyAnnotation;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.EntityFields;

@Controller
@RequestMapping(value="/userForum")
@Scope
public class UserForumControl extends ActionBase{
	private IUserForumService userForumService;
	private JSONFormat format;
	@Resource(name="userForumService")
	public void setUserForumService(IUserForumService userForumService) {
		this.userForumService = userForumService;
	}
	public JSONFormat getFormat() {
		return format;
	}
	public void setFormat(JSONFormat format) {
		this.format = format;
	}

	/**
	 * 
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="getUserForumList.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void getUserForumList(HttpServletResponse response, AjaxPagingData pagingData)throws Exception{
		if(pagingData.getFieldsString() != null && pagingData.getFieldsString().length() > 0){
			List<EntityFields> fields = new ObjectMapper().readValue(pagingData.getFieldsString(), new TypeReference<List<EntityFields>>() {});
			pagingData.setFields(fields);
		}
		super.writeString(response, userForumService.getUserForumList(pagingData));
	}
	
	/**
	 * 
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="deleteUserForum.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void deleteUserForum(HttpServletResponse response, UserForum entity)throws Exception{
		try {
			userForumService.delete(entity);
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
	@RequestMapping(value="addUserForumF.htm",method=RequestMethod.POST)
	@ResponseBody
	@UserVerifyAnnotation
	public void addUserForumF(HttpServletResponse response, String usersessionkey, UserForum entity, String forumTitle)throws Exception{
		entity.setUserId(getUserId(usersessionkey));
		entity.setCreateTime(super.getDate());
		super.writeString(response, userForumService.saveF(entity, forumTitle));
	
	}
	
	/**
	 * 前台主页论坛信息
	 * @param response
	 * @param entity
	 * @throws Exception
	 */
	@RequestMapping(value="userForumInfoF.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	public void userForumInfoF(HttpServletResponse response, HttpSession session, AjaxPagingData pagingData, String forumTitle)throws Exception{
		pagingData.setFieldsNameS("forum.forumTitle");
		pagingData.setFieldsValueS(forumTitle);
		pagingData.setFieldsRank("uf.createTime");
		pagingData.setFieldsRankType("desc");
		super.writeStringNotNull(response, userForumService.getUserForumInfoF(pagingData, forumTitle));
	}
}
