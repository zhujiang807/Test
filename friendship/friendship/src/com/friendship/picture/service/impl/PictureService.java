package com.friendship.picture.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.friendship.picture.dao.IPictureDao;
import com.friendship.picture.service.IPictureService;
import com.java.po.Picture;
import com.java.util.annotation.UserLogAnnotation;
import com.java.util.aop.UserLogConfiguration;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

@Service("pictureService")
public class PictureService implements IPictureService{
	private IPictureDao pictureDao;
	@Resource(name="pictureDao")
	public void setPictureDao(IPictureDao pictureDao) {
		this.pictureDao = pictureDao;
	}

	@Override
	@Transactional(readOnly=true)
	public PagePagingData getPictureList(AjaxPagingData pagingData) {
		PagePagingData data = new PagePagingData();
		data.setData(pictureDao.getPictureList(pagingData));
		data.setTotal(pictureDao.getCount(pagingData));
		return data;
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "添加图片", type = "后", method = UserLogConfiguration.MethodName.PICTURE_SAVE)
	public void save(Picture entity) {
		pictureDao.save(entity);
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "添加图片", type = "后", method = UserLogConfiguration.MethodName.PICTURE_DELETE)
	public void delete(Picture entity) {
		pictureDao.del(entity);
	}

	@Override
	@Transactional(readOnly=true)
	public PagePagingData pictureListF(AjaxPagingData pagingData) {
		PagePagingData data = new PagePagingData();
		data.setData(pictureDao.getPictureListF(pagingData));
		data.setTotal(pictureDao.getCount(pagingData));
		return data;
	}
	
}
