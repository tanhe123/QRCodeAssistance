package com.sdutlinux;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.zxing.activity.CaptureActivity;

public class QRCodeAssistance extends Activity {
    private static final String TAG = "QRCodeAssistancetest";
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
        
        Button scanBarCodeButton = (Button) this.findViewById(R.id.btn_scan_barcode);
        Button checkButton		 = (Button) this.findViewById(R.id.btn_check);
        
        scanBarCodeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent openCameraIntent = new Intent(QRCodeAssistance.this,CaptureActivity.class);
				startActivityForResult(openCameraIntent, 0);		
			}
		});
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		//处理扫描结果（在界面上显示）
	//	if (resultCode == RESULT_OK) {
		//	Bundle result = data.getExtras();
		//	String scanResult = result.getString("result");
		
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
		}
	//}
}