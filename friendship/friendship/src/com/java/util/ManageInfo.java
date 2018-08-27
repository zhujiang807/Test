package com.java.util;

import java.io.Serializable;
import com.java.po.Manage;

/**
 * 存放管理员登录信息
 * @author Administrator
 *
 */
public class ManageInfo  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Manage manage;
	private String manageName;
	private String key;
	
	public Manage getManage() {
		return manage;
	}
	public void setManage(Manage manage) {
		this.manage = manage;
	}
	public String getManageName() {
		return manageName;
	}
	public void setManageName(String manageName) {
		this.manageName = manageName;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}
