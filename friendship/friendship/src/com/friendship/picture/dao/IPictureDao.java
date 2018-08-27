package com.friendship.picture.dao;

import java.util.List;

import com.friendship.picture.extend.PicturePaging;
import com.java.po.Picture;
import com.java.util.paging.AjaxPagingData;

public interface IPictureDao {
	public List<PicturePaging> getPictureList(AjaxPagingData pagingData);
	
	public void save(Picture entity);
	
	public void del(Picture entity);
	
	public Integer getCount(AjaxPagingData pagingData);
	
	public List<PicturePaging> getPictureListF(AjaxPagingData pagingData);

}
