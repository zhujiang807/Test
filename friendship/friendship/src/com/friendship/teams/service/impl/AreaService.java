package com.friendship.teams.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.friendship.teams.dao.IAreaDao;
import com.friendship.teams.service.IAreaService;
import com.java.po.LolArea;
import com.java.util.annotation.UserLogAnnotation;
import com.java.util.aop.UserLogConfiguration;

@Service("areaService")
public class AreaService implements IAreaService{
	private IAreaDao areaDao;
	
	@Resource(name="areaDao")
	public void setAreaDao(IAreaDao areaDao) {
		this.areaDao = areaDao;
	}

	@Override
	@Transactional(readOnly=true)
	public List<LolArea> getAreaList() {
		return areaDao.getAreaList();
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "添加大区", type = "后", method = UserLogConfiguration.MethodName.AREA_SAVE)
	public void save(LolArea entity) {
		areaDao.save(entity);
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "删除大区", type = "后", method = UserLogConfiguration.MethodName.AREA_DELETE)
	public void delete(LolArea entity) {
		areaDao.deleteArea(entity);
	}

	@Override
	@Transactional
	public Integer update(LolArea entity) {
		return areaDao.updateArea(entity);
	}

	@Override
	@Transactional(readOnly=true)
	public LolArea get(String areaName) {
		 List<LolArea> list = areaDao.getAreaList(areaName);
		 if(list != null && list.size() > 0){
			 return list.get(0);
		 }
		 return null;
	}

	@Override
	public List<LolArea> getAreaListF() {
		return areaDao.getAreaListF();
	}

}
