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
import com.java.po.Picture;
import com.java.util.ActionBase;
import com.java.util.Constant;
import com.java.util.annotation.ActionVerifyAnnotation;
import com.java.util.annotation.ManageVerifyAnnotation;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.EntityFields;

@Controller
@RequestMapping(value="/picture")
@Scope
public class PictureControl extends ActionBase{
	private IPictureService pictureService;
	private IConfigService configService;
	@Resource(name="pictureService")
	public void setPictureService(IPictureService pictureService) {
		this.pictureService = pictureService;
	}
	@Resource(name="configService")
	public void setConfigService(IConfigService configService) {
		this.configService = configService;
	}

	/**
	 * 
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="getPictureList.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void getPictureList(HttpServletResponse response, AjaxPagingData pagingData)throws Exception{
		if(pagingData.getFieldsString() != null && pagingData.getFieldsString().length() > 0){
			List<EntityFields> fields = new ObjectMapper().readValue(pagingData.getFieldsString(), new TypeReference<List<EntityFields>>() {});
			pagingData.setFields(fields);
		}
		super.writeString(response, pictureService.getPictureList(pagingData));
	}
	
	@RequestMapping(value="deletePicture.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void deletePicture(HttpServletResponse response, Picture entity)throws Exception{
		try {
			pictureService.delete(entity);
			super.writeString(response, "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 图片上传
	 * @param response
	 * @param entity
	 * @param fileA
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="addPicture.htm",method=RequestMethod.POST)
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void addPicture(HttpServletResponse response,Picture entity, @RequestParam MultipartFile fileA)throws Exception{
		String newPath = null;
		try {
			if(!fileA.isEmpty()){
				String[] path = configService.getConfig(Constant.PICTUREPATH).getConfigValue().split(";");
				newPath = path[0]+entity.getPictureTypeId()+"/"+fileA.getOriginalFilename();
				super.fileUploading(fileA, newPath);
				entity.setPictureUrl(path[1]+newPath);
			}
			entity.setPictureCreateTime(super.getDate());
			pictureService.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeString(response, "上传成功！");
	}
	
	/**
	 * 页面推荐图片
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="getPictureList.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	public void pictureListF(HttpServletResponse response, String pictureType, Integer page)throws Exception{
		AjaxPagingData pagingData = new AjaxPagingData();
		if(pictureType != null){
			pagingData.setFieldsName("p.pictureTypeId");
			pagingData.setFieldsValue(pictureType);
		}
		
		pagingData.setItem(15);
		pagingData.setPage(page);
		pagingData.setFieldsRank("p.pictureCreateTime");
		pagingData.setFieldsRankType("desc");
		super.writeStringNotNull(response, pictureService.pictureListF(pagingData));
	}
}
