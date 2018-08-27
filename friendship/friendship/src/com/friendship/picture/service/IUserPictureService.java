package com.friendship.picture.service;

import java.util.Date;
import java.util.List;

import com.java.po.UserPicture;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

public interface IUserPictureService {
	public PagePagingData getUserPictureList(AjaxPagingData pagingData);
	
	public void save(UserPicture entity);
	
	public void delete(UserPicture entity);
	
	public List<UserPicture> getHomepage(Integer page, Integer item);

	public UserPicture getF(Integer userId, Date pictureCreateTime);

	public PagePagingData pictureListF(AjaxPagingData pagingData);
}
