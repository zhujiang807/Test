package com.friendship.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.friendship.teams.dao.IAreaDao;
import com.friendship.user.dao.IActivitiesDao;
import com.friendship.user.service.IActivitiesService;
import com.java.po.UserActivities;
import com.java.util.annotation.UserLogAnnotation;
import com.java.util.aop.UserLogConfiguration;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

@Service("activitiesService")
public class ActivitiesService implements IActivitiesService{
	private IActivitiesDao activitiesDao;
	private IAreaDao areaDao;

	@Resource(name="activitiesDao")
	public void setActivitesDao(IActivitiesDao activitiesDao) {
		this.activitiesDao = activitiesDao;
	}
	@Resource(name="areaDao")
	public void setAreaDao(IAreaDao areaDao) {
		this.areaDao = areaDao;
	}
	@Override
	@Transactional(readOnly=true)
	public PagePagingData getActivitiesList(AjaxPagingData pagingData) {
		PagePagingData data = new PagePagingData();
		data.setTotal(activitiesDao.getCount(pagingData));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("activities",activitiesDao.getActivitiesList(pagingData));
		map.put("area",areaDao.getAreaList());
		data.setData(map);
		return data;
	}
	
	@Override
	@Transactional
	@UserLogAnnotation(content = "添加活动", type = "后", method = UserLogConfiguration.MethodName.ACTIVITIES_SAVE)
	public void save(UserActivities entity) {
		activitiesDao.save(entity);
	}
	
	@Override
	@Transactional
	@UserLogAnnotation(content = "删除活动", type = "后", method = UserLogConfiguration.MethodName.ACTIVITIES_DELETE)
	public void delete(UserActivities entity) {
		activitiesDao.del(entity);
	}
	
	@Override
	@Transactional
	@UserLogAnnotation(content = "修改活动", type = "后", method = UserLogConfiguration.MethodName.ACTIVITIES_UPDATE)
	public Integer update(UserActivities entity) {
		return activitiesDao.updateAct(entity);
	}
	@Override
	@Transactional(readOnly=true)
	public List<UserActivities> actHomepage() {
		return activitiesDao.actHomepage();
	}
	
	@Override
	@Transactional(readOnly=true)
	public PagePagingData getActivitiesListF(AjaxPagingData pagingData) {
		PagePagingData data = new PagePagingData();
		data.setTotal(activitiesDao.getCount(pagingData));
		data.setData(activitiesDao.getActivitiesListF(pagingData));
		return data;
	}
	
}
