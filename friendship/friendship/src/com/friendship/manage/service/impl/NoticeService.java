package com.friendship.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.friendship.manage.dao.INoticeDao;
import com.friendship.manage.service.INoticeService;
import com.friendship.teams.dao.ITeamsDao;
import com.friendship.user.dao.IJoinDao;
import com.friendship.user.dao.IUserDao;
import com.java.po.QqUser;
import com.java.po.UserJoin;
import com.java.po.UserNotice;
import com.java.util.DateBase;
import com.java.util.annotation.UserLogAnnotation;
import com.java.util.aop.UserLogConfiguration;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

@Service("noticeService")
public class NoticeService implements INoticeService{
	private INoticeDao noticeDao;
	private IUserDao userDao;
	private IJoinDao joinDao;
	private ITeamsDao teamsDao;
	@Resource(name="noticeDao")
	public void setNoticeDao(INoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}
	@Resource(name="userDao")
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	@Resource(name="joinDao")
	public void setJoinDao(IJoinDao joinDao) {
		this.joinDao = joinDao;
	}
	@Resource(name="teamsDao")
	public void setTeamsDao(ITeamsDao teamsDao) {
		this.teamsDao = teamsDao;
	}
	
	@Override
	@Transactional(readOnly=true)
	public PagePagingData getNoticeList(AjaxPagingData pagingData) {
		PagePagingData data = new PagePagingData();
		data.setData(noticeDao.getNoticeList(pagingData));
		data.setTotal(noticeDao.getCount(pagingData));
		return data;
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "删除通知", type = "后", method = UserLogConfiguration.MethodName.NOTICE_DELETE)
	public void delete(UserNotice entity) {
		noticeDao.del(entity);
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "添加单个通知", type = "后", method = UserLogConfiguration.MethodName.NOTICE_SAVE)
	public String save(UserNotice entity, String userQQ) {
		QqUser user = userDao.getUser(userQQ);
		if(user == null){
			return "qq号错误！";
		}
		entity.setUserId(user.getUserId());
		noticeDao.save(entity);
		return "成功！";
	}

	@Override
	@Transactional(readOnly=true)
	public PagePagingData getNoticeListF(AjaxPagingData pagingData) {
		PagePagingData data = new PagePagingData();
		data.setData(noticeDao.getNoticeListF(pagingData));
		data.setTotal(noticeDao.getCount(pagingData));
		return data;
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "添加参加活动通知", type = "后", method = UserLogConfiguration.MethodName.NOTICE_SAVEJOIN)
	public void saveJoin(Integer manId, Integer actCode, String noticeContent) {
		List<UserJoin> list = joinDao.getJoinList(actCode);
		List<UserNotice> noticeList = new ArrayList<UserNotice>();
		if(list != null && list.size() > 0){
			for (UserJoin join : list) {
				UserNotice entity = new UserNotice();
				entity.setManageId(manId);
				entity.setNoticeCreateTime(DateBase.getTimestampYMDHMS());
				entity.setUserId(join.getUserId());
				entity.setNoticeContent(noticeContent);
				noticeList.add(entity);
			}
			noticeDao.save(noticeList);
		}
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "添加比赛活动通知", type = "后", method = UserLogConfiguration.MethodName.NULL)
	public void save(String[] teamsId, Integer manId, String noticeContent) {
		List<UserNotice> noticeList = new ArrayList<UserNotice>();
		for (String qq : teamsId) {
			UserNotice entity = new UserNotice();
			entity.setManageId(manId);
			entity.setNoticeCreateTime(DateBase.getTimestampYMDHMS());
			entity.setUserId(teamsDao.get(Integer.parseInt(qq)).getUserId());
			entity.setNoticeContent(noticeContent);
			noticeList.add(entity);
		}
		
		noticeDao.save(noticeList);
	}

}
