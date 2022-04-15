package com.demo.neteasemusic.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.demo.neteasemusic.R;
import com.demo.neteasemusic.utils.ImageUtil;

public class BtnTvView extends LinearLayout {

    private ImageView iv;
    private TextView tv;

    public BtnTvView(Context context) {
        this(context,null);
    }

    public BtnTvView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BtnTvView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public BtnTvView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LayoutInflater.from(context).inflate(R.layout.btn_item,this,true);
        init();
    }

    private void init() {
        initView();
    }

    private void initView() {
        iv = this.findViewById(R.id.btn_item_iv);
        tv = this.findViewById(R.id.btn_item_tv);
    }

    public void setData(Resources res,int resId, String text){
        Bitmap bitmap = ImageUtil.image2CircleAddFilter(res,resId,200,0,0,0);
        iv.setImageBitmap(bitmap);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        LinearLayout.LayoutParams ivLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        iv.setLayoutParams(ivLayoutParams);
        tv.setText(text);
        LinearLayout.LayoutParams tvLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(tvLayoutParams);
    }
}
