package com.sdutlinux.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.text.style.BulletSpan;
import android.widget.Toast;

public class WebService {
	public static final String SERVER_URL = "http://http://192.168.1.101:8000/devices/phone/";

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
			Toast.makeText(context, "连接失败", 1).show();
		}
		return null;
	}

	public String get(String url) {
		try {
			// 根据内容来源地址创建一个Http请求
			HttpGet request = new HttpGet(url);
			Toast.makeText(context, url, 1).show();
			// // 设置参数的编码
			HttpResponse httpResponse = new DefaultHttpClient().execute(request); // 发送请求并获取反馈
			Toast.makeText(context, "返回代码：" + httpResponse.getStatusLine().getStatusCode(), 1).show();
			// 解析返回的内容
			if (httpResponse.getStatusLine().getStatusCode() != 404) {
				String result = EntityUtils.toString(httpResponse.getEntity());
				return result;
			}
		} catch (Exception e) {
			Toast.makeText(context, "发生错误", 1).show();
		}
		return null;
	}

	public Bundle jsonText(String url, List<BasicNameValuePair>params) {
		try {
			Bundle bundle = new Bundle();
			
			HttpPost request = new HttpPost(url); // 根据内容来源地址创建一个Http请求
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8)); // 设置参数的编码
//			HttpResponse httpResponse = new DefaultHttpClient().execute(request); // 发送请求并获取反馈
			HttpClient client = new DefaultHttpClient();
			// 设置超时时间
			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);
			
			HttpResponse httpResponse = client.execute(request); // 发送请求并获取反馈
			// 解析返回的内容
			if (httpResponse.getStatusLine().getStatusCode() != 404) {
				String result = EntityUtils.toString(httpResponse.getEntity());
				JSONObject jsonObj = new JSONObject(result).getJSONObject("QRinfo");
				String id = jsonObj.getString("id");	//post返回的id为真实id，但不是二维码的信息
				String name = jsonObj.getString("name");
				
				// 放入映射表
				bundle.putString("id", id);
				bundle.putString("name", name);
				
				String content = "解析内容为: " + "id:" + id + " " + "name:" + name;
				Toast.makeText(context, "解析内容为:" + content, 1).show();
				return bundle;
			}
		} catch (Exception e) {
			Toast.makeText(context, "连接超时", 1).show();
		}
		Toast.makeText(context, "解析失败", 1).show();
		return null;
	}
}
