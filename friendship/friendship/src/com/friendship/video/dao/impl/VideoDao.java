package com.friendship.video.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.friendship.video.dao.IVideoDao;
import com.friendship.video.extend.VideoPaging;
import com.java.po.LolVideo;
import com.java.po.VideoType;
import com.java.util.HibernateDaoBase;
import com.java.util.paging.AjaxPagingData;

@Repository("videoDao")
public class VideoDao extends HibernateDaoBase<LolVideo> implements IVideoDao{

	@Override
	public List<LolVideo> getVideoTypeList() {
		return super.getListSql("select * from video_type", VideoType.class);
	}

	@Override
	public void del(LolVideo video) {
		super.delete(super.get("from LolVideo where videoId=?", video.getVideoId()));
	}

	public List<VideoPaging> getVideoList(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select video.*,teams1.teamsName as teamsName1,teams2.teamsName as teamsName2,videoType.videoTypeName from lol_video as video left join video_type as videoType on video.videoTypeId=videoType.videoTypeid left join activities_match as mat on mat.matchId=video.matchId left join lol_teams  as teams1 on teams1.teamsId=mat.teamsId1 left join lol_teams as teams2 on teams2.teamsId=mat.teamsId2 ");
		return getList(sql, pagingData);
	}

	@Override
	public Integer getCount(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select count(*) from lol_video as video left join video_type as videoType on video.videoTypeId=videoType.videoTypeid left join activities_match as mat on mat.matchId=video.matchId left join lol_teams  as teams1 on teams1.teamsId=mat.teamsId1 left join lol_teams as teams2 on teams2.teamsId=mat.teamsId2 ");
		return super.getCountSql(getPagingSql(sql, pagingData));
	}

	@Override
	public List<LolVideo> getHomepage() {
		return super.pagingSql("select videoUrl,videoPicture,videoName from lol_video order by videoCreateTime,videoNumber desc", 1, 8, LolVideo.class);
	}

	@Override
	public List<VideoPaging> videoListF(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select video.videoUrl,video.videoPicture,video.videoName from lol_video as video left join video_type as vt on video.videoTypeId=vt.videoTypeId");
		return getList(sql, pagingData);
	}


	@Override
	public Integer getCountF(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select count(*) from lol_video as video left join video_type as vt on video.videoTypeId=vt.videoTypeId");
		return super.getCountSql(getPagingSql(sql, pagingData));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<VideoPaging> getList(StringBuffer sql, AjaxPagingData pagingData){
		final Integer item = pagingData.getItem();
		final Integer page = pagingData.getPage();
		final String s = getPagingSql(sql, pagingData);
		return super.getHibernateTemplate().execute( new HibernateCallback() {
			@Override
			public List<VideoPaging> doInHibernate(Session session)  {
				Integer item2;
				if(item == null || item < 1)
					item2 = 10;
				else
					item2 = item;
				Query query = session.createSQLQuery(s).setResultTransformer(Transformers.aliasToBean(VideoPaging.class));
				query.setFirstResult((page-1)*item2);
				query.setMaxResults(item2);
				List list = query.list();
				return list;
			}
		});
	}

	@Override
	public List<VideoPaging> getF(AjaxPagingData pagingData) {
		StringBuffer sql = new StringBuffer("select video.videoUrl,video.videoPicture,video.videoName,video.videoNumber,vt.videoTypeName from lol_video as video left join video_type as vt on video.videoTypeId=vt.videoTypeId");
		return getList(sql, pagingData);
	}

	@Override
	public void updateNumber(String videoUrl) {
		super.update("update lol_video set videoNumber=videoNumber+1 where videoUrl like '%"+videoUrl+"%'");
	}

	@Override
	public Integer get(String videoUrl) {
		Object value = super.getField("select videoId from lol_video where videoUrl like '%"+videoUrl+"%'");
		return value==null?null:(Integer) value;
	}
}
