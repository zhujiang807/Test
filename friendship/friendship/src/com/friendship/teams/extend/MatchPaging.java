package com.friendship.teams.extend;

import com.java.po.ActivitiesMatch;

public class MatchPaging extends ActivitiesMatch{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Integer actCode;
	public String actName;
	public String teamsName1;
	public String teamsName2;
	public String areaName;
	public String teamsIcon1;
	public String teamsIcon2;
	
	public Integer getActCode() {
		return actCode;
	}
	public void setActCode(Integer actCode) {
		this.actCode = actCode;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	public String getTeamsName1() {
		return teamsName1;
	}
	public void setTeamsName1(String teamsName1) {
		this.teamsName1 = teamsName1;
	}
	public String getTeamsName2() {
		return teamsName2;
	}
	public void setTeamsName2(String teamsName2) {
		this.teamsName2 = teamsName2;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getTeamsIcon1() {
		return teamsIcon1;
	}
	public void setTeamsIcon1(String teamsIcon1) {
		this.teamsIcon1 = teamsIcon1;
	}
	public String getTeamsIcon2() {
		return teamsIcon2;
	}
	public void setTeamsIcon2(String teamsIcon2) {
		this.teamsIcon2 = teamsIcon2;
	}
	
}
