package com.example.androidprimarycodedemo.four_components.about_activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Create by chenlei on 2018-11-26
 * 主要打印Fragment的生命周期与Activity生命周期的关系
 * 与Activity的调用顺序：
 * ------------------------------
 * Fragment onAttach()
 * Fragment onCreate()
 * Fragment onCreateView()
 * Activity onCreate()
 * Fragment onActivityCreated()
 * Activity onStart()
 * Fragment onStart()
 * Activity onResume()
 * Fragment onResume()
 * ------------------------------
 *        Fragment runing
 * ------------------------------
 * Fragment onPause()
 * Activity onPause()
 * Fragment onStop()
 * Activity onStop()
 * Fragment onDestoryView()
 * Fragment onDestory()
 * Framgnet onDetach()
 * Activity onDestory()
 * ------------------------------
 */
public class AboutFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //初始化日志打印工具
        Logger.e("onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.e("onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.e("onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.e("onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.e("onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.e("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.e("onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.e("onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.e("onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.e("onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Logger.e("onDetach");
    }
}
