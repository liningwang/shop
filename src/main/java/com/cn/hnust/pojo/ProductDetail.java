package com.cn.hnust.pojo;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class ProductDetail {
    private Integer shopid;

    private String shoptitle;

    private Double shopprice;

    private Double shopfreight;

    private Integer shopsalesvolume;

    private Integer shoptype;

    private String shoppictureurl;
    @ApiModelProperty(value = "库存", required = true)
    private Integer shopCount;
	@ApiModelProperty(value = "图文详情", required = true)
    private String shopgraphicdetails;
    @ApiModelProperty(value = "商品一级分类id", required = true)
    private Integer classifyid;
    @ApiModelProperty(value = "商品二级分类id", required = true)
    private Integer twoclassifyid;
    @ApiModelProperty(value = "推荐商品", required = true)
    private Integer recommend;
    @ApiModelProperty(value = "评价总数", required = true)
    private Integer evaluateNumber;
    private String evaluatetime;

    private Integer evaluategrade;

    private String evaluatecontent;

    private String evaluatepictureurl;
    
  private String evaluateHeadUrl;
    
    private String evaluateName;

    public Integer getShopCount() {
		return shopCount;
	}

	public void setShopCount(Integer shopCount) {
		this.shopCount = shopCount;
	}

	public Integer getEvaluateNumber() {
		return evaluateNumber;
	}

	public void setEvaluateNumber(Integer evaluateNumber) {
		this.evaluateNumber = evaluateNumber;
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
		this.shoptitle = shoptitle;
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
		this.shoppictureurl = shoppictureurl;
	}

	public String getShopgraphicdetails() {
		return shopgraphicdetails;
	}

	public void setShopgraphicdetails(String shopgraphicdetails) {
		this.shopgraphicdetails = shopgraphicdetails;
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

	public String getEvaluatetime() {
		return evaluatetime;
	}

	public void setEvaluatetime(String evaluatetime) {
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
		this.evaluatecontent = evaluatecontent;
	}

	public String getEvaluatepictureurl() {
		return evaluatepictureurl;
	}

	public void setEvaluatepictureurl(String evaluatepictureurl) {
		this.evaluatepictureurl = evaluatepictureurl;
	}

	public String getEvaluateHeadUrl() {
		return evaluateHeadUrl;
	}

	public void setEvaluateHeadUrl(String evaluateHeadUrl) {
		this.evaluateHeadUrl = evaluateHeadUrl;
	}

	public String getEvaluateName() {
		return evaluateName;
	}

	public void setEvaluateName(String evaluateName) {
		this.evaluateName = evaluateName;
	}
    

}
