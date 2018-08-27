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
 * LolArea entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "lol_area", catalog = "test")
public class LolArea implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer areaId;
	private String areaName;
	private Integer areaSequence;
	private Timestamp areaCreateTime;

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "areaId", unique = true, nullable = false)
	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	@Column(name = "areaName", nullable = false, length = 25)
	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Column(name = "areaSequence", nullable = false)
	public Integer getAreaSequence() {
		return this.areaSequence;
	}

	public void setAreaSequence(Integer areaSequence) {
		this.areaSequence = areaSequence;
	}

	@Column(name = "areaCreateTime", nullable = false, length = 0)
	public Timestamp getAreaCreateTime() {
		return this.areaCreateTime;
	}

	public void setAreaCreateTime(Timestamp areaCreateTime) {
		this.areaCreateTime = areaCreateTime;
	}

}
