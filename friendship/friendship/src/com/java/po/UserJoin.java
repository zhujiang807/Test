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
 * UserJoin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_join", catalog = "test")
public class UserJoin implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer joinId;
	private Integer activitiesId;
	private Integer teamsId;
	private Integer userId;
	private Integer joinResult;
	private Timestamp joinCreateTime;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "joinId", unique = true, nullable = false)
	public Integer getJoinId() {
		return this.joinId;
	}

	public void setJoinId(Integer joinId) {
		this.joinId = joinId;
	}

	@Column(name = "activitiesId")
	public Integer getActivitiesId() {
		return this.activitiesId;
	}

	public void setActivitiesId(Integer activitiesId) {
		this.activitiesId = activitiesId;
	}
	
	@Column(name = "joinResult")
	public Integer getJoinResult() {
		return joinResult;
	}

	public void setJoinResult(Integer joinResult) {
		this.joinResult = joinResult;
	}

	@Column(name = "userId")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "joinCreateTime", length = 0)
	public Timestamp getJoinCreateTime() {
		return this.joinCreateTime;
	}

	public void setJoinCreateTime(Timestamp joinCreateTime) {
		this.joinCreateTime = joinCreateTime;
	}
	
	@Column(name = "teamsId", length = 0)
	public Integer getTeamsId() {
		return teamsId;
	}

	public void setTeamsId(Integer teamsId) {
		this.teamsId = teamsId;
	}

	
}
