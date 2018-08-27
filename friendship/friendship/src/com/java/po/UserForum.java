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
@Table(name = "user_forum", catalog = "test")
public class UserForum implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer userForumId;
	private Integer userId;
	private Integer forumId;
	private String content;
	private Timestamp createTime;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "userForumId", unique = true, nullable = false)
	public Integer getUserForumId() {
		return userForumId;
	}
	public void setUserForumId(Integer userForumId) {
		this.userForumId = userForumId;
	}
	@Column(name = "userId")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name = "content", length = 200)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name = "createTime")
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@Column(name = "forumId")
	public Integer getForumId() {
		return forumId;
	}
	public void setForumId(Integer forumId) {
		this.forumId = forumId;
	}
	
}
