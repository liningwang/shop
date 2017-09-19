package com.cn.hnust.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cn.hnust.pojo.AdminOrderDetail;
import com.cn.hnust.pojo.AdminReturnDetail;
import com.cn.hnust.pojo.FirstResultJS;
import com.cn.hnust.pojo.FirstResultMap;
import com.cn.hnust.pojo.Product;
import com.cn.hnust.pojo.ProductDetail;
import com.cn.hnust.pojo.ProtocolBean;
import com.cn.hnust.pojo.ReturnDetail;
import com.cn.hnust.pojo.ShopOrder;
import com.cn.hnust.service.IHomePageService;
import com.cn.hnust.service.IPersonService;
import com.cn.hnust.service.IShopCardService;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/admin")
public class AdminPage {
	@Resource
	IShopCardService card;
	@Resource
	IPersonService person;
	@Resource
	IHomePageService home;
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
	@RequestMapping(value = "/addProduct",method=RequestMethod.GET)
	public String addProduct(
			@RequestParam Integer shopid,
			ModelMap model
		){
		ProtocolBean a = home.getAllClassify();
		Map<String,Object> map = (Map<String, Object>) a.getData();
		List<FirstResultMap> categorys = (List<FirstResultMap>) map.get("stairList");
		List<FirstResultJS> cate = new ArrayList<>();
		for(int i = 0;i<categorys.size();i++){
			String item = JSON.toJSONString(categorys.get(i).getSecondLevelList());
			FirstResultJS js = new FirstResultJS();
			js.setStairId(categorys.get(i).getStairId());
			js.setStairTitle(categorys.get(i).getStairTitle());
			js.setSecondLevelList(item);
			cate.add(js);
			System.out.println(item);
		}
		model.addAttribute("category", cate);
		ProductDetail pd = null;
		if(shopid != 0) {
			ProtocolBean pro = home.getProductDetail(shopid);
			pd = (ProductDetail) pro.getData();
		} else {
			pd = new ProductDetail();
			pd.setShopid(0);
		}
		model.addAttribute("product", pd);
		return "edit_product";
	}
	@RequestMapping(value = "/submitProduct",method=RequestMethod.POST)
	@ResponseBody
	public String submitProduct(
			@RequestParam Integer shopid,
			@RequestParam String shopTitle,
			@RequestParam Integer classifyid,
			@RequestParam Double shopPrice,
			@RequestParam Integer secondlevel,
			@RequestParam Integer recommend,
			@RequestParam Integer shopCount,
			@RequestParam String fileu,
			@RequestParam String editor,
			@RequestParam String shopPicText,
			ModelMap model,
			HttpServletRequest request
		){
		System.out.println("shopid " + shopid + " shopTitle " + shopTitle + " classifyid " + 
				classifyid + " shopPrice " + shopPrice + " secondlevel " + secondlevel
				+ " recommend " + recommend + " shopCount " + shopCount + " fileu " + fileu
				+ " editor " + editor);
		if(fileu.equals("")) {
			fileu = null;
		}
		if(shopid <= 0) {
			String picTextUrl = "";
			if(shopPicText != null && !shopPicText.equals("")) {
				picTextUrl = shopPicText;
			} else {
				picTextUrl = picTextDetail(editor,request);
			}
			Product pro = new Product();
			pro.setClassifyid(classifyid);
			pro.setTwoclassifyid(secondlevel);
			pro.setRecommend(recommend);
			pro.setShopCount(shopCount);
			pro.setShopgraphicdetails(picTextUrl);
			pro.setShoptitle(shopTitle);
			pro.setShoppictureurl(fileu);
			pro.setShopprice(shopPrice);
			card.addProduct(pro);
		} else {
			String picTextUrl = "";
			if(shopPicText != null && !shopPicText.equals("")) {
				picTextUrl = shopPicText;
			} else {
				picTextUrl = picTextDetail(editor,request);
			}
			Product pro = new Product();
			pro.setClassifyid(classifyid);
			pro.setTwoclassifyid(secondlevel);
			pro.setRecommend(recommend);
			pro.setShopCount(shopCount);
			pro.setShopgraphicdetails(picTextUrl);
			pro.setShoptitle(shopTitle);
			pro.setShoppictureurl(fileu);
			pro.setShopprice(shopPrice);
			pro.setShopid(shopid);
			card.modifyProduct(pro);
		}
		return "success";
	}
	private String picTextDetail(String html,HttpServletRequest request) {
		byte[] photoimg;
		String result = "";
		try {
//			photoimg = new BASE64Decoder().decodeBuffer(photo);
//			photoimg = Base64.decode(photo);
			photoimg = html.getBytes();
			System.out.println(html);
			// 获取项目运行路径
			String pathRoot = request.getSession().getServletContext()
					.getRealPath("images");
			// 生成uuid作为文件名称
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");

			String fileName = uuid + "file.html";
			// byte[] photoimg =
			// Base64.decode(photo);//此处不能用Base64.decode（）方法解密，我调试时用此方法每次解密出的数据都比原数据大
			// 所以用上面的函数进行解密，在网上直接拷贝的，花了好几个小时才找到这个错误（菜鸟不容易啊）
			System.out.println("图片的大小：" + photoimg.length);
			File file = new File(pathRoot);
			System.out.println("file path " + file.toString());
			if (!file.exists()) {
				file.mkdirs();
			}
			FileOutputStream out = new FileOutputStream(new File(file,fileName));
			out.write(photoimg);
			out.flush();
			out.close();
	
//	        String localIp=request.getRemoteAddr();//锟斤拷取锟斤拷锟斤拷ip
	        String localIp = "114.215.46.63";
//	        int localPort=request.getLocalPort();//锟斤拷取锟斤拷锟截的端匡拷
		    int localPort = 80;
				
			String path1 = request.getSession().getServletContext().getContextPath();
			System.out.println("ip " + localIp + " port" + localPort + " path "+path1 );
		    result = "http://"+localIp+":"+localPort+path1+"/" + "images/" + fileName;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
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
	
	@RequestMapping(value = "/deleteMoreProduct",method=RequestMethod.POST)
	@ApiOperation(value = "删除多个商品", notes = "删除多个商品")
	@ResponseBody
	public String deleteMoreProduct(
			@RequestParam String orderNum
		){
		boolean fail = false;
		System.out.println(orderNum);
		String[] num = orderNum.split(",");
		for(int i = 0;i < num.length;i++) {
			int result = home.deleteProductById(Integer.valueOf((num[i])));
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
	@RequestMapping(value = "/deleteProduct",method=RequestMethod.GET)
	@ApiOperation(value = "删除商品", notes = "删除商品")
	@ResponseBody
	public String deleteProduct(
			@RequestParam Integer shopId
		){
		boolean fail = false;
		System.out.println(shopId);
		int result = home.deleteProductById(shopId);
		if(result != 0) {
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
	@RequestMapping(value = "/productList",method=RequestMethod.GET)
	@ApiOperation(value = "商品列表", notes = "商品列表")
	public String productList(
			@ApiParam(value="页数")
			@RequestParam int page,
			@ApiParam(value="每页所显示的行数")
			@RequestParam int rows,
			ModelMap model
		){
		List<Product> products = home.getAllProduct(page, rows);
		int pageNum = home.getProductCount() / 10;
		System.out.println("pageNum " + pageNum+" currPage " + page);
		model.addAttribute("product", products);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("page", page);
		return "product_list";
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
