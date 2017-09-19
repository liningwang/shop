package com.cn.hnust.dao;

import java.util.List;

import com.cn.hnust.pojo.ClassiFy;
import com.cn.hnust.pojo.SecondLevel;

public interface ClassiFyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClassiFy record);

    int insertSelective(ClassiFy record);

    ClassiFy selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClassiFy record);

    int updateByPrimaryKey(ClassiFy record);
    List<ClassiFy> selectCount(Integer count);
    List<ClassiFy> selectAllFirstClass();
   
}