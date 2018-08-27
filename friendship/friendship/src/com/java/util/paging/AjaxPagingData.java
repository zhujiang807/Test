package com.java.util.paging;

import java.io.Serializable;
import java.util.List;



/**
 * ajax分页数据格式
 * @author Administrator
 *
 */
public class AjaxPagingData implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer page = 1;
	private Integer item;

	private List<EntityFields> fields;
	private String fieldsString;
	
	private String fieldsName;
	private Object fieldsValue;
	private String fieldsName2;
	private Object fieldsValue2;
	
	private String fieldsNameS;
	private Object fieldsValueS;
	private String fieldsNameS2;
	private Object fieldsValueS2;
	
	private String fieldsRank;
	private String fieldsRankType;
	private String fieldsRank2;
	private String fieldsRankType2;
	
	public AjaxPagingData() {}
	
	public AjaxPagingData(Integer page, Integer item,
			String fieldsString, String fieldsName,
			Object fieldsValue, String fieldsName2, Object fieldsValue2,
			String fieldsNameS, Object fieldsValueS, String fieldsNameS2,
			Object fieldsValueS2, String fieldsRank, String fieldsRankType,
			String fieldsRank2, String fieldsRankType2) {
		super();
		this.page = page;
		this.item = item;
		this.fieldsString = fieldsString;
		this.fieldsName = fieldsName;
		this.fieldsValue = fieldsValue;
		this.fieldsName2 = fieldsName2;
		this.fieldsValue2 = fieldsValue2;
		this.fieldsNameS = fieldsNameS;
		this.fieldsValueS = fieldsValueS;
		this.fieldsNameS2 = fieldsNameS2;
		this.fieldsValueS2 = fieldsValueS2;
		this.fieldsRank = fieldsRank;
		this.fieldsRankType = fieldsRankType;
		this.fieldsRank2 = fieldsRank2;
		this.fieldsRankType2 = fieldsRankType2;
	}

	public AjaxPagingData(Integer page, Integer item) {
		super();
		this.page = page;
		this.item = item;
	}



	public AjaxPagingData(Integer page, Integer item, String fieldsRank,
			String fieldsRankType) {
		super();
		this.page = page;
		this.item = item;
		this.fieldsRank = fieldsRank;
		this.fieldsRankType = fieldsRankType;
	}

	
	public AjaxPagingData(Integer page, Integer item, String fieldsName,
			Object fieldsValue, String fieldsRank, String fieldsRankType) {
		super();
		this.page = page;
		this.item = item;
		this.fieldsName = fieldsName;
		this.fieldsValue = fieldsValue;
		this.fieldsRank = fieldsRank;
		this.fieldsRankType = fieldsRankType;
	}

	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public List<EntityFields> getFields() {
		return fields;
	}
	public void setFields(List<EntityFields> fields) {
		this.fields = fields;
	}
	public Integer getItem() {
		return item;
	}
	public void setItem(Integer item) {
		this.item = item;
	}
	public String getFieldsString() {
		return fieldsString;
	}
	public void setFieldsString(String fieldsString) {
		this.fieldsString = fieldsString;
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
	public String getFieldsRank() {
		return fieldsRank;
	}
	public void setFieldsRank(String fieldsRank) {
		this.fieldsRank = fieldsRank;
	}
	public String getFieldsRankType() {
		return fieldsRankType;
	}
	public void setFieldsRankType(String fieldsRankType) {
		this.fieldsRankType = fieldsRankType;
	}
	public String getFieldsName2() {
		return fieldsName2;
	}
	public void setFieldsName2(String fieldsName2) {
		this.fieldsName2 = fieldsName2;
	}
	public Object getFieldsValue2() {
		return fieldsValue2;
	}
	public void setFieldsValue2(Object fieldsValue2) {
		this.fieldsValue2 = fieldsValue2;
	}
	public String getFieldsNameS() {
		return fieldsNameS;
	}
	public void setFieldsNameS(String fieldsNameS) {
		this.fieldsNameS = fieldsNameS;
	}
	public Object getFieldsValueS() {
		return fieldsValueS;
	}
	public void setFieldsValueS(Object fieldsValueS) {
		this.fieldsValueS = fieldsValueS;
	}
	public String getFieldsNameS2() {
		return fieldsNameS2;
	}
	public void setFieldsNameS2(String fieldsNameS2) {
		this.fieldsNameS2 = fieldsNameS2;
	}
	public Object getFieldsValueS2() {
		return fieldsValueS2;
	}
	public void setFieldsValueS2(Object fieldsValueS2) {
		this.fieldsValueS2 = fieldsValueS2;
	}
	public String getFieldsRank2() {
		return fieldsRank2;
	}
	public void setFieldsRank2(String fieldsRank2) {
		this.fieldsRank2 = fieldsRank2;
	}
	public String getFieldsRankType2() {
		return fieldsRankType2;
	}
	public void setFieldsRankType2(String fieldsRankType2) {
		this.fieldsRankType2 = fieldsRankType2;
	}
}
