package com.fahamutech.posprinter;

public interface JZV3PrinterCallback {
    void onReadToPrint();
    void onError(PrinterError printerError);
    void onSuccess();
}
