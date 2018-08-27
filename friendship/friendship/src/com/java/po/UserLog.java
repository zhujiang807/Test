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
@Table(name = "user_log", catalog = "test")
public class UserLog implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer logId;
	private Integer userId;
	private Integer manageId;
	private String logMethod;
	private String logContent;
	private Timestamp logCreateTime;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "logId", unique = true, nullable = false)
	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	@Column(name = "userId")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name = "manageId")
	public Integer getManageId() {
		return manageId;
	}
	public void setManageId(Integer manageId) {
		this.manageId = manageId;
	}
	@Column(name = "logMethod", length = 20)
	public String getLogMethod() {
		return logMethod;
	}
	public void setLogMethod(String logMethod) {
		this.logMethod = logMethod;
	}
	@Column(name = "logContent", length = 200)
	public String getLogContent() {
		return logContent;
	}
	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}
	@Column(name = "logCreateTime")
	public Timestamp getLogCreateTime() {
		return logCreateTime;
	}
	public void setLogCreateTime(Timestamp logCreateTime) {
		this.logCreateTime = logCreateTime;
	}
}
