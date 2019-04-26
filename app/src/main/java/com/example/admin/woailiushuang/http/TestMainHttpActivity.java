package com.example.admin.woailiushuang.http;

import android.os.Bundle;
import android.util.Log;

import com.example.admin.woailiushuang.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TestMainHttpActivity extends AppCompatActivity {
//    private final String url = "https://v.juhe.cn/historyWeather/citys?province_id=2&key=bb52107206585ab074f5e59a8c73875b";
    private final String url = "https://v.xxxjuhe.cn/historyWeather/citys?province_id=2&key=bb52107206585ab074f5e59a8c73875b";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendResponse();

    }


    private void sendResponse() {
        NeOkHttp.INSTANCE.sendJsonRequest(url, null, ResponseClass.class, new IJsonDataListener<ResponseClass>() {
            @Override
            public void onSuccess(ResponseClass m) {
                Log.e("==>", m.toString());
            }

            @Override
            public void onFailure() {

                Log.e("==>", "c出错");
            }
        });
    }

}
