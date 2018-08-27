package com.friendship.video.dao;

import java.util.List;

import com.java.po.VideoType;

public interface IVideoTypeDao {
	public List<VideoType> getVideoTypeList();
	
	public void del(VideoType entity);
	
	public void save(VideoType entity);
	
	public List<VideoType> videoTypeListF();

}
