package com.java.po;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * LolVideo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "lol_video", catalog = "test")
public class LolVideo implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer videoId;
	private Integer videoTypeId;
	private Integer matchId;
	private String videoUrl;
	private String videoName;
	private String videoPicture;
	private Integer videoNumber;
	private Timestamp videoCreateTime;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "videoId", unique = true, nullable = false)
	public Integer getVideoId() {
		return this.videoId;
	}

	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}
	
	@Column(name = "videoTypeId")
	public Integer getVideoTypeId() {
		return videoTypeId;
	}

	public void setVideoTypeId(Integer videoTypeId) {
		this.videoTypeId = videoTypeId;
	}

	@Column(name = "matchId")
	public Integer getMatchId() {
		return this.matchId;
	}

	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
	}

	@Column(name = "videoUrl", length = 100)
	public String getVideoUrl() {
		return this.videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	@Column(name = "videoName", length = 50)
	public String getVideoName() {
		return this.videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	@Column(name = "videoNumber")
	public Integer getVideoNumber() {
		return this.videoNumber;
	}

	public void setVideoNumber(Integer videoNumber) {
		this.videoNumber = videoNumber;
	}

	@Column(name = "videoCreateTime", length = 0)
	public Timestamp getVideoCreateTime() {
		return this.videoCreateTime;
	}

	public void setVideoCreateTime(Timestamp videoCreateTime) {
		this.videoCreateTime = videoCreateTime;
	}

	@Column(name = "videoPicture", length = 100)
	public String getVideoPicture() {
		return videoPicture;
	}
	public void setVideoPicture(String videoPicture) {
		this.videoPicture = videoPicture;
	}
}
