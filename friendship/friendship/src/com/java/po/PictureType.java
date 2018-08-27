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
@Table(name = "picture_type", catalog = "test")
public class PictureType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer pictureTypeId;
	private String pictureTypeName;
	private Timestamp createTime;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "pictureTypeId", unique = true, nullable = false)
	public Integer getPictureTypeId() {
		return pictureTypeId;
	}
	public void setPictureTypeId(Integer pictureTypeId) {
		this.pictureTypeId = pictureTypeId;
	}
	@Column(name = "pictureTypeName", length = 30)
	public String getPictureTypeName() {
		return pictureTypeName;
	}
	public void setPictureTypeName(String pictureTypeName) {
		this.pictureTypeName = pictureTypeName;
	}
	@Column(name = "createTime")
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	
}
