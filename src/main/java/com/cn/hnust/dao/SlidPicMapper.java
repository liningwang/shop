package com.cn.hnust.dao;

import java.util.List;

import com.cn.hnust.pojo.SlidPic;

public interface SlidPicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SlidPic record);

    int insertSelective(SlidPic record);

    SlidPic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SlidPic record);

    int updateByPrimaryKey(SlidPic record);
    
    public List<SlidPic> selectAll();
}