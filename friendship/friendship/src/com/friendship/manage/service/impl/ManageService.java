package com.friendship.manage.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.friendship.manage.dao.IManageDao;
import com.friendship.manage.service.IManageService;
import com.java.po.Manage;
import com.java.util.JSONFormat;
import com.java.util.annotation.UserLogAnnotation;
import com.java.util.aop.UserLogConfiguration;

@Service("manageService")
public class ManageService implements IManageService{
	private IManageDao manageDao;
	
	@Resource(name="manageDao")
	public void setManageDao(IManageDao manageDao) {
		this.manageDao = manageDao;
	}
	
	@Override
	@Transactional(readOnly=true)
	public Boolean loginVerify(String qq) {
		Manage manage = manageDao.loginVerify(qq);
		return manage == null?false:true;
	}

	@Override
	@Transactional(readOnly=true)
	public JSONFormat login(Manage entity) {
		JSONFormat format = new JSONFormat();
		Manage manage = manageDao.loginVerify(entity.getManQq());
		if(manage != null){
			if(manage.getManName().equals(entity.getManName())){
				if(manage.getManPassword().equals(entity.getManPassword())){
					format.setType("success");
					format.setData(manage);
				}else{
					format.setType("error");
					format.setMessage("用户名/密码错误！");
				}
			}else{
				format.setType("error");
				format.setMessage("用户名/密码错误！");
			}
		}else{
			format.setType("error");
			format.setMessage("用户名/密码错误！");
		}
		return format;
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "修改管理员", type = "后", method = UserLogConfiguration.MethodName.MANAGE_UPDATEMANAGE)
	public Integer updateManage(String newPassword, Integer manageId) {
		return manageDao.updateManage(newPassword, manageId);
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "添加管理员", type = "后", method = UserLogConfiguration.MethodName.MANAGE_SAVE)
	public void save(Manage entity) {
		manageDao.save(entity);
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "删除管理员", type = "后", method = UserLogConfiguration.MethodName.MANAGE_DELETE)
	public void delete(Manage entity) {
		manageDao.del(entity);
	}

	@Override
	@Transactional
	public List<Manage> getManageList() {
		return manageDao.getManageList();
	}
}
