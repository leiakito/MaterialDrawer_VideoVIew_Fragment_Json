package com.leia.util;

import java.io.Serializable;

/*
    Author:leia
    Write The Code Change The World    
*/public class ResultBean implements Serializable {

    /**
     * id : 1
     * imageurl : https://imgkr.cn-bj.ufileos.com/1a649b79-895d-4ca6-a2ac-24ef230cde8d.jpg
     * videoname : Love, Death & Robots Season 1 (2019)
     * videourl : http://vt1.doubanio.com/202005170011/8f71bdd0a5f5108109e1bbb44d903199/view/movie/M/402430262.mp4
     * info : 这部名为《爱，死亡和机器人》的动画短片合集由18部分组成，每部分时长5-15分钟。这些短片涵盖多种类型，包括科幻、奇幻、恐怖和喜剧；这些短片也将包含多种形式，包括传统2D和3DCGI短片
     */

    private int id;
    private String imageurl;
    private String videoname;
    private String videourl;
    private String info;

    public ResultBean(String imageurl, String videoname, String videourl, String info) {
        this.imageurl = imageurl;
        this.videoname = videoname;
        this.videourl = videourl;
        this.info = info;
    }

    public ResultBean() {
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "imageurl='" + imageurl + '\'' +
                ", videoname='" + videoname + '\'' +
                ", videourl='" + videourl + '\'' +
                ", info='" + info + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
