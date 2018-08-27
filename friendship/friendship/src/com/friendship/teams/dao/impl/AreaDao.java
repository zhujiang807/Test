package com.friendship.teams.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.friendship.teams.dao.IAreaDao;
import com.java.po.LolArea;
import com.java.util.HibernateDaoBase;

@Repository("areaDao")
public class AreaDao extends HibernateDaoBase<LolArea> implements IAreaDao{

	@Override
	public List<LolArea> getAreaList() {
		return super.find("from LolArea order by areaSequence asc");
	}

	@Override
	public void deleteArea(LolArea entity) {
		super.delete(super.get("from LolArea where areaId=?", entity.getAreaId()));		
	}

	@Override
	public Integer updateArea(LolArea area) {
		StringBuffer sql = new StringBuffer("update lol_area set areaName='").append(area.getAreaName());
		sql.append("',areaSequence=").append(area.getAreaSequence()).append(" where areaId=").append(area.getAreaId());
		return super.update(sql.toString());
	}

	@Override
	public List<LolArea> getAreaList(String areaName) {
		return super.find("from LolArea where areaName=?", areaName);
	}

	@Override
	public List<LolArea> getAreaListF() {
		return super.find("select areaName from LolArea order by areaSequence asc");
	}

}
