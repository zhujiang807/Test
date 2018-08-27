package com.friendship.picture.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.friendship.picture.dao.IPictureTypeDao;
import com.friendship.picture.service.IPictureTypeService;
import com.java.po.PictureType;
import com.java.util.annotation.UserLogAnnotation;
import com.java.util.aop.UserLogConfiguration;
import com.java.util.paging.AjaxPagingData;

@Service("pictureTypeService")
public class PictureTypeService implements IPictureTypeService{
	private IPictureTypeDao pictureTypeDao;
	
	@Resource(name="pictureTypeDao")
	public void setPictureTypeDao(IPictureTypeDao pictureTypeDao) {
		this.pictureTypeDao = pictureTypeDao;
	}

	@Override
	@Transactional(readOnly=true)
	public List<PictureType> getList(AjaxPagingData pagingData) {
		return pictureTypeDao.getList(pagingData);
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "添加图片类型", type = "后", method = UserLogConfiguration.MethodName.PICTURETYPE_SAVE)
	public void save(PictureType entity) {
		pictureTypeDao.save(entity);
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "删除图片类型", type = "后", method = UserLogConfiguration.MethodName.PICTURETYPE_DELETE)
	public void delete(PictureType entity) {
		pictureTypeDao.del(entity);
	}
	
}
