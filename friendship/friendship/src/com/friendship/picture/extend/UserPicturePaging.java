package com.friendship.picture.extend;

import com.java.po.UserPicture;

public class UserPicturePaging extends UserPicture{
	private static final long serialVersionUID = 1L;

	private String userQQ;
	private String userQQName;
	private String userName;
	
	public String getUserQQ() {
		return userQQ;
	}
	public void setUserQQ(String userQQ) {
		this.userQQ = userQQ;
	}
	public String getUserQQName() {
		return userQQName;
	}
	public void setUserQQName(String userQQName) {
		this.userQQName = userQQName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
