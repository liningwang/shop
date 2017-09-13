package com.cn.hnust.pojo;

public class SecondLevel {
    private Integer secondlevelid;

    private String secondleveltitle;

    private String secondlevelpictureurl;

    private Integer classifyid;

    public Integer getSecondlevelid() {
        return secondlevelid;
    }

    public void setSecondlevelid(Integer secondlevelid) {
        this.secondlevelid = secondlevelid;
    }

    public String getSecondleveltitle() {
        return secondleveltitle;
    }

    public void setSecondleveltitle(String secondleveltitle) {
        this.secondleveltitle = secondleveltitle == null ? null : secondleveltitle.trim();
    }

    public String getSecondlevelpictureurl() {
        return secondlevelpictureurl;
    }

    public void setSecondlevelpictureurl(String secondlevelpictureurl) {
        this.secondlevelpictureurl = secondlevelpictureurl == null ? null : secondlevelpictureurl.trim();
    }

    public Integer getClassifyid() {
        return classifyid;
    }

    public void setClassifyid(Integer classifyid) {
        this.classifyid = classifyid;
    }
}