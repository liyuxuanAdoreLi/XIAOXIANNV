package com.example.admin.woailiushuang.SwipCards;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

/**
 * @desc
 * @auth ${user}
 * @time 2019/4/10 14:52
 */
public class BarChartView extends View {
    private float[] mDataList = {65,12,11,69,35};
    private String[] mHorizontalAxis = {"1","2","3","4","5"};
    private int mMAX = 80;
    private List<Bar> mBars = new ArrayList<Bar>();
    int mRadius;
    float mBarWidth;
    int mGap;
    int mSelectedIndex=-1;
    private boolean enableGrowAnimation = true;
    int BAR_GROW_STEP;

    Paint mAxisPaint;
    Paint mBarPaint;

    Rect mTextRect;
    RectF mTemp;

    public BarChartView(Context context) {
        this(context,null);
    }

    public BarChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //
        init();
    }
    private void init() {
        mAxisPaint = new Paint();
        mAxisPaint.setAntiAlias(true);
        mAxisPaint.setTextSize(20);
        mAxisPaint.setTextAlign(Paint.Align.CENTER);

        mBarPaint = new Paint();
        mBarPaint.setColor(Color.BLUE);
        mBarPaint.setAntiAlias(true);

        mTextRect = new Rect();
        mTemp = new RectF();
        //柱状条宽度 默认8dp
        mBarWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 18, getResources().getDisplayMetrics());
        //柱状条与坐标文本之间的间隔大小，默认8dp
        mGap = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());

        BAR_GROW_STEP = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
    }


    public void setmHorizontalAxis(String[] horizontalAxis){
        mHorizontalAxis = horizontalAxis;
    }


    public void setDataList(float[] mDataList,int max) {
        this.mDataList = mDataList;
        this.mMAX = max;
    }

    private class Bar{
        int left;
        int right;
        int top;
        int bottom;

        int currentTop;

        float value;
        float transformedValue;

        public boolean isInside(float x,float y){
            return x>left&&x<right&&y<bottom&&y>top;
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBars.clear();

        //获取宽高
        int width = w - getPaddingLeft() - getPaddingRight();
        int height = h - getPaddingBottom() - getPaddingTop();
        //步长
        int step = width/mHorizontalAxis.length;
        //mRadius
        mRadius = (int)mBarWidth/2;
        //barleft,后续叠加计算
        int barleft = getPaddingLeft()+step/2-mRadius;

        mAxisPaint.getTextBounds(mHorizontalAxis[0],0,mHorizontalAxis[0].length(),mTextRect);

        //柱状图高度
        int maxBarHeight = height - mTextRect.height() - mGap;
        //计算柱状条最大像素与最大数据值的比值
        float hightRatio = maxBarHeight/mMAX;

        for (float data : mDataList){
            Bar bar = new Bar();
            bar.value = data;
            bar.transformedValue = bar.value*hightRatio;
            //计算四个位置
            bar.left = barleft;
            bar.top = (int)(getPaddingTop()+height-bar.transformedValue);
            bar.right = (int) (barleft+mBarWidth);
            bar.bottom = getPaddingTop()+maxBarHeight;

            //记录当前top
            bar.currentTop = bar.bottom;

            mBars.add(bar);

            barleft+=step;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (enableGrowAnimation){
            drawBarswidthAnimation(canvas);

        }else {
            drawBars(canvas);
        }
    }

    private void drawBars(Canvas canvas){

        for (int i=0;i<mBars.size();i++){
            Bar bar = mBars.get(i);
            String s=mHorizontalAxis[i];
            //textx 为柱状条中线位置
            float textX = bar.left+mRadius;
            float textY = getHeight() - getPaddingBottom();
            //绘制坐标文本
            canvas.drawText(s,textX,textY,mAxisPaint);
            mTemp.set(bar.left,bar.top,bar.right,bar.bottom);

            if (mSelectedIndex==i){
                mBarPaint.setColor(Color.RED);
                canvas.drawText(String.valueOf(bar.value),textX,bar.top-mGap,mAxisPaint);
            }else {
                mBarPaint.setColor(Color.BLUE);
            }

            canvas.drawRoundRect(mTemp,mRadius,mRadius,mBarPaint);

        }

    }
    private void drawBarswidthAnimation(Canvas canvas){
        int count = 0;//计数，计算已经绘制完成的bar的数量
        for (int i=0;i<mBars.size();i++){//循环五次每次绘制的高度是等高除非已经到达本bar的value
            Bar bar = mBars.get(i);
            String s=mHorizontalAxis[i];
            //textx 为柱状条中线位置
            float textX = bar.left+mRadius;
            float textY = getHeight() - getPaddingBottom();
            //绘制坐标文本
            canvas.drawText(s,textX,textY,mAxisPaint);

            bar.currentTop -= BAR_GROW_STEP;

            if (bar.currentTop<bar.top){
                bar.currentTop = bar.top;
                count++;
                if (count>=mBars.size()||bar.currentTop==getPaddingTop()){
                    enableGrowAnimation = false;
                }
            }

            mTemp.set(bar.left,bar.currentTop,bar.right,bar.bottom);
            canvas.drawRoundRect(mTemp,mRadius,mRadius,mBarPaint);
        }

        if (enableGrowAnimation){
            //每调用一次则绘制一次draw，postdelayed是在其他线程延迟调用
            postInvalidateDelayed(70);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (enableGrowAnimation){
            return false;
        }
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                //遍历所有bar
                for (int i=0;i<mBars.size();i++){
                    //如果在某个bar里面
                    if (mBars.get(i).isInside(event.getX(),event.getY())){
                        //重新绘制本bar
                        mSelectedIndex = i;
                        invalidate();
                            break;
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                if (mSelectedIndex>=0){
                    mSelectedIndex = -1;
                    invalidate();
                }
                break;
        }

        return true;
    }
}
