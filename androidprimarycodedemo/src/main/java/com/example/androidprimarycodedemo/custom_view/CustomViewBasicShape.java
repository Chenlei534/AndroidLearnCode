package com.example.androidprimarycodedemo.custom_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.andriodprimarycodedemo.R;
import com.orhanobut.logger.Logger;



public class CustomViewBasicShape extends View {
    //创建画笔
    private Paint mPaint;

    public CustomViewBasicShape(Context context) {
        super(context);
    }

    public CustomViewBasicShape(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CustomViewBasicShape(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawPoint(canvas);
        drawLine(canvas);
        drawRect(canvas);
        drawRoundRect(canvas);
        drawOval(canvas);
        drawCircle(canvas);
        drawArc(canvas);
    }

    /**
     * 初始化画笔
     */
    private void initPaint(){
        mPaint=new Paint();
        mPaint.setColor(Color.BLUE);    //画笔颜色
        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mPaint.setStrokeWidth(10f);     //设置画笔宽度为10px
//        mPaint.setStyle(Paint.Style.STROKE);    //描边
//        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);   //填充并描边
    }

    /**
     * 使用Canvas和Paint绘制点
     */
    private void drawPoint(Canvas canvas){
        //绘制一个点
        canvas.drawPoint(100,100,mPaint);
        //绘制一组点
        float[] pointArray=new float[]{
            110,110,
            120,120,
            160,130
        };
        canvas.drawPoints(pointArray,mPaint);
    }

    /**
     * 使用Canvas和Paint绘制直线
     */
    private void drawLine(Canvas canvas){
        //绘制一条直线    (起点x,起点y,重点x,终点y,画笔)
        canvas.drawLine(140,140,970,140,mPaint);
        //绘制一组点
        float[] lineArray=new float[]{
            150,200,550,200,
            150,250,550,250,
            150,300,550,250
        };
        canvas.drawLines(lineArray,mPaint);
    }

    /**
     * 使用Canvas和Paint绘制矩形
     */
    private void drawRect(Canvas canvas){
//        //绘制矩形方法一
//        canvas.drawRect(200,300,600,500,mPaint);
//        //绘制矩形方法二
//        Rect rect=new Rect(200,300,600,500);
//        canvas.drawRect(rect,mPaint);
        //绘制矩形方法三
        mPaint.setStyle(Paint.Style.STROKE);
        RectF rectF=new RectF(200,300,600,500);
        canvas.drawRect(rectF,mPaint);
    }

    /**
     * 使用Canvas和Paint绘制圆角矩形
     */
    @SuppressLint("NewApi")
    private void drawRoundRect(Canvas canvas){
//        //绘制圆角矩形方法一  (startX,startY,stopX,stopY,椭圆半径x,椭圆半径y,画笔)
//        canvas.drawRoundRect(100,550,700,800,50,50,mPaint);
        //绘制圆角矩形方法二
        RectF rectF=new RectF(100,550,600,800);
        canvas.drawRoundRect(rectF,100,150,mPaint);
    }

    /**
     * 使用Canvas和Paint绘制椭圆
     */
    @SuppressLint("NewApi")
    private void drawOval(Canvas canvas){
//        //绘制椭圆方法一
//        canvas.drawOval(150,850,650,1150,mPaint);
        //绘制椭圆方法二
        RectF rectF=new RectF(150,850,650,1150);
        canvas.drawOval(rectF,mPaint);
    }

    /**
     * 使用Canvas和Paint绘制圆
     */
    private void drawCircle(Canvas canvas){
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);
        //绘制一个圆
        canvas.drawCircle(200,1400,150,mPaint);
    }

    /**
     * 使用Canvas和Paint绘制直线
     */
    @SuppressLint("NewApi")
    private void drawArc(Canvas canvas){
        //绘制圆弧方法一
//        canvas.drawArc(400,1200,700,1400,0,90,false,mPaint);
        //绘制圆弧方法二
        RectF rectF=new RectF(400,1200,700,1500);
        //(矩形,开始角度,扫过角度,是否使用圆心,画笔);
        canvas.drawArc(rectF,300,60,true,mPaint);
    }
}
