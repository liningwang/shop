package com.cn.hnust.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.Address;
import com.cn.hnust.pojo.AdminOrderDetail;
import com.cn.hnust.pojo.Product;
import com.cn.hnust.pojo.ProductBean;
import com.cn.hnust.pojo.ProtocolBean;
import com.cn.hnust.pojo.ShopOrder;

public interface IShopCardService {
	public ProtocolBean getShopCard(int userId,int page,int rows);
	public ProtocolBean deleteProduct(int userId,String id);
	public ProtocolBean modifyProductCount(int id,int count);
	public ProtocolBean addAddress(Address addr);
	public ProtocolBean modifyAddress(Address addr);
	public ProtocolBean getAllAddress(int userId,int page,int rows);
	public ProtocolBean setAddress(int userId,int userAddreessId);
	public ProtocolBean deleteAddress(int userAddreessId);
	public ProtocolBean generateOrder(ShopOrder order,List<ProductBean> products);
	public ProtocolBean confirmOrder(int userId,List<ProductBean> products,int code);
	public ProtocolBean payOrder(String orderCode,Double money);
	public ProtocolBean startTimer(String orderNum);
	public ProtocolBean closeTimer(String orderNum);
	public ProtocolBean sendPushForUser(int userId,String content,int msgType);
	public ProtocolBean getUserMsg(int userId);
	public ProtocolBean getCountUserMsg(int userId);
	public void changeOrderType(int type,String orderNum);
	public void inserdTrade(String trade_no,String out_trade_no);
	public ShopOrder getOrderByOrderNum(String orderNum);
	
	public AdminOrderDetail getAdminOrderDetail(String orderNum);
	public List<AdminOrderDetail> getAllAdminOrderDetail(int page,int rows);
	public int getOrderCount();
	public List<AdminOrderDetail> searchOrder(int orderType,String orderNum,String userName);
	int deleteByOrderNum(String orderNum);
	public int modifyProduct(Product shop);
	public int addProduct(Product shop);
}
