package com.sdutlinux.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Toast;

public class WebService {
	public static final String SERVER_URL = "http://192.168.0.101:8000/devices/phone/";
	public static final String LOGIN_URL = "http://192.168.0.101:8000/devices/phone-login/";
	public static final String TAG = "WebServiceTest";
	
	private static String SESSIONID = null;

	private Context context;
	public WebService(Context context) {
		this.context = context;
	}
	
	// 测试用
	public String post(String url, List<BasicNameValuePair> params) {
		try {
 			HttpPost request = new HttpPost(url); // 根据内容来源地址创建一个Http请求
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8)); // 设置参数的编码
			
			// 设置sessionID
			if (null != SESSIONID) {
				request.setHeader("Cookie", "sessionid=" + SESSIONID);
			}
		
			DefaultHttpClient client = new DefaultHttpClient();

			// 设置超时时间
			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);
			
			Log.i(TAG, "pre execute");
			
			HttpResponse httpResponse = client.execute(request); // 发送请求并获取反馈
			
			Log.i(TAG, "after execute");
			
			// 解析返回的内容
			if (httpResponse.getStatusLine().getStatusCode() != 404) {
				Log.i(TAG,  "pre getcookie");
				
				// 获取 Cookie, sessionid
				List<Cookie> cookie = client.getCookieStore().getCookies();
	
				Log.i(TAG, "after getcookie");
				
				for (Cookie c : cookie) {
					if (c.getName().equals("sessionid")) {
						Log.i(TAG, "" + c.getName() + ":" + c.getValue());
						SESSIONID = c.getValue();	
					}
				}
				
				Log.i(TAG, "pre get result");
				
				String result = EntityUtils.toString(httpResponse.getEntity());
				Log.i(TAG, "after getresult");
				return result;
			}
		} catch (Exception e) {
			Log.i(TAG, "exception");
			Toast.makeText(context, "连接失败", Toast.LENGTH_SHORT).show();
		}
		return null;
	}
	
	// 测试用
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
	 * @throws JSONException 
	 */
	public JSONObject getJson(String url, List<BasicNameValuePair> params) throws JSONException {
		String content = post(url, params);
		
		JSONObject jsonObj = stringToJson(content);
		
		return jsonObj;
	}
	
	public JSONObject stringToJson(String content) throws JSONException {
		JSONObject jsonObj = new JSONObject(content).getJSONObject("QRinfo");
		return jsonObj;
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
	
	/**
	 * 从json中获取 key 的值
	 * @param jsonObj
	 * @param key
	 * @return
	 * @throws JSONException 
	 */
	public String getFromJson(JSONObject jsonObj, String key) throws JSONException {
		Iterator<String> keys = jsonObj.keys();
		
		String value = jsonObj.getString(key);
		
		return value;
	}
}
