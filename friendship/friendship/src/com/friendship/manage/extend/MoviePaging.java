package com.friendship.manage.extend;

import java.math.BigInteger;
import com.java.po.Movie;

public class MoviePaging extends Movie{
	private static final long serialVersionUID = 1L;
	
	private BigInteger total;
	
	public BigInteger getTotal() {
		return total;
	}
	
	public void setTotal(BigInteger total) {
		this.total = total;
	}
}
