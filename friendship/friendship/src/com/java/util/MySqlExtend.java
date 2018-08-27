package com.java.util;

import java.sql.Types;
import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQL5Dialect;

public class MySqlExtend extends MySQL5Dialect{
	public MySqlExtend(){
		super();
		
		registerHibernateType(Types.LONGNVARCHAR, Hibernate.TEXT.getName());

		registerHibernateType(-1, Hibernate.STRING.getName());

		registerHibernateType(Types.DECIMAL, Hibernate.BIG_DECIMAL.getName());
	}
}
