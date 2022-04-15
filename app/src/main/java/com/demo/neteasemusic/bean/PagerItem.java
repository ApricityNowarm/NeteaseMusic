package com.demo.neteasemusic.bean;

public class PagerItem {

    private String title;

    private Integer picResId;

    public PagerItem() {
    }

    public PagerItem(String title, Integer picResId) {
        this.title = title;
        this.picResId = picResId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPicResId() {
        return picResId;
    }

    public void setPicResId(Integer picResId) {
        this.picResId = picResId;
    }
}
