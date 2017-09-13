package com.cn.hnust.service;

import com.cn.hnust.pojo.ProtocolBean;
import com.cn.hnust.pojo.UserProduct;

public interface IHomePageService {
	public ProtocolBean getHomePagerData(int page,int rows);
	public ProtocolBean searchProduct(String content,int page,int rows);
	public ProtocolBean getProductDetail(int shopId);
	public ProtocolBean getAllCommentByShopId(int shopId,int page,int rows);
	public ProtocolBean addShopCart(UserProduct userProduct);
	public ProtocolBean getAllClassify();
}
