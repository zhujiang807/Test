package com.friendship.picture.action;

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
import com.friendship.picture.service.IPictureService;
import com.friendship.picture.service.IUserPictureService;
import com.java.po.QqUser;
import com.java.po.UserPicture;
import com.java.util.ActionBase;
import com.java.util.Constant;
import com.java.util.DateBase;
import com.java.util.annotation.ActionVerifyAnnotation;
import com.java.util.annotation.ManageVerifyAnnotation;
import com.java.util.annotation.UserVerifyAnnotation;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.EntityFields;

@Controller
@RequestMapping(value="/userPicture")
@Scope
public class UserPictureControl extends ActionBase{
	private IUserPictureService userPictureService;
	private IPictureService pictureService;
	private IConfigService configService;
	@Resource(name="userPictureService")
	public void setUserPictureService(IUserPictureService userPictureService) {
		this.userPictureService = userPictureService;
	}
	@Resource(name="configService")
	public void setConfigService(IConfigService configService) {
		this.configService = configService;
	}
	@Resource(name="pictureService")
	public void setPictureService(IPictureService pictureService) {
		this.pictureService = pictureService;
	}
	
	/**
	 * 后台数据
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="getUserPictureList.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void getUserPictureList(HttpServletResponse response,AjaxPagingData pagingData)throws Exception{
		if(pagingData.getFieldsString() != null && pagingData.getFieldsString().length() > 0){
			List<EntityFields> fields = new ObjectMapper().readValue(pagingData.getFieldsString(), new TypeReference<List<EntityFields>>() {});
			pagingData.setFields(fields);
		}
		super.writeString(response, userPictureService.getUserPictureList(pagingData));
	}
	
	@RequestMapping(value="deleteUserPicture.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void deleteUserPicture(HttpServletResponse response, UserPicture entity)throws Exception{
		try {
			userPictureService.delete(entity);
			super.writeString(response, "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="addPictureF.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@UserVerifyAnnotation
	public void addPictureF(HttpServletResponse response, String usersessionkey, UserPicture entity, @RequestParam MultipartFile fileA)throws Exception{
		QqUser user = getUser(usersessionkey).getUser();
		UserPicture picture = userPictureService.getF(user.getUserId(), DateBase.getDateYMD());
		if(picture != null){
			super.writeString(response, "你今天已经上传一张了,明天在来吧！");
			return ;
		}
		String newPath = null;
		if(fileA.getSize() > 1500000){
			super.writeString(response, "图片小于1500Kb！");
			return ;
		}
		try {
			if(!fileA.isEmpty()){
				String[] path = configService.getConfig(Constant.USER_PICTURE_PATH).getConfigValue().split(";");
				newPath = path[0]+user.getUserId()+"/"+fileA.getOriginalFilename();
				super.fileUploading(fileA, newPath);
				entity.setPictureUrl(path[1]+newPath);
			}
			entity.setPictureCreateTime(DateBase.getDateYMD());
			entity.setUserId(user.getUserId());
			entity.setPictureName(fileA.getOriginalFilename().substring(0, fileA.getOriginalFilename().indexOf(".")));
			userPictureService.save(entity);
			super.writeString(response, "上传成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 前台数据
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="pictureHomepage.htm",method=RequestMethod.POST)
	@ResponseBody
	public void pictureHomepage(HttpServletResponse response)throws Exception{
		super.writeStringNotNull(response, userPictureService.getHomepage(1,8));
	}
	
	/**
	 * 前台数据
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="userPictureListF.htm",method=RequestMethod.POST)
	@ResponseBody
	public void userPictureListF(HttpServletResponse response, String pictureType, Integer page, String seek)throws Exception{
		if(page == null)
			return;
		AjaxPagingData pagingData = new AjaxPagingData();
		pagingData.setItem(15);
		pagingData.setPage(page);
		
		if(pictureType != null && pictureType.equals("3")){
			if(pictureType != null){
				pagingData.setFieldsNameS("pt.pictureTypeName");
				pagingData.setFieldsValueS("推荐");
			}
			pagingData.setFieldsRank("p.pictureCreateTime");
			pagingData.setFieldsRankType("desc");
			
			super.writeStringNotNull(response, pictureService.pictureListF(pagingData));
			return;
		}
		
		if(seek != null && seek.length() > 0){
			pagingData.setFieldsNameS("user.userName");
			pagingData.setFieldsValueS(seek);
		}
		if(pictureType != null && (pictureType.equals("1") || pictureType.equals("2"))){
			pagingData.setFieldsName("picture.pictureType");
			pagingData.setFieldsValue(pictureType);
		}

		pagingData.setFieldsRank("picture.pictureCreateTime");
		pagingData.setFieldsRankType("desc");
		
		super.writeStringNotNull(response, userPictureService.pictureListF(pagingData));
	}
}
