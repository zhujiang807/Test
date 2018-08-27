package com.friendship.manage.service.impl;


import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.friendship.manage.dao.IAnnouncementDao;
import com.friendship.manage.service.IAnnouncementService;
import com.java.po.Announcement;
import com.java.util.annotation.UserLogAnnotation;
import com.java.util.aop.UserLogConfiguration;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

@Service("announcementService")
public class AnnouncementService implements IAnnouncementService{
	private IAnnouncementDao announcementDao;
	@Resource(name="announcementDao")
	public void setAnnouncementDao(IAnnouncementDao announcementDao) {
		this.announcementDao = announcementDao;
	}
	
	@Override
	@Transactional
	@UserLogAnnotation(content = "添加公告", type = "后", method = UserLogConfiguration.MethodName.ANNOUNCEMENT_SAVE)
	public void save(Announcement entity) {
		announcementDao.save(entity);
	}
	
	@Override
	@Transactional(readOnly=true)
	public PagePagingData getList(AjaxPagingData pagingData) {
		PagePagingData data = new PagePagingData();
		data.setTotal(announcementDao.getCount());
		data.setData(announcementDao.getList(pagingData));
		return data;
	}
	
	@Override
	@Transactional
	public Integer update(Announcement entity) {
		 announcementDao.updateAnnouncement(entity);
		 return 1;
	}
	
	@Override
	@Transactional
	@UserLogAnnotation(content = "删除公告", type = "后", method = UserLogConfiguration.MethodName.ANNOUNCEMENT_DELETE)
	public void del(Announcement entity) {
		announcementDao.del(entity);
	}

	@Override
	@Transactional(readOnly=true)
	public Announcement get() {
		return announcementDao.get();
	}
	
}
