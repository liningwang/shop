package com.cn.hnust.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.Product;
import com.cn.hnust.pojo.ProductDetail;
import com.cn.hnust.pojo.ShopCard;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer shopid);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer shopid);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
    
    List<Product> selectByLimit(@Param("page") Integer page,@Param("rows") Integer rows);
    
    List<Product> selectByContent(@Param("content") String content,@Param("page") int page,
    		@Param("rows") int rows);
    ProductDetail selectProductDetailById(@Param("shopId") int shopId);
    List<ShopCard> selectByUserIdLimit(@Param("userId") int userId,@Param("page") int page,
    		@Param("rows") int rows);
}