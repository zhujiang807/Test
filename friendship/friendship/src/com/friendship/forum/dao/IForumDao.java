package com.friendship.forum.dao;

import java.util.List;

import com.java.po.LolForum;
import com.java.util.paging.AjaxPagingData;

public interface IForumDao {
	public List<LolForum> getForumList(AjaxPagingData pagingData);
	
	public void del(LolForum entity);
	
	public Integer getCount(AjaxPagingData pagingData);
	
	public void save(LolForum entity);
	
	public List<LolForum> forumHomepage();
	
	
	
	
	public List<LolForum> getForumListF(Integer item, Integer page);

	public Integer getCountF();
	
	public List<LolForum> getForumInfoF(String forumTitle);

	public LolForum getF(String forumTitle);
}
