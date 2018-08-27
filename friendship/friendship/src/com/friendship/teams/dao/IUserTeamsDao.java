package com.friendship.teams.dao;

import java.util.List;
import com.friendship.teams.extend.UserTeamsPaging;
import com.java.po.UserTeams;
import com.java.util.paging.AjaxPagingData;

public interface IUserTeamsDao {
	public List<UserTeamsPaging> getUserTeamsList(AjaxPagingData pagingData);
	
	public void del(UserTeams entity);
	
	public void save(UserTeams entity);
	
	public Integer updateCheck(UserTeams entity);
	
	public Integer getCount(AjaxPagingData pagingData);
	
	
	/**
	 * 加入战队时判定是否有同区战队信息
	 * @param userId
	 * @param areaId
	 * @return
	 */
	public List<UserTeams> getF(Integer userId, Integer areaId);

	/**
	 * 战队时判定是否有同区战队信息
	 * @param userId
	 * @param areaId
	 * @return
	 */
	public List<UserTeams> getF2(Integer userId, Integer teamsId);
	
	/**
	 * 查询自己的战队成员信息
	 * @param userId
	 * @return
	 */
	public List<UserTeamsPaging> getListF(Integer userId,  String areaName, AjaxPagingData pagingData);
	
	/**
	 * 查看战队的战队成员信息
	 * @param userId
	 * @return
	 */
	public List<UserTeamsPaging> getListF(Integer teamsId, AjaxPagingData pagingData);
	
	/**
	 *审核战队成员是查询
	 * @param userId
	 * @param areaId
	 * @return
	 */
	public List<UserTeams> getF(String userQQ, String areaName);
	
	/**
	 *退出战队是查询
	 * @param userId
	 * @param areaId
	 * @return
	 */
	public List<UserTeams> getListF(Integer userTeamsId, Integer userId);
	
	/**
	 * 查询参加的战队
	 * @param userId
	 * @return
	 */
	public List<UserTeamsPaging> getJoinListF(Integer userId, AjaxPagingData pagingData);
	
	public Integer updateCheckF(Integer teamsId);
}
