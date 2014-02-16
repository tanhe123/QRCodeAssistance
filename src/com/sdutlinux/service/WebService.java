package com.sdutlinux.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class WebService {
	public static final String SERVER_URL = "http://192.168.1.101:8000/devices/phone";
	public static final String TAG = "WebServiceTest";
	
	private Context context;
	public WebService(Context context) {
		this.context = context;
	}
	
	public String post(String url, List<BasicNameValuePair> params) {
		try {
 			HttpPost request = new HttpPost(url); // 根据内容来源地址创建一个Http请求
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8)); // 设置参数的编码
			
			HttpClient client = new DefaultHttpClient();
			
			// 设置超时时间
			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);
			
			HttpResponse httpResponse = client.execute(request); // 发送请求并获取反馈
			
			// 解析返回的内容
			if (httpResponse.getStatusLine().getStatusCode() != 404) {
				String result = EntityUtils.toString(httpResponse.getEntity());
				return result;
			}
		} catch (Exception e) {
			Toast.makeText(context, "连接失败", Toast.LENGTH_SHORT).show();
		}
		return null;
	}

	public String get(String url) {
		
		try {
			// 根据内容来源地址创建一个Http请求
			HttpGet request = new HttpGet(url);
			// // 设置参数的编码
			HttpResponse httpResponse = new DefaultHttpClient().execute(request); // 发送请求并获取反馈
			// 解析返回的内容
			if (httpResponse.getStatusLine().getStatusCode() != 404) {
				String result = EntityUtils.toString(httpResponse.getEntity());
				return result;
			}
		} catch (Exception e) {
			Toast.makeText(context, "发生错误", Toast.LENGTH_SHORT).show();
		}
		return null;
	}

	/**
	 * 获取 json 信息
	 * @param url 存储信息的 url
	 * @return 返回JSONObject对象
	 */
	public JSONObject getJson(String url) {
		try {
			Log.i(TAG, url);
			// 根据内容来源地址创建一个Http请求
			HttpGet request = new HttpGet(url);
			// 设置参数的编码
			HttpResponse httpResponse = new DefaultHttpClient().execute(request); // 发送请求并获取反馈
			
			// 解析返回的内容
			if (httpResponse.getStatusLine().getStatusCode() != 404) {
				String result = EntityUtils.toString(httpResponse.getEntity());
				JSONObject jsonObj = new JSONObject(result).getJSONObject("QRinfo");
				
				return jsonObj;
			}				
		} catch (Exception e) {
			Toast.makeText(context, "连接超时", Toast.LENGTH_SHORT).show();
		}
		Toast.makeText(context, "解析失败", Toast.LENGTH_SHORT).show();
		return null;
	}
	
	/**
	 * 
	 * @param jsonObj
	 * @throws JSONException 
	 */
	public List<HashMap<String, String>> jsonToList(JSONObject jsonObj) throws JSONException {
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
}
