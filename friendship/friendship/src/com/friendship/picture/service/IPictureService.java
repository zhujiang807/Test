package com.friendship.picture.service;

import com.java.po.Picture;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

public interface IPictureService {
	public PagePagingData getPictureList(AjaxPagingData pagingData);
	
	public void save(Picture entity);
	
	public void delete(Picture entity);
	
	
	public PagePagingData pictureListF(AjaxPagingData pagingData);
}
