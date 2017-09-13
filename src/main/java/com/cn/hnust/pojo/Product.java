package com.cn.hnust.pojo;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class Product {
    private Integer shopid;
    @ApiModelProperty(value="商品标题")
    private String shoptitle;
    @ApiModelProperty(value="价格")
    private Double shopprice;
    @ApiModelProperty(value="运费")
    private Double shopfreight;
    @ApiModelProperty(value="销量")
    private Integer shopsalesvolume;
    @ApiModelProperty(value="商品状态  0：普通 1：特殊")
    private Integer shoptype;
    @ApiModelProperty(value="商品图片")
    private String shoppictureurl;
    @ApiModelProperty(value="商品图文详情")
    private String shopgraphicdetails;
    @ApiModelProperty(value="商品一级分类id")
    private Integer classifyid;
    @ApiModelProperty(value="商品二级分类id")
    private Integer twoclassifyid;
    @ApiModelProperty(value="推荐商品，推荐的商品会显示到首页")
    private Integer recommend;
    @ApiModelProperty(value="商品库存")
    private Integer shopCount;
    
	public Integer getShopCount() {
		return shopCount;
	}

	public void setShopCount(Integer shopCount) {
		this.shopCount = shopCount;
	}

	public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    public String getShoptitle() {
        return shoptitle;
    }

    public void setShoptitle(String shoptitle) {
        this.shoptitle = shoptitle == null ? null : shoptitle.trim();
    }

    public Double getShopprice() {
        return shopprice;
    }

    public void setShopprice(Double shopprice) {
        this.shopprice = shopprice;
    }

    public Double getShopfreight() {
        return shopfreight;
    }

    public void setShopfreight(Double shopfreight) {
        this.shopfreight = shopfreight;
    }

    public Integer getShopsalesvolume() {
        return shopsalesvolume;
    }

    public void setShopsalesvolume(Integer shopsalesvolume) {
        this.shopsalesvolume = shopsalesvolume;
    }

    public Integer getShoptype() {
        return shoptype;
    }

    public void setShoptype(Integer shoptype) {
        this.shoptype = shoptype;
    }

    public String getShoppictureurl() {
        return shoppictureurl;
    }

    public void setShoppictureurl(String shoppictureurl) {
        this.shoppictureurl = shoppictureurl == null ? null : shoppictureurl.trim();
    }

    public String getShopgraphicdetails() {
        return shopgraphicdetails;
    }

    public void setShopgraphicdetails(String shopgraphicdetails) {
        this.shopgraphicdetails = shopgraphicdetails == null ? null : shopgraphicdetails.trim();
    }

    public Integer getClassifyid() {
        return classifyid;
    }

    public void setClassifyid(Integer classifyid) {
        this.classifyid = classifyid;
    }

    public Integer getTwoclassifyid() {
        return twoclassifyid;
    }

    public void setTwoclassifyid(Integer twoclassifyid) {
        this.twoclassifyid = twoclassifyid;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }
}