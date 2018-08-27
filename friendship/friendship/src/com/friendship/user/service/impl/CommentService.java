package com.friendship.user.service.impl;


import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.friendship.teams.dao.ITeamsDao;
import com.friendship.user.dao.ICommentDao;
import com.friendship.user.service.ICommentService;
import com.java.po.Comment;
import com.java.util.annotation.UserLogAnnotation;
import com.java.util.aop.UserLogConfiguration;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

@Service("commentService")
public class CommentService implements ICommentService{
	private ICommentDao commentDao;
	private ITeamsDao teamsDao;
	@Resource(name="commentDao")
	public void setCommentDao(ICommentDao commentDao) {
		this.commentDao = commentDao;
	}
	@Resource(name="teamsDao")
	public void setTeamsDao(ITeamsDao teamsDao) {
		this.teamsDao = teamsDao;
	}

	@Override
	@Transactional(readOnly=true)
	public PagePagingData getCommentList(AjaxPagingData pagingData) {
		PagePagingData data = new PagePagingData();
		data.setData(commentDao.getCommentList(pagingData));
		data.setTotal(commentDao.getCount(pagingData));
		return data;
	}
	
	@Override
	@Transactional
	@UserLogAnnotation(content = "删除用户评论", type = "后", method = UserLogConfiguration.MethodName.COMMENT_DELETE)
	public void delete(Comment entity) {
		commentDao.del(entity);
	}
	
	@Override
	@Transactional
	@UserLogAnnotation(content = "添加评论", type = "前", method = UserLogConfiguration.MethodName.COMMENT_SAVE)
	public String saveT(Comment entity) {
		if(teamsDao.get(entity.getTeamsId()) == null){
			return "评论失败！";
		}
		commentDao.save(entity);
		return "评论成功";
	}
	
	@Override
	@Transactional
	@UserLogAnnotation(content = "添加评论", type = "前", method = UserLogConfiguration.MethodName.COMMENT_SAVE)
	public String saveV(Comment entity) {
		commentDao.save(entity);
		return "评论成功";
	}
	
	@Override
	@Transactional(readOnly=true)
	public PagePagingData getCommentListTF(AjaxPagingData pagingData) {
		PagePagingData data = new PagePagingData();
		data.setData(commentDao.getCommentListT(pagingData));
		data.setTotal(commentDao.getCount(pagingData));
		return data;
	}
}
