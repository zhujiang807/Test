package com.friendship.user.dao;

import java.util.List;
import com.friendship.user.extend.JoinPaging;
import com.java.po.UserJoin;
import com.java.util.paging.AjaxPagingData;

public interface IJoinDao {
	public List<JoinPaging> getJoinList(AjaxPagingData pageinData);

	public void deleteJoin(UserJoin entity);
	
	public Integer getCount(AjaxPagingData pagingData);
	
	public void save(UserJoin entity);
	
	public List<UserJoin> getJoinList(Integer actCode);
	
	public Integer updateJoin(Integer joinId, Integer joinResult);
	
	
	public List<JoinPaging> joinListF(AjaxPagingData pagingData);
	
	public Integer getCountF(AjaxPagingData pagingData);

	public UserJoin get(Integer userId, Integer teamsId, Integer actId);

}
