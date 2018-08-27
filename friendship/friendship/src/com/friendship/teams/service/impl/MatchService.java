package com.friendship.teams.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.friendship.teams.dao.IMatchDao;
import com.friendship.teams.dao.IUserTeamsDao;
import com.friendship.teams.dao.impl.TeamsDao;
import com.friendship.teams.extend.MatchPaging;
import com.friendship.teams.service.IMatchService;
import com.java.po.ActivitiesMatch;
import com.java.po.LolTeams;
import com.java.po.UserTeams;
import com.java.util.annotation.UserLogAnnotation;
import com.java.util.aop.UserLogConfiguration;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

@Service("matchService")
public class MatchService implements IMatchService{
	private IMatchDao matchDao;
	private TeamsDao teamsDao;
	private IUserTeamsDao userTeamsDao;
	@Resource(name="matchDao")
	public void setMatchDao(IMatchDao matchDao) {
		this.matchDao = matchDao;
	}
	@Resource(name="teamsDao")
	public void setTeamsDao(TeamsDao teamsDao) {
		this.teamsDao = teamsDao;
	}
	@Resource(name="userTeamsDao")
	public void setUserTeamsDao(IUserTeamsDao userTeamsDao) {
		this.userTeamsDao = userTeamsDao;
	}
	
	@Override
	@Transactional(readOnly=true)
	public PagePagingData getMatchList(AjaxPagingData pagingData) {
		PagePagingData data = new PagePagingData();
		data.setData(matchDao.getMatchList(pagingData));
		data.setTotal(matchDao.getCount(pagingData));
		return data;
	}
	@Override
	@Transactional
	public Integer update(ActivitiesMatch entity) {
		LolTeams teams = new LolTeams();
		if(entity.getTeamsVictory1() > Integer.parseInt(entity.getFieldValue3())){
			teams.setTeamsId(entity.getTeamsId1());
			teams.setTeamsIntegral(1);
			teamsDao.updateTeamsIntegral(teams);
		}
		
		if(entity.getTeamsVictory1() < Integer.parseInt(entity.getFieldValue3())){
			teams.setTeamsId(entity.getTeamsId1());
			teams.setTeamsIntegral(-1);
			teamsDao.updateTeamsIntegral(teams);
		}
		
		if(entity.getTeamsVictory2() > Integer.parseInt(entity.getFieldValue4())){
			teams.setTeamsId(entity.getTeamsId2());
			teams.setTeamsIntegral(1);
			teamsDao.updateTeamsIntegral(teams);
		}
		
		if(entity.getTeamsVictory2() < Integer.parseInt(entity.getFieldValue4())){
			teams.setTeamsId(entity.getTeamsId2());
			teams.setTeamsIntegral(-1);
			teamsDao.updateTeamsIntegral(teams);
		}
		
		return matchDao.updateMatch(entity);
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "删除战队比赛", type = "后", method = UserLogConfiguration.MethodName.MATCH_DELETE)
	public void delete(ActivitiesMatch entity) {
		matchDao.del(entity);
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "添加战队比赛", type = "后", method = UserLogConfiguration.MethodName.MATCH_SAVE)
	public void save(ActivitiesMatch entity) {
		matchDao.save(entity);
	}
	@Override
	@Transactional(readOnly=true)
	public List<MatchPaging> matchHomepage(AjaxPagingData pagingData) {
		return matchDao.matchHomepageF(pagingData);
	}
	
	@Override
	@Transactional(readOnly=true)
	public PagePagingData teamsMatchListF(AjaxPagingData pagingData, Integer userId, String areaName) {
		PagePagingData data = new PagePagingData();
		LolTeams teams = teamsDao.getF(userId, areaName);
		if(teams == null)
			return data;
		pagingData.setFieldsName("mat.teamsId1=");
		pagingData.setFieldsValue(teams.getTeamsId()+" or mat.teamsId2="+teams.getTeamsId());
		
		data.setData(matchDao.matchHomepageF(pagingData));
		data.setTotal(matchDao.getCountF(pagingData));
		return data;
	}
	
	@Override
	@Transactional(readOnly=true)
	public PagePagingData teamsMatchListF(AjaxPagingData pagingData, Integer userId, Integer userTeamsId) {
		List<UserTeams> list = userTeamsDao.getListF(userTeamsId, userId);
		PagePagingData data = new PagePagingData();
		if(list != null && list.size() > 0){
			pagingData.setFieldsName("mat.teamsId1=");
			pagingData.setFieldsValue(list.get(0).getTeamsId()+" or mat.teamsId2="+list.get(0).getTeamsId());
			data.setData(matchDao.matchHomepageF(pagingData));
			data.setTotal(matchDao.getCountF(pagingData));
		}
		return data;
	}
	
	@Override
	public Integer getCountF(AjaxPagingData pagingData) {
		return matchDao.getCount(pagingData);
	}
	
}
