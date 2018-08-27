package com.friendship.manage.service;

import com.java.po.PrivateLetter;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

public interface ILetterService {
	public PagePagingData getLetterList(AjaxPagingData pagingData);
	
	public String save(PrivateLetter entity);
	
	public void updateCheck(PrivateLetter entity);
	
	public void delete(PrivateLetter entity);
}
