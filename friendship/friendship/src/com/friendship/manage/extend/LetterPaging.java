package com.friendship.manage.extend;

import com.java.po.PrivateLetter;

public class LetterPaging extends PrivateLetter{
	private static final long serialVersionUID = 1L;
	
	private String userQQ;
	private String userQQName;
	
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
