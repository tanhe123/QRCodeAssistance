package com.sdutlinux;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;

import com.sdutlinux.service.WebService;

import android.app.Activity;
import android.bluetooth.BluetoothClass.Service;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ShowInfoActivity extends Activity{
	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show);
		
		listView = (ListView) this.findViewById(R.id.listView);
		
		show();
	}

	private void show() {
		ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String,String>>(); 
		
		HashMap<String, String> item1 = new HashMap<String, String>();
		item1.put("key", "1111");
		item1.put("value", "1111");
		HashMap<String, String> item2 = new HashMap<String, String>();
		item2.put("key", "2222");
		item2.put("value", "2222");
		
		data.add(item1);
		data.add(item2);
		
		SimpleAdapter adapter = new SimpleAdapter(this, data, R.id.listView,
				new String[] {"key", "value"}, new int[] {R.id.key, R.id.value});
		listView.setAdapter(adapter);
	}
	
	/*
	private EditText et_id;
	private EditText et_name;
	private Intent data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show);
		
		et_id = (EditText) findViewById(R.id.et_id);
		et_name = (EditText) findViewById(R.id.et_name);

		et_id.setEnabled(false);
		et_name.setEnabled(false);
		
		data = getIntent();
		et_id.setText(data.getStringExtra("id"));
		et_name.setText(data.getStringExtra("name"));
	}
	
	public void editActivity(View v) {		
		Button btn_edit = (Button) findViewById(R.id.btn_edit);
		et_id = (EditText) findViewById(R.id.et_id);
		et_name = (EditText) findViewById(R.id.et_name);
		
		if (btn_edit.getText().toString().equals(getString(R.string.edit))) {
			et_name.setEnabled(true);

			btn_edit.setText(R.string.save);
		}
		else {
	//		String id = et_id.getText().toString();
			String name = et_name.getText().toString();
			
			// post参数
			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
			params.add(new BasicNameValuePair("_id", data.getStringExtra("_id")));
			params.add(new BasicNameValuePair("name", name));
			
			// POST 操作
			WebService service = new WebService(getApplicationContext());
			
			// TODO:测试完毕取消注释
			String s = service.post(WebService.SERVER_URL+"/change", params);
			
			et_name.setEnabled(true);
			
			btn_edit.setText(R.string.edit);
		//	if (s.trim().equals("done")) Toast.makeText(getApplicationContext(), "保存成功", 1).show();
			//else Toast.makeText(getApplicationContext(), "保存失败", 1).show();
			Toast.makeText(getApplicationContext(), s, 1).show();
		}
	}
	
	public void closeActivity(View v) {
		this.finish();
	}*/
}
