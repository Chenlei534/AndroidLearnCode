package com.example.androidprimarycodedemo.custom_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.andriodprimarycodedemo.R;
import com.orhanobut.logger.Logger;


/**
 * 画布（Canvas）常用操作代码
 * Canvas的相关操作在同一个对象的条件下都是可以叠加的
 */
public class CustomViewCanvas extends View {
    private Paint mPaint;
    private int width;
    private int height;
    //绘制动画使用
    private Context mContext;
    private Handler mHandler;
    private Bitmap mBitmap;
    private int animCurrentPage=0; //当前页码
    private int animAllPage=22;      //总页数
    private int animDuration=1000;   //动画时长

    public CustomViewCanvas(Context context) {
        super(context);
        initPaint();
    }

    public CustomViewCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        initAnimator(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width=MeasureSpec.getSize(widthMeasureSpec);
        height=MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width=w;
        height=h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvasTranslate(canvas);
//        canvasScale(canvas);
//        canvasRotate(canvas);
//        canvasSkew(canvas);
//        canvasDrawText(canvas);
        canvasDrawAnimator(canvas);
    }

    private void initPaint(){
        mPaint=new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5f);
    }

    /**
     * 画布所在位置的移动，多次移动的位置可以叠加
     * @param canvas
     */
    private void canvasTranslate(Canvas canvas){
        //(x轴移动的距离,y轴移动的距离)
        canvas.translate(200,200);
        canvas.drawCircle(0,0,100,mPaint);

        mPaint.setColor(Color.BLUE);
        //移动状态可叠加
        canvas.translate(200,200);
        canvas.drawCircle(0,0,150,mPaint);
    }

    /**
     * 画布大小的缩放以及距中心点的偏移，缩放可以进行叠加，个人理解因为是对同一个对象那个的操作所以会累积
     * 规则：
     *  以1为画布大小单位进行操作
     *      x>1 百分比扩大
     *      0<x<1 百分比缩小
     *      x=0 不显示
     *      -1<x<0 画布按照相应的百分比缩小并根据对应的坐标轴进行翻转
     *      x<-1 画布根据百分比扩大并进行翻转
     * @param canvas
     */
    private void canvasScale(Canvas canvas){
        //将坐标原地移动到画布中心
        canvas.translate(width/2,height/2);

        RectF rectF=new RectF(0,-400,400,0);
        canvas.drawRect(rectF,mPaint);
        //(x轴缩放大小,y轴缩放大小,缩放中心x轴偏移大小,缩放中心y轴偏移大小)
        canvas.scale(0.5f,-0.5f,200,0);

        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF,mPaint);
    }

    /**
     * Cancas旋转
     * @param canvas
     */
    private void canvasRotate(Canvas canvas){
        canvas.translate(width/2,height/2);

        RectF rectF=new RectF(0,-400,400,0);
        canvas.drawRect(rectF,mPaint);
        //(旋转角度0-360,旋转中心x轴改变,旋转中心y轴改变)
        canvas.rotate(150,200,0);

        mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
        canvas.drawRect(rectF,mPaint);
    }

    /**
     * 错切：一种特殊类型的线性变换
     * skew(sx,sy)
     *  float sx:将画布在x方向上倾斜相应的角度，sx倾斜角度的tan值，
     *  float sy:将画布在y轴方向上倾斜相应的角度，sy为倾斜角度的tan值.
     *  可对应公式：
     *      X = x + sx * y
     *      Y = sy * x + y
     * @param canvas
     */
    private void canvasSkew(Canvas canvas){
        // 将坐标系原点移动到画布正中心
        canvas.translate(width / 2, height / 2);

        RectF rect = new RectF(0,0,200,200);   // 矩形区域

        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
        canvas.drawRect(rect,mPaint);

        canvas.skew(1,0);                       // 水平错切 <- 45度

        mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
        canvas.drawRect(rect,mPaint);
    }
    /**
     * 最后
     * 画布由多个图层构成，默认绘制在默认图层上
     * save() //保存Canvas全部状态到栈中
     * save(int saveFlags) //根据参数保存部分状态到栈中
     *
     * saveLayer(...) //无图层alpha（不透明）通道，会将图层状态放入栈中
     * saveLayerAlpha(...) //有图层alpha（不透明）通道，会将图层状态放入栈中
     *
     * restore() //从栈顶取出一个状态恢复
     * restoreToCount() //弹出指定位置以及以上所有状态，并根据指定位置状态进行恢复。
     *
     * getSaveCount() //获取保存的次数，即状态栈中保存状态的数量，最小值为1
     *
     * 常用操作：
     *    save();      //保存状态
     *    ...          //具体操作
     *    restore();   //回滚到之前的状态
     */

    /**
     * 使用Canvas.drawText()方法绘制文字
     * @param canvas
     */
    private void canvasDrawText(Canvas canvas){
        String str="ABCDEFGH";
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(1);
        mPaint.setTextSize(100);

        canvas.drawText(str,300,600,mPaint);
    }

    /**
     * 根据图片绘制动画
     * @param canvas
     */
    private void canvasDrawAnimator(Canvas canvas){
        canvas.translate(width/2,height/2);

        mBitmap= BitmapFactory.decodeResource(mContext.getResources(), R.drawable.animator);

        int wid=mBitmap.getWidth()/(animAllPage/2);
        int hei=mBitmap.getHeight()/2;

        Rect src=null;
        if(animCurrentPage<11) {
            src = new Rect(wid * animCurrentPage, 0, wid * (animCurrentPage + 1), hei);
        }else {
            src = new Rect(wid * (animCurrentPage-11), hei, wid * (animCurrentPage-11 + 1), hei*2);
        }
        Rect dst = new Rect(-200, -200, 200, 200);

        canvas.drawBitmap(mBitmap, src, dst, null);
    }

    /**
     * 初始化动画所需的参数等
     * @param context
     */
    private void initAnimator(Context context){
        mContext=context;
        mPaint = new Paint();
        mPaint.setColor(0xffFF5317);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

        mHandler=new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                animCurrentPage=(animCurrentPage+1)%animAllPage;
                Logger.e(animCurrentPage+"");
                mHandler.sendEmptyMessageDelayed(0,animDuration/animAllPage);
                invalidate();

                return false;
            }
        });
        mHandler.sendEmptyMessageDelayed(0,animDuration/animAllPage);
    }
}
