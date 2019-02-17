package com.example.androidprimarycodedemo.custom_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.andriodprimarycodedemo.R;

public class CustomView extends View {
    //宽度和高度的数值
    private int widthSize;
    private int heightSize;
    //宽度和高度的测量模式
    private int widthMode;
    private int heightMode;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 自定义View的构造函数
     * @param context 当前上下文环境
     * @param attrs 当前View设定的属性值
     * @param defStyleAttr  当前所使用的主题
     */
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义属性值
        TypedArray customViewAttrs=context.obtainStyledAttributes(attrs, R.styleable.CustomView);
        int mColor=customViewAttrs.getColor(R.styleable.CustomView_custom_view_color, Color.RED);
    }

    @SuppressLint("NewApi")
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 用来测量View大小
     * 测量模式：
     *      UNSPECIFIED：00 unspecified
     *          默认值，父控件没有给子View任何限制，子View可以设置为任意大小
     *      EXACTLY：01 exactly
     *          精确模式，表示父控件已经确切的指定了子View的大小，直接指定宽高属性值或者match_parent
     *      AT_MOST：10
     *          最大模式，子View大小没有具体限制，子View最大为父View大小，对应于wrap_content
     *
     * @param widthMeasureSpec  宽度大小和测量模式混合得到的值
     * @param heightMeasureSpec  高度大小和测量模式混合得到的值
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //得到宽度大小和其测量模式
        widthSize=MeasureSpec.getSize(widthMeasureSpec);
        widthMode=MeasureSpec.getMode(widthMeasureSpec);
        //得到高度大小和其测量模式
        heightSize=MeasureSpec.getSize(heightMeasureSpec);
        heightMode=MeasureSpec.getMode(heightMeasureSpec);
        //未对View宽高进行修改
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //对View宽高进行修改
        //setMeasuredDimension(widthSize,heightSize);
    }

    /**
     * 在视图大小发生改变时调用，确定View大小
     * @param w 宽度
     * @param h 高度
     * @param oldw 上一次宽度
     * @param oldh 上一次宽度
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /**
     * 确定子View的布局位置
     * 主要在自定义ViewGroup时使用，调用子View的layout函数，用于确定子View的位置
     *
     * @param changed
     * @param left  子View左侧距离父View左侧的距离
     * @param top   子View顶部距离父View顶部的距离
     * @param right     子View右侧距离父View右侧的距离
     * @param bottom    子View底部距离父View底部的距离
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    /**
     * 用于绘图的方法
     * @param canvas
     *
     * 一个完成的绘制过程会依次绘制
     * 1、背景                 drawBackground()    不能重写
     * 2、主体                 onDraw()            可重写
     * 3、子View               dispatchDraw()      可重写
     * 4、滑动边缘渐变和滑动条   onDrawForeground()  可重写
     * 5、前景                 onDrawForeground()   可重写
     * 上面这5个内容
     * 所以可以通过重写相应的方法来控制View绘制的顺序
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
