package com.java.po;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "advertisement", catalog = "test")
public class Advertisement extends Extend{
	private Integer advId;
	private String userQQ;
	private String advTitle;
	private String advUrl;
	private String advPictureUrl;
	private String advRemarks;
	private Timestamp advBeginTime;
	private Timestamp advEndTime;
	private Timestamp advCreateTime;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "advId", unique = true, nullable = false)
	public Integer getAdvId() {
		return advId;
	}
	public void setAdvId(Integer advId) {
		this.advId = advId;
	}
	@Column(name = "advTitle", length = 25)
	public String getAdvTitle() {
		return advTitle;
	}
	public void setAdvTitle(String advTitle) {
		this.advTitle = advTitle;
	}
	@Column(name = "advUrl", length = 70)
	public String getAdvUrl() {
		return advUrl;
	}
	public void setAdvUrl(String advUrl) {
		this.advUrl = advUrl;
	}
	@Column(name = "advPictureUrl", length = 100)
	public String getAdvPictureUrl() {
		return advPictureUrl;
	}
	public void setAdvPictureUrl(String advPictureUrl) {
		this.advPictureUrl = advPictureUrl;
	}
	@Column(name = "advRemarks", length = 100)
	public String getAdvRemarks() {
		return advRemarks;
	}
	public void setAdvRemarks(String advRemarks) {
		this.advRemarks = advRemarks;
	}
	@Column(name = "advBeginTime", length = 25)
	public Timestamp getAdvBeginTime() {
		return advBeginTime;
	}
	public void setAdvBeginTime(Timestamp advBeginTime) {
		this.advBeginTime = advBeginTime;
	}
	@Column(name = "advEndTime", length = 25)
	public Timestamp getAdvEndTime() {
		return advEndTime;
	}
	public void setAdvEndTime(Timestamp advEndTime) {
		this.advEndTime = advEndTime;
	}
	@Column(name = "advCreateTime", length = 25)
	public Timestamp getAdvCreateTime() {
		return advCreateTime;
	}
	public void setAdvCreateTime(Timestamp advCreateTime) {
		this.advCreateTime = advCreateTime;
	}
	@Column(name = "userQQ", length = 25)
	public String getUserQQ() {
		return userQQ;
	}
	public void setUserQQ(String userQQ) {
		this.userQQ = userQQ;
	}
}
