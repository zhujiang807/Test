package com.friendship.forum.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.friendship.forum.dao.IForumDao;
import com.java.po.LolForum;
import com.java.util.HibernateDaoBase;
import com.java.util.paging.AjaxPagingData;

@Repository("forumDao")
public class ForumDao extends HibernateDaoBase<LolForum> implements IForumDao{

	@Override
	public List<LolForum> getForumList(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("from LolForum ");
		return super.pagingHql(super.getPagingSql(sql, pagingData), pagingData.getPage(), pagingData.getItem() );
	}

	@Override
	public void del(LolForum entity) {
		super.delete(super.get("from LolForum where forumId=?", entity.getForumId()));
	}

	@Override
	public Integer getCount(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select count(*) from lol_forum ");
		return super.getCountSql(super.getPagingSql(sql, pagingData));
	}

	@Override
	public List<LolForum> forumHomepage() {
		return super.pagingHql("select forumTitle,forumCreateTime from LolForum order by forumCreateTime desc", 1, 8);
	}

	@Override
	public List<LolForum> getForumListF(Integer item, Integer page) {
		StringBuffer sql = new StringBuffer("select forumTitle,forumContent,forumCreateTime from LolForum order by forumCreateTime desc");
		return super.pagingHql(sql.toString(), page, item);
	}

	@Override
	public Integer getCountF() {
		StringBuffer sql = new StringBuffer("select count(*) from lol_forum ");
		return super.getCountSql(sql.toString());
	}

	@Override
	public List<LolForum> getForumInfoF(String forumTitle) {
		return super.pagingHql("select forumTitle,forumContent from LolForum where forumTitle like '%"+forumTitle+"%' order by forumCreateTime desc", 1, 1);
	}

	@Override
	public LolForum getF(String forumTitle) {
		return super.get("from LolForum where forumTitle=?", forumTitle);
	}

}
