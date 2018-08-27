package com.friendship.user.service;

import com.java.po.Comment;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

public interface ICommentService {
	public PagePagingData getCommentList(AjaxPagingData pagingData);
	
	public void delete(Comment entity);
	
	public String saveT(Comment entity);
	
	
	
	public PagePagingData getCommentListTF(AjaxPagingData pagingData);

	public String saveV(Comment entity);
}
