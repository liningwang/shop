package com.cn.hnust.pojo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
@ApiModel
public class AdminReturnDetail {
	@ApiModelProperty(value="状态 1：退货申请中 2：退货完成",notes="哈哈")
	Integer orderType;
	@ApiModelProperty(value="退款原因")
	String returnReason;
	@ApiModelProperty(value="商品id")
	Integer shopId;
	Integer cancelState;
	@ApiModelProperty(value="退款订单图片")
	String salesreturnpictures;
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
	@ApiModelProperty(value="价格")
    Double shopprice;
	@ApiModelProperty(value="运费")
    private Double shopfreight;
	@ApiModelProperty(value="退款商品数量")
    Double returnCount;
	@ApiModelProperty(value="订单编号")
	String orderNum;
	@ApiModelProperty(value = "用户名字", required = true)
    private String username;
	@ApiModelProperty(value = "用户电话号码", required = true)
    private String usertelephone;
	@ApiModelProperty(value = "用户所在省", required = true)
    private String userprovince;
	@ApiModelProperty(value = "用户所在市", required = true)
    private String usercity;
	@ApiModelProperty(value = "用户地址所在区", required = true)
    private String userdistrict;
	@ApiModelProperty(value = "用户详细地址", required = true)
    private String userdetailedaddress;
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
	public Double getReturnCount() {
		return returnCount;
	}
	public void setReturnCount(Double returnCount) {
		this.returnCount = returnCount;
	}
	public String getSalesreturnpictures() {
		return salesreturnpictures;
	}
	public void setSalesreturnpictures(String salesreturnpictures) {
		this.salesreturnpictures = salesreturnpictures;
	}
	public Integer getCancelState() {
		return cancelState;
	}
	public void setCancelState(Integer cancelState) {
		this.cancelState = cancelState;
	}
	
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
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
	public String getUserprovince() {
		return userprovince;
	}
	public void setUserprovince(String userprovince) {
		this.userprovince = userprovince;
	}
	public String getUsercity() {
		return usercity;
	}
	public void setUsercity(String usercity) {
		this.usercity = usercity;
	}
	public String getUserdistrict() {
		return userdistrict;
	}
	public void setUserdistrict(String userdistrict) {
		this.userdistrict = userdistrict;
	}
	public String getUserdetailedaddress() {
		return userdetailedaddress;
	}
	public void setUserdetailedaddress(String userdetailedaddress) {
		this.userdetailedaddress = userdetailedaddress;
	}
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
