package com.cn.hnust.pojo;

import java.util.List;

public class MyOrder {
	Integer orderType;
	Double orderRental;
	String orderId;
	List<MyProduct> orderShopList;
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public Double getOrderRental() {
		return orderRental;
	}
	public void setOrderRental(Double orderRental) {
		this.orderRental = orderRental;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public List<MyProduct> getOrderShopList() {
		return orderShopList;
	}
	public void setOrderShopList(List<MyProduct> orderShopList) {
		this.orderShopList = orderShopList;
	}
}

