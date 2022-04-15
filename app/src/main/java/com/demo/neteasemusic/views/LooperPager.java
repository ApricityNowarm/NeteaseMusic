package com.demo.neteasemusic.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.demo.neteasemusic.R;
import com.demo.neteasemusic.utils.SizeUtil;

public class LooperPager extends LinearLayout {

    private AutoViewPager mViewPager;
    private LinearLayout mPointContainer;
    private TextView mTitleTv;
    private BindTitleListener mTitleListener = null;
    private InnerAdapter mInnerAdapter = null;
    private OnItemClickListener mOnItemClickListener = null;

    public LooperPager(Context context) {
        this(context,null);
    }

    public LooperPager(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LooperPager(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public LooperPager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LayoutInflater.from(context).inflate(R.layout.looper_pager_layout,this,true);

        init();
    }

    private void init() {
        initView();
        initEvent();
    }

    private void initEvent() {

        mViewPager.setPagerItemClickListener(new AutoViewPager.OnPagerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if(mOnItemClickListener != null && mInnerAdapter != null) {
                    int realPosition = position % mInnerAdapter.getDataSize();
                    mOnItemClickListener.onItemClick(realPosition);
                }
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //切换回调方法
            }

            @Override
            public void onPageSelected(int position) {
                //切换停下来的回调

                if(mInnerAdapter != null){

                    //真实的position
                    int realPosition = position % mInnerAdapter.getDataSize();

                    //停下来之后设置标题
                    if(mTitleListener != null){
                        mTitleTv.setText(mTitleListener.getTitle(realPosition));
                    }

                    //切换指示器焦点
                    updateIndicator();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //切换状态改变回调
            }
        });
    }


    public interface BindTitleListener {
        String getTitle(int position);
    }


    public void setData(InnerAdapter innerAdapter, BindTitleListener titleListener){
        this.mInnerAdapter = innerAdapter;
        this.mTitleListener = titleListener;
        mViewPager.setAdapter(mInnerAdapter);
        mViewPager.setCurrentItem(Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % mInnerAdapter.getDataSize()));
        //拿到数据个数，根据个数动态创建指示器
        updateIndicator();

    }

    public abstract static class InnerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            //final 两个生命周期不一样
            final int realPosition = position % getDataSize();
            View itemView = getSubView(container,realPosition);
            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        protected abstract int getDataSize();

        protected abstract View getSubView(ViewGroup container, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mOnItemClickListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }


    private void updateIndicator() {
        if(mInnerAdapter != null && mTitleListener != null){
            int count = mInnerAdapter.getDataSize();
            mPointContainer.removeAllViews();
            for (int i = 0; i < count; i++) {
                View point = new View(getContext());

                if(mViewPager.getCurrentItem() % mInnerAdapter.getDataSize() == i){
                    //设置选中
                    point.setBackgroundResource(R.drawable.point_background_on);
                }else {
                    //设置背景
                    point.setBackgroundResource(R.drawable.point_background_off);
                }
                //设置大小
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(SizeUtil.dip2px(getContext(),16),SizeUtil.dip2px(getContext(),4));
                layoutParams.setMargins(SizeUtil.dip2px(getContext(),1),0,SizeUtil.dip2px(getContext(),1),SizeUtil.dip2px(getContext(),2));
                point.setLayoutParams(layoutParams);
                //添加到容器
                mPointContainer.addView(point);
            }
        }
    }


    private void initView() {
        mViewPager = this.findViewById(R.id.looper_vp);
        mPointContainer = this.findViewById(R.id.looper_point_container);
        mTitleTv = this.findViewById(R.id.looper_title);
    }



}
