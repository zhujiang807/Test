package com.friendship.user.action;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.friendship.user.extend.JoinPaging;
import com.friendship.user.service.IJoinService;
import com.java.po.UserActivities;
import com.java.util.ActionBase;
import com.java.util.JSONFormat;
import com.java.util.annotation.ActionVerifyAnnotation;
import com.java.util.annotation.ManageVerifyAnnotation;
import com.java.util.annotation.UserVerifyAnnotation;
import com.java.util.paging.AjaxPagingData;

@Controller
@RequestMapping(value="/join")
@Scope
public class JoinControl extends ActionBase{
	private IJoinService joinService;
	private JSONFormat fromat;
	@Resource(name="joinService")
	public void setJoinService(IJoinService joinService) {
		this.joinService = joinService;
	}
	public JSONFormat getFromat() {
		return fromat;
	}
	public void setFromat(JSONFormat fromat) {
		this.fromat = fromat;
	}
	/**
	 * 后台显示参加活动表
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="getJoinList.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void getJoinList(HttpServletResponse response,AjaxPagingData pagingData)throws Exception{
		super.writeString(response, joinService.getJoinList(pagingData));
	}

	/**
	 * 
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="addJoin.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@UserVerifyAnnotation
	public void addJoin(HttpServletResponse response, String usersessionkey, String actCode, String actName)throws Exception{
		if(actCode == null || actName == null){
			super.writeString(response, "参加失败!");
			return;
		}
		
		Pattern pattern = Pattern.compile("[0-9]*"); 
		Matcher isNum = pattern.matcher(actCode);
		if(!isNum.matches() ){
			super.writeString(response, "参加失败!");
			return;
		}
		
		super.writeString(response, joinService.save(Integer.parseInt(actCode), actName, getUserId(usersessionkey)));
	}

	/**
	 * 批量比赛
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="arrangeMatch.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void arrangeMatch(HttpServletResponse response, UserActivities entity)throws Exception{
		entity.setActCreateTime(super.getDate());
		try {
			super.writeString(response, joinService.arrangeMatch(entity));
		} catch (Exception e) {
			super.writeString(response,"已经安排比赛!");
		}
	}

	/**
	 * 修改
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="updateJoin.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void updateJoin(HttpServletResponse response, JoinPaging entity)throws Exception{

		try {
			entity.setJoinCreateTime(super.getDate());
			joinService.updateJoin(entity);		
		} catch (Exception e) {
			super.writeString(response,"修改失败!");
		}
		super.writeString(response,"修改成功!");
	}
	
	/**
	 * 队长查看自己战队活动记录
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="joinListF.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@UserVerifyAnnotation
	public void joinListF(HttpServletResponse response, String usersessionkey,Integer page, Integer item, String areaName)throws Exception{
		AjaxPagingData pagingData = new AjaxPagingData();
		pagingData.setPage(page);
		pagingData.setItem(item);
		pagingData.setFieldsName(" u.userId");
		pagingData.setFieldsValue(getUserId(usersessionkey));
		pagingData.setFieldsNameS(" a.areaName");
		pagingData.setFieldsValueS(areaName);
		super.writeStringNotNull(response, joinService.joinListF(pagingData));
	}
	
	/**
	 * 队员查看活动记录
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="joinListF2.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@UserVerifyAnnotation
	public void joinListF2(HttpServletResponse response, String usersessionkey, Integer page, Integer item, Integer userTeamsId)throws Exception{
		if(userTeamsId == null)
			return;
		AjaxPagingData pagingData = new AjaxPagingData();
		pagingData.setPage(page);
		pagingData.setItem(item);
		super.writeStringNotNull(response, joinService.joinListF(pagingData, userTeamsId, getUserId(usersessionkey)));
	}
}
