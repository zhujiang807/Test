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
 * QqUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "qq_user", catalog = "test")
public class QqUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String userQQ;
	private String userPassword;
	private String userQQName;
	private String userName;
	private String userSex;
	private Integer userAge;
	private String userPhone;
	private String userEmail;
	private String userAlipay;
	private String userQQEmail;
	private String userAddress;
	private String userRemarks;
	private Timestamp userCreateTime;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "userId", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "userQQ", nullable = false, length = 20)
	public String getUserQQ() {
		return this.userQQ;
	}

	public void setUserQQ(String userQQ) {
		this.userQQ = userQQ;
	}

	@Column(name = "userQQName", length = 50)
	public String getUserQQName() {
		return userQQName;
	}

	public void setUserQQName(String userQQName) {
		this.userQQName = userQQName;
	}

	@Column(name = "userName", length = 15)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "userSex", length = 4)
	public String getUserSex() {
		return this.userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	@Column(name = "userAge")
	public Integer getUserAge() {
		return this.userAge;
	}

	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	@Column(name = "userPhone", length = 20)
	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	@Column(name = "userEmail", length = 30)
	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Column(name = "userAlipay", length = 50)
	public String getUserAlipay() {
		return this.userAlipay;
	}

	public void setUserAlipay(String userAlipay) {
		this.userAlipay = userAlipay;
	}

	@Column(name = "userQQEmail", nullable = false, length = 30)
	public String getUserQQEmail() {
		return userQQEmail;
	}

	public void setUserQQEmail(String userQQEmail) {
		this.userQQEmail = userQQEmail;
	}


	@Column(name = "userAddress", length = 100)
	public String getUserAddress() {
		return this.userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	@Column(name = "userRemarks", length = 100)
	public String getUserRemarks() {
		return this.userRemarks;
	}

	public void setUserRemarks(String userRemarks) {
		this.userRemarks = userRemarks;
	}

	@Column(name = "userCreateTime")
	public Timestamp getUserCreateTime() {
		return this.userCreateTime;
	}

	public void setUserCreateTime(Timestamp userCreateTime) {
		this.userCreateTime = userCreateTime;
	}
	
	@Column(name = "userPassword")
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
