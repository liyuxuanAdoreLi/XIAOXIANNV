package com.example.admin.woailiushuang.http;

import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonCallbackListener<T> implements CallbackListener {

    private Class<T> responseClass;
    private IJsonDataListener mJsonDataLIstener;
    private Handler handler = new Handler(Looper.getMainLooper());

        //我传一个类型，可以让流解析成这个类型的对象，传的时候用handler post出去  用jsonlistener接口接这个类型 并在mainactivity中实现接口获取类型并处理
    public JsonCallbackListener(Class<T> responseClass,IJsonDataListener iJsonDataListener) {

        this.responseClass = responseClass;
        this.mJsonDataLIstener = iJsonDataListener;
    }

    @Override
    public void onSuccess(InputStream inputStream) {
        //inputStream 转换成 对象
        String response = getContent(inputStream);


        final T clazz = JSON.parseObject(response,responseClass);

        handler.post(new Runnable() {
            @Override
            public void run() {
                mJsonDataLIstener.onSuccess(clazz);



            }
        });

    }

    @Override
    public void onFailure() {

    }

    private String getContent(InputStream inputStream){


        String content = null;
        try {


            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {


                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }

            } catch (IOException e) {
                System.out.println("ERROR=" + e.toString());
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println("ERROR=" + e.toString());
                }
            }

            return sb.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  content;
    }
}
