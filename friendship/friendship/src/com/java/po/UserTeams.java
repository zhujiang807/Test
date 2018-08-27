package com.java.po;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_teams", catalog = "test")
public class UserTeams implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer userTeamsId;
	private Integer userId;
	private Integer teamsId;
	private Integer userTeamscheck;
	private Timestamp createTime;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "userTeamsId", unique = true, nullable = false)
	public Integer getUserTeamsId() {
		return userTeamsId;
	}
	public void setUserTeamsId(Integer userTeamsId) {
		this.userTeamsId = userTeamsId;
	}
	
	@Column(name = "userId")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Column(name = "teamsId")
	public Integer getTeamsId() {
		return teamsId;
	}
	public void setTeamsId(Integer teamsId) {
		this.teamsId = teamsId;
	}	
	
	@Column(name = "createTime")
	public Timestamp getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "userTeamscheck")
	public Integer getUserTeamscheck() {
		return userTeamscheck;
	}
	
	public void setUserTeamscheck(Integer userTeamscheck) {
		this.userTeamscheck = userTeamscheck;
	}
	
}
