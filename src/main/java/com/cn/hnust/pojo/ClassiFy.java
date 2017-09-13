package com.cn.hnust.pojo;

public class ClassiFy {
    private Integer id;

    private String classifypictureurl;

    private Integer classifyid;

    private String classifytitle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassifypictureurl() {
        return classifypictureurl;
    }

    public void setClassifypictureurl(String classifypictureurl) {
        this.classifypictureurl = classifypictureurl == null ? null : classifypictureurl.trim();
    }

    public Integer getClassifyid() {
        return classifyid;
    }

    public void setClassifyid(Integer classifyid) {
        this.classifyid = classifyid;
    }

    public String getClassifytitle() {
        return classifytitle;
    }

    public void setClassifytitle(String classifytitle) {
        this.classifytitle = classifytitle == null ? null : classifytitle.trim();
    }
}