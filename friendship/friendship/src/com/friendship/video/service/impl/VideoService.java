package com.friendship.video.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.friendship.video.dao.IVideoDao;
import com.friendship.video.extend.VideoPaging;
import com.friendship.video.service.IVideoService;
import com.java.po.LolVideo;
import com.java.util.annotation.UserLogAnnotation;
import com.java.util.aop.UserLogConfiguration;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

@Service("videoService")
public class VideoService implements IVideoService{
	private IVideoDao videoDao;
	@Resource(name="videoDao")
	public void setVideoDao(IVideoDao videoDao) {
		this.videoDao = videoDao;
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "添加视频", type = "后", method = UserLogConfiguration.MethodName.VIDEO_SAVE)
	public void save(LolVideo entity) {
		videoDao.save(entity);
	}

	@Override
	@Transactional(readOnly=true)
	public List<LolVideo> getVideoTypeList() {
		return videoDao.getVideoTypeList();
	}

	@Override
	@Transactional(readOnly=true)
	public PagePagingData getVideoList(AjaxPagingData pagingData) {
		PagePagingData data = new PagePagingData();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("videoList",videoDao.getVideoList(pagingData));
		map.put("videoTypeList", videoDao.getVideoTypeList());
		map.put("total", videoDao.getCount(pagingData));
		data.setData(map);
		return data;
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "删除视频", type = "后", method = UserLogConfiguration.MethodName.VIDEO_DELETE)
	public void delete(LolVideo entity) {
		videoDao.del(entity);
	}

	@Override
	@Transactional(readOnly=true)
	public List<LolVideo> getHomepage() {
		return videoDao.getHomepage();
	}

	@Override
	@Transactional(readOnly=true)
	public PagePagingData videoListF(AjaxPagingData pagingData) {
		PagePagingData data = new PagePagingData();
		data.setData(videoDao.videoListF(pagingData));
		data.setTotal(videoDao.getCountF(pagingData));
		return data;
	}

	@Override
	@Transactional
	public List<VideoPaging> getF(String videoUrl) {
		videoDao.updateNumber(videoUrl);
		AjaxPagingData pagingData = new AjaxPagingData();
		pagingData.setFieldsNameS("video.videoUrl");
		pagingData.setFieldsValueS(videoUrl);
		pagingData.setPage(1);
		pagingData.setItem(1);
		return videoDao.getF(pagingData);
	}

	@Override
	@Transactional(readOnly=true)
	public Integer get(String videoUrl) {
		return videoDao.get(videoUrl);
	}

}
