package com.friendship.video.action;

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
import com.friendship.video.service.IVideoService;
import com.java.po.LolVideo;
import com.java.util.ActionBase;
import com.java.util.Constant;
import com.java.util.annotation.ActionVerifyAnnotation;
import com.java.util.annotation.ManageVerifyAnnotation;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.EntityFields;


@Controller
@RequestMapping(value="/video")
@Scope
public class VideoControl extends ActionBase{
	private IVideoService videoService;
	private IConfigService configService;
	@Resource(name="videoService")
	public void setVideoService(IVideoService videoService) {
		this.videoService = videoService;
	}
	@Resource(name="configService")
	public void setConfigService(IConfigService configService) {
		this.configService = configService;
	}

	/**
	 * 视频类型
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="getVideoTypeList.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void getVideoTypeList(HttpServletResponse response)throws Exception{
		super.writeString(response, videoService.getVideoTypeList());
	}
	
	/**
	 * 
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="getVideoList.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void getVideoList(HttpServletResponse response, AjaxPagingData pagingData)throws Exception{
		if(pagingData.getFieldsString() != null && pagingData.getFieldsString().length() > 0){
			List<EntityFields> fields = new ObjectMapper().readValue(pagingData.getFieldsString(), new TypeReference<List<EntityFields>>() {});
			pagingData.setFields(fields);
		}
		super.writeString(response, videoService.getVideoList(pagingData));
	}
	
	/**
	 * 主页视频
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="videoHomepage.htm",method=RequestMethod.POST)
	@ResponseBody
	public void videoHomepage(HttpServletResponse response, AjaxPagingData pagingData)throws Exception{
		super.writeStringNotNull(response, videoService.getHomepage());
	}
	
	/**
	 * 
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="videoListF.htm",method=RequestMethod.POST)
	@ResponseBody
	public void videoListF(HttpServletResponse response, String videoTypeName, Integer page, Integer item)throws Exception{
		AjaxPagingData pagingData = new AjaxPagingData();
		if(videoTypeName != null && videoTypeName.length() > 0){
			pagingData.setFieldsNameS("vt.videoTypeName");
			pagingData.setFieldsValueS(videoTypeName);
		}else{
			super.writeStringNotNull(response, new ArrayList<String>());
			return;
		}
		pagingData.setPage(page);
		pagingData.setItem(item);
		pagingData.setFieldsRank("video.videoCreateTime");
		pagingData.setFieldsRank("video.videoNumber");
		pagingData.setFieldsRankType("desc");
		pagingData.setFieldsRankType("desc");
		super.writeStringNotNull(response, videoService.videoListF(pagingData));
	}
	
	/**
	 * 播放视频
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="videoInfoF.htm",method=RequestMethod.POST)
	@ResponseBody
	public void videoInfoF(HttpServletResponse response, String videoUrl)throws Exception{
		if(videoUrl == null){
			return;
		}

		videoUrl = videoUrl.replaceAll("\\\\", "%\\\\\\\\\\\\%");
		super.writeStringNotNull(response, videoService.getF(videoUrl));
	}
	
	/**
	 * 
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="addVideo.htm",method=RequestMethod.POST)
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void addVideo(HttpServletResponse response,LolVideo entity, @RequestParam MultipartFile fileA, @RequestParam MultipartFile fileB)throws Exception{
		String newPath = null;
		try {
			if(!fileA.isEmpty()){
				String[] path = configService.getConfig(Constant.LOL_VIDEO_PATH).getConfigValue().split(";");
				newPath = path[0]+entity.getVideoTypeId()+"/"+entity.getMatchId()==null?"":(entity.getMatchId()+"/")+fileA.getOriginalFilename();
				//视频
				super.fileUploading(fileA, newPath);
				entity.setVideoUrl(path[1]+newPath);
				//视频图片
				newPath = path[0]+entity.getVideoTypeId()+"/"+entity.getMatchId()==null?"":(entity.getMatchId()+"/")+fileB.getOriginalFilename();
				super.fileUploading(fileB, newPath);
				entity.setVideoPicture(path[1]+newPath);
			}
			entity.setVideoCreateTime(super.getDate());
			if(entity.getMatchId() == null) entity.setMatchId(0);
			entity.setVideoNumber(0);
			videoService.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeString(response, "添加视频成功！");
	}
	
	@RequestMapping(value="deleteVideo.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void deleteVideo(HttpServletResponse response, LolVideo entity)throws Exception{
		try {
			videoService.delete(entity);
			super.writeString(response, "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
