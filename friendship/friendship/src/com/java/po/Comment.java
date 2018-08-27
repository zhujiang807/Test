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
 * UserComment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "comment")
public class Comment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer comId;
	private Integer userId;
	private Integer teamsId;
	private Integer videoId;
	private Integer actId;
	private String comContent;
	private Timestamp comCreateTime;


	public Comment() {}
	
	public Comment(Integer userId, Integer teamsId, Integer videoId, Integer actId, String comContent, Timestamp comCreateTime) {
		this.userId = userId;
		this.teamsId = teamsId;
		this.videoId = videoId;
		this.actId = actId;
		this.comContent = comContent;
		this.comCreateTime = comCreateTime;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "comId", unique = true, nullable = false)
	public Integer getComId() {
		return this.comId;
	}
	public void setComId(Integer comId) {
		this.comId = comId;
	}

	@Column(name = "teamsId")
	public Integer getTeamsId() {
		return teamsId;
	}
	public void setTeamsId(Integer teamsId) {
		this.teamsId = teamsId;
	}
	@Column(name = "videoId")
	public Integer getVideoId() {
		return videoId;
	}
	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}
	
	@Column(name = "comContent", length = 1500)
	public String getComContent() {
		return this.comContent;
	}

	public void setComContent(String comContent) {
		this.comContent = comContent;
	}

	@Column(name = "comCreateTime")
	public Timestamp getComCreateTime() {
		return this.comCreateTime;
	}

	public void setComCreateTime(Timestamp comCreateTime) {
		this.comCreateTime = comCreateTime;
	}

	@Column(name = "userId")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Column(name = "actId")
	public Integer getActId() {
		return actId;
	}

	public void setActId(Integer actId) {
		this.actId = actId;
	}
}
