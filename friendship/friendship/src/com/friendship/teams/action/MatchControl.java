package com.friendship.teams.action;

import java.util.ArrayList;
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
import com.friendship.teams.service.IMatchService;
import com.java.po.ActivitiesMatch;
import com.java.util.ActionBase;
import com.java.util.DateBase;
import com.java.util.annotation.ActionVerifyAnnotation;
import com.java.util.annotation.ManageVerifyAnnotation;
import com.java.util.annotation.UserVerifyAnnotation;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.EntityFields;
/**
 * 比赛
 * @author Administrator
 *
 */

@Controller
@Scope
@RequestMapping(value="/match")
public class MatchControl extends ActionBase{
	private IMatchService matchService;
	@Resource(name="matchService")
	public void setMatchService(IMatchService matchService) {
		this.matchService = matchService;
	}

	/**
	 * @param request
	 * @param manage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getMatchList.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void getMatchList(HttpServletResponse response, AjaxPagingData pagingData)throws Exception{
		if(pagingData.getFieldsString() != null && pagingData.getFieldsString().length() > 0){
			List<EntityFields> fields = new ObjectMapper().readValue(pagingData.getFieldsString(), new TypeReference<List<EntityFields>>() {});
			pagingData.setFields(fields);
		}
		super.writeString(response, matchService.getMatchList(pagingData));
	}

	/**
	 * @param request
	 * @param manage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="updateMatch.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void updateMatch(HttpServletResponse response, ActivitiesMatch entity)throws Exception{
		entity.setMatchBeginTime(DateBase.strToTimestamp(entity.getFieldValue1()));
		entity.setMatchEndTime(DateBase.strToTimestamp(entity.getFieldValue2()));
		super.writeString(response, matchService.update(entity));
	}

	/**
	 * @param request
	 * @param manage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="deleteMatch.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void deleteMatch(HttpServletResponse response, ActivitiesMatch entity)throws Exception{
		 matchService.delete(entity);
		super.writeString(response,"成功！");
	}
	
	/**
	 * @param request
	 * @param manage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="addMatch.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void addMatch(HttpServletResponse response, ActivitiesMatch entity)throws Exception{
		entity.setMatchBeginTime(DateBase.strToTimestamp(entity.getFieldValue1()));
		entity.setMatchEndTime(DateBase.strToTimestamp(entity.getFieldValue2()));
		entity.setMatchCreateTime(super.getDate());
		matchService.save(entity);
		super.writeString(response, "添加成功");
	}
	
	/**
	 * 主页显示比赛信息
	 * @param response
	 * @param entity
	 * @throws Exception
	 */
	@RequestMapping(value="matchHomepage.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	public void matchHomepage(HttpServletResponse response, AjaxPagingData pagingData)throws Exception{
		pagingData.setItem(9);
		pagingData.setPage(1);
		pagingData.setFieldsRank("mat.matchCreateTime");
		pagingData.setFieldsRankType("desc");
		super.writeStringNotNull(response, matchService.matchHomepage(pagingData));
	}
	
	/**
	 * 前台显示比赛信息
	 * @param response
	 * @param entity
	 * @throws Exception
	 */
	@RequestMapping(value="matchListF.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	public void matchListF(HttpServletResponse response, Integer page, String seek)throws Exception{
		AjaxPagingData pagingData = new AjaxPagingData();
		List<EntityFields> list = new ArrayList<EntityFields>();
		
		if(seek != null && seek.length() > 0){
			list.add(new EntityFields("tea1.teamsName", seek));
			list.add(new EntityFields("tea2.teamsName", seek));
			list.add(new EntityFields("act.actName", seek));
		}
		pagingData.setFields(list);
		
		pagingData.setItem(10);
		pagingData.setPage(page == null?1:page);
		pagingData.setFieldsRank("mat.matchCreateTime");
		pagingData.setFieldsRankType("desc");
		
		super.writeStringNotNull(response, matchService.matchHomepage(pagingData));
	}
	
	/**
	 * 前台显示比赛总页数
	 * @param response
	 * @param entity
	 * @throws Exception
	 */
	@RequestMapping(value="matchTotalF.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	public void matchTotalF(HttpServletResponse response, String seek)throws Exception{
		AjaxPagingData pagingData = new AjaxPagingData();
		List<EntityFields> list = new ArrayList<EntityFields>();
		if(seek != null && seek.length() > 0){
			list.add(new EntityFields("tea1.teamsName", seek));
			list.add(new EntityFields("tea2.teamsName", seek));
			list.add(new EntityFields("act.actName", seek));
		}
		pagingData.setFields(list);
		super.writeStringNotNull(response, matchService.getCountF(pagingData));
	}
	
	/**
	 * 队长查看战队比赛记录
	 * @param response
	 * @param entity
	 * @throws Exception
	 */
	@RequestMapping(value="teamsMatchF.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@UserVerifyAnnotation
	public void teamsMatchF(HttpServletResponse response, String usersessionkey, Integer page, Integer item, String areaName)throws Exception{
		if(areaName == null)
			return;
		AjaxPagingData pagingData = new AjaxPagingData();
		pagingData.setPage(page);
		pagingData.setItem(item);
		super.writeStringNotNull(response, matchService.teamsMatchListF(pagingData,getUserId(usersessionkey), areaName));
	}
	
	/**
	 * 队友查看战队比赛记录
	 * @param response
	 * @param entity
	 * @throws Exception
	 */
	@RequestMapping(value="teamsMatchF2.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@UserVerifyAnnotation
	public void teamsMatchF2(HttpServletResponse response, String usersessionkey, Integer page, Integer item, Integer userTeamsId)throws Exception{
		if(userTeamsId == null)
			return;
		AjaxPagingData pagingData = new AjaxPagingData();
		pagingData.setPage(page);
		pagingData.setItem(item);
		super.writeStringNotNull(response, matchService.teamsMatchListF(pagingData,getUserId(usersessionkey), userTeamsId));
	}
}
