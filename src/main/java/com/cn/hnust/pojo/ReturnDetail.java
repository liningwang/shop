package com.cn.hnust.pojo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
@ApiModel
public class ReturnDetail {
	@ApiModelProperty(value="状态 1：退货申请中 2：退货完成",notes="哈哈")
	Integer orderType;
	@ApiModelProperty(value="退款原因")
	String returnReason;
	@ApiModelProperty(value="退款总金额")
	Double orderRental;
	@ApiModelProperty(value="退款原因")
	String reasonfortheCredit;
	@ApiModelProperty(value="申请时间")
	String timeOfApplication;
	@ApiModelProperty(value="退款时间")
	String aRefundOfTime;
	@ApiModelProperty(value="退款编号")
	String refundIdInvalid;
	@ApiModelProperty(value="商品图片地址")
	String shopPictureUrl;
	@ApiModelProperty(value="商品名称")
	String shopTitle;
	@ApiModelProperty(value="订单编号")
	String orderNum;
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public String getReturnReason() {
		return returnReason;
	}
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}
	public Double getOrderRental() {
		return orderRental;
	}
	public void setOrderRental(Double orderRental) {
		this.orderRental = orderRental;
	}
	public String getReasonfortheCredit() {
		return reasonfortheCredit;
	}
	public void setReasonfortheCredit(String reasonfortheCredit) {
		this.reasonfortheCredit = reasonfortheCredit;
	}
	public String getTimeOfApplication() {
		return timeOfApplication;
	}
	public void setTimeOfApplication(String timeOfApplication) {
		this.timeOfApplication = timeOfApplication;
	}
	public String getaRefundOfTime() {
		return aRefundOfTime;
	}
	public void setaRefundOfTime(String aRefundOfTime) {
		this.aRefundOfTime = aRefundOfTime;
	}
	public String getRefundIdInvalid() {
		return refundIdInvalid;
	}
	public void setRefundIdInvalid(String refundIdInvalid) {
		this.refundIdInvalid = refundIdInvalid;
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
	
}
