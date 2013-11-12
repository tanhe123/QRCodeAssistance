package com.sdutlinux;

import java.util.ArrayList;
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
import android.widget.Toast;

public class ShowInfoActivity extends Activity{
	private EditText et_id;
	private EditText et_name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show);
		
		et_id = (EditText) findViewById(R.id.et_id);
		et_name = (EditText) findViewById(R.id.et_name);

		Intent data = getIntent();
		et_id.setText(data.getStringExtra("id"));
		et_name.setText(data.getStringExtra("name"));
	}
	
	public void editActivity(View v) {		
		Button btn_edit = (Button) findViewById(R.id.btn_edit);
		et_id = (EditText) findViewById(R.id.et_id);
		et_name = (EditText) findViewById(R.id.et_name);
		
		if (btn_edit.getText().toString().equals(getString(R.string.edit))) {
			et_id.setFocusable(true);
			et_name.setFocusable(true);
			btn_edit.setText(R.string.save);
		}
		else {
			String id = et_id.getText().toString();
			String name = et_name.getText().toString();
			
			// post参数
			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
			params.add(new BasicNameValuePair("id", id));
			params.add(new BasicNameValuePair("name", name));
			
			// TODO: POST 操作
			WebService service = new WebService(getApplicationContext());
			
			// TODO:测试完毕取消注释
//			service.post(WebService.SERVER_URL, params);
			
			et_id.setFocusable(false);
			et_name.setFocusable(false);
			
			btn_edit.setText(R.string.edit);
			
			Toast.makeText(getApplicationContext(), "保存成功", 1).show();
		}
	}
	
	public void closeActivity(View v) {
		this.finish();
	}
}
