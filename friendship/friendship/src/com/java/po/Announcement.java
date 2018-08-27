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
 * Announcement entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "announcement")
@org.hibernate.annotations.Entity(dynamicUpdate=true,dynamicInsert=true)
public class Announcement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer annId;
	private String annTitle;
	private String annContent;
	private Timestamp annCreateTime;


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "annId", unique = true, nullable = false)
	public Integer getAnnId() {
		return this.annId;
	}

	public void setAnnId(Integer annId) {
		this.annId = annId;
	}

	@Column(name = "annTitle", length = 50)
	public String getAnnTitle() {
		return this.annTitle;
	}

	public void setAnnTitle(String annTitle) {
		this.annTitle = annTitle;
	}

	@Column(name = "annContent", length = 100)
	public String getAnnContent() {
		return this.annContent;
	}

	public void setAnnContent(String annContent) {
		this.annContent = annContent;
	}

	@Column(name = "annCreateTime")
	public Timestamp getAnnCreateTime() {
		return this.annCreateTime;
	}

	public void setAnnCreateTime(Timestamp annCreateTime) {
		this.annCreateTime = annCreateTime;
	}

}
