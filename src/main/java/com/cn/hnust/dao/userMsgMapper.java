package com.cn.hnust.dao;

import java.util.List;

import com.cn.hnust.pojo.userMsg;

public interface userMsgMapper {
    int insert(userMsg record);

    int insertSelective(userMsg record);
    List<userMsg> selectByUserId(int userId);
    int selectCountByUserId(int userId);
}