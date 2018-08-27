package com.friendship.video.service;

import java.util.List;

import com.friendship.video.extend.VideoPaging;
import com.java.po.LolVideo;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

public interface IVideoService {
	public List<LolVideo> getVideoTypeList();
	
	public PagePagingData getVideoList(AjaxPagingData pagingData);
	
	public void delete(LolVideo entity);
	
	public void save(LolVideo entity);
	
	public Integer get(String videoUrl);
	
	public List<LolVideo> getHomepage();
	
	public PagePagingData videoListF(AjaxPagingData pagingData);
	
	public List<VideoPaging> getF(String videoUrl);

}
