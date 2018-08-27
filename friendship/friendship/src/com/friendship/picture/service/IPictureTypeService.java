package com.friendship.picture.service;

import java.util.List;

import com.java.po.PictureType;
import com.java.util.paging.AjaxPagingData;

public interface IPictureTypeService {
	public List<PictureType> getList(AjaxPagingData pagingData);
	
	public void save(PictureType entity);
	
	public void delete(PictureType entity);
}
