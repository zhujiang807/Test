package com.java.util.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.friendship.manage.dao.IUserMovieDao;

/**
 * 
 * @author XHW
 * 
 * @date 2011-7-20
 * 
 */
public class HibernateTest {

	public static void main(String[] args) {
		HibernateTest test = new HibernateTest();
		test.add();
	}

	public void add() {
		ApplicationContext context = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IUserMovieDao com = (IUserMovieDao) context.getBean(IUserMovieDao.class);
		System.out.println(com.getF(1, "nn"));
	}
}