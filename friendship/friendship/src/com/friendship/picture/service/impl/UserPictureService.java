package com.friendship.picture.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.friendship.picture.dao.IUserPictureDao;
import com.friendship.picture.service.IUserPictureService;
import com.java.po.UserPicture;
import com.java.util.annotation.UserLogAnnotation;
import com.java.util.aop.UserLogConfiguration.MethodName;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

@Service("userPictureService")
public class UserPictureService implements IUserPictureService{
	private IUserPictureDao userPictureDao;
	
	@Resource(name="userPictureDao")
	public void setUserPictureDao(IUserPictureDao userPictureDao) {
		this.userPictureDao = userPictureDao;
	}

	@Override
	@Transactional(readOnly=true)
	public PagePagingData getUserPictureList(AjaxPagingData pagingData) {
		PagePagingData data = new PagePagingData();
		data.setData(userPictureDao.getPictureList(pagingData));
		data.setTotal(userPictureDao.getCount(pagingData));
		return data;
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "用户上传图片", type = "前", method = MethodName.USERPICTURE_SAVE)
	public void save(UserPicture entity) {
		userPictureDao.save(entity);
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "删除用户图片", type = "后", method = MethodName.USERPICTURE_DELETE)
	public void delete(UserPicture entity){
		userPictureDao.del(entity);
	}

	@Override
	public UserPicture getF(Integer userId, Date pictureCreateTime) {
		return userPictureDao.get(userId, pictureCreateTime);
	}

	@Override
	public List<UserPicture> getHomepage(Integer page, Integer item) {
		return userPictureDao.getHomepage(page, item);
	}

	@Override
	@Transactional(readOnly=true)
	public PagePagingData pictureListF(AjaxPagingData pagingData) {
		PagePagingData data = new PagePagingData();
		data.setData(userPictureDao.getPictureListF(pagingData));
		data.setTotal(userPictureDao.getCount(pagingData));
		return data;
	}

}
