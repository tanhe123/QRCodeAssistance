package com.sdutlinux.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JsonParser {
	public static JSONObject getJsonFromString(String content, String key) throws JSONException {
		JSONObject jsonObj = stringToJson(content).getJSONObject(key);
		return jsonObj;
	}
	
	public static JSONObject getJsonFromJson(JSONObject jsonObject, String key) throws JSONException {
		return jsonObject.getJSONObject(key);
	}
	
	public static JSONObject stringToJson(String content) throws JSONException {
		return new JSONObject(content);
	}
	
	/**
	 * 
	 * @param jsonObj
	 * @throws JSONException 
	 */
	public static List<HashMap<String, String>> jsonToList(JSONObject jsonObj) throws JSONException {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		
		Iterator<String> keys = jsonObj.keys();
		
		while (keys.hasNext()) {
			String key = keys.next();
			String value = jsonObj.getString(key);
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("key", key);
			map.put("value", value);
			
			list.add(map);
		}
		
		return list;
	}
	
	/**
	 * 从json中获取 key 的值
	 * @param jsonObj
	 * @param key
	 * @return
	 * @throws JSONException 
	 */
	public static String getStringFromJson(JSONObject jsonObj, String key) throws JSONException {
		String value = jsonObj.getString(key);
		
		return value;
	}
	/*
	public static List<String> getKeys(JSONObject jsonObject) {
		List<String> keys = new ArrayList<String>();
		for (String key : keys) {
			
		}
		
		jsonObject.names();
		
//		return jsonObject.keys()
	}*/
}
