package com.cn.hnust.dao;

import com.cn.hnust.pojo.moneyDetail;

public interface moneyDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(moneyDetail record);

    int insertSelective(moneyDetail record);

    moneyDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(moneyDetail record);

    int updateByPrimaryKey(moneyDetail record);
}