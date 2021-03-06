package com.fahamutech.posprinter;

import android.content.Context;
import android.widget.Toast;

import com.vanstone.appsdk.client.ISdkStatue;
import com.vanstone.trans.api.PrinterApi;
import com.vanstone.trans.api.SystemApi;
import com.vanstone.utils.CommonConvert;

public class JZV3Printer {

    private int pflag = 0;
    private static JZV3Printer jzv3Printer;

    private JZV3Printer() {
    }

    public static JZV3Printer getInstance() {
        if (jzv3Printer != null) {
            return jzv3Printer;
        } else {
            jzv3Printer = new JZV3Printer();
            return jzv3Printer;
        }
    }

    public void print(Context context, JZV3PrinterCallback jzv3PrinterCallback) {
        if (pflag == 0) {
            jzv3PrinterCallback.onError(new PrinterError("Printer not prepare well!", -1));
        } else {
            int ret = PrinterApi.PrnOpen_Api("", context);
            if (ret == 0) {
                jzv3PrinterCallback.onReadToPrint();
                PrinterError printerError = PrintData(context);
                if (printerError == null) {
                    jzv3PrinterCallback.onSuccess();
                } else {
                    jzv3PrinterCallback.onError(printerError);
                }
            } else {
                jzv3PrinterCallback.onError(new PrinterError("Printer not working", -2));
            }
        }
    }

    private static PrinterError PrintData(Context context) {
        int ret;
        String Buf;
//        while (true) {
        ret = PrinterApi.PrnStart_Api();
        // Log.d("aabb", "PrnStart_Api:" + ret);
        if (ret == 2) {
            Buf = "Printer paper is not enough";
            Toast.makeText(context, Buf, Toast.LENGTH_LONG).show();
            return new PrinterError(Buf, ret);
        } else if (ret == 3) {
            Buf = "Printer is too hot";
            Toast.makeText(context, Buf, Toast.LENGTH_LONG).show();
            return new PrinterError(Buf, ret);
        } else if (ret == 4) {
            Buf = "Return:" + ret + "	PLS put it back\nPress any key to reprint";
            Toast.makeText(context, Buf, Toast.LENGTH_LONG).show();
            return new PrinterError(Buf, ret);
        } else if (ret == 0) {
            return null;
        }
        //else {
        return null;
        // }
        // }
    }

    public void init(final Context context) {
        new Thread() {
            public void run() {
                String CurAppDir = context.getApplicationContext().getFilesDir().getAbsolutePath();
                SystemApi.SystemInit_Api(0, CommonConvert.StringToBytes(CurAppDir + "/" + "\0"), context, new ISdkStatue() {
                    @Override
                    public void sdkInitSuccessed() {
                        pflag = 1;
                    }

                    @Override
                    public void sdkInitFailed() {
                        pflag = 0;
                    }
                });
            }
        }.start();
    }
}
