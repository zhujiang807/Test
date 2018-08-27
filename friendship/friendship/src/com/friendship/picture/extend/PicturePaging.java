package com.friendship.picture.extend;

import com.java.po.Picture;

public class PicturePaging extends Picture{

	private static final long serialVersionUID = 1L;

	private String pictureTypeName;

	public String getPictureTypeName() {
		return pictureTypeName;
	}

	public void setPictureTypeName(String pictureTypeName) {
		this.pictureTypeName = pictureTypeName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
