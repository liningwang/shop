package com.cn.hnust.pojo;

public class OrderProduct {
    private Integer orderid;

    private Integer shopid;

    //商品数量
    private Integer productnum;

    //订单号
    private String ordernum;

    //1购买 2退货
    private Integer type;
    //1待处理  2已同意 3已拒绝
    private Integer cancelState;
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCancelState() {
		return cancelState;
	}

	public void setCancelState(Integer cancelState) {
		this.cancelState = cancelState;
	}
    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    public Integer getProductnum() {
        return productnum;
    }

    public void setProductnum(Integer productnum) {
        this.productnum = productnum;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }
}