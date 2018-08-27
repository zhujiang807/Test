package com.friendship.manage.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.friendship.manage.dao.ILetterDao;
import com.friendship.manage.service.ILetterService;
import com.java.po.PrivateLetter;
import com.java.util.annotation.UserLogAnnotation;
import com.java.util.aop.UserLogConfiguration.MethodName;
import com.java.util.paging.AjaxPagingData;
import com.java.util.paging.PagePagingData;

@Service("letterService")
public class LetterService implements ILetterService{
	private ILetterDao letterDao;
	@Resource(name = "letterDao")
	public void setLetterDao(ILetterDao letterDao) {
		this.letterDao = letterDao;
	}

	@Override
	@Transactional(readOnly=true)
	public PagePagingData getLetterList(AjaxPagingData pagingData) {
		PagePagingData data = new PagePagingData();
		data.setData(letterDao.getLetterList(pagingData));
		data.setTotal(letterDao.getCount(pagingData));
		return data;
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "添加了一条私信", method = MethodName.LETTER_SAVE, type = "前")
	public String save(PrivateLetter entity) {
		if(letterDao.getF(entity.getUserId(), entity.getLetterCreateTime()) == null){
			letterDao.save(entity);
			return "发送成功！";
		}
		else
			return "你今天已经发过了，一天只能发一次！";
		
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "回复私信", method = MethodName.LETTER_UPDATECHECK, type = "后")
	public void updateCheck(PrivateLetter entity) {
		letterDao.updateCheck(entity);
	}

	@Override
	@Transactional
	@UserLogAnnotation(content = "删除私信", method = MethodName.LETTER_UPDATECHECK, type = "后")
	public void delete(PrivateLetter entity) {
		letterDao.del(entity);
	}
	
}
