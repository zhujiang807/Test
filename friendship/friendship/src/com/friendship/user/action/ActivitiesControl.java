package com.friendship.user.action;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
import com.friendship.user.service.IActivitiesService;
import com.java.po.UserActivities;
import com.java.util.ActionBase;
import com.java.util.Constant;
import com.java.util.DateBase;
import com.java.util.JSONFormat;
import com.java.util.annotation.ActionVerifyAnnotation;
import com.java.util.annotation.ManageVerifyAnnotation;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.EntityFields;
/**
 * 活动action
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/activities")
@Scope
public class ActivitiesControl extends ActionBase{
	private IActivitiesService activitiesService;
	private IConfigService configService;
	private JSONFormat format;
	@Resource(name="activitiesService")
	public void setActivitiesService(IActivitiesService activitiesService) {
		this.activitiesService = activitiesService;
	}
	@Resource(name="configService")
	public void setConfigService(IConfigService configService) {
		this.configService = configService;
	}


	/**
	 * 后台显示战队信息列表
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="getActivitiesList.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void getActivitiesList(HttpServletResponse response,AjaxPagingData pagingData)throws Exception{
		format = new JSONFormat();
		if(pagingData.getFieldsString() != null && pagingData.getFieldsString().length() > 0){
			List<EntityFields> fields = new ObjectMapper().readValue(pagingData.getFieldsString(), new TypeReference<List<EntityFields>>() {});
			pagingData.setFields(fields);
		}
		format.setData(activitiesService.getActivitiesList(pagingData));
		super.writeString(response, format);
	}

	/**
	 * 后台显示战队信息列表
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="saveActivities.htm",method=RequestMethod.POST)
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void saveActivities(HttpServletRequest request, HttpServletResponse response,UserActivities activities,@RequestParam MultipartFile fileA, String updateActivities)throws Exception{
		String filePath = null;
		try {
			if(!fileA.isEmpty()){
				String[] path = configService.getConfig(Constant.ACTIVITIES_IMG_PATH).getConfigValue().split(";");
				filePath = path[0]+activities.getActCode()+"."+fileA.getContentType().split("/")[1];
				super.fileUploading(fileA, filePath);
				activities.setActPictureAddress(path[1]+filePath);
			}
			activities.setActBeginTime(DateBase.strToTimestamp(activities.getFieldValue1()));
			activities.setActEndTime(DateBase.strToTimestamp(activities.getFieldValue2()));
			activities.setActCreateTime(super.getDate());
			activities.setActAmount(0);
			activitiesService.save(activities);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeString(response,"添加失败,活动码重复！");
		}
		super.writeString(response,"添加成功！");
	}
	
	/**
	 * 删除
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="deleteActivities.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void deleteActivities(HttpServletResponse response,UserActivities activities)throws Exception{
		try {
			activitiesService.delete(activities);
		} catch (Exception e) {
			super.writeString(response, "id错误");
		}
		super.writeString(response, "删除成功！");
	}
	
	/**
	 * 后台显示战队信息列表
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="updateActivities.htm",method=RequestMethod.POST)
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void updateActivities(HttpServletRequest request, HttpServletResponse response,UserActivities activities,@RequestParam MultipartFile fileA)throws Exception{
		String filePath = null;
		try {
			if(!fileA.isEmpty()){
				String[] path = configService.getConfig(Constant.ACTIVITIES_IMG_PATH).getConfigValue().split(";");
				filePath = path[0]+activities.getActCode()+"."+fileA.getContentType().split("/")[1];
				super.fileUploading(fileA, filePath);
				activities.setActPictureAddress(path[1]+filePath);
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.writeString(response,"添加失败,活动码重复！");
		}
		
		activities.setActBeginTime(DateBase.strToTimestamp(activities.getFieldValue1()));
		activities.setActEndTime(DateBase.strToTimestamp(activities.getFieldValue2()));
		activitiesService.update(activities);
		super.writeString(response,"修改成功！");
	}
	
	/**
	 * 后台显示战队信息列表
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="updateActivities2.htm",method=RequestMethod.POST)
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void updateActivities2(HttpServletRequest request, HttpServletResponse response,UserActivities activities)throws Exception{
		activities.setActBeginTime(DateBase.strToTimestamp(activities.getFieldValue1()));
		activities.setActEndTime(DateBase.strToTimestamp(activities.getFieldValue2()));
		activitiesService.update(activities);
		super.writeString(response,"修改成功！");
	}
	
	@RequestMapping(value="actHomepage.htm",method=RequestMethod.POST)
	@ResponseBody
	public void actHomepage(HttpServletResponse response)throws Exception{
		super.writeStringNotNull(response, activitiesService.actHomepage());
	}
	
	/**
	 * 后台活动页面
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="actPageF.htm",method=RequestMethod.POST)
	public String actPageF()throws Exception{
		return "foreground/page/activities";
	}
	
	/**
	 * 后台显示战队信息列表
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="actListF.htm",method=RequestMethod.POST)
	@ResponseBody
	public void actListF(HttpServletResponse response, Integer page, Integer item)throws Exception{
		AjaxPagingData pagingData = new AjaxPagingData();
		pagingData.setItem(item);
		pagingData.setPage(page);
		pagingData.setFieldsRank("act.actCreateTime");
		pagingData.setFieldsRankType("desc");
		pagingData.setFieldsName("act.actShow");
		pagingData.setFieldsValue("0");
		super.writeStringNotNull(response, activitiesService.getActivitiesListF(pagingData));
	}
	
	/**
	 * 后台显示战队信息列表
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="actInfoF.htm",method=RequestMethod.POST)
	@ResponseBody
	public void actInfoF(HttpServletResponse response, String actCode, String actName)throws Exception{
		if(actCode != null && actName != null){
			Pattern pattern = Pattern.compile("[0-9]*"); 
			Matcher isNum = pattern.matcher(actCode);
			if(!isNum.matches() ){
				super.writeString(response, "服务器错误!");
				return;
			}
			
			AjaxPagingData pagingData = new AjaxPagingData();
			pagingData.setItem(1);
			pagingData.setPage(1);
			pagingData.setFieldsName("act.actCode");
			pagingData.setFieldsValue(actCode);
			pagingData.setFieldsNameS("act.actName");
			pagingData.setFieldsValueS(actName);
			super.writeStringNotNull(response, activitiesService.getActivitiesListF(pagingData));
		}
	}
}
