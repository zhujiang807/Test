package com.java.util;

import javax.servlet.http.HttpServletRequest;
import com.java.po.Manage;
import com.java.po.QqUser;
/**
 * 通过线层获取session数据
 * @author Administrator
 *
 */
public class ThreadLocalUtil {
	private static ThreadLocal<HttpServletRequest> threadLocal = new ThreadLocal<HttpServletRequest>();
	public static HttpServletRequest getHttpServletRequest() {
		return threadLocal.get();
	}

	public static void setHttpServletRequest(HttpServletRequest httpServletRequest) {
		threadLocal.set(httpServletRequest);
	}

	public static QqUser getUser(){
		String userSessionKey = getHttpServletRequest().getParameter("usersessionkey");

		if(userSessionKey != null && userSessionKey.toString().length() > 0){
			Cache cache = CacheBase.get(userSessionKey);
			if(cache != null) {
				UserInfo userInfo = (UserInfo) cache.getData();
				return userInfo.getUser();
			}
		}

		return null;
	}

	public static Manage getManage(){
		String manageSessionkey = getHttpServletRequest().getParameter("managesessionkey");

		if(manageSessionkey != null && manageSessionkey.toString().length() > 0){
			Cache cache = CacheBase.get(manageSessionkey);
			if(cache != null) {
				ManageInfo manageInfo = (ManageInfo) cache.getData();
				return manageInfo.getManage();
			}
		}
		
		return null;
	}
}
