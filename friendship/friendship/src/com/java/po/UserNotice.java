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
 * 用户通知表
 * @author Administrator
 *
 */
@Entity
@Table(name = "user_notice", catalog = "test")
public class UserNotice implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer noticeId;
	private Integer userId;
	private Integer manageId;
	private String noticeContent;
	private Timestamp noticeCreateTime;
	
	
	public UserNotice() {}
	
	public UserNotice(Integer userId, Integer manageId,
			String noticeContent, Timestamp noticeCreateTime) {
		this.userId = userId;
		this.manageId = manageId;
		this.noticeContent = noticeContent;
		this.noticeCreateTime = noticeCreateTime;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "noticeId", unique = true, nullable = false)
	public Integer getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
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
	@Column(name = "noticeContent", length = 100)
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	@Column(name = "noticeCreateTime")
	public Timestamp getNoticeCreateTime() {
		return noticeCreateTime;
	}
	public void setNoticeCreateTime(Timestamp noticeCreateTime) {
		this.noticeCreateTime = noticeCreateTime;
	}
}
