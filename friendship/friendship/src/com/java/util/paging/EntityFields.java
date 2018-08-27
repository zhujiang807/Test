package com.java.util.paging;

import java.io.Serializable;

public class EntityFields implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String fieldsName;
	private Object fieldsValue;
	
	public EntityFields() {}
	
	public EntityFields(String fieldsName, Object fieldsValue) {
		this.fieldsName = fieldsName;
		this.fieldsValue = fieldsValue;
	}
	public String getFieldsName() {
		return fieldsName;
	}
	public void setFieldsName(String fieldsName) {
		this.fieldsName = fieldsName;
	}
	public Object getFieldsValue() {
		return fieldsValue;
	}
	public void setFieldsValue(Object fieldsValue) {
		this.fieldsValue = fieldsValue;
	}
	
	
}
