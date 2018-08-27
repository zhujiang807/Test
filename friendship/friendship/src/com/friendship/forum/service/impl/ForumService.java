package com.friendship.forum.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.friendship.forum.dao.IForumDao;
import com.friendship.forum.service.IForumService;
import com.java.po.LolForum;
import com.java.util.annotation.UserLogAnnotation;
import com.java.util.aop.UserLogConfiguration;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

@Service("forumService")
public class ForumService implements IForumService{
	private IForumDao forumDao;
	@Resource(name="forumDao")
	public void setForumDao(IForumDao forumDao) {
		this.forumDao = forumDao;
	}
	@Override
	@Transactional(readOnly=true)
	public PagePagingData getForumList(AjaxPagingData pagingData) {
		PagePagingData data = new PagePagingData();
		data.setData(forumDao.getForumList(pagingData));
		data.setTotal(forumDao.getCount(pagingData));
		return data;
	}
	@Override
	@Transactional
	@UserLogAnnotation(content = "添加论坛", type = "后", method = UserLogConfiguration.MethodName.FORUM_SAVE)
	public void save(LolForum entity) {
		forumDao.save(entity);
	}
	@Override
	@Transactional
	@UserLogAnnotation(content = "删除论坛", type = "前", method = UserLogConfiguration.MethodName.FORUM_DELETE)
	public void delete(LolForum entity) {
		forumDao.del(entity);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<LolForum> forumHomepage() {
		return forumDao.forumHomepage();
	}
	
	@Override
	@Transactional(readOnly=true)
	public PagePagingData getForumListF(Integer item, Integer page) {
		PagePagingData data = new PagePagingData();
		data.setData(forumDao.getForumListF(item, page));
		data.setTotal(forumDao.getCountF());
		return data;
	}
	
}
