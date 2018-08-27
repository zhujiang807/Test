package com.java.util;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.EntityFields;

public class HibernateDaoBase<T> extends HibernateDaoSupport{
	public HibernateDaoBase(){

	}

	@Resource(name="sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}

	public void saveOrUpdate(T dataBean){
		getHibernateTemplate().saveOrUpdate(dataBean);
	}

	public void save(T dataBean){
		getHibernateTemplate().save(dataBean);
	}
	
	public void save(List<T> dataBean){
		getHibernateTemplate().saveOrUpdateAll(dataBean);
	}

	public void delete(T dataBean){
		getHibernateTemplate().delete(dataBean);
	}

	public void update(T dataBean){
		getHibernateTemplate().update(dataBean);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Integer update(final String sql){
		return super.getHibernateTemplate().execute( new HibernateCallback() {
			@Override
			public Integer doInHibernate(Session session)  {
				Query query = session.createSQLQuery(sql);
				return query.executeUpdate();
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<T> find(String hql,Object ...param){
		return getHibernateTemplate().find(hql, param);
	}

	@SuppressWarnings("unchecked")
	public T get(String hql,Object ...param){
		List<T> list = getHibernateTemplate().find(hql, param);
		if(list == null || list.size() < 1){
			return null;
		}
		return list.get(0);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public T getSql(final String sql){
		List<T> list = super.getHibernateTemplate().execute( new HibernateCallback() {
			@Override
			public List<T> doInHibernate(Session session)  {
				Query query = session.createSQLQuery(sql);
				List list=query.list();
				return list;
			}
		});		
		if(list == null || list.size() < 1){
			return null;
		}
		return list.get(0);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object getField(final String sql){
		List<T> list = super.getHibernateTemplate().execute( new HibernateCallback() {
			@Override
			public List<T> doInHibernate(Session session)  {
				Query query = session.createSQLQuery(sql);
				List list = query.list();
				return list;
			}
		});		
		if(list == null || list.size() < 1){
			return null;
		}
		return list.get(0);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> pagingHql(final String hql, final Integer page, final Integer item){
		return super.getHibernateTemplate().execute( new HibernateCallback() {
			@Override
			public List<T> doInHibernate(Session session)  {
				Integer item2;
				if(item == null || item < 1 || item > 50)
					item2 = 10;
				else if(item > 50)
					item2 = 10;
				else
					item2 = item;
				Query query = session.createQuery(hql);
				query.setFirstResult((page-1)*item2);
				query.setMaxResults(item2);
				List list=query.list();
				return list;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> pagingSql(final String sql, final Integer page, final Integer item, final Class clas){
		return super.getHibernateTemplate().execute( new HibernateCallback() {
			@Override
			public List<T> doInHibernate(Session session)  {
				Integer item2;
				if(item == null || item < 1 || item > 50)
					item2 = 10;
				else
					item2 = item;
				Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(clas));
				query.setFirstResult((page-1)*item2);
				query.setMaxResults(item2);
				List list = query.list();
				return list;
			}
		});
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> getListSql(final String sql, final Class bean){
		return super.getHibernateTemplate().execute( new HibernateCallback() {
			@Override
			public List<T> doInHibernate(Session session)  {				
				Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(bean));;
				List list = query.list();
				return list;
			}
		});
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> getListHql(final String sql, final Class bean){
		return super.getHibernateTemplate().execute( new HibernateCallback() {
			@Override
			public List<T> doInHibernate(Session session)  {				
				Query query = session.createQuery(sql).setResultTransformer(Transformers.aliasToBean(bean));;
				List list = query.list();
				return list;
			}
		});
	}
	
	//hql查询数据条数
	public int getCountHql(String hql){
		List<?> list=this.getHibernateTemplate().find(hql);
		if(list.size()>0){
			Number n=(Number) list.get(0);
			return n.intValue();
		}
		return 0;
	}

	//hql查询数据条数
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Integer getCountSql(final String sql){
		return super.getHibernateTemplate().execute( new HibernateCallback() {
			@Override
			public Integer doInHibernate(Session session)  {
				Query query = session.createSQLQuery(sql);
				List<?> list = query.list();
				if(list.size() > 0){
					Number n=(Number) list.get(0);
					return n.intValue();
				}
				return 0;
			}
		});
	}
	
	public String getPagingSql(StringBuffer sql, AjaxPagingData pagingData){
		if(pagingData == null) return sql.toString();
		Boolean judge = false;
		if((pagingData.getFieldsNameS() != null && pagingData.getFieldsNameS().length() > 0) || (pagingData.getFieldsName2() != null && pagingData.getFieldsName2().length() > 0) || (pagingData.getFieldsName() != null && pagingData.getFieldsName().length() > 0) || (pagingData.getFields() != null && pagingData.getFields().size() > 0)){
			 if(sql.indexOf(" where ") < 0)
				 sql.append(" where ");
			 else
				 sql.append(" and ");
		}
		if(pagingData.getFieldsName() != null && pagingData.getFieldsName().length() > 0){
			sql.append(pagingData.getFieldsName());
			sql.append(pagingData.getFieldsValue().toString().indexOf(" ") >= 0?" ":"=");
			sql.append(pagingData.getFieldsValue());
			judge = true;
		}
		if(pagingData.getFieldsName2() != null && pagingData.getFieldsName2().length() > 0){
			if(judge) sql.append(" and ");
			sql.append(pagingData.getFieldsName2());
			sql.append(pagingData.getFieldsValue2().toString().indexOf(" ") >= 0?" ":"=");
			sql.append(pagingData.getFieldsValue2());
			judge = true;
		}
		if(pagingData.getFieldsNameS() != null && pagingData.getFieldsNameS().length() > 0){
			if(judge) sql.append(" and ");
			sql.append(pagingData.getFieldsNameS()).append(" like'%").append(pagingData.getFieldsValueS()).append("%'");
			judge = true;
		}
		
		List<EntityFields> fields = pagingData.getFields();
		if(fields != null && fields.size() > 0){
			if(judge) sql.append(" and ");
			sql.append("(");
			for (EntityFields field : fields) {
				sql.append(field.getFieldsName()).append(" like'%").append(field.getFieldsValue()).append("%' or ");
			}
			String sq = sql.substring(0, sql.length()-3);
			sql.delete(0, sql.length());
			sql.append(sq);
			sql.append(")");
		}
		
		if(pagingData.getFieldsRank() != null && pagingData.getFieldsRank().length() > 0){
			sql.append(" order by ").append(pagingData.getFieldsRank()).append(" ").append(pagingData.getFieldsRankType());
		}
		
		if(pagingData.getFieldsRank2() != null && pagingData.getFieldsRank2().length() > 0){
			sql.append(",").append(pagingData.getFieldsRank2()).append(" ").append(pagingData.getFieldsRankType2());
		}
		
		return sql.toString();
	}

}
