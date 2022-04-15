package com.demo.neteasemusic.views;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class AutoViewPager extends ViewPager {

    private final Handler mHandler;

    private long delayTime = 5000;

    private OnPagerItemClickListener mItemClickListener = null;

    public AutoViewPager(@NonNull Context context) {
        this(context, null);
    }

    private boolean isClick = false;
    private float downX;
    private float downY;
    private long downTime;

    public AutoViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mHandler = new Handler(Looper.getMainLooper());
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                switch (action){
                    case MotionEvent.ACTION_DOWN:
                        downX = motionEvent.getX();
                        downY = motionEvent.getY();
                        downTime = System.currentTimeMillis();
                        stopLooper();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        float dx = Math.abs(motionEvent.getX() - downX);
                        float dy = Math.abs(motionEvent.getY() - downY);
                        long dTime = System.currentTimeMillis() - downTime;
                        isClick = (dx <= 5 && dy <= 5 && dTime <= 1000);
                        if(isClick && mItemClickListener != null){
                            mItemClickListener.onItemClick(getCurrentItem());
                        }
                        startLooper();
                        break;
                }
                return false;
            }
        });
    }


    public void setPagerItemClickListener(OnPagerItemClickListener onPagerItemClickListener){
        this.mItemClickListener = onPagerItemClickListener;
    }

    public interface OnPagerItemClickListener{
        void onItemClick(int position);
    }


    public void setDelayTime(long delayTime){
        this.delayTime = delayTime;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startLooper();
    }

    private void startLooper() {
        mHandler.removeCallbacks(mTask);
        mHandler.postDelayed(mTask,delayTime);
    }

    private Runnable mTask = new Runnable() {
        @Override
        public void run() {
            int currentItem = getCurrentItem();
            currentItem++;
            setCurrentItem(currentItem);
            mHandler.postDelayed(this,delayTime);
        }
    };

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopLooper();
    }

    private void stopLooper() {
        mHandler.removeCallbacks(mTask);
    }
}
