package com.sdutlinux;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
	
	public void closeActivity(View v) {
		this.finish();
	}
}
