package com.cn.hnust.dao;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.OrderProduct;

public interface OrderProductMapper {
    int insert(OrderProduct record);

    int insertSelective(OrderProduct record);
    int updateReturnShop(@Param("ordernum") String ordernum,@Param("shopid") int shopid);
    int acceptReturnShop(@Param("ordernum") String ordernum,@Param("shopid") int shopid);
    int refuseReturnShop(@Param("ordernum") String ordernum,@Param("shopid") int shopid);
    int deleteByOrderNum(@Param("orderNum") String orderNum);
}