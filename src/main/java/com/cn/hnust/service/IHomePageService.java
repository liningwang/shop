package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.ClassiFy;
import com.cn.hnust.pojo.Product;
import com.cn.hnust.pojo.ProtocolBean;
import com.cn.hnust.pojo.SecondLevel;
import com.cn.hnust.pojo.UserProduct;

public interface IHomePageService {
	public ProtocolBean getHomePagerData(int page,int rows);
	public ProtocolBean searchProduct(String content,int page,int rows);
	public ProtocolBean getProductDetail(int shopId);
	public ProtocolBean getAllCommentByShopId(int shopId,int page,int rows);
	public int selectCountComment(int shopId);
	public ProtocolBean addShopCart(UserProduct userProduct);
	public ProtocolBean getAllClassify();
	public List<Product> getAllProduct(int page,int rows);
	public int getProductCount();
	public int deleteProductById(int shopId);
	public List<ClassiFy> getAllFirstClass();
	public List<SecondLevel> getAllSecondClass();
}
