package com.friendship.manage.extend;

import com.java.po.UserMovie;

public class UserMoviePaging extends UserMovie{
	private static final long serialVersionUID = 1L;
	
	private String userQQ;
	private String userQQName;
	private String userName;
	private String movieName;
	
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
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
}
