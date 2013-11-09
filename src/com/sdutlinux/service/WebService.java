package com.sdutlinux.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.content.Context;
import android.widget.Toast;

public class WebService {
	public static final String SERVER_URL = "http://192.168.1.152:5000";

	private Context context;
	public WebService(Context context) {
		this.context = context;
	}
	
	public static String post(String url, List<BasicNameValuePair> params) {
		try {
			HttpPost request = new HttpPost(url); // 根据内容来源地址创建一个Http请求
			//List params = new ArrayList();
			//params.add(new BasicNameValuePair("id", "527a239151b9ba081f19a3b2")); // 添加必须的参数
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8)); // 设置参数的编码
			HttpResponse httpResponse = new DefaultHttpClient().execute(request); // 发送请求并获取反馈
			// 解析返回的内容
			if (httpResponse.getStatusLine().getStatusCode() != 404) {
				String result = EntityUtils.toString(httpResponse.getEntity());
		//		Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
		//		showText.setText(result);
				return result;
			}
		} catch (Exception e) {
		}
		return null;
	}

	public static String get(String url) {
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
		}
		return null;
	}

	public String jsonText(String url, List<BasicNameValuePair>params) {
		try {
			Toast.makeText(context, "正在post，请等待", 1);
			HttpPost request = new HttpPost(url); // 根据内容来源地址创建一个Http请求
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8)); // 设置参数的编码
			// 设置参数的编码
			HttpResponse httpResponse = new DefaultHttpClient().execute(request); // 发送请求并获取反馈
			// 解析返回的内容
			Toast.makeText(context, "正在解析", 1);
			if (httpResponse.getStatusLine().getStatusCode() != 404) {
				String result = EntityUtils.toString(httpResponse.getEntity());
				JSONObject jsonObj = new JSONObject(result).getJSONObject("QRinfo");
				String id = jsonObj.getString("id");
				String name = jsonObj.getString("name");
				String content = "解析内容为: " + "id:" + id + " " + "name:" + name;
				Toast.makeText(context, "解析内容为:" + content, 1);
				return content;
			}
		} catch (Exception e) {
		}
		
		Toast.makeText(context, "解析失败", 1);
		return null;
	}
}
