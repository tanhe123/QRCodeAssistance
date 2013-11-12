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
	private TextView idTextView;
	private TextView nameTextView;
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        idTextView = (TextView) this.findViewById(R.id.tv_id_result);
        nameTextView = (TextView) this.findViewById(R.id.tv_name_result);
        
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
//			Toast.makeText(getApplicationContext(), scanResult, 1).show();
			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
			// 添加post的参数
//			params.add(new BasicNameValuePair("id", scanResult));
//			params.add(new BasicNameValuePair("id", "527a239151b9ba081f19a3b2"));
//			WebService service = new WebService(getApplicationContext());
//			Map<String, String> mp = service.jsonText(WebService.SERVER_URL, params);
			
//			idTextView.setText(mp.get("id"));
//			nameTextView.setText(mp.get("name"));
			
			Intent intent = new Intent(QRCodeAssistance.this, ShowInfoActivity.class);
			startActivity(intent);
//		}
	}
}