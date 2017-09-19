package com.cn.hnust.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.hnust.dao.ClassiFyMapper;
import com.cn.hnust.dao.CommentMapper;
import com.cn.hnust.dao.ProductMapper;
import com.cn.hnust.dao.SecondLevelMapper;
import com.cn.hnust.dao.SlidPicMapper;
import com.cn.hnust.dao.UserProductMapper;
import com.cn.hnust.pojo.ClassiFy;
import com.cn.hnust.pojo.CommentDetail;
import com.cn.hnust.pojo.FirstResultMap;
import com.cn.hnust.pojo.HomePage;
import com.cn.hnust.pojo.Product;
import com.cn.hnust.pojo.ProductDetail;
import com.cn.hnust.pojo.ProtocolBean;
import com.cn.hnust.pojo.SecondLevel;
import com.cn.hnust.pojo.SlidPic;
import com.cn.hnust.pojo.UserProduct;
import com.cn.hnust.service.IHomePageService;
@Service
public class HomePagerServiceImpl implements IHomePageService{

	@Resource
	CommentMapper comment;
	@Resource
	ClassiFyMapper classify;
	@Resource
	ProductMapper product;
	@Resource
	SlidPicMapper slidpic;
	@Resource
	UserProductMapper userProductMapper;
	@Resource
	SecondLevelMapper second_level;
	@Override
	public ProtocolBean getHomePagerData(int page, int rows) {
		// TODO Auto-generated method stub
		List<ClassiFy> classifys = classify.selectCount(7);
		List<SlidPic> slidPic =  slidpic.selectAll();
		int start = page * rows;
		List<Product> products = product.selectByLimit(start, rows);
		ProtocolBean bean = new ProtocolBean();
		HomePage home = new HomePage();
		home.setClassifyList(classifys);
		home.setSlideShowUrlList(slidPic);
		home.setRecommendationList(products);
		bean.setMsg("获取首页成功！");
		bean.setResultCode(0);
		bean.setData(home);
		return bean;
	}
	@Override
	public ProtocolBean searchProduct(String content, int page, int rows) {
		// TODO Auto-generated method stub
		String seach = "%"+content+"%";
		List<Product> products = product.selectByContent(seach, page, rows);
		ProtocolBean bean = new ProtocolBean();
		bean.setData(products);
		bean.setResultCode(0);
		bean.setMsg("搜索成功");
		return bean;
	}
	@Override
	public ProtocolBean getProductDetail(int shopId) {
		// TODO Auto-generated method stub
		ProductDetail map = product.selectProductDetailById(shopId);
		int count = comment.selectCountComment(shopId);
		map.setEvaluateNumber(count);
		System.out.println(map.toString());
		ProtocolBean bean = new ProtocolBean();
		bean.setData(map);
		bean.setMsg("获取详情成功");
		bean.setResultCode(0);
		return bean;
	}
	@Override
	public ProtocolBean getAllCommentByShopId(int shopId,int page,int rows) {
		// TODO Auto-generated method stub
		int index = page * rows;
		List<CommentDetail> detail = comment.selectAllByShopId(shopId,index,rows);
		Map<String,Object> map = new HashMap<>();
		map.put("evaluateList", detail);
		ProtocolBean bean = new ProtocolBean();
		bean.setData(map);
		bean.setMsg("获取评论成功");
		bean.setResultCode(0);
		return bean;
	}
	@Override
	public ProtocolBean addShopCart(UserProduct userProduct) {
		// TODO Auto-generated method stub
		int result = userProductMapper.insertSelective(userProduct);
		ProtocolBean bean = new ProtocolBean();
		Map map = new HashMap<>();
		if(result>0) {
			bean.setMsg("插入成功");
			bean.setResultCode(0);
		} else {
			bean.setMsg("插入失败");
			bean.setResultCode(1);
		}
		bean.setData(map);
		return bean;
	}
	@Override
	public ProtocolBean getAllClassify() {
		// TODO Auto-generated method stub
		List<FirstResultMap> result = second_level.selectAllClassify();
		Map<String,Object> map = new HashMap<>();
		map.put("stairList", result);
		ProtocolBean bean = new ProtocolBean();
		bean.setData(map);
		bean.setResultCode(0);
		return bean;
	}
	@Override
	public int selectCountComment(int shopId) {
		// TODO Auto-generated method stub
		return comment.selectCountComment(shopId);
	}
	@Override
	public List<Product> getAllProduct(int page, int rows) {
		// TODO Auto-generated method stub
		page = page * rows;
		return product.selectByLimit(page, rows);
	}
	@Override
	public int getProductCount() {
		// TODO Auto-generated method stub
		return product.selectProductCount();
	}
	@Override
	public int deleteProductById(int shopId) {
		// TODO Auto-generated method stub
		return product.deleteByPrimaryKey(shopId);
	}
	@Override
	public List<ClassiFy> getAllFirstClass() {
		// TODO Auto-generated method stub
		return classify.selectAllFirstClass();
	}
	@Override
	public List<SecondLevel> getAllSecondClass() {
		// TODO Auto-generated method stub
		return second_level.selectAllSecondClass();
	}

	
}
