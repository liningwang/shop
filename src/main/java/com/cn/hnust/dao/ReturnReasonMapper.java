package com.cn.hnust.dao;

import java.util.List;

import com.cn.hnust.pojo.ReturnReason;

public interface ReturnReasonMapper {
    int deleteByPrimaryKey(Integer salesreturnid);

    int insert(ReturnReason record);

    int insertSelective(ReturnReason record);

    ReturnReason selectByPrimaryKey(Integer salesreturnid);

    int updateByPrimaryKeySelective(ReturnReason record);

    int updateByPrimaryKey(ReturnReason record);
    List<ReturnReason> selectAll();
}