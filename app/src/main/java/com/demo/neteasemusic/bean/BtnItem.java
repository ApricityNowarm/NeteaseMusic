package com.demo.neteasemusic.bean;

public class BtnItem {
    private Integer res;
    private String text;


    public BtnItem() {
    }

    public BtnItem(Integer res, String text) {
        this.res = res;
        this.text = text;
    }

    public Integer getRes() {
        return res;
    }

    public void setRes(Integer res) {
        this.res = res;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
