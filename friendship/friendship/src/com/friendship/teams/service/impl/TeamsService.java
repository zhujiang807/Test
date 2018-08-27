package com.friendship.teams.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.friendship.teams.dao.IAreaDao;
import com.friendship.teams.dao.ITeamsDao;
import com.friendship.teams.dao.ITeamsDelDao;
import com.friendship.teams.dao.IUserTeamsDao;
import com.friendship.teams.extend.TeamsPaging;
import com.friendship.teams.service.ITeamsService;
import com.java.po.LolTeams;
import com.java.po.LolTeamsDel;
import com.java.po.QqUser;
import com.java.po.UserTeams;
import com.java.util.DateBase;
import com.java.util.JSONFormat;
import com.java.util.annotation.UserLogAnnotation;
import com.java.util.aop.UserLogConfiguration;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

@Service("teamsService")
public class TeamsService implements ITeamsService{
	private ITeamsDao teamsDao;
	private IAreaDao areaDao;
	private IUserTeamsDao userTeamsDao;
	private ITeamsDelDao teamsDelDao;
	@Resource(name="teamsDao")
	public void setTeamssDao(ITeamsDao teamsDao) {
		this.teamsDao = teamsDao;
	}
	@Resource(name="areaDao")
	public void setAreaDao(IAreaDao areaDao) {
		this.areaDao = areaDao;
	}
	@Resource(name="userTeamsDao")
	public void setUserTeamsDao(IUserTeamsDao userTeamsDao) {
		this.userTeamsDao = userTeamsDao;
	}
	@Resource(name="teamsDelDao")
	public void setTeamsDelDao(ITeamsDelDao teamsDelDao) {
		this.teamsDelDao = teamsDelDao;
	}
	
	@Override
	@Transactional(readOnly=true)
	public PagePagingData getTeamsList(AjaxPagingData pagingData) {
		PagePagingData data = new PagePagingData();
		data.setTotal(teamsDao.getTeamssCount(pagingData));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("teams",teamsDao.getTeamsList(pagingData));
		map.put("area",areaDao.getAreaList());
		data.setData(map);
		return data;
	}
	
	@Override
	@Transactional
	@UserLogAnnotation(content = "删除战队", type = "后", method = UserLogConfiguration.MethodName.TEAMS_DELETE)
	public void delete(LolTeams entity) {
		teamsDao.deleteTeams(entity);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<TeamsPaging> getHomepage(AjaxPagingData pagingData) {
		return teamsDao.getHomepage(pagingData);
	}
	
	@Override
	@Transactional(readOnly=true)
	public PagePagingData getTeamsListF(AjaxPagingData pagingData) {
		PagePagingData data = new PagePagingData();
		data.setData(teamsDao.getTeamsListF(pagingData));
		data.setTotal(teamsDao.getTeamssCount(pagingData));
		return data;
	}
	
	@Override
	@Transactional
	public JSONFormat joinTeamsF(LolTeams entity, QqUser user) {
		JSONFormat format = new JSONFormat();
		//队长战队
		LolTeams teams = teamsDao.get(entity.getTeamsId());
		if(teams == null){
			return format;
		}
		
		List<UserTeams> userTeamsList = userTeamsDao.getF(user.getUserId(), teams.getAreaId());

		if(userTeamsList.size() > 0){
			UserTeams userTeams = userTeamsList.get(0);
			if(userTeams.getUserTeamscheck().equals(1)){
				format.setData("你已经加入了这个大区的战队了,一个大区只能加入一个战队！");
			}else{
				format.setType("error");
				format.setData("你已经加入这个大区的战队，在审核中，请稍等！");
			}
			return format;
		}else{
			List<UserTeams> userTeamsList2 = userTeamsDao.getF2(user.getUserId(), teams.getTeamsId());
			if(userTeamsList2 != null && userTeamsList2.size() > 0){
				UserTeams userTeams = userTeamsList2.get(0);
				userTeams.setUserTeamscheck(0);
				userTeamsDao.updateCheck(userTeams);
				format.setType("success");
				format.setData("你在次申请加入这个战队,队长审核中,请稍等！");
				return format;
			}
		}
		
		UserTeams userTeams = new UserTeams();
		userTeams.setUserId(user.getUserId());
		userTeams.setUserTeamscheck(0);
		userTeams.setTeamsId(teams.getTeamsId());
		userTeams.setCreateTime(DateBase.getTimestampYMDHMS());
		userTeamsDao.save(userTeams);
		format.setType("success");
		format.setData("你已经申请加入这个战队,队长审核中,请稍等！");
		
		return format;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<TeamsPaging> getUserTeamsListF(AjaxPagingData pagingData) {
		return teamsDao.getTeamsListF(pagingData);
	}
	
	@Override
	@Transactional
	@UserLogAnnotation(content = "添加战队信息", type = "前", method = UserLogConfiguration.MethodName.TEAMS_SAVE)
	public String saveF(LolTeams entity) {
		List<UserTeams> list = userTeamsDao.getF(entity.getUserId(), entity.getAreaId());
		if(list != null && list.size() > 0){
			if(list.get(0).getUserTeamscheck() == 1)
				return "这个大区你已经有战队了,请换个大区创建战队！";
			else if(list.get(0).getUserTeamscheck() == 0)
				return "这个大区你申请了战队，如果要加入其他战队请到'加入的战队'菜单中取消审核！";
		}
		
		if(teamsDao.getF(entity.getTeamsName(), entity.getAreaId()) < 1){
			teamsDao.save(entity);
			UserTeams ut = new UserTeams();
			ut.setUserTeamscheck(1);
			ut.setUserId(entity.getUserId());
			ut.setTeamsId(teamsDao.getF(entity.getUserId(), entity.getAreaId()).getTeamsId());
			ut.setCreateTime(DateBase.getTimestampYMDHMS());
			userTeamsDao.save(ut);
			return "创建战队成功,请到我的战队查看！";
		}else{
			return "名称重复,请换名称！";
		}
	}
	
	@Override
	@Transactional
	@UserLogAnnotation(content = "解散战队", type = "前", method = UserLogConfiguration.MethodName.TEAMS_DELETEF)
	public String deleteF(Integer userId, String areaName) {
		LolTeams teams = teamsDao.getF(userId, areaName);
		if(teams == null){
			return "";
		}
		
		teamsDao.delete(teams);
		userTeamsDao.updateCheckF(teams.getTeamsId());
		LolTeamsDel teamsDel = new LolTeamsDel();
		teamsDel.setAreaId(teams.getAreaId());
		teamsDel.setTeamsId(teams.getTeamsId());
		teamsDel.setUserId(teams.getUserId());
		teamsDel.setTeamsIcon(teams.getTeamsIcon());
		teamsDel.setTeamsIntegral(teams.getTeamsIntegral());
		teamsDel.setTeamsNumber(teams.getTeamsNumber());
		teamsDel.setTeamsName(teams.getTeamsName());
		teamsDel.setTeamsRemarks(teams.getTeamsRemarks());
		teamsDel.setTeamsCreateTime(DateBase.getTimestampYMDHMS());
		teamsDelDao.save(teamsDel);
		return "解散战队成功！";
	}
	
	@Override
	@Transactional
	public String updateF(LolTeams entity) {
		LolTeams teams = teamsDao.getF(entity.getUserId(), entity.getAreaId());
		if(teams == null){
			return " ";
		}
		if(!entity.getTeamsName().equals(teams.getTeamsName())){
			if(teamsDao.getF(entity.getTeamsName(), entity.getAreaId()) > 0)
				return "名称已经存在,请换名称！";
		}
		
		if(teamsDao.update(teams.getTeamsId(), entity.getTeamsName(), entity.getTeamsIcon(), entity.getTeamsRemarks()) > 0)
			return "修改成功！";
		else
			return "修改失败";
	}
	
}
