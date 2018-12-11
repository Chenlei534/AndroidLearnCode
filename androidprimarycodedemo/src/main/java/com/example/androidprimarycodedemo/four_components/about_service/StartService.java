package com.example.androidprimarycodedemo.four_components.about_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;


/**
 * Create by chenlei on 2018-11-26
 */
public class StartService extends Service {
    private int count=0;
    private boolean flag=true;
    /**
     * 服务绑定是调用
     * onCreate()->onBind()
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 服务初始化时调用，只调用一次 onCreate()
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.e("onCreate");
    }

    /**
     * 服务启动时调用 onCreate()->onStartCommand()
     * 如果读多次启动同一服务则该方法调用多次
     * @param intent
     * @param flags
     * @param startId
     * @return
     *  返回值有四种：
     *      START_STICKY：start_sticky
     *          返回此值时Service被系统kill后，系统将会尝试重新创建Service，
     *      并调用onStartCommand()方法，如果在此期间没有新的命令被传递到Service
     *      则参数Intent为null
     *      START_NOTE_STICKY：
     *          返回值为该值时，如果Service被系统kill则系统不会自动重启该服务
     *      START_REDELIVEER_INTENT：start_redeliveer_intent
     *          返回此值时，如果Service被系统kill杀死，系统将自动重启该服务，并重新传递Intent
     *      START_STICKY_COMPATIBILITY：start_sticky_compatibility
     *          start_sticky的兼容版本并不保证kill后一定能重启
     *      系统默认返回START_STICKY
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.e("onStartCommand");
        flag=true;
        startCount();
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 服务解绑时调用
     * onCreate()->onBind()->onUnbind()
     * @param intent
     * @return
     */
    @Override
    public boolean onUnbind(Intent intent) {
        Logger.e("onUnbind");
        return super.onUnbind(intent);
    }

    /**
     * 服务销毁时调用
     * 启动服务 onCreate()->onStartCommand()->onDestroy()
     * 绑定服务 onCreate()->onBind()->onUnbind()->onDestroy()
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        flag=false;
        Logger.e("onDestory");
    }

    /**
     * 启动一个线程来计数模拟后台任务
     */
    private void startCount(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (flag){
                        count++;
                        Logger.e(count+"");
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Logger.e("error");
                }
            }
        }).start();
    }
}
