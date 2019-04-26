package com.example.admin.woailiushuang.http;

import android.text.format.Time;
import android.util.TimeUtils;

import java.util.concurrent.ArrayBlockingQueue;
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

    }

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
