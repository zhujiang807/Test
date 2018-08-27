package com.java.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_picture", catalog = "test")
public class UserPicture implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer userPictureId;
	private Integer userId;
	private Integer pictureType;
	private String pictureName;
	private String pictureUrl;
	private Date pictureCreateTime;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "userPictureId", unique = true, nullable = false)
	public Integer getUserPictureId() {
		return userPictureId;
	}
	public void setUserPictureId(Integer userPictureId) {
		this.userPictureId = userPictureId;
	}
	@Column(name = "userId")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name = "pictureType")
	public Integer getPictureType() {
		return pictureType;
	}
	public void setPictureType(Integer pictureType) {
		this.pictureType = pictureType;
	}
	@Column(name = "pictureName", length = 25)
	public String getPictureName() {
		return pictureName;
	}
	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}
	@Column(name = "pictureUrl", length = 100)
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	@Column(name = "pictureCreateTime")
	public Date getPictureCreateTime() {
		return pictureCreateTime;
	}
	public void setPictureCreateTime(Date pictureCreateTime) {
		this.pictureCreateTime = pictureCreateTime;
	}
}
