package com.example.admin.woailiushuang.http;

public class ResponseClass {
    private int resultCode;
    private  String reason;
    private int error_code;

    @Override
    public String toString() {
        return "ResponseClass{" +
                "resultCode=" + resultCode +
                ", reason='" + reason + '\'' +
                ", error_code=" + error_code +
                '}';
    }

    public int getResultCode(){
        return  resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
}
