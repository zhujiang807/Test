package com.java.util.interceptor;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.java.util.Cache;
import com.java.util.CacheBase;
import com.java.util.DateBase;
import com.java.util.ThreadLocalUtil;
import com.java.util.Tool;
import com.java.util.annotation.ActionVerifyAnnotation;
import com.java.util.annotation.ManageVerifyAnnotation;
import com.java.util.annotation.UserVerifyAnnotation;

/**
 * 防止sql注入拦截器
 * @author Administrator
 *
 */
public class ActionVerifyInterceptor implements HandlerInterceptor{
	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
					throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		String manageSessionkey = request.getParameter("managesessionkey");
		String userSessionKey = request.getParameter("usersessionkey");
		
		
		
		if(manageSessionkey != null){
			CacheBase.updateTime(manageSessionkey);
		}
		
		if(userSessionKey != null){
			CacheBase.updateTime(userSessionKey);
		}

		Class bean = obj.getClass();
		String[] methodName = request.getRequestURI().split("/");
		String methodName2 = methodName[methodName.length-1];
		methodName2 = methodName2.substring(0, methodName2.length()-4);
		Method[] methods = bean.getDeclaredMethods();
		for (Method method : methods) {
			ActionVerifyAnnotation actionVerifyAnnotation = method.getAnnotation(ActionVerifyAnnotation.class);
			ManageVerifyAnnotation manageVerifyAnnotation = method.getAnnotation(ManageVerifyAnnotation.class);
			UserVerifyAnnotation userVerifyAnnotation = method.getAnnotation(UserVerifyAnnotation.class);
			
			//判定非法注入
			if(method.getName().equals(methodName2)){
				if(actionVerifyAnnotation != null){
					Map<String, String[]> param = request.getParameterMap();
					for (String p : param.keySet()) {
						for (String value : param.get(p)){
							if(Tool.hasSQLKeyword(value)){
								response.setCharacterEncoding("UTF-8");
								response.setHeader("Content-type", "text/html;charset=UTF-8");
								PrintWriter write = response.getWriter();
								write.write("非法注入,请不要搞破坏！");
								write.close();
								return false;
							}
						}
					}
				}
				
				//判定管理员是否登录
				if(manageVerifyAnnotation != null){
					if(manageSessionkey != null && manageSessionkey.toString().length() > 0){
						Cache cache = CacheBase.get(manageSessionkey);
						if(cache == null) {
							setManage(response, request);
							return false;
						}

						if((DateBase.getDateYMDHMS().getTime() - cache.getOperationTime().getTime()) > cache.getDepositTime()){
							setManage(response, request);
							CacheBase.remove(manageSessionkey);
							return false;
						}
					}else{
						setManage(response, request);
						return false;
					}
				}
				
				//判定用户是否登录
				if(userVerifyAnnotation != null){
					if(userSessionKey != null && userSessionKey.toString().length() > 0){
						Cache cache = CacheBase.get(userSessionKey);
						if(cache == null) {
							setUser(response, request);
							return false;
						}
						if((DateBase.getDateYMDHMS().getTime() - cache.getOperationTime().getTime()) > cache.getDepositTime()){
							setUser(response, request);
							CacheBase.remove(userSessionKey);
							return false;
						}
					}else{
						setUser(response, request);
						return false;
					}
				}
			}
		}
		
		ThreadLocalUtil.setHttpServletRequest(request);
		return true;
	}

	private void setUser(HttpServletResponse response, HttpServletRequest request) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		String ajax = request.getHeader("X-Requested-With");
		PrintWriter write = response.getWriter();
		if(ajax != null && ajax.equals("XMLHttpRequest"))
			write.write("请先登录,在做操作！");
		else
			write.write("<html><body><script type=\"text/javascript\">alert('请先登录!');</script></body><html>");
		write.close();
	}
	
	private void setManage(HttpServletResponse response, HttpServletRequest request) throws Exception{
		String ajax = request.getHeader("X-Requested-With");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		PrintWriter write = response.getWriter();
		if(ajax != null && ajax.equals("XMLHttpRequest"))
			write.write("没有权限/登录过期！");
		else{
			write.write("<html><body><script type=\"text/javascript\">" +
					"window.parent.frames.location.href='../../index.html';alert('重新登录!')" +
					"</script></body><html>");
		}
		write.close();
	}
}
