package com.friendship.manage.service;

import com.java.po.Advertisement;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

public interface IAdvertisementService {
	public PagePagingData getAdvList(AjaxPagingData pagingData);
	
	public void save(Advertisement entity);
	
	public void delete(Advertisement entity);
	
	public Advertisement getF(String advTitle);
}
