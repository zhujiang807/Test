package com.friendship.manage.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.friendship.manage.service.IAdvertisementService;
import com.java.po.Advertisement;
import com.java.util.ActionBase;
import com.java.util.DateBase;
import com.java.util.annotation.ActionVerifyAnnotation;
import com.java.util.annotation.ManageVerifyAnnotation;
import com.java.util.paging.AjaxPagingData;
/**
 * 广告action
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/advertisement")
@Scope
public class AdvertisementControl extends ActionBase{
	private IAdvertisementService advertisementService;
	private static Logger logger = Logger.getLogger(AdvertisementControl.class);
	
	@Resource(name="advertisementService")
	public void setAdvertisementService(IAdvertisementService advertisementService) {
		this.advertisementService = advertisementService;
	}
	
	/**
	 * 返回信息
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="getAdvList.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void getAdvList(HttpServletResponse response,AjaxPagingData pagingData)throws Exception{
		super.writeString(response, advertisementService.getAdvList(pagingData));
	}
	
	/**
	 * 删除
	 * @param response
	 * @param pagingData
	 * @throws Exception
	 */
	@RequestMapping(value="deleteAdv.htm",method=RequestMethod.POST)
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void deleteAdv(HttpServletResponse response, Advertisement entity)throws Exception{
		try {
			advertisementService.delete(entity);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		super.writeString(response, "删除成功！");
	}
	
	/**
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="addAdv.htm",method=RequestMethod.POST)
	@ActionVerifyAnnotation
	@ManageVerifyAnnotation
	public void addAdv(HttpServletResponse response, Advertisement entity)throws Exception{
		try {
			entity.setAdvBeginTime(DateBase.strToTimestamp(entity.getFieldValue1()));
			entity.setAdvEndTime(DateBase.strToTimestamp(entity.getFieldValue2()));
			entity.setAdvCreateTime(super.getDate());
			advertisementService.save(entity);
			super.writeString(response, "添加成功!");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	
	/**
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="advInfoF.htm",method=RequestMethod.POST)
	@ActionVerifyAnnotation
	public void advInfoF(HttpServletResponse response, String advTitle)throws Exception{
		if(advTitle == null || advTitle.length() < 1)
			return;
		super.writeStringNotNull(response, advertisementService.getF(advTitle));
	}
}
