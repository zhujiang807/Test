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
@Table(name = "file_config", catalog = "test")
public class FileConfig implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer configId;
	private String configName;
	private String configValue;
	private String configRemarks;
	private Timestamp configCreateTime;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "configId", unique = true, nullable = false)
	public Integer getConfigId() {
		return configId;
	}
	public void setConfigId(Integer configId) {
		this.configId = configId;
	}
	
	@Column(name = "configName", length = 50)
	public String getConfigName() {
		return configName;
	}
	public void setConfigName(String configName) {
		this.configName = configName;
	}
	
	@Column(name = "configValue")
	public String getConfigValue() {
		return configValue;
	}
	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
	
	@Column(name = "configCreateTime")
	public Timestamp getConfigCreateTime() {
		return configCreateTime;
	}
	public void setConfigCreateTime(Timestamp configCreateTime) {
		this.configCreateTime = configCreateTime;
	}
	@Column(name = "configRemarks", length = 25)
	public String getConfigRemarks() {
		return configRemarks;
	}
	public void setConfigRemarks(String configRemarks) {
		this.configRemarks = configRemarks;
	}

}
