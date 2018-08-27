package com.friendship.manage.dao;

import java.util.Date;
import java.util.List;
import com.friendship.manage.extend.LetterPaging;
import com.java.po.PrivateLetter;
import com.java.util.paging.AjaxPagingData;

public interface ILetterDao {
	public List<LetterPaging> getLetterList(AjaxPagingData pagingData);
	
	public void updateCheck(PrivateLetter entity);
	
	public void save(PrivateLetter entity);

	public Integer getCount(AjaxPagingData pagingData);
	
	public void del(PrivateLetter entity);
	
	
	
	public PrivateLetter getF(Integer userId, Date createTime);

}
