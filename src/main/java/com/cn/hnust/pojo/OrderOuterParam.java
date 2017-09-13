package com.cn.hnust.pojo;

public class OrderOuterParam {
	Double totalMoney;
	Double shopfreight;
	public Double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public Double getShopfreight() {
		return shopfreight;
	}
	public void setShopfreight(Double shopfreight) {
		this.shopfreight = shopfreight;
	}
	@Override
	public String toString() {
		return "OrderOuterParam [totalMoney=" + totalMoney + ", shopfreight=" + shopfreight + "]";
	}
	
}
