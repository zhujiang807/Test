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
 * ActivitiesMatch entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "activities_match", catalog = "test")
public class ActivitiesMatch extends Extend  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer matchId;
	private Integer activitiesId;
	private Integer teamsId1;
	private Integer teamsId2;
	private Integer teamsVictory1;
	private Integer teamsVictory2;
	private Timestamp matchBeginTime;
	private Timestamp matchEndTime;
	private Integer matchType;
	private Integer matchShow;
	private Timestamp matchCreateTime;
	

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "matchId", unique = true, nullable = false)
	public Integer getMatchId() {
		return this.matchId;
	}

	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
	}

	@Column(name = "activitiesId")
	public Integer getActivitiesId() {
		return activitiesId;
	}

	public void setActivitiesId(Integer activitiesId) {
		this.activitiesId = activitiesId;
	}
	
	@Column(name = "teamsId1")
	public Integer getTeamsId1() {
		return teamsId1;
	}

	public void setTeamsId1(Integer teamsId1) {
		this.teamsId1 = teamsId1;
	}

	@Column(name = "teamsId2")
	public Integer getTeamsId2() {
		return teamsId2;
	}

	public void setTeamsId2(Integer teamsId2) {
		this.teamsId2 = teamsId2;
	}

	@Column(name = "teamsVictory1")
	public Integer getTeamsVictory1() {
		return teamsVictory1;
	}
	
	public void setTeamsVictory1(Integer teamsVictory1) {
		this.teamsVictory1 = teamsVictory1;
	}
	
	@Column(name = "teamsVictory2")
	public Integer getTeamsVictory2() {
		return teamsVictory2;
	}

	public void setTeamsVictory2(Integer teamsVictory2) {
		this.teamsVictory2 = teamsVictory2;
	}

	@Column(name = "matchBeginTime", length = 0)
	public Timestamp getMatchBeginTime() {
		return this.matchBeginTime;
	}

	public void setMatchBeginTime(Timestamp matchBeginTime) {
		this.matchBeginTime = matchBeginTime;
	}

	@Column(name = "matchEndTime", length = 0)
	public Timestamp getMatchEndTime() {
		return this.matchEndTime;
	}

	public void setMatchEndTime(Timestamp matchEndTime) {
		this.matchEndTime = matchEndTime;
	}

	@Column(name = "matchCreateTime", length = 0)
	public Timestamp getMatchCreateTime() {
		return this.matchCreateTime;
	}

	@Column(name = "matchType")
	public Integer getMatchType() {
		return matchType;
	}

	public void setMatchType(Integer matchType) {
		this.matchType = matchType;
	}


	@Column(name = "matchShow")
	public Integer getMatchShow() {
		return matchShow;
	}

	public void setMatchShow(Integer matchShow) {
		this.matchShow = matchShow;
	}

	public void setMatchCreateTime(Timestamp matchCreateTime) {
		this.matchCreateTime = matchCreateTime;
	}

}
