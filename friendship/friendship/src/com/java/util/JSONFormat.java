package com.java.util;
/**
 * json返回数据格式
 * @author zhujiang
 *
 */
public class JSONFormat {
	private String type;
	private Object data;
	private String redirect;
	private String message;
	
	public JSONFormat() {
		super();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
