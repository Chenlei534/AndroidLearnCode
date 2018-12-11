package com.example.androidprimarycodedemo.four_components.about_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.orhanobut.logger.Logger;

/**
 * Create by chenlei on 2018-11-27
 * 静态注册该广播
 */
public class StaticRegisterReceiver extends BroadcastReceiver {
    /**
     * 重写该方法，当接收到注册的相应广播后会执行该方法
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        Logger.e("接收到一个静态广播");
    }
}