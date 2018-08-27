package com.friendship.picture.dao;

import java.util.List;

import com.java.po.PictureType;
import com.java.util.paging.AjaxPagingData;

public interface IPictureTypeDao {
	public List<PictureType> getList(AjaxPagingData pagingData);
	
	public void save(PictureType entity);
	
	public void del(PictureType entity);
}
