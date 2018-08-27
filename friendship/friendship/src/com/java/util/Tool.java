/**
 * 
 */
package com.java.util;

import java.io.File;
import java.security.MessageDigest;
import java.io.FileInputStream;
import java.math.BigInteger;

/**
 * @author Administrator
 * 工具类，封装常用方法
 */
public final class Tool {
	
	/*
	 * 从路径中提取文件名，例如：c:\a\bb\ccc.java或者c:/a/bb/ccc.java ，返回：ccc.java
	 * @param path 文件路径
	 * @return 非空获取成功，null获取失败
	 */
	public static String pickFileNameFromPath( String path ) {
		String ret = null , temp = path;
		temp.replace('\\', '/');
		int pos = temp.lastIndexOf("/");
		if( pos != -1 )
			ret = temp.substring(pos);
		return ret;
	}
	
	/*
	 * 从文件名中提取前缀，例如：ccc.java ，返回：ccc
	 * @param fileName 文件名
	 * @return 非空获取成功，null获取失败
	 */
	public static String pickPrefixFromFileName( String fileName ) {
		String ret = null;
		int pos = fileName.indexOf(".");
		if( pos != -1 )
			ret = fileName.substring(0,pos);
		return ret;
	}
	
	/*
	 * 从文件名中提取后缀，例如：ccc.java ，返回：java
	 * @param fileName 文件名
	 * @return 非空获取成功，null获取失败
	 */
	public static String pickSuffixFromFileName( String fileName ) {
		String ret = null;
		int pos = fileName.lastIndexOf(".");
		if( pos != -1 )
			ret = fileName.substring(pos+1);
		return ret;
	}
	
	/**
	 * 生成UUID
	 * @return 返回UUID
	 */
	public static String GenerateGUID(){
		String strGUID = java.util.UUID.randomUUID().toString();
		return strGUID.replace("-", "");
	}
	
	/**
	 * 计算字符串Md5码
	 * @param s 字符串
	 * @return null-计算失败
	 */
	public static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
	/**
	 * 
	 * @param 计算文件md5码
	 * @return null-计算失败
	 */
	public static String FileMD5( File file ) {
		
		FileInputStream in = null;
		try{
			if( !file.isFile() || !file.exists() ) {
	        	throw new Exception("无效文件对象");
	        }
			MessageDigest digest = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			byte buffer[] = new byte[1024];int len=0;
			while ( (len = in.read(buffer, 0, 1024)) != -1 ) {
				digest.update(buffer, 0, len);
			}
			BigInteger bigInt = new BigInteger(1, digest.digest());
		    return bigInt.toString(16);
		}catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
        	if( in != null ) {
        		try{in.close();}catch (Exception e){}
        	}
        }
	}
	
	public static String FileMD5( String filePath ) {
        return FileMD5( new File(filePath) );
	}
	
	/**
	将字符串中的一些特殊字符用HTML转义或标签替换，如换行，单引号，双引号等
	*/
	public static String myHtmlEncode( final String html ) {
		if( html == null || html.isEmpty() ) {
			return "";
		}
		String str = html;
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		str = str.replaceAll("'", "&acute;");
		str = str.replaceAll("\r\n", "<br>");
		return str;
	}
	
	public static String myHtmlDecode( final String html ) {
		if( html == null || html.isEmpty() ) {
			return "";
		}
		String str = html;
		str = str.replaceAll("&amp;", "&");
		str = str.replaceAll("&lt;", "<");
		str = str.replaceAll("&gt;", ">");
		str = str.replaceAll("&quot;", "\"");
		str = str.replaceAll("&acute;", "'");	
		str = str.replaceAll("<br>", "\r\n");
		return str;
	}
	
	/**
	 * 检查目标字符串是否包含sql关键字
	 * @param str
	 * @return
	 */
	public static boolean hasSQLKeyword( final String strParam ) {
		if( strParam == null || strParam.isEmpty() ) {
			return false;
		}
		String str = strParam.toLowerCase();
        String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|*|%|chr|mid|master|truncate|" +
                "char|declare|sitename|xp_cmdshell|;|or|-|+|,|like'|and|exec|execute|insert|create|drop|" +
                "table|from|grant|group_concat|column_name|" +
                "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|" +
                "chr|mid|master|truncate|char|declare|or|;|-|--|+|,|like|%|#"; // sql关键字
        String[] badStrs = badStr.split("\\|");
        for (int i = 0; i < badStrs.length; i++) {
            if (str.indexOf( badStrs[i] ) >= 0) {
            	if(strParam.matches("\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{2}:\\d{2}:\\d{2}") && badStrs[i].equals("-")){
            		continue;
            	}
                return true;
            }
        }
        return false;
	}
	
	/**
	 * 产生session存放用户信息key
	 * @return
	 */
	public static String getSessionUserKey(String qq){
		return MD5(((int)(Math.random()*100000000))+""+qq);
	}
	
	/**
	 * 产生session存放管理员信息key
	 * @return
	 */
	public static String getSessionManageKey(String qq){
		return MD5(((int)(Math.random()*100000000))+""+qq);
	}
}
