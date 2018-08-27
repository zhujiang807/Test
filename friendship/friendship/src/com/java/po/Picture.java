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
@Table(name = "picture", catalog = "test")
public class Picture implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer pictureId;
	private Integer pictureTypeId;
	private String pictureName;
	private String pictureUrl;
	private Timestamp pictureCreateTime;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "pictureId", unique = true, nullable = false)
	public Integer getPictureId() {
		return pictureId;
	}
	public void setPictureId(Integer pictureId) {
		this.pictureId = pictureId;
	}
	@Column(name = "pictureName", length = 25)
	public String getPictureName() {
		return pictureName;
	}
	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}
	@Column(name = "pictureTypeId")
	public Integer getPictureTypeId() {
		return pictureTypeId;
	}
	public void setPictureTypeId(Integer pictureTypeId) {
		this.pictureTypeId = pictureTypeId;
	}
	@Column(name = "pictureUrl", length = 70)
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	@Column(name = "pictureCreateTime", length = 10)
	public Timestamp getPictureCreateTime() {
		return pictureCreateTime;
	}
	public void setPictureCreateTime(Timestamp pictureCreateTime) {
		this.pictureCreateTime = pictureCreateTime;
	}
	
}
