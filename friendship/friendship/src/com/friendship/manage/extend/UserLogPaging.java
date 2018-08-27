package com.friendship.manage.extend;

import com.java.po.UserLog;

public class UserLogPaging extends UserLog{

	private static final long serialVersionUID = 1L;

	private String userName;
	private String userQQ;
	private String manName;
	private String userQQName;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserQQ() {
		return userQQ;
	}
	public void setUserQQ(String userQQ) {
		this.userQQ = userQQ;
	}
	public String getManName() {
		return manName;
	}
	public void setManName(String manName) {
		this.manName = manName;
	}
	public String getUserQQName() {
		return userQQName;
	}
	public void setUserQQName(String userQQName) {
		this.userQQName = userQQName;
	}
}
