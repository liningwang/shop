package com.cn.hnust.pojo;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class ShopOrder {
    private Integer orderid;

    private Integer userid;

    private Integer useraddreessid;

    private Double rentalprice;

    private String buyermessage;

    private String ordernum;
    
    private Integer orderType;
    
    @ApiModelProperty(value="下单时间")
    private String orderTime;
    
    //商品总价
    private Double shopTotalPrices;
    @Override
	public String toString() {
		return "ShopOrder [orderid=" + orderid + ", userid=" + userid + ", useraddreessid=" + useraddreessid
				+ ", rentalprice=" + rentalprice + ", buyermessage=" + buyermessage + ", ordernum=" + ordernum
				+ ", orderType=" + orderType + ", orderTime=" + orderTime + ", shopTotalPrices=" + shopTotalPrices
				+ ", expressagePrices=" + expressagePrices + ", sumofSalesPrice=" + sumofSalesPrice + "]";
	}

	//快递费用
    private Double expressagePrices;
    //价格合计
    private Double sumofSalesPrice;
   
	public String getOrderTime() {
		return orderTime;
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

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getUseraddreessid() {
        return useraddreessid;
    }

    public void setUseraddreessid(Integer useraddreessid) {
        this.useraddreessid = useraddreessid;
    }

    public Double getRentalprice() {
        return rentalprice;
    }

    public void setRentalprice(Double rentalprice) {
        this.rentalprice = rentalprice;
    }

    public String getBuyermessage() {
        return buyermessage;
    }

    public void setBuyermessage(String buyermessage) {
        this.buyermessage = buyermessage == null ? null : buyermessage.trim();
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum == null ? null : ordernum.trim();
    }
}