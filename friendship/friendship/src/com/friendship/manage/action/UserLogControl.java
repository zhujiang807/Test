package com.friendship.manage.action;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.friendship.manage.service.IUserLogService;
import com.java.util.ActionBase;
import com.java.util.annotation.ManageVerifyAnnotation;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.EntityFields;

@Controller
@Scope
@RequestMapping(value="/userLog")
public class UserLogControl extends ActionBase{
	private IUserLogService userLogService;
	@Resource(name="userLogService")
	public void setUserLogService(IUserLogService userLogService) {
		this.userLogService = userLogService;
	}
	
	@RequestMapping(value="userLogListB.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void userLogList(HttpServletResponse response, AjaxPagingData pagingData)throws Exception{
		if(pagingData.getFieldsString() != null && pagingData.getFieldsString().length() > 0){
			List<EntityFields> fields = new ObjectMapper().readValue(pagingData.getFieldsString(), new TypeReference<List<EntityFields>>() {});
			pagingData.setFields(fields);
		}
		super.writeString(response, userLogService.getUserLogList(pagingData));
	}
}
