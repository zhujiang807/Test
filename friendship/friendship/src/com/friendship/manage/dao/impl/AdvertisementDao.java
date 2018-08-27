package com.friendship.manage.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.friendship.manage.dao.IAdvertisementDao;
import com.java.po.Advertisement;
import com.java.util.HibernateDaoBase;
import com.java.util.paging.AjaxPagingData;

@Repository("advertisementDao")
public class AdvertisementDao extends HibernateDaoBase<Advertisement> implements IAdvertisementDao{

	@Override
	public List<Advertisement> getAdvList(AjaxPagingData pagingData) {
		StringBuffer hql = new StringBuffer("from Advertisement");
		return super.pagingHql(super.getPagingSql(hql, pagingData), pagingData.getPage(), pagingData.getItem());
	}

	@Override
	public void del(Advertisement entity) {
		super.delete(super.get("from Advertisement where advId=?", entity.getAdvId()));
	}

	@Override
	public Integer getCount(AjaxPagingData pagingData) {
		StringBuffer hql = new StringBuffer("select count(*) from advertisement");
		return super.getCountSql(super.getPagingSql(hql, pagingData));
	}

	@Override
	public Advertisement getF(String advTitle) {
		return super.getListSql("select userQQ,advTitle,advUrl,advPictureUrl,advRemarks from Advertisement where advTitle='"+advTitle+"'", Advertisement.class).get(0);
	}
	
}
