package com.cn.hnust.pojo;

import java.util.Date;

public class Comment {
    private Integer id;

    private Date evaluatetime;

    private Integer evaluategrade;

    private String evaluatecontent;

    private String evaluatepictureurl;

    private Integer shopid;

    private Integer userid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getEvaluatetime() {
        return evaluatetime;
    }

    public void setEvaluatetime(Date evaluatetime) {
        this.evaluatetime = evaluatetime;
    }

    public Integer getEvaluategrade() {
        return evaluategrade;
    }

    public void setEvaluategrade(Integer evaluategrade) {
        this.evaluategrade = evaluategrade;
    }

    public String getEvaluatecontent() {
        return evaluatecontent;
    }

    public void setEvaluatecontent(String evaluatecontent) {
        this.evaluatecontent = evaluatecontent == null ? null : evaluatecontent.trim();
    }

    public String getEvaluatepictureurl() {
        return evaluatepictureurl;
    }

    public void setEvaluatepictureurl(String evaluatepictureurl) {
        this.evaluatepictureurl = evaluatepictureurl == null ? null : evaluatepictureurl.trim();
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}