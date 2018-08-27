package com.friendship.manage.dao.impl;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.friendship.manage.dao.IManageDao;
import com.java.po.Manage;
import com.java.util.HibernateDaoBase;

@Repository("manageDao")
public class ManageDao extends HibernateDaoBase<Manage> implements IManageDao{

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Manage loginVerify(final String qq) {
		final String sql = "select * from manage where manQq=?";
		return super.getHibernateTemplate().execute( new HibernateCallback() {
			@Override
	        public Manage doInHibernate(Session session)  {
				Query query = session.createSQLQuery(sql).addEntity(Manage.class);
				query.setString(0, qq);
				List<Manage> list = query.list();
				if(list == null || list.size() <= 0){
					return null;
				}
				return list.get(0);
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Integer updateManage(final String newPassword, final Integer manageId) {
		Manage manage =  super.getHibernateTemplate().execute( new HibernateCallback() {
			@Override
	        public Manage doInHibernate(Session session)  {
				String sql = "select * from manage where manId=?";
				Query query = session.createSQLQuery(sql).addEntity(Manage.class);
				query.setInteger(0, manageId);
				List<Manage> list = query.list();
				if(list == null || list.size() <= 0){
					return null;
				}
				return list.get(0);
			}
		});
		
		if(manage !=null){
			manage.setManPassword(newPassword);
			super.update(manage);
			return 1;
		}
		
		return null;
	}

	@Override
	public void del(Manage entity) {
		super.delete(super.get("from Manage where manId=?", entity.getManId()));
	}

	@Override
	public List<Manage> getManageList() {
		return super.find("from Manage");
	}

}
