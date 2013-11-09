package com.sdutlinux;

import java.util.ArrayList;
import java.util.List;

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
	private TextView resultTextView;
	private EditText qrStrEditText;
	private ImageView qrImgImageView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        resultTextView = (TextView) this.findViewById(R.id.tv_scan_result);
            
        Button scanBarCodeButton = (Button) this.findViewById(R.id.btn_scan_barcode);
        scanBarCodeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "haha", 1);
				resultTextView.setText("ha");
			/*	//打开扫描界面扫描条形码或二维码
				List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
				// 添加post的参数
				//params.add(new BasicNameValuePair("id", scanResult));
				Toast.makeText(getApplicationContext(), "haha", 1);
				resultTextView.setText("haha");
				params.add(new BasicNameValuePair("id", "527a239151b9ba081f19a3b2"));
				WebService service = new WebService(getApplicationContext());
				resultTextView.setText(service.jsonText(WebService.SERVER_URL, params));

		//		Intent openCameraIntent = new Intent(QRCodeAssistance.this,CaptureActivity.class);
			//	startActivityForResult(openCameraIntent, 0);
		*/
			}
		});
    }
/*
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		//处理扫描结果（在界面上显示）
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
			resultTextView.setText(scanResult);
			Toast.makeText(getApplicationContext(), scanResult, 1).show();
			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
			// 添加post的参数
			//params.add(new BasicNameValuePair("id", scanResult));
			params.add(new BasicNameValuePair("id", "527a239151b9ba081f19a3b2"));
			WebService service = new WebService(getApplicationContext());
			resultTextView.setText(service.jsonText(WebService.SERVER_URL, params));
		}
	}*/
}