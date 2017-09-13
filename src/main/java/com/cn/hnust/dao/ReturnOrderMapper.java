package com.cn.hnust.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.AdminReturnDetail;
import com.cn.hnust.pojo.ReturnDetail;
import com.cn.hnust.pojo.ReturnOrder;
import com.cn.hnust.pojo.ReturnProduct;

public interface ReturnOrderMapper {
    int deleteByPrimaryKey(Integer returnid);

    int insert(ReturnOrder record);

    int insertSelective(ReturnOrder record);

    ReturnOrder selectByPrimaryKey(Integer returnid);

    int updateByPrimaryKeySelective(ReturnOrder record);

    int updateByPrimaryKey(ReturnOrder record);
    
    int returnNum(int userId);
    
    List<ReturnProduct> getReturnList(@Param("userId") int userId);
    ReturnDetail returnDetail(@Param("userId") int userId,@Param("orderNum") String orderNum,
    		@Param("shopId") int shopId);
    
    ReturnOrder selectByReturnNum(String returnnum);
    HashMap<String,Object> selectMapByReturnNum(String returnnum);
    
    AdminReturnDetail returnDetailByReturnNum(@Param("returnNum") String returnNum);
    List<AdminReturnDetail> returnDetailAll(@Param("page") int page,@Param("rows") int rows);
    int returnAllCount();
}