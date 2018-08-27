package com.friendship.manage.dao;

import java.util.List;

import com.java.po.Advertisement;
import com.java.util.paging.AjaxPagingData;

public interface IAdvertisementDao {
	public List<Advertisement> getAdvList(AjaxPagingData pagingData);
	
	public void save(Advertisement entity);
	
	public void del(Advertisement entity);
	
	public Integer getCount(AjaxPagingData pagingData);
	
	
	
	public Advertisement getF(String advTitle);
}
