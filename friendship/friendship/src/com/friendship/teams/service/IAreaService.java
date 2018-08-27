package com.friendship.teams.service;

import java.util.List;

import com.java.po.LolArea;

public interface IAreaService {
	public List<LolArea> getAreaList();
	
	public void save(LolArea entity);
	
	public void delete(LolArea entity);
	
	public Integer update(LolArea entity);
	
	public LolArea get(String areaName);
	
	
	
	public List<LolArea> getAreaListF();
}
