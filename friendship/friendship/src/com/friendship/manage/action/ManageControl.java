package com.friendship.manage.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.friendship.manage.service.IManageService;
import com.java.po.Manage;
import com.java.po.QqUser;
import com.java.util.ActionBase;
import com.java.util.CacheBase;
import com.java.util.JSONFormat;
import com.java.util.ManageInfo;
import com.java.util.Tool;
import com.java.util.annotation.ActionVerifyAnnotation;
import com.java.util.annotation.ManageVerifyAnnotation;
import com.java.util.annotation.UserVerifyAnnotation;

@Controller
@Scope
@RequestMapping(value="/manage")
public class ManageControl extends ActionBase{
	
	private JSONFormat format;
	private IManageService manageService;
	
	@Resource(name="manageService")
	public void setManageService(IManageService manageService) {
		this.manageService = manageService;
	}	

	public JSONFormat getFormat() {
		return format;
	}

	/**
	 * 后台登录判定
	 * @param request
	 * @param manage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="loginVerify.htm",method=RequestMethod.POST)
	@ResponseBody
	@UserVerifyAnnotation
	public void loginVerify(HttpServletResponse response, String usersessionkey)throws Exception{
		format = new JSONFormat();
		Object objUser = getUser(usersessionkey).getUser();
		if(objUser != null){
			QqUser user = (QqUser) objUser;
			if(manageService.loginVerify(Tool.MD5(user.getUserQQ()))){
				format.setType("success");
				format.setRedirect("html/backstage/login.html");
			}else{
				format.setType("error");
				format.setMessage("你没有权限登录!");
			}
		}else{
			format.setType("error");
			format.setMessage("你还没有登录qq!");
		}			
		super.writeString(response, format);
	}

	/**
	 * 后台登录
	 * @param request
	 * @param manage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="login.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@UserVerifyAnnotation
	public void login(HttpServletResponse response, String usersessionkey, HttpServletRequest request, Manage manage)throws Exception{
		format = new JSONFormat();
		Object objUser = getUser(usersessionkey).getUser();
		if(objUser != null){
			manage.setManQq(Tool.MD5(((QqUser)objUser).getUserQQ()));
			format = manageService.login(manage);
			if(format.getType().equals("success")){
				String manageKey = Tool.getSessionManageKey(((Manage)format.getData()).getManQq());
				
				ManageInfo manageInfo2 = new ManageInfo();
				manageInfo2.setManage((Manage)format.getData());
				CacheBase.push(manageKey, manageInfo2);
				
				ManageInfo manageInfo = new ManageInfo();
				manageInfo.setKey(manageKey);
				manageInfo.setManageName(((Manage)format.getData()).getManName());
				format.setRedirect("../../../html/backstage/index.html");
				format.setData(manageInfo);
			}
		}
		super.writeStringNotNull(response, format);
	}
	
	/**
	 * 修改管理员信息
	 * @param response
	 * @param manage
	 * @throws Exception
	 */
	@RequestMapping(value="updateManage.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void updateManage(HttpServletResponse response,String managesessionkey, String formerPassword,String newPassword)throws Exception{
		format = new JSONFormat();		
		Manage manage = getManage(managesessionkey).getManage();
		if(!formerPassword.equals(manage.getManPassword())){
			format.setType("error");
			format.setMessage("旧密码错误!");
		}else{
			Integer result = manageService.updateManage(newPassword, manage.getManId());
			if(result > 0)
				format.setType("success");
			else
				format.setType("error");
		}
		
		super.writeString(response, format);
	}
	
	/**
	 * 
	 * @param response
	 * @param manage
	 * @throws Exception
	 */
	@RequestMapping(value="deleteManage.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void deleteManage(HttpServletResponse response, Manage entity)throws Exception{
		try {
			manageService.delete(entity);
			super.writeString(response, "删除成功！");
		} catch (Exception e) {
		}
	}
	
	/**
	 * 
	 * @param response
	 * @param manage
	 * @throws Exception
	 */
	@RequestMapping(value="addManage.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void addManage(HttpServletResponse response, Manage entity)throws Exception{
		try {
			entity.setManCreateTime(super.getDate());
			entity.setManPassword(Tool.MD5(entity.getManPassword()));
			entity.setManQq(Tool.MD5(entity.getManQq()));
			manageService.save(entity);
			super.writeString(response, "添加成功！");
		} catch (Exception e) {
		}
	}
	
	/**
	 * 
	 * @param response
	 * @param manage
	 * @throws Exception
	 */
	@RequestMapping(value="getManageList.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void getManageList(HttpServletResponse response)throws Exception{
		try {
			super.writeString(response, manageService.getManageList());
		} catch (Exception e) {
		}
	}
}
