package com.friendship.user.extend;

import com.java.po.Comment;

public class CommentPaging extends Comment{
	private static final long serialVersionUID = 1L;
	private String userQQ;
	private String userQQName;
	private String userName;
	private String teamsName;
	private String videoName;
	private String videoUrl;
	private String actName;

	public String getUserQQ() {
		return userQQ;
	}
	public void setUserQQ(String userQQ) {
		this.userQQ = userQQ;
	}
	public String getUserQQName() {
		return userQQName;
	}
	public void setUserQQName(String userQQName) {
		this.userQQName = userQQName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTeamsName() {
		return teamsName;
	}
	public void setTeamsName(String teamsName) {
		this.teamsName = teamsName;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	
}
