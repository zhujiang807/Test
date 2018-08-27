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
@Table(name="private_letter", catalog = "test")
public class PrivateLetter implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer letterId;
	private Integer userId;
	private String letterContent;
	private Integer letterCheck;
	private Date letterCreateTime;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "letterId", unique = true, nullable = false)
	public Integer getLetterId() {
		return letterId;
	}
	public void setLetterId(Integer letterId) {
		this.letterId = letterId;
	}
	@Column(name = "userId")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name = "letterContent", length = 1000)
	public String getLetterContent() {
		return letterContent;
	}
	public void setLetterContent(String letterContent) {
		this.letterContent = letterContent;
	}
	@Column(name = "letterCheck")
	public Integer getLetterCheck() {
		return letterCheck;
	}
	public void setLetterCheck(Integer letterCheck) {
		this.letterCheck = letterCheck;
	}
	@Column(name = "letterCreateTime")
	public Date getLetterCreateTime() {
		return letterCreateTime;
	}
	public void setLetterCreateTime(Date letterCreateTime) {
		this.letterCreateTime = letterCreateTime;
	}
}
