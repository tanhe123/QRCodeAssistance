package com.sdutlinux;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.RadioGroup;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.sdutlinux.service.SysApplication;
import com.sdutlinux.service.WebService;

public class ShowInfoActivity extends Activity{
//	private ExpandableListView expListView;
//	private TextView nameTxt;
	
	private final static String TAG = "showinfoactivitytest";
//	private static final String CATEGORY = "Catogery";
	
	private TabHost tabHost;
	private RadioGroup radioGroup;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_info);
		
		radioGroup = (RadioGroup) this.findViewById(R.id.radio_group);
		
		LayoutInflater inflater = LayoutInflater.from(this);
		LocalActivityManager localActivityManager = new LocalActivityManager(this, true);
		localActivityManager.dispatchCreate(savedInstanceState);
		tabHost = (TabHost) this.findViewById(android.R.id.tabhost);
		//如果通过findViewById得到TabHost 一定要调用 TabHost.setup();
		tabHost.setup(localActivityManager);
		
		
		
		/*
		SysApplication.getInstance().addActivity(this);
		
		expListView = (ExpandableListView) this.findViewById(R.id.expListView);	
		nameTxt = (TextView) this.findViewById(R.id.nameTxt);
		
		Intent data = getIntent();
		String id = data.getStringExtra("id");
		String name = data.getStringExtra("name");
		
		nameTxt.setText("设备名称: " + name);
		
		show(id);*/
	}
	/*
	private void show(String id) {
		new UpdateTask(id).execute(WebService.SERVER_URL);
	}
	
	
	class UpdateTask extends AsyncTask<String, String, Boolean> {
		private String num;
		
		private List<List<HashMap<String, String>>> childData;
		private List<HashMap<String, String>> groupData;
		
		public UpdateTask(String num) {
			this.num = num;
		}
	*/	
		/**
		 * 后台查询信息
		 */
		/*@Override
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
		*/
		/**
		 * 如果登录成功，返回 类似于 [0,1,2,3,4,5] 这样的字符串
		 * 如果失败, 返回 [-1]
		 *//*
		protected void onPostExecute(Boolean result) {
			ExpandableListAdapter mAdapter = new SimpleExpandableListAdapter(ShowInfoActivity.this, groupData,
					R.layout.category, new String[] {
							CATEGORY }, new int[] { R.id.category }, childData,
					R.layout.item, new String[] {
							"key", "value" }, new int[] { R.id.key, R.id.value });
			expListView.setAdapter(mAdapter);
		}
	}*/
}
