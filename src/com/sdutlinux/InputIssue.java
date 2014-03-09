package com.sdutlinux;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.widget.Spinner;

public class InputIssue extends Activity {
	private Spinner sp_teachers;
	private Editor edt_title, edt_desc;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input_issue);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.input_issue, menu);
		return true;
	}

}
