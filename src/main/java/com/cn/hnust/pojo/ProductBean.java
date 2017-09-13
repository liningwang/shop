package com.cn.hnust.pojo;

public class ProductBean {
	int ProductId;
	int ProductNum;
	public int getProductId() {
		return ProductId;
	}
	public void setProductId(int productId) {
		ProductId = productId;
	}
	public int getProductNum() {
		return ProductNum;
	}
	public void setProductNum(int productNum) {
		ProductNum = productNum;
	}
	@Override
	public String toString() {
		return "ProductBean [ProductId=" + ProductId + ", ProductNum=" + ProductNum + "]";
	}
	
	
}
