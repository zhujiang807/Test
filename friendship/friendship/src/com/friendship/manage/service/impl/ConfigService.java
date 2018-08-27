package com.friendship.manage.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.friendship.manage.dao.IConfigDao;
import com.friendship.manage.service.IConfigService;
import com.java.po.FileConfig;
import com.java.util.annotation.UserLogAnnotation;
import com.java.util.aop.UserLogConfiguration;

@Service("configService")
public class ConfigService implements IConfigService{
	private IConfigDao configDao;

	@Resource(name="configDao")
	public void setConfigDao(IConfigDao configDao) {
		this.configDao = configDao;
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "添加了一个config配置", type = "后", method = UserLogConfiguration.MethodName.CONFIG_SAVE)
	public void save(FileConfig entity) {
		configDao.save(entity);
	}

	@Override
	@Transactional(readOnly=true)
	public List<FileConfig> getConfigList() {
		return configDao.getConfigList();
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "删除一个config配置", type = "后", method = UserLogConfiguration.MethodName.CONFIG_DELETE)
	public void delete(FileConfig entity) {
		configDao.deleteConfig(entity);
	}

	@Override
	@Transactional
	public Integer update(FileConfig entity) {
		return configDao.updateConfig(entity);
	}

	@Override
	@Transactional(readOnly=true)
	public FileConfig getConfig(String configName) {
		return configDao.getConfig(configName);
	}

}
