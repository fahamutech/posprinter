package com.vanstone.print;

import com.vanstone.appsdk.client.ISdkStatue;
import com.vanstone.trans.api.PrinterApi;
import com.vanstone.trans.api.SystemApi;
import com.vanstone.utils.CommonConvert;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	public static final String TAG = "MainActivity";
	Button print;
	int pflag = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		print = (Button) findViewById(R.id.Print);
		print.setOnClickListener(this);

		new Thread()
		{
			public void run() {
				String CurAppDir = getApplicationContext().getFilesDir().getAbsolutePath();
				SystemApi.SystemInit_Api(0, CommonConvert.StringToBytes(CurAppDir+"/" + "\0"), MainActivity.this, new ISdkStatue() {
					@Override
					public void sdkInitSuccessed() {
						pflag = 1;
					}
					@Override
					public void sdkInitFailed() {
						pflag = 0;
					}
				});
			};
		}.start();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "onStart");
	}
	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");
	}
	@Override
	protected void onPause() {
		super.onPause();
		Log.d(TAG, "onPause");
	}
	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG, "onStop");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy");
	}
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d(TAG, "onRestart");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){ 
		case R.id.Print:
			if(pflag == 1)
				PrtCardInfo();
			else
				Toast.makeText(MainActivity.this, "Not prepare well!", Toast.LENGTH_SHORT).show();
			break;	
		}
	}

	public  void PrtCardInfo(){
		    int ret = PrinterApi.PrnOpen_Api("", MainActivity.this);
		    if(ret == 0)
		    {
				PrinterApi.PrnClrBuff_Api();
				PrinterApi.PrnFontSet_Api(32, 32, 0);
				PrinterApi.PrnSetGray_Api(15);
				PrinterApi.PrnLineSpaceSet_Api((short) 5, 0);
				PrinterApi.PrnStr_Api("     POS Receipt");
				PrinterApi.PrnFontSet_Api(24, 24, 0);
				PrinterApi.PrnStr_Api("       		CARDHOLDER COPY");
				PrinterApi.PrnStr_Api("--------------------------------");
				PrinterApi.PrnStr_Api("MERCHANT NAME:");
				PrinterApi.PrnStr_Api("CARREFOUR");
				PrinterApi.PrnStr_Api("MERCHANT NO.: ");
				PrinterApi.PrnStr_Api("120401124594");
				PrinterApi.PrnStr_Api("TERMINAL NO.: ");
				PrinterApi.PrnStr_Api("TRANS TYPE.: ");
				PrinterApi.PrnFontSet_Api(32, 32, 0);
				PrinterApi.PrnStr_Api("Sale");
				PrinterApi.PrnFontSet_Api(24, 24, 0);
				PrinterApi.PrnStr_Api("PAYMENT TYPE.: ");
				PrinterApi.PrnStr_Api("CARDHOLDER SIGNATURE:\n\n\n\n");
				PrinterApi.PrnStr_Api("--------------------------------");
				PrinterApi.PrnStr_Api("I accept this trade and allow it on my account");
				PrinterApi.PrnStr_Api("----------x------------x-------");
				//PrinterApi.PrnStr_Api("\n\n\n\n\n\n\n\n");
				PrinterApi.PrnStr_Api("                    .");
				PrinterApi.PrnStr_Api("                    .");
				PrintData();
		    }
	}

	public static int PrintData()
	{
		int ret;
		String Buf = null;
		while(true)
		{
			ret = PrinterApi.PrnStart_Api();
			Log.d("aabb", "PrnStart_Api:"+ret);
			if(ret == 2)
			{
				Buf = "Return:" + ret + "	paper is not enough";
			}
			else if(ret  == 3)
			{
				Buf = "Return:" + ret + "	too hot";
			}
			else if(ret == 4 )
			{
				Buf = "Return:" + ret + "	PLS put it back\nPress any key to reprint";
			}
			else if(ret == 0 )
			{
				return 0;
			}
			return -1;
		}
	}
}

