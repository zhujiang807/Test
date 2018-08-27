package com.java.util.aop;

import javax.annotation.Resource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.friendship.manage.dao.IUserLogDao;
import com.java.po.Manage;
import com.java.po.QqUser;
import com.java.po.UserLog;
import com.java.util.DateBase;
import com.java.util.ThreadLocalUtil;
import com.java.util.annotation.UserLogAnnotation;

@Component("userLogAspect")
@Aspect
@Order(1)
public class UserLogAspect {
	private IUserLogDao userLogDao;
	@Resource(name="userLogDao")
	public void setUserLogDao(IUserLogDao userLogDao) {
		this.userLogDao = userLogDao;
	}

	@AfterReturning("within(com.friendship..*.service.impl..*) && @annotation(annotation)")  
	public void after(JoinPoint join, UserLogAnnotation annotation){
		operation(join, annotation, "after");
	}

	@AfterThrowing("within(com.friendship..*.service.impl..*) && @annotation(annotation)")
	public void throwing(JoinPoint join, UserLogAnnotation annotation){
		operation(join, annotation, "throwing");
	}

	private void operation(JoinPoint join, UserLogAnnotation annotation, String after){
		//前台还是后台
		String type = annotation.type();
		Manage manage = null;
		QqUser user = null;

		UserLog log = new UserLog();
		StringBuffer content = new StringBuffer();
		if(type.equals("后")){
			//获取线层中的指定用户数据
			manage = ThreadLocalUtil.getManage();
			if(manage != null){
				log.setManageId(manage.getManId());
				log.setUserId(0);
				content.append("管理员:");
				content.append(manage.getManName()).append(",在");

			}
		}else if(type.equals("前")){
			user = ThreadLocalUtil.getUser();
			if(user != null){
				log.setUserId(user.getUserId());
				log.setManageId(0);
				content.append("用户qq:");
				content.append(user.getUserQQ()).append(",在");
			}
		}
		content.append(DateBase.getDateString());
		content.append(" 的时候进行了：");
		content.append(annotation.content());
		if(after.equals("throwing"))
			content.append(";操作失败,抛出异常！");
		
		//获取指定方法的返回值
		String con = "";
		synchronized (con) {
			con = UserLogConfiguration.getLogContent(annotation.method(), join.getArgs(), join.getThis(), after);
		}

		content.append(con.equals("")?"":";其他信息："+con);
		log.setLogMethod(annotation.method().toString().toLowerCase());
		log.setLogContent(content.toString());
		log.setLogCreateTime(DateBase.getTimestampYMDHMS());
		userLogDao.save(log);
	}
}
