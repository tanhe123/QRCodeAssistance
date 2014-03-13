package com.sdutlinux;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.sdutlinux.service.SysApplication;
import com.zxing.activity.CaptureActivity;

public class QRCodeAssistance extends Activity {
    private static final String TAG = "QRCodeAssistancetest";
		
    private boolean isAnonymous;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置图标
        this.requestWindowFeature(Window.FEATURE_LEFT_ICON);
        setContentView(R.layout.main);
        
		// 设置图标
        setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.icon);
        
        SysApplication.getInstance().addActivity(this);
        
        // 处理匿名登录
        isAnonymous = 
        		getIntent().getBooleanExtra("anonymous", false);
        if (isAnonymous) {
        	setTitle(getTitle() + " [匿名用户]");
        }
        
        Button btn_scan_barcode = (Button) this.findViewById(R.id.btn_scan_barcode);
        Button btn_check		= (Button) this.findViewById(R.id.btn_check);
        Button btn_exit			= (Button) this.findViewById(R.id.btn_exit);
        
        btn_scan_barcode.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent openCameraIntent = new Intent(QRCodeAssistance.this,CaptureActivity.class);
				startActivityForResult(openCameraIntent, 0);		
			}
		});
        
        btn_check.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(QRCodeAssistance.this, InputIssue.class);
				startActivity(intent);
			}
		});
        
        btn_exit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				SysApplication.getInstance().exit();
			}
		});
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		//处理扫描结果（在界面上显示）
//		if (resultCode == RESULT_OK) {
//			Bundle result = data.getExtras();
//			String scanResult = result.getString("result");
		
			// 测试开始
			String scanResult = "20036370AA;钥匙柜;";
			// 测试开始
																																																																																						
			String[] results = scanResult.split(";");
		
			Bundle bundle = new Bundle();																																																																																																																																																																																																																																																																																																																																															
			bundle.putString("id", results[0]);
			bundle.putString("name", results[1]);
																																																																																																																																																																																																																																																																																																					
			Intent intent = new Intent(QRCodeAssistance.this, ShowInfoActivity.class);
			intent.putExtras(bundle);
			startActivity(intent);																																																																													
//		}									
	}
}