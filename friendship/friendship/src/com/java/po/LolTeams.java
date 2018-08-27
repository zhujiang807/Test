package com.java.po;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * LolTeamss entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "lol_teams", catalog = "test")
public class LolTeams implements  Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer  teamsId;
	private Integer userId;
	private Integer areaId;
	private String teamsIcon;
	private String teamsName;
	private Integer teamsIntegral;
	private String teamsRemarks;
	private Integer teamsNumber;
	private Timestamp teamsCreateTime;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "teamsId", nullable = false)
	public Integer getTeamsId() {
		return teamsId;
	}

	public void setTeamsId(Integer teamsId) {
		this.teamsId = teamsId;
	}
	
	public void setTeamsCreateTime(Timestamp teamsCreateTime) {
		this.teamsCreateTime = teamsCreateTime;
	}

	@Column(name = "userId")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "areaId")
	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	@Column(name = "teamsIcon", length = 100)
	public String getTeamsIcon() {
		return this.teamsIcon;
	}

	public void setTeamsIcon(String teamsIcon) {
		this.teamsIcon = teamsIcon;
	}

	@Column(name = "teamsName", nullable = false, length = 30)
	public String getTeamsName() {
		return this.teamsName;
	}

	public void setTeamsName(String teamsName) {
		this.teamsName = teamsName;
	}

	@Column(name = "teamsIntegral")
	public Integer getTeamsIntegral() {
		return this.teamsIntegral;
	}

	public void setTeamsIntegral(Integer teamsIntegral) {
		this.teamsIntegral = teamsIntegral;
	}

	@Column(name = "teamsRemarks", length = 100)
	public String getTeamsRemarks() {
		return this.teamsRemarks;
	}

	public void setTeamsRemarks(String teamsRemarks) {
		this.teamsRemarks = teamsRemarks;
	}

	@Column(name = "teamsNumber")
	public Integer getTeamsNumber() {
		return this.teamsNumber;
	}

	public void setTeamsNumber(Integer teamsNumber) {
		this.teamsNumber = teamsNumber;
	}

	@Column(name = "teamsCreateTime", length = 0)
	public Timestamp getTeamsCreateTime() {
		return this.teamsCreateTime;
	}
}
