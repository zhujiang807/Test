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
@Table(name = "lol_forum", catalog = "test")
public class LolForum implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer forumId;
	private Integer userId;
	private String forumTitle;
	private String forumContent;
	private Timestamp forumCreateTime;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "forumId", unique = true, nullable = false)
	public Integer getForumId() {
		return forumId;
	}
	public void setForumId(Integer forumId) {
		this.forumId = forumId;
	}
	@Column(name = "forumTitle", length = 50)
	public String getForumTitle() {
		return forumTitle;
	}
	public void setForumTitle(String forumTitle) {
		this.forumTitle = forumTitle;
	}
	@Column(name = "forumContent")
	public String getForumContent() {
		return forumContent;
	}
	public void setForumContent(String forumContent) {
		this.forumContent = forumContent;
	}
	@Column(name = "forumCreateTime")
	public Timestamp getForumCreateTime() {
		return forumCreateTime;
	}
	public void setForumCreateTime(Timestamp forumCreateTime) {
		this.forumCreateTime = forumCreateTime;
	}
	@Column(name = "userId")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
