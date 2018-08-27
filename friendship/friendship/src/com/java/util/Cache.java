package com.java.util;

import java.io.Serializable;
import java.util.Date;

public class Cache implements Serializable{
	private static final long serialVersionUID = 1L;

	private Object data;
	private Date operationTime = DateBase.getDateYMDHMS();
	private Integer depositTime = 3600000;

	public Cache(Object data) {
		this.data = data;
	}

	public Cache(Object data, Date operationTime) {
		this.data = data;
		this.operationTime = operationTime;
	}

	public Cache(Object data , Integer depositTime) {
		this.data = data;
		this.depositTime = depositTime;
	}

	public Cache(Object data, Date operationTime, Integer depositTime) {
		this.data = data;
		this.operationTime = operationTime;
		this.depositTime = depositTime;
	}

	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Date getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
	public Integer getDepositTime() {
		return depositTime;
	}
	public void setDepositTime(Integer depositTime) {
		this.depositTime = depositTime;
	}
}
