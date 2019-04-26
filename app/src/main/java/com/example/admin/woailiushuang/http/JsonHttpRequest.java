package com.example.admin.woailiushuang.http;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonHttpRequest implements IHttpRequest {
    String mUrl;
    CallbackListener listener;
    byte[] mByte;

    HttpURLConnection httpURLConnection;

    @Override
    public void setUrl(String url) {
        this.mUrl = url;
    }

    @Override
    public void setData(byte[] data) {
        mByte = data;
    }

    @Override
    public void setListener(CallbackListener listener) {
        this.listener = listener;
    }

    @Override
    public void excute() {
            //执行具体的网络操作
        URL url = null;
        try {
            url = new URL(this.mUrl);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setConnectTimeout(6000);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setInstanceFollowRedirects(true);//是否可以被重定向
            httpURLConnection.setReadTimeout(3000);
            httpURLConnection.setDoInput(true);//是否可以写入数据
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");//
            httpURLConnection.setRequestProperty("Content-Type","application/json;charset=UTF-8");
            httpURLConnection.connect();//链接，从上述至此的配置必须要在connect之前完成，实际上他是建立了一个与服务器的tcp链接

            //  是用字节流发送数据
            OutputStream out = httpURLConnection.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(out);
            bos.write(mByte);
            bos.flush();;
            out.close();
            bos.close();
            // 字符流写入数据
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream im  = httpURLConnection.getInputStream();

                listener.onSuccess(im);
            }else {
        // trow runTimeException
            }




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            httpURLConnection.disconnect();
        }

    }
}
