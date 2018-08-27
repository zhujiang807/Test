package com.friendship.manage.action;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.friendship.manage.service.ILetterService;
import com.java.po.PrivateLetter;
import com.java.util.ActionBase;
import com.java.util.DateBase;
import com.java.util.annotation.ManageVerifyAnnotation;
import com.java.util.annotation.UserVerifyAnnotation;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.EntityFields;

@Controller
@RequestMapping(value="/letter")
@Scope
public class LetterControl extends ActionBase{
	private ILetterService letterService;
	private static Logger logger = Logger.getLogger(LetterControl.class); 
	@Resource(name="letterService")
	public void setLetterService(ILetterService letterService) {
		this.letterService = letterService;
	}
	
	@RequestMapping(value="getLetterList.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void getLetterList(HttpServletResponse response, AjaxPagingData pagingData)throws Exception{
		if(pagingData.getFieldsString() != null && pagingData.getFieldsString().length() > 0){
			List<EntityFields> fields = new ObjectMapper().readValue(pagingData.getFieldsString(), new TypeReference<List<EntityFields>>() {});
			pagingData.setFields(fields);
		}
		super.writeString(response, letterService.getLetterList(pagingData));
	}
	
	@RequestMapping(value="deleteLetter.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void deleteLetter(HttpServletResponse response, PrivateLetter entity)throws Exception{
		try {
			letterService.delete(entity);
			super.writeString(response, "删除成功！");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	@RequestMapping(value="updateCheck.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void updateCheck(HttpServletResponse response, PrivateLetter entity)throws Exception{
		try {
			letterService.updateCheck(entity);
			super.writeString(response, "回复成功！");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	@RequestMapping(value="addLetterF.htm",method=RequestMethod.POST)
	@ResponseBody
	@UserVerifyAnnotation
	public void addLetterF(HttpServletResponse response, String usersessionkey, PrivateLetter entity)throws Exception{
		if(entity.getLetterContent().length() > 1000){
			super.writeString(response, "私信太多了！");
		}
		entity.setLetterCreateTime(DateBase.getDateYMD());
		entity.setUserId(getUserId(usersessionkey));
		entity.setLetterCheck(0);
		super.writeString(response, letterService.save(entity));
	}
}
