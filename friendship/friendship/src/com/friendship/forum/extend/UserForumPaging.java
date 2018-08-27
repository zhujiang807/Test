package com.friendship.forum.extend;

import com.java.po.UserForum;

public class UserForumPaging extends UserForum{

	private static final long serialVersionUID = 1L;

	private String userQQ;
	private String userName;
	private String forumTitle;
	private String userQQName;
	
	public String getUserQQ() {
		return userQQ;
	}
	public void setUserQQ(String userQQ) {
		this.userQQ = userQQ;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getForumTitle() {
		return forumTitle;
	}
	public void setForumTitle(String forumTitle) {
		this.forumTitle = forumTitle;
	}
	public String getUserQQName() {
		return userQQName;
	}
	public void setUserQQName(String userQQName) {
		this.userQQName = userQQName;
	}
}
