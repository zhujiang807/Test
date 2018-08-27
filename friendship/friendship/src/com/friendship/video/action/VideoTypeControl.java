package com.friendship.video.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.friendship.video.service.IVideoTypeService;
import com.java.po.VideoType;
import com.java.util.ActionBase;
import com.java.util.annotation.ActionVerifyAnnotation;
import com.java.util.annotation.ManageVerifyAnnotation;

@Controller
@RequestMapping(value="/videoType")
@Scope
public class VideoTypeControl extends ActionBase{
	private IVideoTypeService videoTypeService;
	@Resource(name="videoTypeService")
	public void setVideoTypeService(IVideoTypeService videoTypeService) {
		this.videoTypeService = videoTypeService;
	}

	@RequestMapping(value="getVideoTypeList.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void getVideoTypeList(HttpServletResponse response)throws Exception{
		super.writeString(response, videoTypeService.getVideoTypeList());
	}
	
	@RequestMapping(value="deleteVideoType.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void deleteVideoType(HttpServletResponse response, VideoType entity)throws Exception{
		try {
			videoTypeService.delete(entity);
			super.writeString(response, "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="addVideoType.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void addVideoType(HttpServletResponse response, VideoType entity)throws Exception{
		try {
			entity.setVideoTypeTime(super.getDate());
			videoTypeService.save(entity);
			super.writeString(response, "添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="videoTypeListF.htm",method=RequestMethod.POST)
	@ResponseBody
	public void videoTypeListF(HttpServletResponse response)throws Exception{
		super.writeStringNotNull(response, videoTypeService.videoTypeListF());
	}
}
