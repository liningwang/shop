package com.cn.hnust.pojo;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class ReturnOrder {
	
    @Override
	public String toString() {
		return "ReturnOrder [returnid=" + returnid + ", ordernum=" + ordernum + ", shopid=" + shopid
				+ ", salesreturnid=" + salesreturnid + ", refundprice=" + refundprice + ", salesreturnexplain="
				+ salesreturnexplain + ", salesreturnpictures=" + salesreturnpictures + ", starttime=" + starttime
				+ ", endtime=" + endtime + ", returnnum=" + returnnum + "]";
	}

	private Integer returnid;

    @ApiModelProperty(value="订单编号",required=true)
    private String ordernum;
    @ApiModelProperty(value="商品ID",required=true)
    private Integer shopid;
    @ApiModelProperty(value="退货原因ID",required=true)
    private Integer salesreturnid;
    @ApiModelProperty(value="退款金额",required=true)
    private Double refundprice;
    @ApiModelProperty(value="退货说明",required=true)
    private String salesreturnexplain;
    @ApiModelProperty(value="退货图片地址（多张逗号隔开）",required=true)
    private String salesreturnpictures;

    private String starttime;

    private String endtime;

    private String returnnum;
    @ApiModelProperty(value="退货个数")
    private Integer returnCount;
    @ApiModelProperty(value="状态 1：退货申请中 2：退货完成")
    private Integer orderType;
    @ApiModelProperty(value="1待处理  2已同意 3已拒绝")
    private Integer cancelState;
    @ApiModelProperty(value="支付成功后，返回的支付宝交易号")

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getCancelState() {
		return cancelState;
	}

	public void setCancelState(Integer cancelState) {
		this.cancelState = cancelState;
	}

	public Integer getReturnCount() {
		return returnCount;
	}

	public void setReturnCount(Integer returnCount) {
		this.returnCount = returnCount;
	}

	public Integer getReturnid() {
        return returnid;
    }

    public void setReturnid(Integer returnid) {
        this.returnid = returnid;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum == null ? null : ordernum.trim();
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    public Integer getSalesreturnid() {
        return salesreturnid;
    }

    public void setSalesreturnid(Integer salesreturnid) {
        this.salesreturnid = salesreturnid;
    }

    public Double getRefundprice() {
        return refundprice;
    }

    public void setRefundprice(Double refundprice) {
        this.refundprice = refundprice;
    }

    public String getSalesreturnexplain() {
        return salesreturnexplain;
    }

    public void setSalesreturnexplain(String salesreturnexplain) {
        this.salesreturnexplain = salesreturnexplain == null ? null : salesreturnexplain.trim();
    }

    public String getSalesreturnpictures() {
        return salesreturnpictures;
    }

    public void setSalesreturnpictures(String salesreturnpictures) {
        this.salesreturnpictures = salesreturnpictures == null ? null : salesreturnpictures.trim();
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime == null ? null : endtime.trim();
    }

    public String getReturnnum() {
        return returnnum;
    }

    public void setReturnnum(String returnnum) {
        this.returnnum = returnnum == null ? null : returnnum.trim();
    }
}