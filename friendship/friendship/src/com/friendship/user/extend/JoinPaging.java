package com.friendship.user.extend;

import com.java.po.UserJoin;


public class JoinPaging extends UserJoin{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String teamsName;
	private String actName;
	private String userQQ;
	private String userName;
	private Integer actCode;
	private String areaName;
	private String ranRemarks;
	
	public String getTeamsName() {
		return teamsName;
	}
	public void setTeamsName(String teamsName) {
		this.teamsName = teamsName;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
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
	public Integer getActCode() {
		return actCode;
	}
	public void setActCode(Integer actCode) {
		this.actCode = actCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getRanRemarks() {
		return ranRemarks;
	}
	public void setRanRemarks(String ranRemarks) {
		this.ranRemarks = ranRemarks;
	}
}
