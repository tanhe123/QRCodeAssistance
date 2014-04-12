package com.sdutlinux;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.sdutlinux.service.WebService;
import com.sdutlinux.utils.JsonParser;
import com.sdutlinux.utils.SimpleProgressDialog;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class IssueInfoActivity extends Activity {
	private ExpandableListView ev_issue_info;
	private SimpleProgressDialog progressDialog;
	
	private static final String TAG = "IssueInfoActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_issue_info);
		
		ev_issue_info = (ExpandableListView) this.findViewById(R.id.el_issue_info);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.issue_info, menu);
		return true;
	}
	

	class UpdateTask extends AsyncTask<String, String, Boolean> {
		private String num;
		
		
		private List<List<HashMap<String, String>>> childData;
		private List<HashMap<String, String>> groupData;
		private List<HashMap<String, String>> datas;
		
		public UpdateTask(String num) {
			this.num = num;
			progressDialog = new SimpleProgressDialog(IssueInfoActivity.this, "提示", "正在获取问题信息");
		}
		
		@Override
		protected void onPreExecute() {
			progressDialog.show();
		}
		
		/**
		 * 后台查询信息
		 */
		@Override
		protected Boolean doInBackground(String... params) {

			String url = params[0];
			
			WebService service = new WebService(getApplicationContext());
			
			
//			String[] labels = new String[] {"设备基本信息", "设备类型", "使用单位相关", "财务相关", "财务审核相关", "归口审核相关"};
			
			List<BasicNameValuePair> postParams = new ArrayList<BasicNameValuePair>();
			
			postParams.add(new BasicNameValuePair("num", num));
			postParams.add(new BasicNameValuePair("flag", 2+""));	// 2 代表维修记录
			
			groupData = new ArrayList<HashMap<String, String>>();
			childData = new ArrayList<List<HashMap<String, String>>>();
			
			JSONObject jsonObject;
			try {
				jsonObject = service.getJson(url, postParams);
				Log.i(TAG, jsonObject.names().toString());
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			return true;
			
			/*
//			for (int i = 0; i <= 5; i++) {
				HashMap<String, String> curGroupMap = new HashMap<String, String>();
				groupData.add(curGroupMap);
				curGroupMap.put(CATEGORY, labels[i]);
				
				
				
				try {
					JSONObject jsonObj = service.getJson(url, postParams);				
					List<HashMap<String, String>> children = service.jsonToList(jsonObj);
					childData.add(children);
					
				} catch (JSONException e) {
					return false;
				}
//			}
			
			
			datas = new ArrayList<HashMap<String,String>>();
				List<BasicNameValuePair> postParams = new ArrayList<BasicNameValuePair>();
				
				postParams.add(new BasicNameValuePair("num", num));
				postParams.add(new BasicNameValuePair("flag", 2+""));
				
				try {
					JSONObject jsonObj = service.getJson(url, postParams);
					
					List<HashMap<String, String>> data = JsonParser.jsonToList(jsonObj);
					datas.addAll(data);
					
				} catch (JSONException e) {
					return false;
				}
			}*/
		}
		
		protected void onPostExecute(Boolean result) {
//			SimpleAdapter adapter = new SimpleAdapter(IssueInfoActivity.this, datas, R.layout.item, new String[] {"key",  "value"},
//					new int[] {R.id.key, R.id.value});
//			lv_history.setAdapter(adapter);
			
//			progressDialog.dismiss();
		}
	}
}
