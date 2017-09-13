package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.Comment;
import com.cn.hnust.pojo.CommentDetail;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
    
    List<CommentDetail> selectAllByShopId(@Param("shopId") int shopId,@Param("page") int page,
    		@Param("rows") int rows);
}