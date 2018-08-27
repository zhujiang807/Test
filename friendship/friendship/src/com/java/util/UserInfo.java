package com.java.util;

import java.io.Serializable;
import com.java.po.QqUser;

/**
 * 存放用户登录信息
 * @author Administrator
 *
 */
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private QqUser user;
	private String userName;
	private String key;
	
	
	public UserInfo() {}
	
	public UserInfo(QqUser user, String userName, String key) {
		this.user = user;
		this.userName = userName;
		this.key = key;
	}
	
	public UserInfo(String userName, String key) {
		this.userName = userName;
		this.key = key;
	}
	

	public QqUser getUser() {
		return user;
	}
	public void setUser(QqUser user) {
		this.user = user;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}
