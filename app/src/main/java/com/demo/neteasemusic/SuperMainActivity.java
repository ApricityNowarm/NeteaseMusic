package com.demo.neteasemusic;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.demo.neteasemusic.bean.BtnItem;
import com.demo.neteasemusic.bean.MusicItem;
import com.demo.neteasemusic.bean.PagerItem;
import com.demo.neteasemusic.utils.ImageUtil;
import com.demo.neteasemusic.utils.SizeUtil;
import com.demo.neteasemusic.views.BtnTvView;
import com.demo.neteasemusic.views.LooperPager;

import java.util.ArrayList;
import java.util.List;

public class SuperMainActivity extends AppCompatActivity {

    private LooperPager mLooperPager;

    private List<PagerItem> mData = new ArrayList<>();
    private List<BtnItem> btnData = new ArrayList<>();
    private List<MusicItem> musicItems = new ArrayList<>();

    private LinearLayout mBtnTvWrap;
    private RecyclerView musicList;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_main);
        initData();
        initView();
        initEvent();
    }

    private void initEvent() {
        if(mLooperPager != null){
            mLooperPager.setOnItemClickListener(new LooperPager.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(SuperMainActivity.this,"点击了第" + (position + 1) + "图片",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private LooperPager.InnerAdapter mInnerAdapter = new LooperPager.InnerAdapter() {

        @Override
        protected int getDataSize() {
            return mData.size();
        }

        @Override
        protected View getSubView(ViewGroup container, int position) {
            ImageView iv = new ImageView(container.getContext());
            //圆角图片
            Bitmap res = ImageUtil.image2RoundBitMap(getResources(),mData.get(position).getPicResId(), SizeUtil.dip2px(container.getContext(),15));
            iv.setImageBitmap(res);
//            iv.setImageResource(mData.get(position).getPicResId());
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            ViewPager.LayoutParams layoutParams = new ViewPager.LayoutParams();
            iv.setLayoutParams(layoutParams);
            return iv;
        }

    };

    private void initData() {
        //pager数据
        mData.add(new PagerItem("新歌首发",R.drawable.lunbo1));
        mData.add(new PagerItem("广告",R.drawable.lunbo2));
        mData.add(new PagerItem("新歌首发",R.drawable.lunbo3));
        mData.add(new PagerItem("专辑",R.drawable.lunbo4));
        mData.add(new PagerItem("专辑",R.drawable.lunbo5));
        mData.add(new PagerItem("新歌首发",R.drawable.lunbo6));
        mData.add(new PagerItem("活动",R.drawable.lunbo7));
        mData.add(new PagerItem("广告",R.drawable.lunbo8));
        mData.add(new PagerItem("广告",R.drawable.lunbo9));

        //btn数据
        btnData.add(new BtnItem(R.drawable.btn_day,"每日推荐"));
        btnData.add(new BtnItem(R.drawable.btn_fm,"私人FM"));
        btnData.add(new BtnItem(R.drawable.btn_music_list,"歌单"));
        btnData.add(new BtnItem(R.drawable.btn_list,"排行榜"));
        btnData.add(new BtnItem(R.drawable.btn_live,"直播"));
        btnData.add(new BtnItem(R.drawable.btn_album,"数字专辑"));
        btnData.add(new BtnItem(R.drawable.btn_miss,"专注冥想"));
        btnData.add(new BtnItem(R.drawable.btn_ktv,"歌房"));
        btnData.add(new BtnItem(R.drawable.btn_game,"游戏专区"));

        //music数据
        musicItems.add(new MusicItem(R.drawable.m_1975,"desc","desc"));
        musicItems.add(new MusicItem(R.drawable.m_ipasoy,"desc","desc"));
        musicItems.add(new MusicItem(R.drawable.m_lwayhome,"desc","desc"));
        musicItems.add(new MusicItem(R.drawable.m_mof,"desc","desc"));
        musicItems.add(new MusicItem(R.drawable.m_tih,"desc","desc"));
        musicItems.add(new MusicItem(R.drawable.m_tss,"desc","desc"));
        musicItems.add(new MusicItem(R.drawable.m_wrwoe,"desc","desc"));



    }


    private void initView() {
        mLooperPager = this.findViewById(R.id.view_pager);

        mLooperPager.setData(mInnerAdapter, new LooperPager.BindTitleListener() {
            @Override
            public String getTitle(int position) {
                return mData.get(position).getTitle();
            }
        });

        mBtnTvWrap = this.findViewById(R.id.wrap_btn_view);

        for (int i = 0; i < btnData.size(); i++) {
            BtnTvView btnTvView = new BtnTvView(getBaseContext());
            BtnItem btnItem = btnData.get(i);
            btnTvView.setData(getResources(),btnItem.getRes(),btnItem.getText());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(SizeUtil.dip2px(getBaseContext(),70), ViewGroup.LayoutParams.MATCH_PARENT);
            btnTvView.setLayoutParams(layoutParams);
            mBtnTvWrap.addView(btnTvView);
        }

        musicList = this.findViewById(R.id.music_list);
        //线性布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false);
        musicList.setLayoutManager(linearLayoutManager);
        musicList.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new MyViewHolder(LayoutInflater.from(getBaseContext()).inflate(R.layout.music_item,parent,false));
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                MusicItem itemData = musicItems.get(position);
                ((MyViewHolder) holder).setData(itemData.getRes(),itemData.getMusicName(),itemData.getDesc());
            }

            @Override
            public int getItemCount() {
                return musicItems.size();
            }


            class MyViewHolder extends RecyclerView.ViewHolder {

                private ImageView iv;
                private TextView name;
                private TextView desc;

                public MyViewHolder(@NonNull View itemView) {
                    super(itemView);

                    iv = itemView.findViewById(R.id.music_iv);
                    name = itemView.findViewById(R.id.music_name_tv);
                    desc = itemView.findViewById(R.id.music_desc_tv);
                }

                public void setData(int res,String name,String desc){
                    this.iv.setImageResource(res);
                    this.name.setText(name);
                    this.desc.setText(desc);
                }
            }
        });


    }
}
