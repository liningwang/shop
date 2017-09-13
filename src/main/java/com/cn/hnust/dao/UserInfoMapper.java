package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.UserInfo;
import com.cn.hnust.pojo.UserPerson;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);
    int insertPersonSelective(UserPerson record);
    UserInfo selectByPrimaryKey(Integer userid);
    List<UserInfo> selectByPhoneNumber(String phoneNumber);
    
    int updateByPrimaryKeySelective(UserInfo record);
    int updateByPhoneNumberSelective(UserInfo record);
    int updateByIdAndPassSelective(UserInfo record);
    int updateByPrimaryKey(UserInfo record);
    int updatePersonByPrimaryKeySelective(UserPerson person);
    UserInfo login(@Param("phone") String phone,@Param("password") String password);
    UserInfo loginThrid(String openId);
    UserPerson selectPersonByPrimaryKey(int userid);
    List<UserInfo> selectByOpenId(String openid);
}