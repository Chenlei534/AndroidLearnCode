package com.example.androidprimarycodedemo.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.orhanobut.logger.Logger;

/**
 * Create by chenlei on 2018-12-12
 * 自定义View中Path的基本使用
 */
public class CustomViewPath extends View {
    private int width;
    private int height;
    private int centerX;
    private int centerY;
    private Paint mPaint;
    private Path mPath;
    private PointF start;
    private PointF end;
    private PointF control;

    public CustomViewPath(Context context) {
        super(context);
    }

    /**
     * 构造函数
     * @param context
     * @param attrs
     */
    public CustomViewPath(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //使用软件来绘制View Layer，绘制到一个Bitmap，并顺便关闭硬件加速（划重点 关闭硬件加速）
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        initPaintAndPath();

        //初始化PointF
        start=new PointF(0,0);
        end=new PointF(0,0);
        control=new PointF(0,0);
    }

    /**
     * 绘制方法
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        initCanvas(canvas);

//        pathLineTo(canvas);
//        pathMoveTo(canvas);
//        pathSetLastPoint(canvas);
//        pathClose(canvas);
//        pathBasicShape(canvas);
//        pathAddPath(canvas);
        pathDrawBezier(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.width=MeasureSpec.getSize(widthMeasureSpec);
        this.height=MeasureSpec.getSize(heightMeasureSpec);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX=w/2;
        centerY=h/2;
        //初始化数据点和控制点的位置
        start.x = centerX-200;
        start.y = centerY;
        end.x = centerX+200;
        end.y = centerY;
        control.x = centerX;
        control.y = centerY-100;
    }

    /**
     * 画笔初始化
     */
    private void initPaintAndPath(){
        mPaint=new Paint();
        mPath=new Path();

        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10f);
    }

    /**
     * 初始化画布，使画布终点移动到屏幕中间并翻转y轴
     * @param canvas
     */
    private void initCanvas(Canvas canvas){
        canvas.translate(width/2,height/2);
        canvas.scale(1,-1);
    }

    /**
     * 使用Path.linTo()方法绘制一条之直线，
     * 如果未绘制或者设置过起点，则默认起点为坐标原点，
     * 如果绘制过或者设置过起点，则起点为上次绘制的点或者设置的点
     * @param canvas
     */
    private void pathLineTo(Canvas canvas){
        mPath.lineTo(200,200);
        mPath.lineTo(200,0);
        mPath.lineTo(0,0);

        canvas.drawPath(mPath,mPaint);
    }

    /**
     * 使用Path.moveTo()方法设置下一次操作的起点位置
     * @param canvas
     */
    private void pathMoveTo(Canvas canvas){
        mPath.lineTo(200,200);
        mPath.moveTo(300,300);
        mPath.lineTo(200,0);
        mPath.lineTo(0,0);

        canvas.drawPath(mPath,mPaint);
    }

    /**
     * 使用Path.setLastPoint()方法设置上一次操作的最后一个点的位置
     * @param canvas
     */
    private void pathSetLastPoint(Canvas canvas){
        mPath.lineTo(200,200);
        mPath.setLastPoint(400,200);
        mPath.lineTo(200,0);
        mPath.lineTo(0,0);

        canvas.drawPath(mPath,mPaint);
    }

    /**
     * Path.close()方法如果当前最后一个点和第一个点不重合则连接两个点
     * @param canvas
     */
    private void pathClose(Canvas canvas){
        mPath.lineTo(200,200);
        mPath.lineTo(200,0);
        mPath.close();

        canvas.drawPath(mPath,mPaint);
    }

    /**
     * 使用Path的
     *     addCircle()      添加一个圆到path
     *     addOval()        添加一个椭圆到path
     *     addRect()        添加一个矩形到path
     *     addRoundRect()   添加一个圆角矩形到path
     *     addArc()         添加一个圆弧到path
     *     addTo()          添加一个圆弧到path，如果起点和上次最后一个点不同则连接两个点
     * 上面的addCircle、addOval、addRect、addRoundRect最后一个参数都是Path.Direction
     * Path.Direction.CW  表示顺时针绘制
     * Path.Direction.CCW 表示逆时针绘制
     * @param canvas
     */
    private void pathBasicShape(Canvas canvas){
        RectF rectF=new RectF(-200,200,200,-200);
        mPath.addRect(rectF,Path.Direction.CW);

        canvas.drawPath(mPath,mPaint);

//        顺时针绘制效果
//        RectF rectF=new RectF(-200,200,200,-200);
//        mPath.addRect(rectF,Path.Direction.CW);
//        mPath.setLastPoint(-300,-300);
//
//        canvas.drawPath(mPath,mPaint);

//        逆时针绘制效果
//        RectF rectF=new RectF(-200,200,200,-200);
//        mPath.addRect(rectF,Path.Direction.CCW);
//        mPath.setLastPoint(-300,-300);
//
//        canvas.drawPath(mPath,mPaint);
    }

    /**
     * 使用Path.addPath()方法将两个path合并为一个
     * @param canvas
     */
    private void pathAddPath(Canvas canvas){
        Path src=new Path();

        mPath.addRect(-200,200,200,-200,Path.Direction.CW);
        src.addCircle(0,0,100,Path.Direction.CW);
        //将src进行位移后与mPath合并
        mPath.addPath(src,0,200);

        canvas.drawPath(mPath,mPaint);
    }
    /**
     *   public void set (Path src)  将新的path赋值到现有path
     *   public void offset (float dx, float dy)        对path进行一段平移
     *   public void offset (float dx, float dy, Path dst)  对path进行一段平移，将当前path平移后的状态存入dst中，不会影响当前path
     */

    /**
     * 使用Path.quadTo()绘制二阶贝塞尔曲线
     * 使用Path.cubicTo()绘制三阶贝塞尔曲线
     */
    private void pathDrawBezier(Canvas canvas){
        mPath=new Path();
        //绘制起始点、终止点和控制点位置
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20f);
        canvas.drawPoint(start.x,start.y,mPaint);
        canvas.drawPoint(end.x,end.y,mPaint);
        canvas.drawPoint(control.x,control.y,mPaint);
        //绘制辅助线
        mPaint.setStrokeWidth(4);
        canvas.drawLine(start.x,start.y,control.x,control.y,mPaint);
        canvas.drawLine(end.x,end.y,control.x,control.y,mPaint);
        //绘制贝塞尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);
        mPath.moveTo(start.x,start.y);
        mPath.quadTo(control.x,control.y,end.x,end.y);

        canvas.drawPath(mPath,mPaint);
    }

    /**
     * 根据触摸位置更新控制点并提示重绘
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                control.x=centerX;
                control.y=centerY-100;
                break;
            case MotionEvent.ACTION_MOVE:
                control.x=event.getX();
                control.y=event.getY();
                break;
        }
        invalidate();

        return true;
    }
}
