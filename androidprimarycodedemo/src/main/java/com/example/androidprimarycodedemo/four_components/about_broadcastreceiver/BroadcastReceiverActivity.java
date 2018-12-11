package com.example.androidprimarycodedemo.four_components.about_broadcastreceiver;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.andriodprimarycodedemo.R;

/**
 * Create by chenlei on 2018-11-27
 * 广播测试类
 * 广播的使用步骤：
 *  1、新建类继承BroadcastReceiver并重写onReceive()方法
 *  2、在其他类中通过代码动态注册或者在AndroidManifest.xml中静态注册
 *  3、可以发送广播，或者接收系统广播
 */
public class BroadcastReceiverActivity extends AppCompatActivity {
    private Button dynamicRegisterButton;
    private Button dynamicUnregisterButton;
    private Button sendDynamicBroadcastButton;
    private Button sendStaticBroadcastButton;
    private Button registerSendLocalBroadcastButton;
    private Button unregisterLocalBrocadcastButton;

    private static final String RECEIVER_ACTION="MY_BROADCAST";
    private DynamicRegisterReceiver mDynamicRegisterReceiver;
    private LocalBroadcastManager mLocalBroadcastManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);

        dynamicRegisterButton=findViewById(R.id.dynamic_register_button);
        dynamicUnregisterButton=findViewById(R.id.dynamic_unregister_button);
        sendDynamicBroadcastButton=findViewById(R.id.send_dynamic_broadcast_button);
        sendStaticBroadcastButton=findViewById(R.id.send_static_broadcast_button);
        registerSendLocalBroadcastButton=findViewById(R.id.register_send_local_broadcast_button);
        unregisterLocalBrocadcastButton=findViewById(R.id.unregister_local_broadcast_button);
        //事件监听
        dynamicRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //动态注册广播
                mDynamicRegisterReceiver=new DynamicRegisterReceiver(); //实例化广播接收者和IntentFilter
                IntentFilter intentFilter=new IntentFilter();
                intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
                intentFilter.addAction(RECEIVER_ACTION);    //设置接收广播类型
                intentFilter.addAction(Intent.ACTION_SCREEN_OFF);   //可以接收多个广播类型
                registerReceiver(mDynamicRegisterReceiver,intentFilter);    //注册广播
            }
        });
        dynamicUnregisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //动态注册广播使用结束后一定要注销
                unregisterReceiver(mDynamicRegisterReceiver);
            }
        });
        sendDynamicBroadcastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //发送动态广播
                Intent intent=new Intent();
                intent.setAction(RECEIVER_ACTION);
                sendBroadcast(intent);
            }
        });
        sendStaticBroadcastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //发送静态广播
                /**
                 * Android8.0以上禁止使用大部分的静态广播
                 */
                Intent intent=new Intent();
                intent.setAction("MY_STATIC_BROADCAST");
                intent.setComponent(new ComponentName("com.example.andriodprimarycodedemo.four_components.about_broadcastreceiver","StaticRegisterReceiver"));
                sendBroadcast(intent);
            }
        });
        registerSendLocalBroadcastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //注册本地广播
                mDynamicRegisterReceiver=new DynamicRegisterReceiver();
                mLocalBroadcastManager=LocalBroadcastManager.getInstance(BroadcastReceiverActivity.this);
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(RECEIVER_ACTION);
                mLocalBroadcastManager.registerReceiver(mDynamicRegisterReceiver,intentFilter);
                //发送本地广播
                Intent intent=new Intent();
                intent.setAction(RECEIVER_ACTION);
                mLocalBroadcastManager.sendBroadcast(intent);
            }
        });
        unregisterLocalBrocadcastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //解除本地广播
                mLocalBroadcastManager.unregisterReceiver(mDynamicRegisterReceiver);
            }
        });

    }

}
