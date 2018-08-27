package com.friendship.video.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.friendship.video.dao.IVideoTypeDao;
import com.java.po.VideoType;
import com.java.util.HibernateDaoBase;

@Repository("videoTypeDao")
public class VideoTypeDao extends HibernateDaoBase<VideoType> implements IVideoTypeDao{

	@Override
	public List<VideoType> getVideoTypeList() {
		return super.find("from VideoType order by videoTypeTime desc");
	}

	@Override
	public void del(VideoType entity) {
		super.delete(super.get("from VideoType where videoTypeId=?", entity.getVideoTypeId()));
	}

	@Override
	public List<VideoType> videoTypeListF() {
		return super.getListSql("select videoTypeName from video_type order by videoTypeTime desc", VideoType.class);
	}

}
