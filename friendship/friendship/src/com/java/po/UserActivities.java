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
 * UserActivities entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_activities", catalog = "test")
public class UserActivities extends Extend implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer actId;
	private Integer areaId;
	private Integer actCode;
	private String actName;
	private String actUrl;
	private String actPictureAddress;
	private Timestamp actBeginTime;
	private Timestamp actEndTime;
	private String actRemarks;
	private Integer actShow;
	private Integer actAmount;
	private Timestamp actCreateTime;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "actId", unique = true, nullable = false)
	public Integer getActId() {
		return this.actId;
	}

	public void setActId(Integer actId) {
		this.actId = actId;
	}

	@Column(name = "actName", length = 50)
	public String getActName() {
		return this.actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}
	
	@Column(name = "actCode")
	public Integer getActCode() {
		return actCode;
	}

	public void setActCode(Integer actCode) {
		this.actCode = actCode;
	}

	@Column(name = "areaId", length = 50)
	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	@Column(name = "actUrl", length = 50)
	public String getActUrl() {
		return this.actUrl;
	}

	public void setActUrl(String actUrl) {
		this.actUrl = actUrl;
	}

	@Column(name = "actPictureAddress", length = 50)
	public String getActPictureAddress() {
		return this.actPictureAddress;
	}

	public void setActPictureAddress(String actPictureAddress) {
		this.actPictureAddress = actPictureAddress;
	}

	@Column(name = "actBeginTime", length = 0)
	public Timestamp getActBeginTime() {
		return this.actBeginTime;
	}

	public void setActBeginTime(Timestamp actBeginTime) {
		this.actBeginTime = actBeginTime;
	}

	@Column(name = "actEndTime", length = 0)
	public Timestamp getActEndTime() {
		return this.actEndTime;
	}

	public void setActEndTime(Timestamp actEndTime) {
		this.actEndTime = actEndTime;
	}

	@Column(name = "actRemarks", length = 200)
	public String getActRemarks() {
		return this.actRemarks;
	}

	public void setActRemarks(String actRemarks) {
		this.actRemarks = actRemarks;
	}

	@Column(name = "actShow")
	public Integer getActShow() {
		return this.actShow;
	}

	public void setActShow(Integer actShow) {
		this.actShow = actShow;
	}

	@Column(name = "actAmount")
	public Integer getActAmount() {
		return this.actAmount;
	}

	public void setActAmount(Integer actAmount) {
		this.actAmount = actAmount;
	}

	@Column(name = "actCreateTime", length = 0)
	public Timestamp getActCreateTime() {
		return this.actCreateTime;
	}

	public void setActCreateTime(Timestamp actCreateTime) {
		this.actCreateTime = actCreateTime;
	}


}
