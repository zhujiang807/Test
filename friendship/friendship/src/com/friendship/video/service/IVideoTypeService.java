package com.friendship.video.service;

import java.util.List;

import com.java.po.VideoType;

public interface IVideoTypeService {
	public List<VideoType> getVideoTypeList();
	
	public void delete(VideoType entity);
	
	public void save(VideoType entity);
	
	
	public List<VideoType> videoTypeListF();
}
