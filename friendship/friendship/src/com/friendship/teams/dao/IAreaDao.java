package com.friendship.teams.dao;

import java.util.List;

import com.java.po.LolArea;

public interface IAreaDao {
	public List<LolArea> getAreaList();
	
	public void deleteArea(LolArea entity);
	
	public void save(LolArea entity);
	
	public Integer updateArea(LolArea area);
	
	public List<LolArea> getAreaList(String areaName);
	
	
	public List<LolArea> getAreaListF();
}
