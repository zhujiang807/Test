package com.java.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class CacheBase implements Serializable{
	private static final long serialVersionUID = 1L;
	public static ConcurrentHashMap<String, Cache> hashMap = new ConcurrentHashMap<String, Cache>();

	/**
	 * 加入元素
	 * @param key
	 * @param value
	 */
	public synchronized static void push(String key, Object value){
		Cache cache = new Cache(value);
		hashMap.put(key, cache);
	}
	
	/**
	 * 加入元素
	 * @param key
	 * @param value
	 */
	public synchronized static void push(String key, Object value, Date operationTime, Integer depositTime){
		Cache cache = new Cache(value, operationTime, depositTime );
		hashMap.put(key, cache);
	}
	
	/**
	 * 加入元素
	 * @param key
	 * @param value
	 */
	public synchronized static void push(String key, Object value, Date operationTime){
		Cache cache = new Cache(value, operationTime);
		hashMap.put(key, cache);
	}
	
	/**
	 * 加入元素
	 * @param key
	 * @param value
	 */
	public synchronized static void push(String key, Object value, Integer depositTime){
		Cache cache = new Cache(value, depositTime);
		hashMap.put(key, cache);
	}
	
	/**
	 * 通过key取出value
	 * @param key
	 * @return
	 */
	public synchronized static Cache get(String key){
		return hashMap.get(key);
	}
	
	/**
	 * 通过key删除value
	 * @param key
	 */
	public synchronized static void remove(String key){
		hashMap.remove(key);
	}
	
	public synchronized static void updateTime(String key){
		Cache cache =  hashMap.get(key);
		if(cache != null){
			cache.setOperationTime(DateBase.getDateYMDHMS());
		}
	}
	
	
	/**
	 * 自动清除map数据,超过时间的数据
	 */
	public synchronized void remove(){
		ArrayList<String> keyList = new ArrayList<String>();
		for (String key : hashMap.keySet()) {
			Cache cache = hashMap.get(key);
			if((DateBase.getDateYMDHMS().getTime() - cache.getOperationTime().getTime()) > cache.getDepositTime()){
				keyList.add(key);
			}
		}
		
		for (String key : keyList) {
			hashMap.remove(key);
		}
	}
}