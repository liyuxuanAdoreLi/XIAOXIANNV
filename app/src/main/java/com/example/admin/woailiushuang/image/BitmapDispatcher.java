package com.example.admin.woailiushuang.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.LinkedBlockingQueue;

public class BitmapDispatcher extends Thread {


    private Handler handler = new Handler(Looper.getMainLooper());

    private LinkedBlockingQueue<BitmapRequest> BrQueue = null;
    private RequestListener listener;

    BitmapDispatcher(LinkedBlockingQueue<BitmapRequest> BrQueue){
        this.BrQueue = BrQueue;
    }

    @Override
    public void run() {
        super.run();
        BitmapRequest br = null;

        while (!isInterrupted()) {  // 特殊情况导致中断
            try {
                br = BrQueue.take();  //brqueue为0时候 阻塞
                listener = br.getListener();
                setloaddingImage(br);
                Bitmap bitmap = getBitmap(br);
                setImageView(br, bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setImageView(BitmapRequest br, final Bitmap bitmap) {
        if (bitmap !=null && br.getImageView()!=null&&br.getUrlMd5().equals(br.getImageView().getTag())){
            final BitmapRequest bitr = br;
            handler.post(new Runnable() {
                @Override
                public void run() {
                    bitr.getImageView().setImageBitmap(bitmap);
                }
            });
        }
    }

    Bitmap getBitmap(BitmapRequest br) {
        Bitmap bitmap = null;
        bitmap = downloadBitmap(br.getUrl());
        return bitmap;
    }

    Bitmap downloadBitmap(String Url) {
        InputStream is = null;
        Bitmap bitmap = null;
        try {
            URL url = new URL(Url);
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            is = connect.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            listener.onSuccess(bitmap);
        } catch (Exception e) {
            listener.onError();
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
    }
        public void setloaddingImage (BitmapRequest br){
            final int res = br.getRes();
            final ImageView imageView = br.getImageView();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageResource(res);
                }
            });

        }
    }
