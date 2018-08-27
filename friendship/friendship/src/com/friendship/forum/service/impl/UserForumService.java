package com.friendship.forum.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.friendship.forum.dao.IForumDao;
import com.friendship.forum.dao.IUserForumDao;
import com.friendship.forum.service.IUserForumService;
import com.java.po.LolForum;
import com.java.po.UserForum;
import com.java.util.annotation.UserLogAnnotation;
import com.java.util.aop.UserLogConfiguration;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

@Service("userForumService")
public class UserForumService implements IUserForumService{
	private IUserForumDao userForumDao;
	private IForumDao forumDao;
	@Resource(name="userForumDao")
	public void setUserForumDao(IUserForumDao userForumDao) {
		this.userForumDao = userForumDao;
	}
	@Resource(name="forumDao")
	public void setForumDao(IForumDao forumDao) {
		this.forumDao = forumDao;
	}

	@Override
	@Transactional(readOnly=true)
	public PagePagingData getUserForumList(AjaxPagingData pagingData) {
		PagePagingData data = new PagePagingData();
		data.setData(userForumDao.getUserForumList(pagingData));
		data.setTotal(userForumDao.getCount(pagingData));
		return data;
	}
	
	@Override
	@Transactional
	public String saveF(UserForum entity, String forumTitle) {
		LolForum forum = forumDao.getF(forumTitle);
		if(forum == null){
			return "";
		}
		entity.setForumId(forum.getForumId());
		userForumDao.save(entity);
		return "评论成功";
	}
	
	@Override
	@Transactional
	@UserLogAnnotation(content = "删除用户评论论坛信息", type = "后", method = UserLogConfiguration.MethodName.USERFORUM_DELETE)
	public void delete(UserForum entity) {
		userForumDao.del(entity);
	}

	@Override
	@Transactional
	public PagePagingData getUserForumInfoF(AjaxPagingData pagingData,String forumTitle) {
		PagePagingData data = new PagePagingData();
		data.setTotal(userForumDao.getCount(pagingData));
		data.setData(userForumDao.getUserForumInfoF(pagingData));
		return data;
	}
	
}
