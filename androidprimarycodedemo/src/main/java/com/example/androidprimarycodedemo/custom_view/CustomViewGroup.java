package com.example.androidprimarycodedemo.custom_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 自定义布局
 */
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
     * @param changed    View是否有新的位置和尺寸即是否改变
     * @param left    子View左侧距离父View左侧的距离
     * @param top     子View顶部距离父View顶部的距离
     * @param right   子View右侧距离父View右侧的距离
     * @param bottom  子View底部距离父View底部的距离
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        //子View个数
        int count=getChildCount();
        //子View对象
        View child=getChildAt(0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }
}
