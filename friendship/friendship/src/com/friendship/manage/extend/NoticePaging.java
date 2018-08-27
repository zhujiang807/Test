package com.friendship.manage.extend;

import com.java.po.UserNotice;

public class NoticePaging extends UserNotice{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String manName;
	private String userQQ;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getManName() {
		return manName;
	}
	public void setManName(String manName) {
		this.manName = manName;
	}
	public String getUserQQ() {
		return userQQ;
	}
	public void setUserQQ(String userQQ) {
		this.userQQ = userQQ;
	}
}
