package com.friendship.teams.extend;

import com.java.po.LolTeams;


public class TeamsPaging extends LolTeams{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userQQ;
	private String areaName;
	private String userName;
	
	public String getUserQQ() {
		return userQQ;
	}
	public void setUserQQ(String userQQ) {
		this.userQQ = userQQ;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
