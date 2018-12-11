package com.example.androidprimarycodedemo.custom_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class CustomViewGroup extends ViewGroup {


    public CustomViewGroup(Context context) {
        super(context);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("NewApi")
    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 确定子View的布局位置
     * 主要在自定义ViewGroup时使用，调用子View的layout函数，用于确定子View的位置
     *
     * @param b
     * @param i  子View左侧距离父View左侧的距离
     * @param i1   子View顶部距离父View顶部的距离
     * @param i2     子View右侧距离父View右侧的距离
     * @param i3    子View底部距离父View底部的距离
     */
    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        //子View个数
        int count=getChildCount();
        //子View对象
        View child=getChildAt(0);
    }
}
