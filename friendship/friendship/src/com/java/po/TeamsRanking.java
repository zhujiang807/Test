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
 * TeamsRanking entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "teams_ranking", catalog = "test")
public class TeamsRanking implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer ranId;
	private Integer activitiesId;
	private Integer teamsId;
	private Integer ranNumber;
	private String ranRemarks;
	private Timestamp ranCreateTime;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ranId", unique = true, nullable = false)
	public Integer getRanId() {
		return this.ranId;
	}

	public void setRanId(Integer ranId) {
		this.ranId = ranId;
	}

	@Column(name = "activitiesId")
	public Integer getActivitiesId() {
		return this.activitiesId;
	}

	public void setActivitiesId(Integer activitiesId) {
		this.activitiesId = activitiesId;
	}

	@Column(name = "teamsId")
	public Integer getTeamsId() {
		return this.teamsId;
	}

	public void setTeamsId(Integer teamsId) {
		this.teamsId = teamsId;
	}

	@Column(name = "ranNumber")
	public Integer getRanNumber() {
		return this.ranNumber;
	}

	public void setRanNumber(Integer ranNumber) {
		this.ranNumber = ranNumber;
	}

	@Column(name = "ranRemarks", length = 100)
	public String getRanRemarks() {
		return this.ranRemarks;
	}

	public void setRanRemarks(String ranRemarks) {
		this.ranRemarks = ranRemarks;
	}

	@Column(name = "ranCreateTime", length = 0)
	public Timestamp getRanCreateTime() {
		return this.ranCreateTime;
	}

	public void setRanCreateTime(Timestamp ranCreateTime) {
		this.ranCreateTime = ranCreateTime;
	}

}
