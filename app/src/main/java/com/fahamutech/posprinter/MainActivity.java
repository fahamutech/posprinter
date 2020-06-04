//package com.fahamutech.posprinter;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.Toast;
//
//import com.vanstone.trans.api.PrinterApi;
//
//public class MainActivity extends Activity implements OnClickListener {
//    public static final String TAG = "MainActivity";
//    Button print;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        print = findViewById(R.id.Print);
//        print.setOnClickListener(this);
//
//        // initialize printer sdk
//        JZV3Printer.getInstance().init(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.Print) {
//            JZV3Printer.getInstance().print(this, new JZV3PrinterCallback() {
//                @Override
//                public void onReadToPrint() {
//                    PrtCardInfo();
//                }
//
//                @Override
//                public void onError(PrinterError printerError) {
//                    Toast.makeText(MainActivity.this, printerError.toString(), Toast.LENGTH_LONG).show();
//                }
//            });
//        }
//    }
//
//    public void PrtCardInfo() {
//        PrinterApi.PrnClrBuff_Api();
//        PrinterApi.PrnFontSet_Api(32, 32, 0);
//        PrinterApi.PrnSetGray_Api(15);
//        PrinterApi.PrnLineSpaceSet_Api((short) 5, 0);
//        PrinterApi.PrnStr_Api("     POS Receipt");
//        PrinterApi.PrnFontSet_Api(24, 24, 0);
//        PrinterApi.PrnStr_Api("       		CARDHOLDER COPY");
//        PrinterApi.PrnStr_Api("--------------------------------");
//        PrinterApi.PrnStr_Api("MERCHANT NAME:");
//        PrinterApi.PrnStr_Api("CARREFOUR");
//        PrinterApi.PrnStr_Api("MERCHANT NO.: ");
//        PrinterApi.PrnStr_Api("120401124594");
//        PrinterApi.PrnStr_Api("TERMINAL NO.: ");
//        PrinterApi.PrnStr_Api("TRANS TYPE.: ");
//        PrinterApi.PrnFontSet_Api(32, 32, 0);
//        PrinterApi.PrnStr_Api("Sale");
//        PrinterApi.PrnFontSet_Api(24, 24, 0);
//        PrinterApi.PrnStr_Api("PAYMENT TYPE.: ");
//        PrinterApi.PrnStr_Api("CARDHOLDER SIGNATURE:\n\n\n\n");
//        PrinterApi.PrnStr_Api("--------------------------------");
//        PrinterApi.PrnStr_Api("I accept this trade and allow it on my account");
//        PrinterApi.PrnStr_Api("----------x------------x-------");
//        //PrinterApi.PrnStr_Api("\n\n\n\n\n\n\n\n");
//        PrinterApi.PrnStr_Api("                    .");
//        PrinterApi.PrnStr_Api("                    .");
//    }
//
//}
//
