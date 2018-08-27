package com.friendship.video.dao;

import java.util.List;

import com.friendship.video.extend.VideoPaging;
import com.java.po.LolVideo;
import com.java.util.paging.AjaxPagingData;

public interface IVideoDao {
	public void save(LolVideo entity);
	
	public List<LolVideo> getVideoTypeList();

	public List<VideoPaging> getVideoList(AjaxPagingData pagingData);
	
	public void del(LolVideo video);
	
	public Integer getCount(AjaxPagingData pagingData);
	
	public Integer get(String videoUrl);

	
	
	public List<LolVideo> getHomepage();
	
	public List<VideoPaging> videoListF(AjaxPagingData pagingData);
	
	public Integer getCountF(AjaxPagingData pagingData);
	
	public List<VideoPaging> getF(AjaxPagingData pagingData);
	
	public void updateNumber(String videoUrl);
}
