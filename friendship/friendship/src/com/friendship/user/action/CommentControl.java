package com.friendship.user.action;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.friendship.user.service.ICommentService;
import com.friendship.video.service.IVideoService;
import com.java.po.Comment;
import com.java.util.ActionBase;
import com.java.util.annotation.ActionVerifyAnnotation;
import com.java.util.annotation.ManageVerifyAnnotation;
import com.java.util.annotation.UserVerifyAnnotation;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.EntityFields;

@Controller
@Scope
@RequestMapping(value="/comment")
public class CommentControl extends ActionBase{
	private ICommentService commentService;
	private IVideoService videoService;
	@Resource(name="commentService")
	public void setCommentService(ICommentService commentService) {
		this.commentService = commentService;
	}
	@Resource(name="videoService")
	public void setVideoService(IVideoService videoService) {
		this.videoService = videoService;
	}

	@RequestMapping(value="getCommentList.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void getCommentList(HttpServletResponse response, AjaxPagingData pagingData)throws Exception{
		if(pagingData.getFieldsString() != null && pagingData.getFieldsString().length() > 0){
			List<EntityFields> fields = new ObjectMapper().readValue(pagingData.getFieldsString(), new TypeReference<List<EntityFields>>() {});
			pagingData.setFields(fields);
		}
		super.writeString(response, commentService.getCommentList(pagingData));
	}
	
	@RequestMapping(value="deleteComment.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void deleteComment(HttpServletResponse response, HttpServletRequest request, Comment entity)throws Exception{
		try {
			commentService.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeString(response, "id错误");
		}
		super.writeString(response, "删除成功！");
	}
	
	@RequestMapping(value="addCommentVF.htm",method=RequestMethod.POST)
	@ResponseBody
	@UserVerifyAnnotation
	public void addCommentVF(HttpServletResponse response, String usersessionkey, String videoUrl, String content)throws Exception{
		if(videoUrl == null || content == null){
			super.writeStringNotNull(response, "评论失败！");
			return;
		}
		
		videoUrl = videoUrl.replaceAll("\\\\", "%\\\\\\\\\\\\%");
		Integer id = videoService.get(videoUrl);
		
		if(id == null){
			super.writeStringNotNull(response, "评论失败！");
			return;
		}
		
		Comment entity = new Comment(getUserId(usersessionkey), 0, id, 0, content, getDate());
		super.writeStringNotNull(response, commentService.saveV(entity));
	}
	
	@RequestMapping(value="addCommentTF.htm",method=RequestMethod.POST)
	@ResponseBody
	@UserVerifyAnnotation
	public void addCommentTF(HttpServletResponse response, String usersessionkey, Integer teamsId, String content)throws Exception{
		if(teamsId == null || content == null){
			super.writeStringNotNull(response, "评论失败！");
			return;
		}
		Comment entity = new Comment(getUserId(usersessionkey), teamsId, 0, 0, content, getDate());
		super.writeStringNotNull(response, commentService.saveT(entity));
	}
	
	@RequestMapping(value="commentListTF.htm",method=RequestMethod.POST)
	@ResponseBody
	public void commentListTF(HttpServletResponse response, Integer teamsId, Integer page)throws Exception{
		if(teamsId == null){
			return;
		}
		
		AjaxPagingData pagingData = new AjaxPagingData();
		pagingData.setPage(page);
		pagingData.setItem(15);
		pagingData.setFieldsRank("comm.comCreateTime");
		pagingData.setFieldsRankType("desc");
		pagingData.setFieldsName("comm.teamsId");
		pagingData.setFieldsValue(teamsId);
		super.writeStringNotNull(response, commentService.getCommentListTF(pagingData));
	}
	
	@RequestMapping(value="commentListVF.htm",method=RequestMethod.POST)
	@ResponseBody
	public void commentListVF(HttpServletResponse response, String videoUrl, Integer page)throws Exception{
		if(videoUrl == null){
			return;
		}
		videoUrl = videoUrl.replaceAll("\\\\", "%\\\\\\\\\\\\%");
		AjaxPagingData pagingData = new AjaxPagingData();
		pagingData.setPage(page);
		pagingData.setItem(15);
		pagingData.setFieldsRank("comm.comCreateTime");
		pagingData.setFieldsRankType("desc");
		pagingData.setFieldsNameS("video.videoUrl");
		pagingData.setFieldsValueS(videoUrl);
		super.writeStringNotNull(response, commentService.getCommentListTF(pagingData));
	}
}
