package com.friendship.manage.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.friendship.manage.dao.IAnnouncementDao;
import com.java.po.Announcement;
import com.java.util.HibernateDaoBase;
import com.java.util.paging.AjaxPagingData;

@Repository("announcementDao")
public class AnnouncementDao extends HibernateDaoBase<Announcement> implements IAnnouncementDao{

	@Override
	public List<Announcement> getList(AjaxPagingData pagingData) {
		return super.pagingHql("from Announcement order by annCreateTime desc", pagingData.getPage(), pagingData.getItem());
	}

	@Override
	public Integer updateAnnouncement(Announcement entity) {
		StringBuffer sql = new StringBuffer("update announcement set annTitle='").append(entity.getAnnTitle()).append("'");
		sql.append(",annContent='").append(entity.getAnnContent()).append("'").append(" where annId=").append(entity.getAnnId());
		return super.update(sql.toString());
	}

	@Override
	public void del(Announcement entity) {
		super.delete(super.get("from Announcement where annId=?", entity.getAnnId()));
	}

	@Override
	public Integer getCount() {
		return super.getCountSql("select count(*) from announcement");
	}

	@Override
	public Announcement get() {
		return super.get("from Announcement order by annCreateTime desc limit 1");
	}

}
