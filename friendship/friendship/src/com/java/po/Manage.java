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
@Table(name = "manage", catalog = "test")
public class Manage implements  Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer manId;
	private String manQq;
	private String manName;
	private String manPassword;
	private Timestamp manCreateTime;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "manId", nullable = false, unique = true)
	public Integer getManId() {
		return manId;
	}
	public void setManId(Integer manId) {
		this.manId = manId;
	}
	
	@Column(name = "manQq", length = 20)
	public String getManQq() {
		return manQq;
	}
	public void setManQq(String manQq) {
		this.manQq = manQq;
	}
	
	@Column(name = "manName", length = 50)
	public String getManName() {
		return manName;
	}
	public void setManName(String manName) {
		this.manName = manName;
	}
	
	@Column(name = "manPassword", length = 100)
	public String getManPassword() {
		return manPassword;
	}
	public void setManPassword(String manPassword) {
		this.manPassword = manPassword;
	}
	
	@Column(name = "manCreateTime")
	public Timestamp getManCreateTime() {
		return manCreateTime;
	}
	public void setManCreateTime(Timestamp manCreateTime) {
		this.manCreateTime = manCreateTime;
	}
	
	
}
