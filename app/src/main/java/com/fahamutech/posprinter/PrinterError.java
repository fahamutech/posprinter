package com.fahamutech.posprinter;


public class PrinterError {
    private String message;
    private int code;

    PrinterError(String message, int code) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "code: " + getCode() + " , message: " + getMessage();
    }
}
