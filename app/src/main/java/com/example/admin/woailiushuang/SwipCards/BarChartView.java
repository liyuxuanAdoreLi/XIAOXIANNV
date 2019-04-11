package com.example.admin.woailiushuang.SwipCards;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
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
        mBarWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        //柱状条与坐标文本之间的间隔大小，默认8dp
        mGap = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
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
        mRadius = (int)mRadius/2;
        //barleft,后续叠加计算
        int barleft = getPaddingLeft()+step/2-mRadius;

        mAxisPaint.getTextBounds(mHorizontalAxis[0],0,mHorizontalAxis[0].length(),mTextRect);

        Rect t = mTextRect;
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
            bar.bottom = getPaddingTop()+maxBarHeight;

            //记录当前top
            bar.currentTop = bar.bottom;

            mBars.add(bar);

            barleft+=step;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        drawBars(canvas);

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
            canvas.drawRoundRect(mTemp,mRadius,mRadius,mBarPaint);
        }

    }
}
