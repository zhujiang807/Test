package com.friendship.picture.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.friendship.picture.dao.IPictureTypeDao;
import com.java.po.PictureType;
import com.java.util.HibernateDaoBase;
import com.java.util.paging.AjaxPagingData;

@Repository("pictureTypeDao")
public class PictureTypeDao extends HibernateDaoBase<PictureType> implements IPictureTypeDao{

	@Override
	public List<PictureType> getList(AjaxPagingData pagingData) {
		return super.find(super.getPagingSql(new StringBuffer("from PictureType"), pagingData));
	}

	@Override
	public void del(PictureType entity) {
		super.delete(super.get("from PictureType where pictureTypeId=?", entity.getPictureTypeId()));
	}

}
