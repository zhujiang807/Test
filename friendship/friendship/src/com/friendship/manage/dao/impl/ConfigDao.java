package com.friendship.manage.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.friendship.manage.dao.IConfigDao;
import com.java.po.FileConfig;
import com.java.util.HibernateDaoBase;

@Repository("configDao")
public class ConfigDao extends HibernateDaoBase<FileConfig> implements IConfigDao{

	@Override
	public List<FileConfig> getConfigList() {
		return super.find("from FileConfig");
	}

	@Override
	public void deleteConfig(FileConfig entity) {
		super.delete(super.get("from FileConfig where configId=?", entity.getConfigId()));
	}

	@Override
	public Integer updateConfig(FileConfig entity) {
		FileConfig config = super.get("from FileConfig where configId=?", entity.getConfigId());
		config.setConfigName(entity.getConfigName());
		config.setConfigRemarks(entity.getConfigRemarks());
		config.setConfigValue(entity.getConfigValue());
		super.update(config);
		return 1;
	}

	@Override
	public FileConfig getConfig(String configName) {
		return super.get("from FileConfig where configName=?", configName);
	}
	
}
