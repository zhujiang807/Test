package com.java.po;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "video_type", catalog = "test")
public class VideoType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer videoTypeId;
	private String videoTypeName;
	private Timestamp videoTypeTime;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "videoTypeId", unique = true, nullable = false)
	public Integer getVideoTypeId() {
		return videoTypeId;
	}
	public void setVideoTypeId(Integer videoTypeId) {
		this.videoTypeId = videoTypeId;
	}
	
	@Column(name = "videoTypeName", length=50)
	public String getVideoTypeName() {
		return videoTypeName;
	}
	public void setVideoTypeName(String videoTypeName) {
		this.videoTypeName = videoTypeName;
	}
	
	@Column(name = "videoTypeTime")
	public Timestamp getVideoTypeTime() {
		return videoTypeTime;
	}
	public void setVideoTypeTime(Timestamp videoTypeTime) {
		this.videoTypeTime = videoTypeTime;
	}

}
