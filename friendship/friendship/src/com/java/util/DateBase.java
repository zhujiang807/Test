package com.java.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateBase {
	/**
	* 字符串转换成日期
	* @param str
	* @return date
	*/
	public static Date strToDate(String str) {
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   Date date = null;
	   try {
	    date = format.parse(str);
	   } catch (Exception e) {
	    e.printStackTrace();
	   }
	   return date;
	}
	
	public static Date strToDateYMD(String str) {
		   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		   Date date = null;
		   try {
		    date = format.parse(str);
		   } catch (Exception e) {
		    e.printStackTrace();
		   }
		   return date;
		}
	
	/**
	 * 时间date转为timestamp
	 * @param date
	 * @return
	 */
	public static Timestamp dateToTimestamp(Date date){
		return Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date));
	}

	/**
	 * 字符串转为timestamp
	 * @param date
	 * @return
	 */
	public static Timestamp strToTimestamp(String date){		
		return Timestamp.valueOf(date);
	}
	
	/**
	 * 获取当前时间
	 * @return
	 */
	public static Timestamp getTimestampYMDHMS(){
		return Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
	}
	
	/**
	 * 获取当前时间
	 * @return
	 */
	public static Date getDateYMD(){
		return strToDateYMD(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	}
	
	/**
	 * 获取当前时间
	 * @return
	 */
	public static Date getDateYMDHMS(){
		return strToDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
	}
	
	/**
	 * 获取当前时间
	 * @return
	 */
	public static String getDateString(){
		return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
	}
	
	/**
	 * 获取当前时间
	 * @return
	 */
	public static String getDateStringYMD(){
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
}
