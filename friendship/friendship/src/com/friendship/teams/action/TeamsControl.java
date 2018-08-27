package com.friendship.teams.action;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.friendship.manage.service.IConfigService;
import com.friendship.teams.extend.TeamsPaging;
import com.friendship.teams.service.IAreaService;
import com.friendship.teams.service.ITeamsService;
import com.java.po.LolArea;
import com.java.po.LolTeams;
import com.java.po.QqUser;
import com.java.util.ActionBase;
import com.java.util.Constant;
import com.java.util.JSONFormat;
import com.java.util.annotation.ActionVerifyAnnotation;
import com.java.util.annotation.ManageVerifyAnnotation;
import com.java.util.annotation.UserVerifyAnnotation;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.EntityFields;

/**
 * 团队action
 * @author Administrator
 *
 */

@Controller
@RequestMapping(value="/teams")
@Scope
public class TeamsControl extends ActionBase{
	private ITeamsService teamsService;
	private IAreaService areaService;
	private JSONFormat format;
	private IConfigService configService;

	@Resource(name="teamsService")
	public void setTeamsService(ITeamsService teamsService) {
		this.teamsService = teamsService;
	}
	@Resource(name="areaService")
	public void setAreaService(IAreaService areaService) {
		this.areaService = areaService;
	}
	@Resource(name="configService")
	public void setConfigService(IConfigService configService) {
		this.configService = configService;
	}

	public JSONFormat getFormat() {
		return format;
	}
	/**
	 * 后台显示战队信息列表
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="getTeamsList.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void getTeamsList(HttpServletResponse response,AjaxPagingData pagingData)throws Exception{
		format = new JSONFormat();
		if(pagingData.getFieldsString() != null && pagingData.getFieldsString().length() > 0){
			List<EntityFields> fields = new ObjectMapper().readValue(pagingData.getFieldsString(), new TypeReference<List<EntityFields>>() {});
			pagingData.setFields(fields);
		}
		format.setData(teamsService.getTeamsList(pagingData));
		super.writeString(response, format);
	}

	/**
	 * 删除战队信息
	 * @param response
	 * @param pagingData
	 */
	@RequestMapping(value="deleteTeams.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void deleteTeams(HttpServletResponse response,LolTeams teams){
		try {
			teamsService.delete(teams);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeString(response, "删除失败");
		}
		super.writeString(response, "删除成功！");
	}

	/**
	 * 前台主页数据
	 * @param response
	 * @param pagingData
	 */
	@RequestMapping(value="teamsHomepage.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	public void teamsHomepage(HttpServletResponse response){
		AjaxPagingData pagingData = new AjaxPagingData();
		pagingData.setItem(6);
		pagingData.setPage(1);
		pagingData.setFieldsRank("t.teamsCreateTime");
		pagingData.setFieldsRankType("desc");
		super.writeStringNotNull(response, teamsService.getHomepage(pagingData));
	}

	/**
	 * 前台显示战队信息列表
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="teamsListF.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	public void teamsListF(HttpServletResponse response,Integer page, Integer item, String areaName, String seek)throws Exception{
		AjaxPagingData pagingData = new AjaxPagingData();
		List<EntityFields> list = new ArrayList<EntityFields>();
		if(areaName != null && areaName.length() > 0){
			pagingData.setFieldsNameS("a.areaName");
			pagingData.setFieldsValueS(areaName);
		}
		if(seek != null && seek.length() > 0){
			list.add(new EntityFields("t.teamsName", seek));
			list.add(new EntityFields("u.userName", seek));
		}
		pagingData.setFields(list);
		if(item == null) pagingData.setItem(20);
		else pagingData.setItem(item);
		pagingData.setPage(page);
		pagingData.setFieldsRank("t.teamsCreateTime");
		pagingData.setFieldsRankType("desc");

		super.writeStringNotNull(response, teamsService.getTeamsListF(pagingData));
	}

	/**
	 * 加入战队
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="joinTeamsF.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@UserVerifyAnnotation
	public void joinTeamsF(HttpServletResponse response, String usersessionkey, LolTeams entity)throws Exception{
		
		super.writeStringNotNull(response, teamsService.joinTeamsF(entity, getUser(usersessionkey).getUser()));
	}

	/**
	 * 前台显示用户自己战队信息列表
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="userTeamsListF.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@UserVerifyAnnotation
	public void userTeamsListF(HttpServletResponse response, String usersessionkey)throws Exception{
		QqUser user = getUser(usersessionkey).getUser();
		AjaxPagingData pagingData = new AjaxPagingData();
		format = new JSONFormat();

		pagingData.setItem(50);
		pagingData.setPage(1);
		pagingData.setFieldsName("u.userId");
		pagingData.setFieldsValue(user.getUserId());
		pagingData.setFieldsRank("t.teamsNumber");
		pagingData.setFieldsRankType("desc");

		format.setData(teamsService.getUserTeamsListF(pagingData));
		super.writeStringNotNull(response, format);
	}

	/**
	 * 用户自己战队信息页面
	 * @return
	 */
	@RequestMapping(value="addTeamsPageF.htm",method=RequestMethod.POST)
	@UserVerifyAnnotation
	public String addTeamsPageF(){
		return "foreground/page/addTeams";
	}

	@RequestMapping(value="addTeams.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@UserVerifyAnnotation
	public void addTeams(HttpServletResponse response, String usersessionkey, TeamsPaging entity, @RequestParam MultipartFile fileA)throws Exception{
		LolArea area = verifyTeams(response, entity);
		if(area == null)
			return;

		if(fileA.getSize() > 100000){
			super.writeString(response, "图片太大，图片小于100kb！");
			return;
		}

		QqUser user = getUser(usersessionkey).getUser();
		LolTeams teams = new LolTeams();
		teams.setAreaId(area.getAreaId());
		teams.setTeamsName(entity.getTeamsName());
		teams.setTeamsRemarks(entity.getTeamsRemarks());
		teams.setUserId(user.getUserId());
		teams.setTeamsCreateTime(super.getDate());
		teams.setTeamsNumber(1);
		teams.setTeamsIntegral(0);

		if(!fileA.isEmpty()){
			String[] path = configService.getConfig(Constant.TEAMS_PICTURE_PATH).getConfigValue().split(";");
			String filePath = path[0]+teams.getAreaId()+"\\"+user.getUserQQ().substring(0,3);
			super.fileUploading(fileA, filePath);
			teams.setTeamsIcon(path[1]+filePath);
		}
		super.writeString(response, teamsService.saveF(teams));
	}


	/**
	 * 解散战队
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="delTeamsF.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@UserVerifyAnnotation
	public void delTeamsF(HttpServletResponse response, String usersessionkey, String areaName)throws Exception{
		super.writeString(response, teamsService.deleteF(getUserId(usersessionkey), areaName));
	}

	@RequestMapping(value="updateTeamsF.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@UserVerifyAnnotation
	public void updateTeamsF(HttpServletResponse response, String usersessionkey, TeamsPaging entity, @RequestParam MultipartFile fileA)throws Exception{

		LolArea area = verifyTeams(response, entity);
		if(area == null)
			return;
		
		QqUser user = getUser(usersessionkey).getUser();
		LolTeams teams = new LolTeams();
		teams.setAreaId(area.getAreaId());
		teams.setTeamsName(entity.getTeamsName());
		teams.setTeamsRemarks(entity.getTeamsRemarks());
		teams.setUserId(user.getUserId());

		if(fileA != null){
			if(fileA.getSize() > 100000){
				super.writeString(response, "图片太大，图片小于200kb！");
				return;
			}
			if(!fileA.isEmpty()){
				String[] path = configService.getConfig(Constant.TEAMS_PICTURE_PATH).getConfigValue().split(";");
				String filePath = path[0]+teams.getAreaId()+"/"+user.getUserQQ().substring(0,3)+"."+fileA.getContentType().split("/")[1];
				super.fileUploading(fileA, filePath+"");
				teams.setTeamsIcon(path[1]+filePath);
			}
		}

		super.writeString(response, teamsService.updateF(teams));
	}

	@RequestMapping(value="updateTeamsF2.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@UserVerifyAnnotation
	public void updateTeamsF2(HttpServletResponse response, String usersessionkey, TeamsPaging entity)throws Exception{

		LolArea area = verifyTeams(response, entity);
		if(area == null)
			return;

		QqUser user = getUser(usersessionkey).getUser();
		LolTeams teams = new LolTeams();
		teams.setAreaId(area.getAreaId());
		teams.setTeamsName(entity.getTeamsName());
		teams.setTeamsRemarks(entity.getTeamsRemarks());
		teams.setUserId(user.getUserId());

		super.writeString(response, teamsService.updateF(teams));
	}
	
	private LolArea verifyTeams(HttpServletResponse response, TeamsPaging entity)throws Exception{
		LolArea area = null;
		if(entity.getAreaName() != null && entity.getAreaName().length() > 0){
			area = areaService.get(entity.getAreaName());
			if(area == null || area.getAreaId() < 0){
				super.writeString(response, "请不要搞破坏！");
				return null;
			}
		}else{
			super.writeString(response, "没有大区名称！");
			return null;
		}

		if(entity.getTeamsName() == null || entity.getTeamsName().length() < 1 || entity.getTeamsRemarks() == null || entity.getTeamsRemarks().length() < 1){
			super.writeString(response, "请不要搞破坏！");
			return null;
		}

		return area;
	}
	
}
