package com.cn.hnust.pojo;

import java.util.List;

public class ConfirmOrder {
	int userAddreessId;
	String userName;
	String userTelephone;
	String userProvince;
	String userCity;
	String userDistrict;
	String userDetailedAddress;
	Double shopRental;
	Double shopFreight;
	Double shopDeduction;
	List<Product> shopMessage;
	public int getUserAddreessId() {
		return userAddreessId;
	}
	public void setUserAddreessId(int userAddreessId) {
		this.userAddreessId = userAddreessId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserTelephone() {
		return userTelephone;
	}
	public void setUserTelephone(String userTelephone) {
		this.userTelephone = userTelephone;
	}
	public String getUserProvince() {
		return userProvince;
	}
	public void setUserProvince(String userProvince) {
		this.userProvince = userProvince;
	}
	public String getUserCity() {
		return userCity;
	}
	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}
	public String getUserDistrict() {
		return userDistrict;
	}
	public void setUserDistrict(String userDistrict) {
		this.userDistrict = userDistrict;
	}
	public String getUserDetailedAddress() {
		return userDetailedAddress;
	}
	public void setUserDetailedAddress(String userDetailedAddress) {
		this.userDetailedAddress = userDetailedAddress;
	}
	public Double getShopRental() {
		return shopRental;
	}
	public void setShopRental(Double shopRental) {
		this.shopRental = shopRental;
	}
	public Double getShopFreight() {
		return shopFreight;
	}
	public void setShopFreight(Double shopFreight) {
		this.shopFreight = shopFreight;
	}
	public Double getShopDeduction() {
		return shopDeduction;
	}
	public void setShopDeduction(Double shopDeduction) {
		this.shopDeduction = shopDeduction;
	}
	public List<Product> getShopMessage() {
		return shopMessage;
	}
	public void setShopMessage(List<Product> shopMessage) {
		this.shopMessage = shopMessage;
	}
	
}
