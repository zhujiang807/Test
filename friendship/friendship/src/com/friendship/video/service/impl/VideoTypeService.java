package com.friendship.video.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.friendship.video.dao.IVideoTypeDao;
import com.friendship.video.service.IVideoTypeService;
import com.java.po.VideoType;
import com.java.util.annotation.UserLogAnnotation;
import com.java.util.aop.UserLogConfiguration;

@Service("videoTypeService")
public class VideoTypeService implements IVideoTypeService{
	private IVideoTypeDao videoTypeDao;
	@Resource(name="videoTypeDao")
	public void setVideoTypeDao(IVideoTypeDao videoTypeDao) {
		this.videoTypeDao = videoTypeDao;
	}

	@Override
	@Transactional(readOnly=true)
	public List<VideoType> getVideoTypeList() {
		return videoTypeDao.getVideoTypeList();
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "删除视频类型", type = "后", method = UserLogConfiguration.MethodName.VIDEOTYPE_DELETE)
	public void delete(VideoType entity) {
		videoTypeDao.del(entity);
	}

	@Override
	@Transactional
	public void save(VideoType entity) {
		videoTypeDao.save(entity);
	}

	@Override
	@Transactional(readOnly=true)
	public List<VideoType> videoTypeListF() {
		return videoTypeDao.videoTypeListF();
	}

}
