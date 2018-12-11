package com.example.androidprimarycodedemo.four_components.about_activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.example.andriodprimarycodedemo.R;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Create by chenlei on 2018-11-26
 * @author chenlei
 * 主要重写Activity中相关方方法，以及对每个方法的注释
 */
public class AboutActivity extends AppCompatActivity {
    /**
     * activity启动创建时首先调用的方法
     * @param savedInstanceState 如果activity因为异常状态重建则saveInstanceState内保存销毁前状态否则为空
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        //初始化日志打印工具
        Logger.e("onCreate");
    }

    /**
     * activity在启动创建时调用 onCreate()->onStart()
     * 或者在activity从后台不可见变为可见、解锁屏时调用 onRestart()->onStart()
     */
    @Override
    protected void onStart() {
        super.onStart();
        Logger.e("onStart");
    }

    /**
     * activity在启动创建时调用 oneCreate()->onStart()->onResume()
     * 或者在activity从前台被遮挡但是可见时变为不被遮挡时调用 onResume()
     * 该方法调用结束后activity进入运行状态
     */
    @Override
    protected void onResume() {
        super.onResume();
        Logger.e("onResume");
    }

    /**
     * activity在运行时被弹窗遮挡但仍在前台时调用 onPause()
     * 或者运行时打开新的activity后该activity转入后台时调用 onPause()
     * 或者运行时activity被销毁时调用 onPause()
     * 在启动新的activity时调用顺序：ActivityA:onPause()->ActivityB:onCreate()
     */
    @Override
    protected void onPause() {
        super.onPause();
        Logger.e("onPause");
    }

    /**
     * activity运行时打开新的activity后该activity转入后台或者锁屏时调用 onPause()->onStop()
     * 或者运行时activity被销毁时调用 onPause()->onStop()
     */
    @Override
    protected void onStop() {
        super.onStop();
        Logger.e("onStop");
    }

    /**
     * activity运行时被销毁时调用 onPause()->onStop()->onDestroy()
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.e("onDestroy");
    }

    /**
     * activity由后台不可见状态变为可见状态时调用 onStop()->onRestart()->onStart()
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        Logger.e("onRestart");
    }

    /**
     * 该方法在activity在异常状态下如系统配置发生改变（横竖屏切换等）
     * 或者资源内存不足（优先级低的先被杀死，优先级：运行>前台>后台）被杀死时
     * 调用 onSaveInstanceState()->onStop()->onDestroy()
     * 用来保存activity状态，将activity的相关状态数据保存在Bundle中
     * @param outState 保存销毁前activity的相关状态
     * @param outPersistentState
     */
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Logger.e("onSaveInstanceState");
    }

    /**
     * 当activity在异常状态被杀死重建后调用 onCreate()->onStart()->onRestoreInstanceState()
     * 状态相关数据保存在Bundle中
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Logger.e("onRestoreInstanceState");
    }

    /**
     * 当activity的启动模式设置为singleTop、singleTask时当activity被多次启动时
     * 会回调该方法 onNewIntent()
     * Activity的四种启动模式：standard、singleTop、singleTask、singleInstance
     * 需要在AndroidManifest.xml文件activity节点中配置
     * android:launchMode="standard"
     *  默认启动模式，每次启动都会在栈顶新建activity
     * android:launchMode="singleTop"
     *  如果activity在栈顶，启动时直接复用，否则新建
     * android:launchMode="singleTask"
     *  1.如果taskAffinity值与当前栈名相同则在当前栈中新建Activity，
     *  如果不同则根据taskAffinity的值新建一个任务栈并在该栈中新建Activity
     *  2.如果activity在栈中，启动时则上面activity全部出栈，否则新建
     * android:launchMode="singleInstance"
     *  activity单独的位于一个任务栈中
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Logger.e("onNewIntent");
    }

    /**
     * 如果在AndroidManifest.xml文件中配置了 android:configChanges="..."
     * 当系统配置发生改变时不会销毁activity然后重建但是会回调该方法
     * 当横竖品切换时需要在AndroidManifest.xml文件中相应节点配置
     * android:configChanges="orientation"该方法
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Logger.e("onConfigurationChanged");
    }

    /**
     * 该方法在创建menu时回调
     * 调用顺序 onCreate()->onStart()->onResume()->onCreateOptionMenu()
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Logger.e("onCreateOptionMenu");
        return super.onCreateOptionsMenu(menu);
    }
}
