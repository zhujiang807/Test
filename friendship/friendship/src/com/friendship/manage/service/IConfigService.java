package com.friendship.manage.service;

import java.util.List;

import com.java.po.FileConfig;

public interface IConfigService {
	public void save(FileConfig entity);
	
	public List<FileConfig> getConfigList();
	
	public void delete(FileConfig entity);
	
	public Integer update(FileConfig entity);
	
	public FileConfig getConfig(String configName);

}
