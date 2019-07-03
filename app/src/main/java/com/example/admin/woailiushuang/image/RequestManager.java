package com.example.admin.woailiushuang.image;

import java.util.concurrent.LinkedBlockingQueue;

public class RequestManager {

    private volatile static RequestManager requestManager;

    private LinkedBlockingQueue<BitmapRequest> requestQueue = new LinkedBlockingQueue<>();

    private BitmapDispatcher[] bitmapDispatchers = null;

    private RequestManager() {
        start();
    }

    private void start() {
        stop();
        startAllDispatcher();
    }

    private void stop() {
        if (bitmapDispatchers != null && bitmapDispatchers.length > 0) {
            for (BitmapDispatcher bitmapDispatcher : bitmapDispatchers) {
                if (!bitmapDispatcher.isInterrupted()) {
                    bitmapDispatcher.interrupt();
                }
            }
        }

    }

    private void startAllDispatcher() {
        //获取手机支持的最大线程数
        int threadCount = Runtime.getRuntime().availableProcessors();
        // 另bitmapDIspatcher的数量等于最大线程数
        bitmapDispatchers = new BitmapDispatcher[threadCount];
        for (int i = 0; i < threadCount; i++) {
            BitmapDispatcher bitmapDispatcher = new BitmapDispatcher(requestQueue);
            bitmapDispatchers[0] = bitmapDispatcher;
            bitmapDispatcher.start();
        }
    }

    public static RequestManager getInstance() {
        if (null == requestManager) {
            synchronized (RequestManager.class) {
                if (null == requestManager) {
                    requestManager = new RequestManager();
                }
            }
        }
        return requestManager;
    }

    public void addBitmapRequest(BitmapRequest bitmapRequest) {
        if (bitmapRequest == null) return;

        if (!requestQueue.contains(bitmapRequest)) {
            requestQueue.add(bitmapRequest);
        }
    }
}
