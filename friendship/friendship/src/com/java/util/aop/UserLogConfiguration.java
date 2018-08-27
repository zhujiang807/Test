package com.java.util.aop;

import com.friendship.teams.extend.UserTeamsPaging;
import com.friendship.user.extend.JoinPaging;
import com.java.po.ActivitiesMatch;
import com.java.po.Advertisement;
import com.java.po.Announcement;
import com.java.po.Comment;
import com.java.po.FileConfig;
import com.java.po.LolArea;
import com.java.po.LolForum;
import com.java.po.LolTeams;
import com.java.po.LolVideo;
import com.java.po.Manage;
import com.java.po.Menu;
import com.java.po.Movie;
import com.java.po.Picture;
import com.java.po.PictureType;
import com.java.po.PrivateLetter;
import com.java.po.QqUser;
import com.java.po.TeamsRanking;
import com.java.po.UserActivities;
import com.java.po.UserForum;
import com.java.po.UserJoin;
import com.java.po.UserMovie;
import com.java.po.UserNotice;
import com.java.po.UserPicture;
import com.java.po.UserTeams;
import com.java.po.VideoType;

/**
 * 用于返回用户日志记录信息，每个方法一个枚举 
 * @author Administrator
 *
 */
public class UserLogConfiguration {
	private static LolForum forum;
	private static UserForum userForum;
	private static Manage manage;
	private static Advertisement advertisement;
	private static Announcement announcement;
	private static FileConfig config;
	private static Menu menu;
	private static PictureType pictureType;
	private static Picture picture;
	private static LolArea area;
	private static ActivitiesMatch match;
	private static TeamsRanking ranking;
	private static LolTeams teams;
	private static UserTeams userTeams;
	private static UserTeamsPaging userTeamsPaging;
	private static UserActivities activities;
	private static Comment comment;
	private static UserJoin join;
	private static QqUser user;
	private static LolVideo video;
	private static VideoType videoType;
	private static JoinPaging joinPaging;
	private static PrivateLetter letter;
	private static Movie movie;
	private static UserPicture userPicture;
	private static UserNotice userNotice;
	
	public static String getLogContent(MethodName name, Object[] param, Object paramReturn, String after){
		StringBuffer content = new StringBuffer("");
		switch (name) {
			case NULL:
				break;
			case FORUM_SAVE:
				forum = (LolForum) param[0];
				content.append("添加了个论坛，名称为：").append(forum.getForumTitle());
				break;
			case FORUM_DELETE:
				forum = (LolForum) param[0];
				content.append("删除了个论坛，id为：").append(forum.getForumId());
				break;
			case USERFORUM_DELETE:
				userForum= (UserForum) param[0];
				content.append("删除了用户评论论坛，id为：").append(userForum.getUserForumId());
				break;
			case MANAGE_LOGIN:
				break;
			case MANAGE_DELETE:
				manage = (Manage) param[0];
				content.append("删除了其他管理员，id为：").append(manage.getManId());
				break;
			case MANAGE_SAVE:
				manage = (Manage) param[0];
				content.append("添加了其他管理员，名称为：").append(manage.getManName());
				break;
			case MANAGE_UPDATEMANAGE:
				String newPassword = (String) param[0];
				content.append("修改了密码，密码称为：").append(newPassword);
				break;
			case ADVERTISEMENT_SAVE:
				advertisement = (Advertisement) param[0];
				content.append("添加了广告，标题为：").append(advertisement.getAdvTitle());
				break;
			case ADVERTISEMENT_DELETE:
				advertisement = (Advertisement) param[0];
				content.append("删除了广告，id为：").append(advertisement.getAdvId());
				break;
			case ANNOUNCEMENT_SAVE:
				announcement =  (Announcement) param[0];
				content.append("添加了公告，标题为：").append(announcement.getAnnTitle());
				break;
			case ANNOUNCEMENT_DELETE:
				announcement =  (Announcement) param[0];
				content.append("删除了公告，id为：").append(announcement.getAnnId());
				break;
			case CONFIG_SAVE:
				config = (FileConfig) param[0];
				content.append("添加了公告，标题为：").append(config.getConfigRemarks());
				break;
			case CONFIG_DELETE:
				config = (FileConfig) param[0];
				content.append("删除了公告，id为：").append(config.getConfigId());
				break;
			case MENU_SAVE:
				menu = (Menu) param[0];
				content.append("添加了一个菜单，名称为：").append(menu.getMenuName());
				break;
			case MENU_DELETE:
				menu = (Menu) param[0];
				content.append("删除了一个菜单，id为：").append(menu.getMenuId());
				break;
			case NOTICE_SAVE:
				String userQQ = (String) param[1];
				content.append("添加了一个通知，用户qq号为：").append(userQQ);
				break;
			case NOTICE_SAVEJOIN:
				Integer actCode = (Integer) param[1];
				content.append("添加参加活动的通知，活动actCode为：").append(actCode);
				break;
			case NOTICE_DELETE:
				userNotice = (UserNotice) param[0];
				content.append("删除了一个通知，id为：").append(userNotice.getNoticeId());
				break;
			case PICTURETYPE_SAVE:
				pictureType = (PictureType) param[0];
				content.append("添加一个图片类型，名称为：").append(pictureType.getPictureTypeName());
				break;
			case PICTURETYPE_DELETE:
				pictureType = (PictureType) param[0];
				content.append("删除了一个图片类型，id为：").append(pictureType.getPictureTypeId());
				break;
			case PICTURE_SAVE:
				picture =  (Picture) param[0];
				content.append("添加一个图片，名称为：").append(picture.getPictureName());
				break;
			case PICTURE_DELETE:
				picture = (Picture) param[0];
				content.append("删除了一个图片，id为：").append(pictureType.getPictureTypeId());
				break;
			case USERPICTURE_SAVE:
				userPicture = (UserPicture) param[0];
				content.append("用户上传一张图片,用户id为：").append(userPicture.getUserId());
				break;
			case USERPICTURE_DELETE:
				userPicture = (UserPicture) param[0];
				content.append("管理员删除一张图片,id为：").append(userPicture.getUserPictureId());
				break;
			case AREA_SAVE:
				area =  (LolArea) param[0];
				content.append("添加一个游戏区域，名称为：").append(area.getAreaName());
				break;
			case AREA_DELETE:
				area = (LolArea) param[0];
				content.append("删除了一个游戏区域，id为：").append(area.getAreaId());
				break;
			case MATCH_SAVE:
				match =  (ActivitiesMatch) param[0];
				content.append("添加比赛，比赛参加活动id为：").append(match.getActivitiesId());
				break;
			case MATCH_DELETE:
				match = (ActivitiesMatch) param[0];
				content.append("删除了战队比赛，id为：").append(match.getMatchId());
				break;
			case RANKING_SAVE:
				ranking = (TeamsRanking) param[0];
				content.append("添加战队排名，战队id为：").append(ranking.getTeamsId());
				break;
			case RANKING_DELETE:
				ranking = (TeamsRanking) param[0];
				content.append("删除战队排名，id为：").append(ranking.getRanId());
				break;
			case RANKING_UPDATE:
				ranking = (TeamsRanking) param[0];
				content.append("修改战队排名，id为：").append(ranking.getRanId());
				content.append(";战队id为：").append(ranking.getTeamsId());
				break;
			case TEAMS_DELETE:
				teams = (LolTeams) param[0];
				content.append("删除战队，id为：").append(teams.getTeamsId());
				break;
			case TEAMS_UPDATE:
				teams = (LolTeams) param[0];
				content.append("修改战队信息，战队名为：").append(teams.getTeamsName());
				break;
			case TEAMS_SAVE:
				teams = (LolTeams) param[0];
				content.append("添加战队信息，用户id为：").append(teams.getUserId());
				content.append(";战队名称为：").append(teams.getTeamsName());
				content.append(";添加结果：").append(paramReturn.toString());
				break;
			case TEAMS_DELETEF:
				content.append("解散战队，用户id为：").append(param[0]);
				content.append(";大区名称为：").append(param[1]);
				break;
			case USERTEAMS_SAVE:
				userTeams = (UserTeams) param[0];
				content.append("用户加入战队,用户id为：").append(userTeams.getUserId());
				content.append(";加入的战队id为：").append(userTeams.getTeamsId());
				break;
			case USERTEAMS_CHECK:
				userTeamsPaging = (UserTeamsPaging) param[0];
				content.append("队长审核战队成员,成员qq为：").append(userTeamsPaging.getUserQQ());
				content.append(";审核结果：").append(userTeamsPaging.getUserTeamscheck());
				break;
			case USERTEAMS_CHECK2:
				content.append("退出战队,队长qq号为：").append(param[0]);
				content.append(";用户id为:").append(param[1]);
				break;
			case ACTIVITIES_SAVE:
				activities = (UserActivities) param[0];
				content.append("添加了一个活动，活动名：").append(activities.getActName());
				break;
			case ACTIVITIES_UPDATE:
				activities = (UserActivities) param[0];
				content.append("修改了一个活动，活动名：").append(activities.getActName());
				content.append(";活动id为：").append(activities.getActId());
				break;
			case ACTIVITIES_DELETE:
				activities = (UserActivities) param[0];
				content.append("删除了一个活动，id为：").append(activities.getActId());
				break;
			case COMMENT_SAVE:
				break;
			case COMMENT_DELETE:
				comment = (Comment) param[0];
				content.append("删除了一个评论，id为：").append(comment.getComId());
				break;
			case JOIN_SAVE:
				content.append("用户参加了一个活动，活动名为：").append(param[1]);
				content.append(";报名结果：").append(paramReturn.toString());
				break;
			case JOIN_ARRANGEMATCH:
				activities = (UserActivities) param[0];
				content.append("管理员安排比赛，活动码为：").append(activities.getActCode());
				break;
			case JOIN_UPDATEJOIN:
				joinPaging = (JoinPaging) param[0];
				content.append("用户比赛结果，用户活动id为：").append(joinPaging.getJoinId());
				break;
			case USER_USERUPDATE:
				user = (QqUser) param[0];
				content.append("用户修改自己信息，qq号为：").append(user.getUserQQ());
				break;
			case VIDEOTYPE_DELETE:
				videoType = (VideoType) param[0];
				content.append("删除了一个视频类型，id为：").append(videoType.getVideoTypeId());
				break;
			case VIDEO_SAVE:
				video = (LolVideo) param[0];
				content.append("添加了一个视频,名称为：").append(video.getVideoName());
				break;
			case VIDEO_DELETE:
				video = (LolVideo) param[0];
				content.append("删除了一个视频，id为：").append(video.getVideoId());
				break;
			case LETTER_SAVE:
				letter = (PrivateLetter) param[0];
				content.append("用户给管理员发了一条私信，请及时查收");
				break;
			case LETTER_UPDATECHECK:
				letter = (PrivateLetter) param[0];
				content.append("管理员回复了一条私信，id为：").append(letter.getLetterId());
				break;
			case LETTER_DELETE:
				letter = (PrivateLetter) param[0];
				content.append("管理员删除了一条私信，id为：").append(letter.getLetterId());
				break;
			case MOVIE_SAVE:
				movie = (Movie) param[0];
				content.append("管理员添加了一个电影，名称为：").append(movie.getMovieName());
				break;
		}
		return content.toString();
	}
	
	public enum MethodName{
		//没有
		NULL,
		//论坛 日志
		FORUM_SAVE,FORUM_DELETE,USERFORUM_DELETE,

		//管理员 操作广告 操作公告 操作配置 操作菜单 日志
		MANAGE_LOGIN,MANAGE_DELETE,MANAGE_SAVE,MANAGE_UPDATEMANAGE,
		ADVERTISEMENT_SAVE,ADVERTISEMENT_DELETE,
		ANNOUNCEMENT_SAVE,ANNOUNCEMENT_DELETE,
		CONFIG_SAVE,CONFIG_DELETE,
		MENU_SAVE,MENU_DELETE,
		NOTICE_SAVE,NOTICE_DELETE,NOTICE_SAVEJOIN,

		//图片类型 图片 用户图片 日志
		PICTURETYPE_SAVE,PICTURETYPE_DELETE,
		PICTURE_SAVE,PICTURE_DELETE,
		USERPICTURE_DELETE,USERPICTURE_SAVE,
		
		//大区 战队比赛 战队排名 战队 用户加入战队 日志
		AREA_SAVE,AREA_DELETE,
		MATCH_SAVE,MATCH_DELETE,
		RANKING_SAVE,RANKING_DELETE,RANKING_UPDATE,
		TEAMS_DELETE,TEAMS_UPDATE,TEAMS_SAVE,TEAMS_DELETEF,
		USERTEAMS_SAVE,USERTEAMS_CHECK,USERTEAMS_CHECK2,
		
		//活动 用户评论 用户参加活动 用户 日志
		ACTIVITIES_SAVE,ACTIVITIES_UPDATE,ACTIVITIES_DELETE,
		COMMENT_SAVE,COMMENT_DELETE,
		JOIN_SAVE,JOIN_ARRANGEMATCH,JOIN_UPDATEJOIN,
		USER_USERUPDATE,
		
		//视频类型 视频 日志
		VIDEOTYPE_DELETE,
		VIDEO_SAVE,VIDEO_DELETE,
		
		//私信 日志
		LETTER_SAVE,LETTER_UPDATECHECK,LETTER_DELETE,
		
		//电影 用户评论电影 日志
		MOVIE_SAVE,
	}
}
