package com.example.admin.woailiushuang.DiyView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.customview.widget.ViewDragHelper;

/**
 * @desc
 * @auth liyuxuan
 * @time 2019/4/9 14:20
 */
public class SwipCards extends ViewGroup {

    int mCenterX;
    int mCenterY;
    private static final int MAX_DEGREE = 60;
    private static final float MAX_ALPHA_RANGE = 0.5f;

    private int mCardGap = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
    ViewDragHelper mViewDragHelper;
    ViewDragHelper.Callback mCallback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            return true;
        }

        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
            return left;//指的是，left改变后就直接用这个left作为左面边距,实时更新
        }

        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            return top;
        }

        @Override
        public void onViewPositionChanged(@NonNull View changedView, int left, int top, int dx, int dy) {
            //计算位置变化后，与原来位置的中心点变化量
            int diffX = left + changedView.getWidth()/2 -mCenterX;
            float ratio = diffX * 0.1f / getWidth();
            //角度
            float degree = MAX_DEGREE * ratio;
            changedView.setRotation(degree);
            //透明度
            float alpha = 1- Math.abs(ratio)*MAX_ALPHA_RANGE;
            changedView.setAlpha(alpha);
        }

        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            final int left = releasedChild.getLeft();
            final int right = releasedChild.getRight();
            final int top = releasedChild.getTop();
            final int bottom = releasedChild.getBottom();
            if (left > mCenterX) {
                animateToRight(releasedChild);
            } else if (right < mCenterX) {
                animateToLeft(releasedChild);
            } else {
                animateToCenter(releasedChild);
            }
//            animateTo(releasedChild,left,right,top,bottom);
        }
    };


    public void animateTo(View releasedChild,int left,int right,int top,int bottom){
    }

    public SwipCards(Context context) {
        super(context);
    }

    public SwipCards(Context context, AttributeSet attrs) {
        super(context, attrs);
        mViewDragHelper = ViewDragHelper.create(this,mCallback);
    }

    public void animateToRight(View releasedChild){
        int finalLeft = getWidth() + releasedChild.getHeight();
        int finalTop = releasedChild.getTop();
        mViewDragHelper.smoothSlideViewTo(releasedChild, finalLeft, finalTop);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mCenterX = w / 2;
        mCenterY = h / 2;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
                int left = mCenterX - child.getMeasuredWidth()/2;
                int top = mCenterY - child.getMeasuredHeight()/2+mCardGap*(getChildCount()-i);
                int right = left + child.getMeasuredWidth();
                int bottom = top + child.getMeasuredHeight();
                child.layout(left,top,right,bottom);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        mViewDragHelper.processTouchEvent(event);
        return true;
    }
    private void animateToCenter(View releasedChild) {
        int finalLeft = mCenterX - releasedChild.getWidth() / 2;
        int indexOfChild = indexOfChild(releasedChild);
        int finalTop = mCenterY - releasedChild.getHeight() / 2 + mCardGap * (getChildCount() - indexOfChild);
        mViewDragHelper.smoothSlideViewTo(releasedChild, finalLeft, finalTop);
        invalidate();
    }

    private void animateToLeft(View releasedChild) {
        int finalLeft = -getWidth();
        int finalTop = 0;
        mViewDragHelper.smoothSlideViewTo(releasedChild, finalLeft, finalTop);
        invalidate();
    }
    @Override
    public void computeScroll() {
        //continueSettling 内部调用scroller的computeAxeooffset 计算滚动偏移量
//      false  动画结束时候返回
        if (mViewDragHelper.continueSettling(false)) {
            invalidate();
        }
    }
}
