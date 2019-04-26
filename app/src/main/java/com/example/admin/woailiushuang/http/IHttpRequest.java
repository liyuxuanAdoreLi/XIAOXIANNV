package com.example.admin.woailiushuang.http;

public interface IHttpRequest {

    void setUrl(String url);

    void setData (byte[] data);//todo 为什么？

    void setListener(CallbackListener listener);


    void excute();//执行http 请求
}
