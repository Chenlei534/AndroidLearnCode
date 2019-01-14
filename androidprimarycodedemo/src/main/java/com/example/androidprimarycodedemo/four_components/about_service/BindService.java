package com.example.androidprimarycodedemo.four_components.about_service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import com.example.andriodprimarycodedemo.R;
import com.example.androidprimarycodedemo.custom_view.CustomViewActivity;
import com.example.androidprimarycodedemo.four_components.about_activity.AboutActivity;
import com.orhanobut.logger.Logger;

/**
 * Create by chenlei on 2018-11-26
 */
public class BindService extends Service {
    /**
     * 服务绑定时调用
     * onCreate()->onBind()
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //返回需要进行交互的类
        return new MyService();
    }

    /**
     * 服务初始化时调用，只调用一次 onCreate()
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCreate() {
        super.onCreate();
    //    setReceptionService();
    //    setRe2();
        Logger.e("onCreate");
    }

    /**
     * 服务启动时调用 onCreate()->onStartCommand()
     * 如果读多次启动同一服务则该方法调用多次
     * @param intent
     * @param flags
     * @param startId
     * @return
     *   返回值有四种：
     *      START_STICKY：start_sticky
     *          返回此值时Service被系统kill后，系统将会尝试重新创建Service，
     *       并调用onStartCommand()方法，如果在此期间没有新的命令被传递到Service
     *       则参数Intent为null
     *      START_NOTE_STICKY：
     *          返回值为该值时，如果Service被系统kill则系统不会自动重启该服务
     *      START_REDELIVEER_INTENT：start_redeliveer_intent
     *          返回此值时，如果Service被系统kill杀死，系统将自动重启该服务，并重新传递Intent
     *      START_STICKY_COMPATIBILITY：start_sticky_compatibility
     *         start_sticky的兼容版本并不保证kill后一定能重启
     *      系统默认返回START_STICKY
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.e("onStartCommand");

    //    setReceptionService();
        return START_REDELIVER_INTENT;
    }

    /**
     * 服务解绑时调用
     * onCreate()->onBind()->onUnbind()
     * @param intent
     * @return
     */
    @Override
    public boolean onUnbind(Intent intent) {
        stopForeground(true);
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
        Logger.e("onDestory");
    }

    /**
     * 建立内部类继承Binder实现与其他组件之间的通信
     * 该类是其他组件与Seriver之间通信的桥梁
     */
    class MyService extends Binder{
        /**
         * 打印其他组件中传入的字符串
         * @param str
         */
        public void serviceFuncation(String str){
            Logger.e(str);
        }

        /**
         * 开始一个前台服务
         */
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        public void startReceptionService(){
            setReceptionService();
        }
    }

    /**
     * 设置为前台服务
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setReceptionService(){
        /**
         * Andorid 8.0弹窗需要NotificaitonChannel
         */
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // 通知渠道的id
        String id = "my_channel_01";
        // 用户可以看到的通知渠道的名字.
        CharSequence name = "这是一个渠道名称";
        // 用户可以看到的通知渠道的描述
        String description = "这是一个渠道描述";
        NotificationChannel mChannel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            mChannel = new NotificationChannel(id, name, importance);
            // 配置通知渠道的属性
            mChannel.setDescription(description);
            // 设置通知出现时的闪灯（如果 android 设备支持的话）
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            // 设置通知出现时的震动（如果 android 设备支持的话）
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            //最后在notificationmanager中创建该通知渠道
            mNotificationManager.createNotificationChannel(mChannel);
        }
        //设置需要跳转的Intent数组
        Intent[] intents = new Intent[2];
        Intent intent_main = new Intent(getApplicationContext(), AboutActivity.class);
        Intent intent_target = new Intent(getApplicationContext(), CustomViewActivity.class);
        intents[0] = intent_main;
        intents[1] = intent_target;

        PendingIntent pi=PendingIntent.getActivities(BindService.this,0, intents,PendingIntent.FLAG_UPDATE_CURRENT);
        //设置通知栏
        Notification.Builder builder = new Notification.Builder(BindService.this)
                    .setContentTitle("前台服务")
                    .setContentText("后台服务运行中")
                    .setWhen(System.currentTimeMillis())
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pi)   //设置点击跳转到某Activity
                    .setAutoCancel(false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(id);
        }

        Notification notification=builder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        mNotificationManager.notify(1, notification);

        startForeground(1,notification);
        Logger.e("前台服务");
    }
}
