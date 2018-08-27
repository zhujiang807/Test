package com.java.util;

import java.io.File;
import java.io.InputStream;
import java.io.Writer;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author zhuyou
 */

public class ActionBase{
	
	/**
	 *	以字符串形式响应客户端请求
	 */
	protected void writeString(HttpServletResponse response, Object obj) {
		response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
		try {
			Writer writer = response.getWriter();
			writer.write(getJsonString(obj, false));
			writer.flush();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 *	以字符串形式响应客户端请求
	 */
	protected void writeStringNotNull(HttpServletResponse response, Object obj) {
		response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
		try {
			Writer writer = response.getWriter();
			writer.write(getJsonString(obj, true));
			writer.flush();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 *	以字符串形式响应客户端请求
	 */
	protected void writeHtml(HttpServletResponse response, String  content) {
		response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
		try {
			Writer writer = response.getWriter();
			writer.write(content);
			writer.flush();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 *	获取站点基本路径
	 */
	protected String getWebsiteBasePath(HttpServletRequest request) {
		String contextpath = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextpath;
		return basePath;
	}
	
	/**
	 * 对象转为字符串
	 * @param obj
	 * @return
	 */
	protected static String getJsonString(Object obj, Boolean notNull) {
		
		String retString = "";
		try {
			ObjectMapper mapper = new ObjectMapper();
			if(notNull) mapper.setSerializationInclusion(Include.NON_NULL);  
			retString = mapper.writeValueAsString(obj);
		}catch( Exception ex ) {
			ex.printStackTrace();
		}
		
		return retString;
	}
	
	/**
	 *	将请求体中的json字串格式化成map对象
	 */
	protected Map<String,Object> jsonToMap(HttpServletRequest request) {
		Map<String,Object> ret = null;
		try{
			ObjectMapper mapper = new ObjectMapper();
			InputStream iStream = request.getInputStream();
			ret = mapper.readValue(iStream, new TypeReference<Map<String,Object>>(){} );
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return ret;
	}
	
	/**
	 *	将请求体中的json字串格式化成对象
	 */
	protected <T> Object jsonToObject( Class<T> type, HttpServletRequest request) {
		try{
			ObjectMapper mapper = new ObjectMapper();
			InputStream iStream = request.getInputStream();
			return mapper.readValue(iStream, type );
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	public synchronized UserInfo getUser(String key){
		return (UserInfo) CacheBase.get(key).getData();
	}
	
	public synchronized Integer getUserId(String key){
		return ((UserInfo) CacheBase.get(key).getData()).getUser().getUserId();
	}
	
	public synchronized Date getOperationTime(String key){
		return CacheBase.get(key).getOperationTime();
	}
	
	public synchronized ManageInfo getManage(String key){
		return (ManageInfo) CacheBase.get(key).getData();
	}
	
	public synchronized Integer getManageId(String key){
		return ((ManageInfo) CacheBase.get(key).getData()).getManage().getManId();
	}
	
	/**
	 *	将请求体中的json字串格式化成list
	 * @param <T>
	 */
	protected <T> List<T> jsonToList(String value) {
		try{
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(value, new TypeReference<List<T>>() {});
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	protected Timestamp getDate(){
		return Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
	}
	
	/**
	 * 文件上传
	 * @param file
	 * @param newPath
	 * @param fileName
	 * @param config
	 * @throws Exception
	 */
	protected void fileUploading(MultipartFile file, String newPath) throws Exception{
		File actFile = new File(newPath);                      //地址
		if(!actFile.exists()){  
			actFile.mkdirs();  
		} 
		file.transferTo(actFile);
	}
}
