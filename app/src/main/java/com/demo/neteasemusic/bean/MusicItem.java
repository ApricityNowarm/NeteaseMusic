package com.demo.neteasemusic.bean;

public class MusicItem {
    private Integer res;
    private String musicName;
    private String desc;

    public MusicItem() {
    }

    public MusicItem(Integer res, String musicName, String desc) {
        this.res = res;
        this.musicName = musicName;
        this.desc = desc;
    }

    public Integer getRes() {
        return res;
    }

    public void setRes(Integer res) {
        this.res = res;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
