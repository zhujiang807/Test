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
 * Menu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "menu", catalog = "test")
public class Menu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer menuId;
	private Integer fatherMenuId;
	private String menuName;
	private Integer menuNumber;
	private Integer menuShow;
	private String menuUrl;
	private Integer menuType;
	private Integer menuShowType;
	private Timestamp menuCreateTime;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "menuId", unique = true, nullable = false)
	public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	@Column(name = "fatherMenuId")
	public Integer getFatherMenuId() {
		return this.fatherMenuId;
	}

	public void setFatherMenuId(Integer fatherMenuId) {
		this.fatherMenuId = fatherMenuId;
	}

	@Column(name = "menuName", length = 20)
	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Column(name = "menuNumber")
	public Integer getMenuNumber() {
		return this.menuNumber;
	}

	public void setMenuNumber(Integer menuNumber) {
		this.menuNumber = menuNumber;
	}

	@Column(name = "menuShow")
	public Integer getMenuShow() {
		return this.menuShow;
	}

	public void setMenuShow(Integer menuShow) {
		this.menuShow = menuShow;
	}

	@Column(name = "menuCreateTime")
	public Timestamp getMenuCreateTime() {
		return this.menuCreateTime;
	}

	public void setMenuCreateTime(Timestamp menuCreateTime) {
		this.menuCreateTime = menuCreateTime;
	}
	
	@Column(name = "menuUrl", length = 100)
	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	
	@Column(name = "menuType")
	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}
	
	@Column(name = "menuShowType")
	public Integer getMenuShowType() {
		return menuShowType;
	}
	public void setMenuShowType(Integer menuShowType) {
		this.menuShowType = menuShowType;
	}

}
