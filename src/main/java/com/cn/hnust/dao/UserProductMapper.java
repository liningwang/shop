package com.cn.hnust.dao;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.UserProduct;

public interface UserProductMapper {
    int deleteByPrimaryKey(@Param("shopid") Integer shopid,@Param("userid") Integer userid);

    int insert(UserProduct record);

    int insertSelective(UserProduct record);

    UserProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserProduct record);

    int updateByPrimaryKey(UserProduct record);
}