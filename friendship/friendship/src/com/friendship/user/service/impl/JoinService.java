package com.friendship.user.service.impl;


import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.friendship.teams.dao.IMatchDao;
import com.friendship.teams.dao.IRankingDao;
import com.friendship.teams.dao.ITeamsDao;
import com.friendship.teams.dao.IUserTeamsDao;
import com.friendship.user.dao.IActivitiesDao;
import com.friendship.user.dao.IJoinDao;
import com.friendship.user.extend.JoinPaging;
import com.friendship.user.service.IJoinService;
import com.java.po.ActivitiesMatch;
import com.java.po.LolTeams;
import com.java.po.TeamsRanking;
import com.java.po.UserActivities;
import com.java.po.UserJoin;
import com.java.po.UserTeams;
import com.java.util.DateBase;
import com.java.util.annotation.UserLogAnnotation;
import com.java.util.aop.UserLogConfiguration;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

@Service("joinService")
public class JoinService implements IJoinService{
	private IJoinDao joinDao;
	private IMatchDao matchDao;
	private IActivitiesDao activitiesDao;
	private IRankingDao rankingDao;
	private IUserTeamsDao userTeamsDao;
	private ITeamsDao teamsDao;
	@Resource(name="joinDao")
	public void setJoinDao(IJoinDao joinDao) {
		this.joinDao = joinDao;
	}
	@Resource(name="matchDao")
	public void setMatchDao(IMatchDao matchDao) {
		this.matchDao = matchDao;
	}
	@Resource(name="activitiesDao")
	public void setActivitiesDao(IActivitiesDao activitiesDao) {
		this.activitiesDao = activitiesDao;
	}
	@Resource(name="rankingDao")
	public void setRankingDao(IRankingDao rankingDao) {
		this.rankingDao = rankingDao;
	}
	@Resource(name="userTeamsDao")
	public void setUserTeamsDao(IUserTeamsDao userTeamsDao) {
		this.userTeamsDao = userTeamsDao;
	}
	@Resource(name="teamsDao")
	public void setTeamsDao(ITeamsDao teamsDao) {
		this.teamsDao = teamsDao;
	}
	
	@Override
	@Transactional(readOnly=true)
	public PagePagingData getJoinList(AjaxPagingData pagingData) {
		PagePagingData data = new PagePagingData();
		data.setData(joinDao.getJoinList(pagingData));
		data.setTotal(joinDao.getCount(pagingData));
		return data;
	}

	@Override
	@Transactional
	public void delete(UserJoin entity) {
		joinDao.deleteJoin(entity);
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "用户参加活动", type = "前", method = UserLogConfiguration.MethodName.JOIN_SAVE)
	public String save(Integer actCode, String actName, Integer userId) {
		UserActivities act = activitiesDao.getF(actCode, actName);
		if(act == null){
			return "错误！";
		}
		
		if(DateBase.getTimestampYMDHMS().getTime() < act.getActBeginTime().getTime()){
			return "活动还没有开始,敬请等待！";
		}
		
		if(DateBase.getTimestampYMDHMS().getTime() > act.getActEndTime().getTime()){
			return "活动已经结束！";
		}
		
		LolTeams teams = teamsDao.getF(userId, act.getAreaId());
		if(teams == null){
			return "这个大区你没有战队/队长才可以报名！";
		}
		
		if(joinDao.get(userId, teams.getTeamsId(), act.getActId()) != null){
			return "你已经参加过活动了！";
		}
		
		UserJoin join = new UserJoin();
		join.setActivitiesId(act.getActId());
		join.setUserId(userId);
		join.setTeamsId(teams.getTeamsId());
		join.setJoinCreateTime(DateBase.getTimestampYMDHMS());
		join.setJoinResult(0);
		joinDao.save(join);
		
		act.setActAmount(act.getActAmount()+1);
		activitiesDao.updateAct(act);
		
		return "报名成功";
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	@UserLogAnnotation(content = "安排比赛", type = "后", method = UserLogConfiguration.MethodName.JOIN_ARRANGEMATCH)
	public String arrangeMatch(UserActivities entity) {
		List<UserJoin> list = joinDao.getJoinList(entity.getActCode());
		List<ActivitiesMatch> matchList = new ArrayList<ActivitiesMatch>();
		List<UserActivities> activitiesList = activitiesDao.getList(entity.getActCode());
		if(activitiesList == null || activitiesList.size() < 1){
			return "活动码错误！";
		}
		
		UserActivities activities = activitiesList.get(0);
		Integer matchType = 0;
		if(list.size() <= 16 && list.size() > 8){
			matchType = 3;
		}else if(list.size() <= 8){
			matchType = 5;
		}else{
			matchType = 1;
		}
		
		Integer j = 1;
		for (int i = 0; i < list.size()/2; i++) {
			ActivitiesMatch match = new ActivitiesMatch();
			match.setActivitiesId(activities.getActId());
			match.setTeamsId1(list.get(i).getTeamsId());
			match.setTeamsId2(list.get(list.size()-j).getTeamsId());
			match.setTeamsVictory1(0);
			match.setTeamsVictory2(0);
			match.setMatchType(matchType);
			match.setMatchShow(0);
			match.setMatchCreateTime(entity.getActCreateTime());
			match.setMatchBeginTime(entity.getActCreateTime());
			match.setMatchEndTime(entity.getActCreateTime());
			matchList.add(match);
			j++;
		}
 
		if(list.size()%2 != 0){
			ActivitiesMatch match = new ActivitiesMatch();
			match.setActivitiesId(activities.getActId());
			match.setTeamsId1(list.get(list.size()/2).getTeamsId());
			match.setTeamsId2(0);
			match.setTeamsVictory1(0);
			match.setTeamsVictory2(0);
			match.setMatchType(matchType);
			match.setMatchShow(0);
			match.setMatchCreateTime(entity.getActCreateTime());
			match.setMatchBeginTime(entity.getActCreateTime());
			match.setMatchEndTime(entity.getActCreateTime());
			matchList.add(match);
		}
		
		matchDao.saveList(matchList);
		return "成功安排比赛";
	}
	
	@Override
	@Transactional(rollbackFor=RuntimeException.class)
	@UserLogAnnotation(content = "用户比赛结果", type = "后", method = UserLogConfiguration.MethodName.JOIN_UPDATEJOIN)
	public void updateJoin(JoinPaging entity) {
		joinDao.updateJoin(entity.getJoinId(), entity.getJoinResult());
		TeamsRanking ranking = new TeamsRanking();
		ranking.setActivitiesId(entity.getActivitiesId());
		ranking.setTeamsId(entity.getTeamsId());
		ranking.setRanNumber(entity.getJoinResult());
		if(entity.getTeamsId() != null)
			ranking.setRanRemarks("战队："+entity.getTeamsName()+",在"+entity.getActName()+"活动中获得："+entity.getJoinResult()+"名");
		ranking.setRanCreateTime(entity.getJoinCreateTime());
		TeamsRanking ran = rankingDao.get(entity.getActivitiesId(), entity.getTeamsId());
		if(ran == null)
			rankingDao.saveOrUpdate(ranking);
		else{
			ran.setRanNumber(entity.getJoinResult());
			ran.setRanRemarks(ranking.getRanRemarks()==null?ran.getRanRemarks():ranking.getRanRemarks());
			rankingDao.update(ran);
		}
	}
		
	@Override
	@Transactional(readOnly=true)
	public PagePagingData joinListF(AjaxPagingData pagingData) {
		PagePagingData data = new PagePagingData();
		data.setData(joinDao.joinListF(pagingData));
		data.setTotal(joinDao.getCountF(pagingData));
		return data;
	}
	
	@Override
	@Transactional(readOnly=true)
	public PagePagingData joinListF(AjaxPagingData pagingData, Integer userTeamsId, Integer userId) {
		List<UserTeams> list = userTeamsDao.getListF(userTeamsId, userId);
		PagePagingData data = new PagePagingData();
		if(list != null && list.size() > 0){
			pagingData.setFieldsName(" uj.teamsId");
			pagingData.setFieldsValue(list.get(0).getTeamsId());
			data.setData(joinDao.joinListF(pagingData));
			data.setTotal(joinDao.getCountF(pagingData));
		}
		return data;
	}
}
