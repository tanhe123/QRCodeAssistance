package com.sdutlinux;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import com.sdutlinux.service.SysApplication;
import com.sdutlinux.service.WebService;
import com.sdutlinux.util.SimpleProgressDialog;

public class BasicInfoActivity extends Activity {
	private ExpandableListView expListView;
	private TextView nameTxt;
	 
	private SimpleProgressDialog progressDialog;
	
	private final static String TAG = "showinfoactivitytest";
	private static final String CATEGORY = "Catogery";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic_info);
		
		SysApplication.getInstance().addActivity(this);
		
		expListView = (ExpandableListView) this.findViewById(R.id.expListView);	
		nameTxt = (TextView) this.findViewById(R.id.nameTxt);
		
		Intent data = getIntent();
		String id = data.getStringExtra("id");
		String name = data.getStringExtra("name");
		
		nameTxt.setText("设备名称: " + name);
	}

	private void show(String id) {
		new UpdateTask(id).execute(WebService.SERVER_URL);
	}
	
	class UpdateTask extends AsyncTask<String, String, Boolean> {
		private String num;
		
		private List<List<HashMap<String, String>>> childData;
		private List<HashMap<String, String>> groupData;
		
		public UpdateTask(String num) {
			this.num = num;
			progressDialog = new SimpleProgressDialog(BasicInfoActivity.this, "提示", "正在获取机器信息");
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
			
			String[] labels = new String[] {"设备基本信息", "设备类型", "使用单位相关", "财务相关", "财务审核相关", "归口审核相关"};
			
			WebService service = new WebService(getApplicationContext());
			
			groupData = new ArrayList<HashMap<String, String>>();
			childData = new ArrayList<List<HashMap<String, String>>>();
			
			for (int i = 0; i <= 5; i++) {
				HashMap<String, String> curGroupMap = new HashMap<String, String>();
				groupData.add(curGroupMap);
				curGroupMap.put(CATEGORY, labels[i]);
				
				List<BasicNameValuePair> postParams = new ArrayList<BasicNameValuePair>();
				
				postParams.add(new BasicNameValuePair("num", num));
				postParams.add(new BasicNameValuePair("flag", i+""));
				
				try {
					JSONObject jsonObj = service.getJson(url, postParams);	
					List<HashMap<String, String>> children = service.jsonToList(jsonObj);
					childData.add(children);
					
				} catch (JSONException e) {
					return false;
				}
			}
			
			return true;
		}
		
		/**
		 * 如果登录成功，返回 类似于 [0,1,2,3,4,5] 这样的字符串
		 * 如果失败, 返回 [-1]
		 */
		protected void onPostExecute(Boolean result) {
			ExpandableListAdapter mAdapter = new SimpleExpandableListAdapter(BasicInfoActivity.this, groupData,
					R.layout.category, new String[] {
							CATEGORY }, new int[] { R.id.category }, childData,
					R.layout.item, new String[] {
							"key", "value" }, new int[] { R.id.key, R.id.value });
			expListView.setAdapter(mAdapter);
			
			progressDialog.dismiss();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.basic_info, menu);
		return true;
	}

}
