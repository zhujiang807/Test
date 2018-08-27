package com.friendship.picture.action;

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
import com.friendship.picture.service.IPictureTypeService;
import com.java.po.PictureType;
import com.java.util.ActionBase;
import com.java.util.annotation.ActionVerifyAnnotation;
import com.java.util.annotation.ManageVerifyAnnotation;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.EntityFields;

@Controller
@RequestMapping(value="/pictureType")
@Scope
public class PictureTypeControl extends ActionBase{
	private IPictureTypeService pictureTypeService;
	@Resource(name="pictureTypeService")
	public void setPictureTypeService(IPictureTypeService pictureTypeService) {
		this.pictureTypeService = pictureTypeService;
	}
	
	@RequestMapping(value="getPictureTypeList.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void getPictureTypeList(HttpServletResponse response, AjaxPagingData pagingData)throws Exception{
		if(pagingData.getFieldsString() != null && pagingData.getFieldsString().length() > 0){
			List<EntityFields> fields = new ObjectMapper().readValue(pagingData.getFieldsString(), new TypeReference<List<EntityFields>>() {});
			pagingData.setFields(fields);
		}
		super.writeString(response, pictureTypeService.getList(pagingData));
	}
	
	@RequestMapping(value="deletePictureType.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void deletePictureType(HttpServletResponse response, PictureType entity)throws Exception{
		try {
			pictureTypeService.delete(entity);
			super.writeString(response, "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="addPictureType.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void addPictureType(HttpServletResponse response, PictureType entity)throws Exception{
		try {
			entity.setCreateTime(super.getDate());
			pictureTypeService.save(entity);
			super.writeString(response, "添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
