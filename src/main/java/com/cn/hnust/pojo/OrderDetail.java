package com.cn.hnust.pojo;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModelProperty;

//订单详情
public class OrderDetail {
	@ApiModelProperty(value = "用户名字", required = true)
    private String username;
	@ApiModelProperty(value = "用户电话号码", required = true)
    private String usertelephone;
	
    private Integer useraddreessid;

    private String buyermessage;

    private String ordernum;
    
    private Integer orderType;
    
    @ApiModelProperty(value="下单时间")
    private String orderTime;
    
    //商品总价
    private Double shopTotalPrices;
    //快递费用
    private Double expressagePrices;
    //价格合计
    private Double sumofSalesPrice;
    List<MyProduct> orderShopList;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsertelephone() {
		return usertelephone;
	}
	public void setUsertelephone(String usertelephone) {
		this.usertelephone = usertelephone;
	}
	public Integer getUseraddreessid() {
		return useraddreessid;
	}
	public void setUseraddreessid(Integer useraddreessid) {
		this.useraddreessid = useraddreessid;
	}
	public String getBuyermessage() {
		return buyermessage;
	}
	public void setBuyermessage(String buyermessage) {
		this.buyermessage = buyermessage;
	}
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public Double getShopTotalPrices() {
		return shopTotalPrices;
	}
	public void setShopTotalPrices(Double shopTotalPrices) {
		this.shopTotalPrices = shopTotalPrices;
	}
	public Double getExpressagePrices() {
		return expressagePrices;
	}
	public void setExpressagePrices(Double expressagePrices) {
		this.expressagePrices = expressagePrices;
	}
	public Double getSumofSalesPrice() {
		return sumofSalesPrice;
	}
	public void setSumofSalesPrice(Double sumofSalesPrice) {
		this.sumofSalesPrice = sumofSalesPrice;
	}
	public List<MyProduct> getOrderShopList() {
		return orderShopList;
	}
	public void setOrderShopList(List<MyProduct> orderShopList) {
		this.orderShopList = orderShopList;
	}
    
}
