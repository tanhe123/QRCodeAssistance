package com.sdutlinux;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.message.BasicNameValuePair;

import com.sdutlinux.R;
import com.sdutlinux.service.WebService;
import com.google.zxing.WriterException;
import com.zxing.activity.CaptureActivity;
import com.zxing.encoding.EncodingHandler;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class QRCodeAssistance extends Activity {
    /** Called when the activity is first created. */
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button scanBarCodeButton = (Button) this.findViewById(R.id.btn_scan_barcode);
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
//		if (resultCode == RESULT_OK) {
//			Bundle bundle = data.getExtras();
//			String scanResult = bundle.getString("result");
		
			// 测试开始
			String scanResult = "20036370AA;钥匙柜;";
			// 测试开始
			
//			Toast.makeText(getApplicationContext(), scanResult, 1).show();
			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
			// 添加post的参数
			params.add(new BasicNameValuePair("_id", scanResult));
		//	params.add(new BasicNameValuePair("_id", "527a239151b9ba081f19a3b2"));
			WebService service = new WebService(getApplicationContext());
			Bundle bundleResult = service.jsonText(WebService.SERVER_URL, params);

			
			//bundleResult.putString("_id", scanResult);
			
			/*
			/////////测试代码开始
			Bundle bundle = new Bundle();
			bundle.putString("id", "1");
			bundle.putString("name", "tan");
			////////////测试代码结束
			*/
			
			Intent intent = new Intent(QRCodeAssistance.this, ShowInfoActivity.class);
			//intent.putExtras(bundleResult);
			startActivity(intent);
//		}
	}
}