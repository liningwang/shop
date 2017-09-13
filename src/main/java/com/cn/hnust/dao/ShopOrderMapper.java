package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.AdminOrderDetail;
import com.cn.hnust.pojo.MyOrder;
import com.cn.hnust.pojo.OrderDetail;
import com.cn.hnust.pojo.ShopOrder;

public interface ShopOrderMapper {
    int deleteByPrimaryKey(Integer orderid);

    int insert(ShopOrder record);

    int insertSelective(ShopOrder record);

    ShopOrder selectByPrimaryKey(Integer orderid);

    int updateByPrimaryKeySelective(ShopOrder record);

    int updateByPrimaryKey(ShopOrder record);
    
    int computeUnPayCount(Integer userId);
    int computeUnSendCount(Integer userId);
    int computeUnRecieveCount(Integer userId);
    int computeUnCommentCount(Integer userId);
    List<MyOrder> selectOrderDetail(@Param("userId") Integer userId,@Param("orderType") Integer orderType,
    		@Param("page") Integer page,@Param("rows") Integer rows);
    int deleteByOrderNumAndUserId(@Param("userId") Integer userId,@Param("orderNum") String orderNum);
    int updateByOrderNumAndUserIdSelective(ShopOrder shop);
    int updateByOrderNumSelective(ShopOrder shop);
    OrderDetail getOrderDetail(@Param("orderNum") String orderNum,@Param("userId") int userId);
    int timerMy(@Param("orderNum") String orderNum);
//    int closeTimer(@Param("orderNum") String orderNum);
    int setTimer();
    int deleteTimer(@Param("orderNum") String orderNum);
    ShopOrder getOrderByOrderNum(@Param("orderNum") String orderNum);
    //admin
    AdminOrderDetail getAdminOrderDetail(@Param("orderNum") String orderNum);
    List<AdminOrderDetail> getAllAdminOrderDetail(@Param("page") Integer page,@Param("rows") Integer rows);
    int getCountOrder();
    List<AdminOrderDetail> searchAdminOrderDetail(@Param("orderNum") String orderNum,@Param("orderType") Integer orderType,
    		@Param("userName") String userName);
    int deleteByOrderNum(@Param("orderNum") String orderNum);
}