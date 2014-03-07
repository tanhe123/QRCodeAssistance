package com.sdutlinux;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class InputIssue extends Activity {
	private 
	
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
