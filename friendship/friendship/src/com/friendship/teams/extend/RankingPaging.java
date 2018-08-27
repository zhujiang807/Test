package com.friendship.teams.extend;

import com.java.po.TeamsRanking;

public class RankingPaging extends TeamsRanking{

	private static final long serialVersionUID = 1L;

	public String actName;
	public Integer actCode;
	public String teamsName;
	public String areaName;
	public String userQQ;
	public String userName;
	
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	public Integer getActCode() {
		return actCode;
	}
	public void setActCode(Integer actCode) {
		this.actCode = actCode;
	}
	public String getTeamsName() {
		return teamsName;
	}
	public void setTeamsName(String teamsName) {
		this.teamsName = teamsName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getUserQQ() {
		return userQQ;
	}
	public void setUserQQ(String userQQ) {
		this.userQQ = userQQ;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
