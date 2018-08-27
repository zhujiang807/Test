package com.friendship.manage.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.friendship.manage.service.IConfigService;
import com.java.po.FileConfig;
import com.java.util.ActionBase;
import com.java.util.Constant;
import com.java.util.JSONFormat;
import com.java.util.annotation.ActionVerifyAnnotation;
import com.java.util.annotation.ManageVerifyAnnotation;

@Controller
@RequestMapping(value="/config")
@Scope
public class ConfigControl extends ActionBase{
	private IConfigService configService;
	private JSONFormat format;

	public JSONFormat getFormat() {
		return format;
	}
	public void setFormat(JSONFormat format) {
		this.format = format;
	}
	@Resource(name="configService")
	public void setConfigService(IConfigService configService) {
		this.configService = configService;
	}

	@RequestMapping(value="getConfigList.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void getConfigList(HttpServletResponse response,HttpSession session)throws Exception{
		format = new JSONFormat();
		format.setData(configService.getConfigList());
		super.writeString(response, format);
	}

	@RequestMapping(value="addConfig.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void addConfig(HttpServletResponse response,FileConfig config)throws Exception{
		try {
			config.setConfigCreateTime(super.getDate());
			configService.save(config);		
		} catch (Exception e) {
			e.printStackTrace();
			super.writeString(response, "名称重复！");
		}
		super.writeString(response, "添加成功！");
	}

	@RequestMapping(value="deleteConfig.htm",method=RequestMethod.POST)
	@ResponseBody
	@ActionVerifyAnnotation
	public void deleteConfig(HttpServletResponse response,FileConfig config)throws Exception{
		try {
			configService.delete(config);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeString(response, "id错误！");
		}
		super.writeString(response, "删除成功！");
	}

	@RequestMapping(value="updateConfig.htm",method=RequestMethod.POST)
	@ResponseBody
	@ManageVerifyAnnotation
	public void updateConfig(HttpServletResponse response,FileConfig config)throws Exception{
		try {
			configService.update(config);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeString(response, "名称重复！");
		}
		super.writeString(response, "修改成功！");
	}
	
	/**
	 * 主页底部信息
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="getBottomInfo.htm",method=RequestMethod.POST)
	@ResponseBody
	public void getBottomInfo(HttpServletResponse response)throws Exception{
		super.writeString(response, configService.getConfig(Constant.WEBPAGE_BOTTOM_INFO).getConfigValue());
	}
}
