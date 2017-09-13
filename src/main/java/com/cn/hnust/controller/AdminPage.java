package com.cn.hnust.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.pojo.AdminOrderDetail;
import com.cn.hnust.pojo.AdminReturnDetail;
import com.cn.hnust.pojo.ProtocolBean;
import com.cn.hnust.pojo.ReturnDetail;
import com.cn.hnust.pojo.ShopOrder;
import com.cn.hnust.service.IPersonService;
import com.cn.hnust.service.IShopCardService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/admin")
public class AdminPage {
	@Resource
	IShopCardService card;
	@Resource
	IPersonService person;
	@RequestMapping(value = "/index")
	public String getIndex(
		){
		return "index";
	}
	
	@RequestMapping(value = "/menu")
	public String getmenu(
		){
		return "menu";
	}
	@RequestMapping(value = "/main")
	public String getMain(
		){
		return "main";
	}
	@RequestMapping(value = "/top")
	public String getTop(
		){
		return "top";
	}
	@RequestMapping(value = "/bar")
	public String getBar(
		){
		return "bar";
	}
	@RequestMapping(value = "/logout")
	public String logout(
		){
		return "logout";
	}
	@RequestMapping(value = "/orders")
	public String getFirstPage(
			@ApiParam(value="页数")
			@RequestParam int page,
			@ApiParam(value="每页所显示的行数")
			@RequestParam int rows,
			ModelMap model){
		System.out.println("page " + page + " rows " + rows);
		List<AdminOrderDetail> orders = card.getAllAdminOrderDetail(page,rows);
		System.out.println("orders " + orders.toString()+ " size " + orders.size());
		model.addAttribute("products", orders);
		model.addAttribute("page", page);
		int num = card.getOrderCount() / 10;
		model.addAttribute("pageNum", num);
		System.out.println("pageNum " + card.getOrderCount() + " num " + num);
		return "order_list";
	}
	@RequestMapping(value = "/searchOrder",method=RequestMethod.POST)
	public String searchOrder(
			@RequestParam int orderType,
			@RequestParam String content,
			ModelMap model
		){
		String orderNum = content;
		String userName = content;
		List<AdminOrderDetail> orders = card.searchOrder(orderType, orderNum, userName);
		System.out.println("orders " + orders.toString()+ " size " + orders.size());
		model.addAttribute("products", orders);
		return "order_list";
	}
	
	@RequestMapping(value = "/order_detail")
	public String OrderDetail(
			@RequestParam String orderNum,
			ModelMap model
		){
		AdminOrderDetail order = card.getAdminOrderDetail(orderNum);
		System.out.println("orders " + order.toString());
		model.addAttribute("product", order);
		return "order_detail";
	}
	@RequestMapping(value = "/changeOrder",method=RequestMethod.GET)
	@ResponseBody
	public String changeOrder(
			@RequestParam String orderNum,
			@RequestParam int orderType
		){
		card.changeOrderType(orderType, orderNum);
		ShopOrder order = card.getOrderByOrderNum(orderNum);
		card.sendPushForUser(order.getUserid(), "你购买的商品已经发货请注意查收", 1);
		return "修改成功";
	}
	@RequestMapping(value = "/deleteOrder",method=RequestMethod.GET)
	@ResponseBody
	public String deleteOrder(
			@RequestParam String orderNum
		){
		int result = card.deleteByOrderNum(orderNum);
		if(result == 1) {
			return "删除成功";
		} else if(result == 0 ) {
			return "删除失败";
		}
		return null;
	}
	@RequestMapping(value = "/deleteMoreOrder",method=RequestMethod.POST)
	@ResponseBody
	public String deleteMoreOrder(
			@RequestParam String orderNum
		){
		boolean fail = false;
		System.out.println(orderNum);
		String[] num = orderNum.split(",");
		for(int i = 0;i < num.length;i++) {
			int result = card.deleteByOrderNum(num[i]);
			if(result == 0 ) {
				fail = true;
			}
		}
		if(!fail) {
			return "success";
		} else {
			return "fail";
		}
	}
	@RequestMapping(value = "/changeMoreOrder",method=RequestMethod.POST)
	@ResponseBody
	public String changeMoreOrder(
			@RequestParam String orderNum
		){
		boolean fail = false;
		System.out.println(orderNum);
		String[] num = orderNum.split(",");
		for(int i = 0;i < num.length;i++) {
			card.changeOrderType(3, num[i]);
			ShopOrder order = card.getOrderByOrderNum(num[i]);
			card.sendPushForUser(order.getUserid(), "你购买的商品已经发货请注意查收", 1);
		}
		
		return "修改成功";
	}
	
	@RequestMapping(value = "/orderReturnList",method=RequestMethod.GET)
	@ApiOperation(value = "订单退款", notes = "订单退款")
	public String orderReturnList(
			@ApiParam(value="页数")
			@RequestParam int page,
			@ApiParam(value="每页所显示的行数")
			@RequestParam int rows,
			ModelMap model
		){
		List<AdminReturnDetail> returns = person.returnList(page,rows);
		int pageNum = person.returnCount() / 10;
		System.out.println("pageNum " + pageNum+" currPage " + page);
		model.addAttribute("product", returns);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("page", page);
		return "order_return";
	}
	
	@RequestMapping(value = "/orderReturnDetail",method=RequestMethod.GET)
	@ApiOperation(value = "退款详情", notes = "退款详情")
	public String orderReturnList(
			@RequestParam String returnNum,
			ModelMap model
		){
		AdminReturnDetail returnOr = person.returnOrderByNum(returnNum);
		model.addAttribute("product", returnOr);
		String pics = returnOr.getSalesreturnpictures();
		String[] pic = pics.split(",");
		System.out.println(pic.length);
		model.addAttribute("pic", pic);
		return "order_detail_return";
	}
	@RequestMapping(value = "/acceptReturn", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="同意退款",notes="同意退款")
	public String returnOrder(
			@ApiParam(value="退款单号")
			@RequestParam String returnNum,
			@RequestParam String orderNum
			){
		System.out.println(returnNum);
		ProtocolBean pro = person.returnPay(returnNum);
		if(pro.getResultCode() == 0) {
			ShopOrder order = card.getOrderByOrderNum(orderNum);
			card.sendPushForUser(order.getUserid(), "退款成功注意查收", 1);
			return "success";
		} else {
			return "fail";
		}
	}
	@RequestMapping(value = "/refuseReturn", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="拒绝退款",notes="拒绝退款")
	public String refuseOrder(
			@RequestParam String returnNum,
			@RequestParam String orderNum,
			@RequestParam int shopId
			){
		System.out.println(returnNum+" order " + orderNum + " shopId " + shopId);
		int result = person.refusePay(orderNum, shopId, returnNum);
		if(result == 1) {
			ShopOrder order = card.getOrderByOrderNum(orderNum);
			card.sendPushForUser(order.getUserid(), "拒绝退款请求", 1);
			return "success";
		} else {
			return "fail";
		}
	}
}
