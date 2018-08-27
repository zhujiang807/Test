package com.friendship.user.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.friendship.user.dao.ICommentDao;
import com.friendship.user.extend.CommentPaging;
import com.java.po.Comment;
import com.java.util.HibernateDaoBase;
import com.java.util.paging.AjaxPagingData;

@Repository("commentDao")
public class CommentDao extends HibernateDaoBase<Comment> implements ICommentDao{

	@Override
	public void del(Comment entity) {
		super.delete(super.get("from Comment where comId=?", entity.getComId()));
	}

	@Override
	public List<CommentPaging> getCommentList(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select comm.*,teams.teamsName,video.videoName,video.videoUrl,act.actName from comment as comm left join lol_teams as teams on teams.teamsId=comm.teamsId left join lol_video as video on video.videoId=comm.videoId left join qq_user as user on user.userId=comm.userId left join user_activities as act on act.actId=comm.actId ");
		return getList(sql, pagingData);
	}

	@Override
	public Integer getCount(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select count(*) from comment as comm left join lol_teams as teams on teams.teamsId=comm.teamsId left join lol_video as video on video.videoId=comm.videoId left join qq_user as user on user.userId=comm.userId left join user_activities as act on act.actId=comm.actId ");
		return super.getCountSql(getPagingSql(sql, pagingData));
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<CommentPaging> getList(StringBuffer sql, AjaxPagingData pagingData){
		final Integer item = pagingData.getItem();
		final Integer page = pagingData.getPage();
		final String s = super.getPagingSql(sql, pagingData);
		return super.getHibernateTemplate().execute( new HibernateCallback() {
			@Override
			public List<CommentPaging> doInHibernate(Session session)  {
				Integer item2;
				if(item == null || item < 1)
					item2 = 10;
				else
					item2 = item;
				Query query = session.createSQLQuery(s).setResultTransformer(Transformers.aliasToBean(CommentPaging.class));
				query.setFirstResult((page-1)*item2);
				query.setMaxResults(item2);
				List list = query.list();
				return list;
			}
		});
	}

	@Override
	public List<CommentPaging> getCommentListT(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select comm.comContent,user.userName,comm.comCreateTime from comment as comm left join qq_user as user on user.userId=comm.userId left join lol_video as video on video.videoId=comm.videoId");
		return getList(sql, pagingData);
	}
}
