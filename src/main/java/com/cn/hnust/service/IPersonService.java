package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.AdminReturnDetail;
import com.cn.hnust.pojo.Comment;
import com.cn.hnust.pojo.ProtocolBean;
import com.cn.hnust.pojo.ReturnDetail;
import com.cn.hnust.pojo.ReturnOrder;
import com.cn.hnust.pojo.UserInfo;
import com.cn.hnust.pojo.UserPerson;

public interface IPersonService {
	public ProtocolBean submitReturn(ReturnOrder returnOrder);
	public ProtocolBean personCenter(int userId);
	public ProtocolBean getMyOrder(int userId,int orderType,int page,int rows);
	public ProtocolBean cancelDeleteOrder(int userId,String orderId,int type);
	public ProtocolBean getOrderDetail(String orderNum,int userId);
	public ProtocolBean getReturnList(int userId);
	public ProtocolBean getReturnDetail(int userId,String orderNum,int shopId);
	public ProtocolBean getReturnReason();
	public ProtocolBean addComment(int userId,List<Comment> comments);
	public ProtocolBean getPersonInfo(int userId);
	public ProtocolBean updatePersonInfo(UserPerson info);
	public ProtocolBean returnPay(String returnNum);
	public int refusePay(String orderNum,int shopid,String returnNo);
	//admin
	public List<AdminReturnDetail> returnList(int page,int rows);
	public AdminReturnDetail returnOrderByNum(String returnNum);
	public int returnCount();
}
