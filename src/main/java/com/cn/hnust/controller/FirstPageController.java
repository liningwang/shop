package com.cn.hnust.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.pojo.ProtocolBean;
import com.cn.hnust.pojo.UserInfo;
import com.cn.hnust.pojo.UserProduct;
import com.cn.hnust.service.IHomePageService;
import com.cn.hnust.service.IUserInfoService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/first")
public class FirstPageController {
	
	@Resource
	IHomePageService homeService;
	
	@RequestMapping(value = "/homepage", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="首页",notes="获取首页内容")
	public ProtocolBean getFirstPage(
			@ApiParam(value="页数")
			@RequestParam int page,
			@ApiParam(value="每页所显示的行数")
			@RequestParam int rows){
		System.out.println("page " + page + " rows " + rows);
		return homeService.getHomePagerData(page, rows);
	}
	
	@RequestMapping(value = "/classificationofgoods", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="分类",notes="获取所有分类")
	public ProtocolBean getAllClass(){
		return homeService.getAllClassify();
	}
	
	@RequestMapping(value = "/addshoppingcart", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="加入购物车",notes="加入购物车")
	public ProtocolBean addShopCart(
			@ApiParam(value="用户id")
			@RequestParam int userId,
			@ApiParam(value="商品id")
			@RequestParam int shopId,
			@ApiParam(value="商品数量")
			@RequestParam int shopNumber){
		UserProduct product = new UserProduct();
		product.setShopid(shopId);
		product.setUserid(userId);
		product.setShopnumber(shopNumber);
		return homeService.addShopCart(product);
	}
	
	@RequestMapping(value = "/seeallevaluation", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="获取评论",notes="通过商品id，获取商品评论")
	public ProtocolBean getAllComment(
			@ApiParam(value="商品Id")
			@RequestParam int shopId,
			@ApiParam(value="页数")
			@RequestParam int page,
			@ApiParam(value="每页所显示的行数")
			@RequestParam int rows){
		System.out.println("page " + page + " rows " + rows + " shopId " + shopId);
		return homeService.getAllCommentByShopId(shopId,page, rows);
	}
	
	@RequestMapping(value = "/commoditydetails", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="商品详情",notes="获取商品详情")
	public ProtocolBean getProductDetail(
			@ApiParam(value = "商品的id值") 
			@RequestParam
			int shopId){
		System.out.println("shopId " + shopId);
		return homeService.getProductDetail(shopId);
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="搜索",notes="搜索商品")
	public ProtocolBean search(
			@ApiParam(value="要搜索的内容")
			@RequestParam String content,
			@ApiParam(value="页数")
			@RequestParam int page,
			@ApiParam(value="每页所显示的行数")
			@RequestParam int rows,
			HttpServletRequest request){
		String con = "";
//		try {
//			con = new String(content.getBytes("ISO-8859-1"),"utf-8");
			con = content;
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println("page " + page + " rows " + rows + " content " + con);
		return homeService.searchProduct(con, page, rows);
	}
}
