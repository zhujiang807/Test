package com.java.util.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.java.util.aop.UserLogConfiguration.MethodName;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserLogAnnotation {
	public String type(); //b表示后台；f表示前台
	public String content(); //内容
	public MethodName method(); //方法名
}
