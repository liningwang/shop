package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.Address;

public interface AddressMapper {
    int deleteByPrimaryKey(Integer useraddreessid);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Integer useraddreessid);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);
    
    List<Address> selectByUserId(@Param("userId") int userId,@Param("page") int page,
    		@Param("rows") int rows);
    
    Address selectDefaultByUserId(@Param("userId") int userId);
}