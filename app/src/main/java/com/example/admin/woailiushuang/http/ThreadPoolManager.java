package com.example.admin.woailiushuang.http;

import android.text.format.Time;
import android.util.Log;
import android.util.TimeUtils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolManager {
    static ThreadPoolManager instance = new ThreadPoolManager();

    public static ThreadPoolManager getInstance() {
        return instance;
    }


    private LinkedBlockingDeque<Runnable> mQueue = new LinkedBlockingDeque<>();

    private ThreadPoolExecutor threadPoolExecutor;

    public ThreadPoolManager() {

        //创建线程池
        threadPoolExecutor = new ThreadPoolExecutor(3, 10, 15, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4), new RejectedExecutionHandler() {
            @Override//如果出错直接吧runnable交给队列吧
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                addTask(r);
            }
        });

        threadPoolExecutor.execute(corethread);
        threadPoolExecutor.execute(delayThread);
    }


    //创建一个延迟队列
    private DelayQueue<HttpTask> delayQueue = new DelayQueue<>();
    //添加延迟任务
    public void addDelayTask(HttpTask   task){
        if (task!=null){
            task.setDelayTime(3000);
            delayQueue.offer(task);
        }


    }
    //延迟runnable
    private Runnable delayThread = new Runnable() {
        HttpTask ht;
        @Override
        public void run() {
            while (true){
                try {
                    ht = delayQueue.take();
                    if (ht.getDelayCount()<3){
                        threadPoolExecutor.execute(ht);
                        Log.e("===重试机制===",ht.getDelayCount()+" "+System.currentTimeMillis());
                        ht.setDelayCount(ht.getDelayCount()+1);
                    }else {
                        Log.e("===重试机制===","执行失败！！！！！");
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };



    //核心线程 "叫号"  也可以加进线程池
    private Runnable corethread = new Runnable() {
        Runnable runnable;
        @Override
        public void run() {
            while (true){
                try {
                    runnable = mQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                threadPoolExecutor.execute(runnable);

            }
        }
    };

    public void addTask(Runnable runnable) {
        if (runnable != null) {
            try {
                mQueue.put(runnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }




}
