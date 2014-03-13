package com.sdutlinux;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class InputIssue extends Activity {
	private Spinner sp_teachers;
	private Editor edt_title, edt_desc;
	private String[] teachers;
	private Thread initSpinnerThread;
	private ProgressDialog progressDialog;
	
	private static final int START = 1;
	private static final int OVER = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input_issue);
		
		sp_teachers = (Spinner) this.findViewById(R.id.sp_teachers);
		
		initSpinnerTeachers();
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
			
			// 获取老师名单
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// 得到老师名单
			teachers = new String[] {"管老师", "陈老师", "谭老师", "张老师",
					"汤老师", "刘老师"};
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
			progressDialog = new ProgressDialog(this);
			progressDialog.setTitle("获取老师列表");
			progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progressDialog.setMessage("正在获取，请稍后");
			progressDialog.setCancelable(false);	// 不可取消
		}
		
		progressDialog.show();
	}
	
	private void dismissProgressDialog() {
		progressDialog.dismiss();
	}
}
