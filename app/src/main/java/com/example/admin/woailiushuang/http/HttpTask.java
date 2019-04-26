package com.example.admin.woailiushuang.http;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;


//封装 请求  与 相应
//》要能启动执行请求
//todo 这个由addTask执行的，为啥还在外面封装一层》？
public class HttpTask <T> implements Runnable, Delayed {

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
        try{
            httpRequest.excute();//如果httpRequest抛出了异常，则这一行肯定也会抛出异常

        }catch (Exception e){

            ThreadPoolManager.getInstance().addDelayTask(this);
        }

    }



    private long delayTime;
    private int delayCount;

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = System.currentTimeMillis()+delayTime;
    }

    public int getDelayCount() {
        return delayCount;
    }

    public void setDelayCount(int delayCount) {
        this.delayCount = delayCount;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.delayTime-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}