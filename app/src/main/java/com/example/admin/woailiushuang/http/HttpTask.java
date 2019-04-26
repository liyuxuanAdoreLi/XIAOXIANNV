package com.example.admin.woailiushuang.http;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;


//封装 请求  与 相应
//》要能启动执行请求
//todo 这个由addTask执行的，为啥还在外面封装一层》？
public class HttpTask <T> implements Runnable{

        IHttpRequest httpRequest;

    public HttpTask(String url,T requestDate,IHttpRequest httpRequest,CallbackListener listener) {
        this.httpRequest = httpRequest;
        httpRequest.setUrl(url);
        httpRequest.setListener(listener);
        String str = JSON.toJSONString(requestDate);
        try {
            httpRequest.setData(str.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        httpRequest.excute();

    }
}