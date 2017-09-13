package com.cn.hnust.pojo;

public class ReturnProduct {

	String orderId;
	Integer shopId;
	Double orderRental;
	Integer shopNumber;
	String shopPictureUrl;
	String shopTitle;
	Double shopPrice;
	Integer orderType;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public Double getOrderRental() {
		return orderRental;
	}
	public void setOrderRental(Double orderRental) {
		this.orderRental = orderRental;
	}
	public Integer getShopNumber() {
		return shopNumber;
	}
	public void setShopNumber(Integer shopNumber) {
		this.shopNumber = shopNumber;
	}
	public String getShopPictureUrl() {
		return shopPictureUrl;
	}
	public void setShopPictureUrl(String shopPictureUrl) {
		this.shopPictureUrl = shopPictureUrl;
	}
	public String getShopTitle() {
		return shopTitle;
	}
	public void setShopTitle(String shopTitle) {
		this.shopTitle = shopTitle;
	}
	public Double getShopPrice() {
		return shopPrice;
	}
	public void setShopPrice(Double shopPrice) {
		this.shopPrice = shopPrice;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
}
