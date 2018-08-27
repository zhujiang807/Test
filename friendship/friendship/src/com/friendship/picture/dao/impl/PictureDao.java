package com.friendship.picture.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.friendship.picture.dao.IPictureDao;
import com.friendship.picture.extend.PicturePaging;
import com.java.po.Picture;
import com.java.util.HibernateDaoBase;
import com.java.util.paging.AjaxPagingData;

@Repository("pictureDao")
public class PictureDao extends HibernateDaoBase<Picture> implements IPictureDao{

	@Override
	public List<PicturePaging> getPictureList(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select p.*,pt.pictureTypeName from picture as p left join picture_type as pt on p.pictureTypeId=pt.pictureTypeId ");
		return getList(sql, pagingData);
	}

	@Override
	public void del(Picture entity) {
		super.delete(super.get("from Picture where pictureId=?", entity.getPictureId()));
	}

	@Override
	public Integer getCount(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select count(*) from picture as p left join picture_type as pt on p.pictureTypeId=pt.pictureTypeId ");
		return super.getCountSql(super.getPagingSql(sql,pagingData));
	}

	@Override
	public List<PicturePaging> getPictureListF(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select p.pictureName,p.pictureUrl from picture as p left join picture_type as pt on p.pictureTypeId=pt.pictureTypeId ");
		return getList(sql, pagingData);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<PicturePaging> getList(StringBuffer sql, AjaxPagingData pagingData){
		final Integer item = pagingData.getItem();
		final Integer page = pagingData.getPage();
		final String s = getPagingSql(sql, pagingData);
		return super.getHibernateTemplate().execute( new HibernateCallback() {
			@Override
			public List<PicturePaging> doInHibernate(Session session)  {
				Integer item2;
				if(item == null || item < 1)
					item2 = 10;
				else
					item2 = item;
				Query query = session.createSQLQuery(s).setResultTransformer(Transformers.aliasToBean(PicturePaging.class));
				query.setFirstResult((page-1)*item2);
				query.setMaxResults(item2);
				List list = query.list();
				return list;
			}
		});
	}
}
