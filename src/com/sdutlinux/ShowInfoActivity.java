package com.sdutlinux;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import com.sdutlinux.service.SysApplication;
import com.sdutlinux.service.WebService;

public class ShowInfoActivity extends Activity{
	private ExpandableListView expListView;
	private TextView nameTxt;
	
	private final static String TAG = "showinfoactivitytest";
	private static final String CATEGORY = "Catogery";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show);
		
		SysApplication.getInstance().addActivity(this);
		
		expListView = (ExpandableListView) this.findViewById(R.id.expListView);	
		nameTxt = (TextView) this.findViewById(R.id.nameTxt);
		
		Intent data = getIntent();
		String id = data.getStringExtra("id");
		String name = data.getStringExtra("name");
		
		nameTxt.setText("设备名称: " + name);
		
		show(id);
	}
	
	private void show(String id) {
		String[] labels = new String[] {"设备基本信息", "设备类型", "使用单位相关", "财务相关", "财务审核相关", "归口审核相关"};
		
		WebService service = new WebService(getApplicationContext());
		
		List<HashMap<String, String>> groupData = new ArrayList<HashMap<String, String>>();
		List<List<HashMap<String, String>>> childData = new ArrayList<List<HashMap<String, String>>>();
		
		
		for (int i = 0; i <= 5; i++) {
			HashMap<String, String> curGroupMap = new HashMap<String, String>();
			groupData.add(curGroupMap);
			curGroupMap.put(CATEGORY, labels[i]);

			
			JSONObject jsonObj = service.getJson(WebService.SERVER_URL + "/" + id + "/" + i);	
			try {
				List<HashMap<String, String>> children = service.jsonToList(jsonObj);
				childData.add(children);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		ExpandableListAdapter mAdapter = new SimpleExpandableListAdapter(this, groupData,
				R.layout.category, new String[] {
						CATEGORY }, new int[] { R.id.category }, childData,
				R.layout.item, new String[] {
						"key", "value" }, new int[] { R.id.key, R.id.value });
		expListView.setAdapter(mAdapter);
		
	}
}
