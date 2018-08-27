package com.friendship.user.action;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.friendship.user.service.IUserService;
import com.java.po.QqUser;
import com.java.util.ActionBase;
import com.java.util.Cache;
import com.java.util.CacheBase;
import com.java.util.DateBase;
import com.java.util.JSONFormat;
import com.java.util.Tool;
import com.java.util.UserInfo;
import com.java.util.annotation.ActionVerifyAnnotation;
import com.java.util.annotation.ManageVerifyAnnotation;
import com.java.util.annotation.UserVerifyAnnotation;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.EntityFields;

@Controller
@RequestMapping(value="/user")
@Scope
public class UserControl extends ActionBase{
	private IUserService userService;
	private JSONFormat format;
	@Resource(name="userService")
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	/**
	 * 返回后台user数据
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="getUserList.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void getUserList(HttpServletResponse response,AjaxPagingData pagingData)throws Exception{
		format = new JSONFormat();
		if(pagingData.getFieldsString() != null && pagingData.getFieldsString().length() > 0){
			List<EntityFields> fields = new ObjectMapper().readValue(pagingData.getFieldsString(), new TypeReference<List<EntityFields>>() {});
			pagingData.setFields(fields);
		}
		format.setData(userService.getUserList(pagingData));
		super.writeString(response, format);
	}

	@RequestMapping(value="loginF.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	public void loginF(HttpServletResponse response,String userQQ, String userPassword)throws Exception{
		format = new JSONFormat();
		if(userQQ == null || userQQ.length() < 1 || userPassword == null || userPassword.length() < 1){
			format.setType("error");
			format.setData("用户名或密码错误！");
		}else{
			Pattern pattern = Pattern.compile("[0-9]*"); 
			Matcher isNum = pattern.matcher(userQQ);
			if(!isNum.matches() ){
				format.setType("error");
				format.setData("用户名或密码错误！");
			}else{
				QqUser user = userService.loginF(userQQ);
				if(user == null){
					format.setType("error");
					format.setData("用户名或密码错误！");
					return;
				}else{
					if(user.getUserPassword().equals(userPassword)){
						String key = Tool.getSessionUserKey(userQQ);

						format.setType("success");
						format.setData(new UserInfo(user.getUserName(), key));

						CacheBase.push(key, new UserInfo(user,user.getUserName(), key));
					}else{
						format.setType("error");
						format.setData("用户名或密码错误！");
					}
				}
			}
		}
		super.writeStringNotNull(response, format);
	}

	@RequestMapping(value="QQloginF.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	public void QQloginF(HttpServletResponse response)throws Exception{

	}

	/**
	 *用户是否登录检验
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="userCheckoutF.htm",method=RequestMethod.POST)
	public void userCheckoutF(HttpServletResponse response, String usersessionkey)throws Exception{
		if(usersessionkey == null){
			super.writeString(response, "error");
			return;
		}

		Cache cache = CacheBase.get(usersessionkey);
		if(cache == null) {
			super.writeString(response, "error");
		}else{
			if((DateBase.getDateYMD().getTime() - cache.getOperationTime().getTime()) > cache.getDepositTime()){
				super.writeString(response, "error");
			}else{
				super.writeString(response, "success");
			}
		}
	}

	@RequestMapping(value="userInfoF.htm",method=RequestMethod.POST)
	@ResponseBody
	@UserVerifyAnnotation
	public void userInfoF(HttpServletResponse response, String usersessionkey)throws Exception{
		super.writeString(response, getUser(usersessionkey).getUser());
	}

	@RequestMapping(value="updateUserF.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	@UserVerifyAnnotation
	public void updateUserF(HttpServletResponse response, String usersessionkey, QqUser entity)throws Exception{
		UserInfo userInfo = getUser(usersessionkey);
		QqUser user = userInfo.getUser();
		entity.setUserId(user.getUserId());
		entity.setUserCreateTime(user.getUserCreateTime());
		entity.setUserQQ(user.getUserQQ());
		entity.setUserQQEmail(user.getUserQQEmail());
		entity.setUserQQName(user.getUserQQName());
		user = userService.userUpdate(entity);
		userInfo.setUser(user);
		super.writeString(response, "修改成功！");
	}
}
