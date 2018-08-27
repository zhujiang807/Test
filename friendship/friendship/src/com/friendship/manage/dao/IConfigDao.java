package com.friendship.manage.dao;

import java.util.List;

import com.java.po.FileConfig;

public interface IConfigDao {
	public void save(FileConfig entity);
	
	public List<FileConfig> getConfigList();
	
	public void deleteConfig(FileConfig entity);
	
	public Integer updateConfig(FileConfig entity);
	
	public FileConfig getConfig(String configName);
}
