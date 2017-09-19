package com.cn.hnust.dao;

import java.util.List;

import com.cn.hnust.pojo.FirstResultMap;
import com.cn.hnust.pojo.SecondLevel;

public interface SecondLevelMapper {
    int deleteByPrimaryKey(Integer secondlevelid);

    int insert(SecondLevel record);

    int insertSelective(SecondLevel record);

    SecondLevel selectByPrimaryKey(Integer secondlevelid);

    int updateByPrimaryKeySelective(SecondLevel record);

    int updateByPrimaryKey(SecondLevel record);
    
    List<FirstResultMap> selectAllClassify();
    List<SecondLevel> selectAllSecondClass();
}