package com.sdutlinux;

// 密码安全问题， 配置文件存取，其实就是读取 xml
 

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sdutlinux.service.DataService;
import com.sdutlinux.service.SysApplication;
import com.sdutlinux.service.WebService;

public class UserLogin extends Activity {
	private static final String TAG	= "UserLoginTest";
	
	private DataService dataService;
	private WebService webService;
	
	private EditText edt_userName;
	private EditText edt_password;
	private Button btn_login;
	private TextView txt_error;
	private CheckBox chb_remember;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_login);
		
		SysApplication.getInstance().addActivity(this);
		
		dataService = new DataService(getApplicationContext());
		webService  = new WebService(getApplicationContext());
		
		edt_userName = (EditText) findViewById(R.id.edt_username);
		edt_password = (EditText) findViewById(R.id.edt_password);
		btn_login	 = (Button)	  findViewById(R.id.btn_login);
		txt_error	 = (TextView) findViewById(R.id.txt_error);
		chb_remember = (CheckBox) findViewById(R.id.chb_remember);
		
		if (dataService.getBoolean("remember")) {
			String userName = dataService.getString("username");
			String password = dataService.getString("password");
			
			edt_userName.setText(userName);
			edt_password.setText(password);
			
			chb_remember.setChecked(true);
		}
		
		
		btn_login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String userName = edt_userName.getText().toString().trim();
				String password = edt_password.getText().toString().trim();
				
				if (userName.equals("")) {
					txt_error.setText("帐号不能为空");
					txt_error.setVisibility(View.VISIBLE);
					edt_userName.requestFocus();
					return ;
				}
				
				if (password.equals("")) {
					txt_error.setText("密码不能为空");
					txt_error.setVisibility(View.VISIBLE);
					edt_password.requestFocus();
					return ;
				}

				
				try {
					//String result = 
							login(userName, password);/*
					if ( ! result.contains("-1") ) {	// 登录成功
						if (chb_remember.isChecked()) { // 点击了记住密码
							dataService.saveString("username", userName);
							dataService.saveString("password", password);
							dataService.saveBoolean("remember", chb_remember.isChecked());
							
							Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
							
						} else {						// 如果不保存密码
							dataService.clear();
						}
						
						Intent intent = new Intent(UserLogin.this, QRCodeAssistance.class);
						intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(intent);
						
						// 结束当前的Activity
						UserLogin.this.finish();
					} else {							// 登录失败
						txt_error.setText("帐号或密码错误");
						txt_error.setVisibility(View.VISIBLE);
					}*/
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * 如果登录成功，返回 类似于 [0,1,2,3,4,5] 这样的字符串
	 * 如果失败, 返回 [-1]
	 * @param username
	 * @param password
	 * @return 
	 * @throws JSONException
	 */
	public void login(String username, String password) throws JSONException {
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));
		

		
		JSONObject jsonObj = webService.getJson(WebService.LOGIN_URL, params);
		
		//String flag = webService.getFromJson(jsonObj, "flag");
		
	//	return flag;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.user_login, menu);
		return true;
	}
}
