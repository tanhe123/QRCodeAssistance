package com.sdutlinux;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.sdutlinux.service.WebService;
import com.sdutlinux.utils.SimpleProgressDialog;

public class InputIssueActivity extends Activity {
	private Spinner sp_teachers;
	private EditText et_title, et_desc;
	private String[] teachers;
	private Thread initSpinnerThread;
	private Button bt_input, bt_cancel;
	
	private SimpleProgressDialog progressDialog;
	
	private WebService webService;
	
	private static final int START = 1;
	private static final int OVER = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input_issue);
		
		sp_teachers = (Spinner) this.findViewById(R.id.sp_teachers);
		bt_input	= (Button) this.findViewById(R.id.bt_input);
		bt_cancel	= (Button) this.findViewById(R.id.bt_cancel);
		
		et_title	= (EditText) this.findViewById(R.id.et_issue_title);
		et_desc		= (EditText) this.findViewById(R.id.et_issue_desc);
		
		initSpinnerTeachers();

		bt_cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// 提交问题记录
			}
		});
		
		bt_cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(InputIssueActivity.this, QRCodeAssistance.class);
				startActivity(intent);
				finish();
			}
		});
		
		webService = new WebService(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.input_issue, menu);
		
		
		return true;
	}
	
	
	private void initSpinnerTeachers() {
		initSpinnerThread = new Thread(initSpinnerRunnable);
		initSpinnerThread.start();
	}
	
	private void initSpinner(String [] teachers) {
		// 将可选部分与 ArrayAdapter 连接起来
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, teachers);
		
		// 设置下拉列表的风格 
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		// 将 adapter 添加到 spinner 中
		sp_teachers.setAdapter(adapter);
		
		// 添加事件到 spinner
	//	sp_teachers.setOnItemSelectedListener();
	}
	
	private Runnable initSpinnerRunnable = new Runnable() {
		@Override
		public void run() {
			// 任务开始前
			mHandler.sendEmptyMessage(START);
			
			// test 开始
			// 获取老师名单
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			
			// 得到老师名单
//			teachers = new String[] {"管老师", "陈老师", "谭老师", "张老师",
//					"汤老师", "刘老师"};
			// test 结束
			
			try {
				JSONObject jsonObject = webService.getJson(WebService.TEACHER_LIST_URL);
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			
			mHandler.sendEmptyMessage(OVER); 
		}
	};
	
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case OVER:
				initSpinner(teachers);
				// 关闭 progressDialog
				dismissProgressDialog();
								
				break;
			case START:
				// 显示 progressDialog
				showProgressDialog();
				
				break;
			default:
				break;
			}
		}
	};
	
	private void showProgressDialog() {
		if (progressDialog == null) {
			progressDialog = new SimpleProgressDialog(this, "获取老师列表", "正在获取，清稍后");
		}
		
		progressDialog.show();
	}
	
	private void dismissProgressDialog() {
		progressDialog.dismiss();
	}
}
