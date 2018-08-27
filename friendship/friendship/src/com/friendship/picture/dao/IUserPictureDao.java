package com.friendship.picture.dao;

import java.util.Date;
import java.util.List;
import com.friendship.picture.extend.UserPicturePaging;
import com.java.po.UserPicture;
import com.java.util.paging.AjaxPagingData;

public interface IUserPictureDao {
	public List<UserPicturePaging> getPictureList(AjaxPagingData pagingData);
	
	public Integer getCount(AjaxPagingData pagingData);
	
	public List<UserPicture> getHomepage(Integer page, Integer item);
	
	public void save(UserPicture picture);
	
	public void del(UserPicture entity);

	
	public UserPicture get(Integer userId, Date pictureCreateTime);
	
	public List<UserPicturePaging> getPictureListF(AjaxPagingData pagingData);
}
