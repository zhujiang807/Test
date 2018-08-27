package com.friendship.video.extend;

import com.java.po.LolVideo;

public class VideoPaging extends LolVideo{

	private static final long serialVersionUID = 1L;
	
	private String videoTypeName;
	private String teamsName1;
	private String teamsName2;
	
	public String getVideoTypeName() {
		return videoTypeName;
	}
	public void setVideoTypeName(String videoTypeName) {
		this.videoTypeName = videoTypeName;
	}
	public String getTeamsName1() {
		return teamsName1;
	}
	public void setTeamsName1(String teamsName1) {
		this.teamsName1 = teamsName1;
	}
	public String getTeamsName2() {
		return teamsName2;
	}
	public void setTeamsName2(String teamsName2) {
		this.teamsName2 = teamsName2;
	}
}
