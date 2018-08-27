package com.friendship.manage.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.friendship.manage.service.INoticeService;
import com.java.po.UserNotice;
import com.java.util.ActionBase;
import com.java.util.annotation.ManageVerifyAnnotation;
import com.java.util.annotation.UserVerifyAnnotation;
import com.java.util.paging.AjaxPagingData;

/**
 * 通知action
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/notice")
@Scope
public class NoticeControl extends ActionBase{
	private INoticeService noticeService;

	@Resource(name="noticeService")
	public void setNoticeService(INoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	/**
	 * 后台通知信息
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="noticeListB.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void noticeListB(HttpServletResponse response,AjaxPagingData pagingData, String seek)throws Exception{
		if(seek != null && seek.length() > 0){
			pagingData.setFieldsNameS("notice.noticeContent");
			pagingData.setFieldsValueS(seek);
		}
		super.writeStringNotNull(response, noticeService.getNoticeList(pagingData));
	}
	
	/**
	 * 删除通知信息
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="deleteNotice.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void deleteNoticeB(HttpServletResponse response,UserNotice entity)throws Exception{
		noticeService.delete(entity);
		super.writeStringNotNull(response, "成功！");
	}
	
	/**
	 * 添加通知信息
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="addNotice.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void addNotice(HttpServletResponse response, String managesessionkey, UserNotice entity, String userQQ)throws Exception{
		entity.setManageId(getManageId(managesessionkey));
		entity.setNoticeCreateTime(getDate());
		super.writeStringNotNull(response, noticeService.save(entity, userQQ));
	}
	
	/**
	 * 批量添加通知信息
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="addNoticeList.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void addNoticeList(HttpServletResponse response, String managesessionkey, UserNotice entity, String teamsId)throws Exception{
		noticeService.save(teamsId.split(";"), getManageId(managesessionkey), entity.getNoticeContent());
		super.writeStringNotNull(response, "成功！");
	}
	
	/**
	 * 批量添加参加活动通知信息
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="addNoticeJoin.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void addNoticeJoin(HttpServletResponse response, String managesessionkey, UserNotice entity, Integer actCode)throws Exception{
		noticeService.saveJoin(getManageId(managesessionkey), actCode, entity.getNoticeContent());
		super.writeStringNotNull(response, "成功！");
	}
	
	/**
	 * 前台通知信息
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="noticeListF.htm",method=RequestMethod.POST)
	@ResponseBody
	@UserVerifyAnnotation
	public void noticeListF(HttpServletResponse response, String usersessionkey, Integer page, String seek)throws Exception{
		if(page == null || page < 1){
			return;
		}

		AjaxPagingData pagingData = new AjaxPagingData(page, 15, "notice.userId", getUserId(usersessionkey), "notice.noticeCreateTime", "desc");
		
		if(seek != null && seek.length() > 0){
			pagingData.setFieldsNameS("notice.noticeContent");
			pagingData.setFieldsValueS(seek);
		}
		
		super.writeStringNotNull(response, noticeService.getNoticeListF(pagingData));
	}
}
