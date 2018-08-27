package com.friendship.manage.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.friendship.manage.dao.IAdvertisementDao;
import com.friendship.manage.service.IAdvertisementService;
import com.java.po.Advertisement;
import com.java.util.annotation.UserLogAnnotation;
import com.java.util.aop.UserLogConfiguration;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

@Service("advertisementService")
public class AdvertisementService implements IAdvertisementService{
	private IAdvertisementDao advertisementDao;
	@Resource(name="advertisementDao")
	public void setAdvertisementDao(IAdvertisementDao advertisementDao) {
		this.advertisementDao = advertisementDao;
	}

	@Override
	public PagePagingData getAdvList(AjaxPagingData pagingData) {
		PagePagingData data = new PagePagingData();
		data.setData(advertisementDao.getAdvList(pagingData));
		data.setTotal(advertisementDao.getCount(pagingData));
		return data;
	}

	@Override
	@UserLogAnnotation(content = "添加广告", type = "后", method = UserLogConfiguration.MethodName.ADVERTISEMENT_SAVE)
	public void save(Advertisement entity) {
		advertisementDao.save(entity);
	}

	@Override
	@UserLogAnnotation(content = "删除广告", type = "后", method = UserLogConfiguration.MethodName.ADVERTISEMENT_DELETE)
	public void delete(Advertisement entity) {
		advertisementDao.del(entity);
	}

	@Override
	public Advertisement getF(String advTitle) {
		return advertisementDao.getF(advTitle);
	}

}
