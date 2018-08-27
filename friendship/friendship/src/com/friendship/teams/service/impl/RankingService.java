package com.friendship.teams.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.friendship.teams.dao.IRankingDao;
import com.friendship.teams.service.IRankingService;
import com.java.po.TeamsRanking;
import com.java.util.annotation.UserLogAnnotation;
import com.java.util.aop.UserLogConfiguration;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

@Service("rankingService")
public class RankingService implements IRankingService{
	private IRankingDao rankingDao;
	@Resource(name="rankingDao")
	public void setRankingDao(IRankingDao rankingDao) {
		this.rankingDao = rankingDao;
	}
	@Override
	@Transactional
	public PagePagingData getRankingList(AjaxPagingData pagingData) {
		PagePagingData data = new PagePagingData();
		data.setData(rankingDao.getRankingList(pagingData));
		data.setTotal(rankingDao.getCount(pagingData));
		return data;
	}
	
	@Override
	@Transactional
	@UserLogAnnotation(content = "战队比赛排名删除", type = "后", method = UserLogConfiguration.MethodName.RANKING_DELETE)
	public void delete(TeamsRanking entity) {
		rankingDao.del(entity);
	}
	
	@Override
	@Transactional
	@UserLogAnnotation(content = "战队比赛排名修改", type = "后", method = UserLogConfiguration.MethodName.RANKING_UPDATE)
	public Integer update(TeamsRanking entity) {
		return rankingDao.updateRanking(entity);
	}
	
	@Override
	@Transactional
	public PagePagingData getRankingListF(AjaxPagingData pagingData) {
		PagePagingData data = new PagePagingData();
		data.setData(rankingDao.getRankingListF(pagingData));
		data.setTotal(rankingDao.getCount(pagingData));
		return data;
	}
	
}
