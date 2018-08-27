package com.friendship.teams.action;

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
import com.friendship.teams.extend.UserTeamsPaging;
import com.friendship.teams.service.IUserTeamsService;
import com.java.po.QqUser;
import com.java.po.UserTeams;
import com.java.util.ActionBase;
import com.java.util.annotation.ActionVerifyAnnotation;
import com.java.util.annotation.ManageVerifyAnnotation;
import com.java.util.annotation.UserVerifyAnnotation;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.EntityFields;

@Controller
@RequestMapping(value="/userTeams")
@Scope
public class UserTeamsControl extends ActionBase{
	private IUserTeamsService userTeamsService;
	@Resource(name="userTeamsService")
	public void userTeamsService(IUserTeamsService userTeamsService) {
		this.userTeamsService = userTeamsService;
	}
	
	/**
	 * 
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="getUserTeamsList.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void getUserTeamsList(HttpServletResponse response, AjaxPagingData pagingData)throws Exception{
		if(pagingData.getFieldsString() != null && pagingData.getFieldsString().length() > 0){
			List<EntityFields> fields = new ObjectMapper().readValue(pagingData.getFieldsString(), new TypeReference<List<EntityFields>>() {});
			pagingData.setFields(fields);
		}
		super.writeString(response, userTeamsService.getUserTeamsList(pagingData));
	}
	
	/**
	 * 
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="deleteUserTeams.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void deleteUserTeams(HttpServletResponse response, UserTeams entity)throws Exception{
		try {
			userTeamsService.delete(entity);
			super.writeString(response, "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 队长查看战队成员
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="joinUserF.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@UserVerifyAnnotation
	public void joinUserF(HttpServletResponse response, String usersessionkey, String areaName)throws Exception{
		QqUser user = getUser(usersessionkey).getUser();
		AjaxPagingData pagingData = new AjaxPagingData();
		pagingData.setPage(1);
		pagingData.setItem(1000);
		pagingData.setFieldsName2("ut.userTeamscheck");
		pagingData.setFieldsValue2("1");
		
		super.writeStringNotNull(response, userTeamsService.getListF(user.getUserId(), areaName, pagingData));
	}
	
	/**
	 * 队员查看战队成员
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="joinUserF2.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@UserVerifyAnnotation
	public void joinUserF2(HttpServletResponse response, String usersessionkey, UserTeams entity)throws Exception{
		QqUser user = getUser(usersessionkey).getUser();
		AjaxPagingData pagingData = new AjaxPagingData();
		pagingData.setPage(1);
		pagingData.setItem(1000);
		pagingData.setFieldsName2("ut.userTeamscheck");
		pagingData.setFieldsValue2("1");
		
		super.writeStringNotNull(response, userTeamsService.getListF(user.getUserId(), entity.getUserTeamsId(), pagingData));
	}
	
	/**
	 * 审核成员
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="checkUserF.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@UserVerifyAnnotation
	public void checkUserF(HttpServletResponse response, String usersessionkey, String areaName)throws Exception{
		QqUser user = getUser(usersessionkey).getUser();
		AjaxPagingData pagingData = new AjaxPagingData();
		pagingData.setPage(1);
		pagingData.setItem(1000);
		pagingData.setFieldsName2("ut.userTeamscheck");
		pagingData.setFieldsValue2("0");
		
		super.writeStringNotNull(response, userTeamsService.getListF(user.getUserId(), areaName, pagingData));
	}
	
	/**
	 * 审核结果
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="checkF.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@UserVerifyAnnotation
	public void checkF(HttpServletResponse response, String usersessionkey, UserTeamsPaging paging, String type)throws Exception{
		if(paging.getUserQQ().equals(getUser(usersessionkey).getUser().getUserQQ())){
			super.writeStringNotNull(response, "自己不可以退出战队！");
			return;
		}
		if(paging.getUserTeamscheck() == 0)
			paging.setUserTeamscheck(-1);
		userTeamsService.updateCheckF(paging, type);
		super.writeStringNotNull(response, "成功！");
	}
	
	/**
	 * 自己退出战队
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="userCheckF.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@UserVerifyAnnotation
	public void userCheckF(HttpServletResponse response, String usersessionkey, UserTeams userTeams)throws Exception{
		if(userTeams.getUserTeamsId() == null){
			super.writeStringNotNull(response, "失败！");
			return ;
		}
		super.writeStringNotNull(response, userTeamsService.updateCheckF(userTeams.getUserTeamsId(), getUserId(usersessionkey)));
	}
	
	/**
	 * 参加的战队页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="joinTeamsPageF.htm",method=RequestMethod.POST)
	@UserVerifyAnnotation
	public String joinTeamsPageF()throws Exception{
		return "foreground/page/joinTeams";
	}
	
	/**
	 * 加入战队的信息
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="joinTeamsListF.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@UserVerifyAnnotation
	public void joinTeamsListF(HttpServletResponse response, String usersessionkey)throws Exception{
		QqUser user = getUser(usersessionkey).getUser();
		AjaxPagingData pagingData = new AjaxPagingData();
		pagingData.setPage(1);
		pagingData.setItem(50);
		pagingData.setFieldsName2("ut.userTeamscheck");
		pagingData.setFieldsValue2("1");
		
		super.writeStringNotNull(response, userTeamsService.getJoinListF(user.getUserId(), pagingData));
	}
	
	/**
	 * 审核中的战队信息
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="teamsListF.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@UserVerifyAnnotation
	public void teamsListF(HttpServletResponse response, String usersessionkey)throws Exception{
		QqUser user = getUser(usersessionkey).getUser();
		AjaxPagingData pagingData = new AjaxPagingData();
		pagingData.setPage(1);
		pagingData.setItem(50);
		pagingData.setFieldsName2("ut.userTeamscheck");
		pagingData.setFieldsValue2("0");
		
		super.writeStringNotNull(response, userTeamsService.getJoinListF(user.getUserId(), pagingData));
	}
}
