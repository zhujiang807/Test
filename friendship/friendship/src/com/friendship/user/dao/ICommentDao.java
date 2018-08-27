package com.friendship.user.dao;

import java.util.List;
import com.friendship.user.extend.CommentPaging;
import com.java.po.Comment;
import com.java.util.paging.AjaxPagingData;

public interface ICommentDao {
	public void del(Comment entity);
	
	public List<CommentPaging> getCommentList(AjaxPagingData pagingData);
	
	public Integer getCount(AjaxPagingData pagingData);
	
	public void save(Comment entity);

	
	/**
	 * 查看战队评论
	 * @param pagingData
	 * @return
	 */
	public List<CommentPaging> getCommentListT(AjaxPagingData pagingData);

}
