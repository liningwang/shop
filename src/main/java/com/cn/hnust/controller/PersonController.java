package com.cn.hnust.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.cn.hnust.pojo.Comment;
import com.cn.hnust.pojo.ProtocolBean;
import com.cn.hnust.pojo.ReturnDetail;
import com.cn.hnust.pojo.ReturnOrder;
import com.cn.hnust.pojo.ReturnReason;
import com.cn.hnust.pojo.UserPerson;
import com.cn.hnust.service.IPersonService;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
@Controller
@RequestMapping("/person")
public class PersonController {
	@Resource
	IPersonService person;
	
	@RequestMapping(value = "/aReturnRequest", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="申请退款",notes="申请退款")
	public ProtocolBean returnOrder(
			@ModelAttribute
			ReturnOrder returnOrder
			){
		System.out.println(returnOrder.toString());
		return person.submitReturn(returnOrder);
	}
	
	@RequestMapping(value = "/admin/aReturnRequest", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="同意退款",notes="同意退款")
	public ProtocolBean returnOrder(
			@ApiParam(value="退款单号")
			@RequestParam String returnNum
			){
		System.out.println(returnNum);
		return person.returnPay(returnNum);
	}
	
	@RequestMapping(value = "/personaldata", method = RequestMethod.POST)
	@ResponseBody
	@ApiResponses(@ApiResponse(code=0,message="成功",response=UserPerson.class))
	@ApiOperation(value="个人资料",notes="个人资料")
	public ProtocolBean personInfo(
			@ApiParam(value="用户id")
			@RequestParam int userId
			){
		return person.getPersonInfo(userId);
	}
	
	@RequestMapping(value = "/personaldataupload", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="上传资料",notes="上传资料")
	public ProtocolBean updatePerson(
			@ModelAttribute
			UserPerson userPerson,
			HttpServletRequest request
			){
		String url="";
		if(userPerson.getHeadportrait() != null || !userPerson.equals("")) {
			url = formUpload(userPerson.getHeadportrait(), request);
		}
		userPerson.setHeadportrait(url);
		return person.updatePersonInfo(userPerson);
	}
	
	private String formUpload(String photo,HttpServletRequest request) {
		byte[] photoimg;
		String result = "";
		try {
//			photoimg = new BASE64Decoder().decodeBuffer(photo);
			photoimg = Base64.decode(photo);
//			photoimg = photo.getBytes();
			System.out.println(photo);
			// 获取项目运行路径
			String pathRoot = request.getSession().getServletContext()
					.getRealPath("images");
			// 生成uuid作为文件名称
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");

			String fileName = uuid + "head.png";
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
	
			String localIp=request.getLocalAddr();//锟斤拷取锟斤拷锟斤拷ip
			int localPort=request.getLocalPort();//锟斤拷取锟斤拷锟截的端匡拷
				
			String path1 = request.getSession().getServletContext().getContextPath();
			System.out.println("ip " + localIp + " port" + localPort + " path "+path1 );
		    result = "http://"+localIp+":"+localPort+path1+"/" + "images/" + fileName;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	@ApiOperation(value="上传文件",notes="上传文件")
	public void fileUpload2(@RequestParam CommonsMultipartFile file,
			HttpServletRequest request,PrintWriter pw) throws IOException {
	        String filePath1 =uploadFile(file, request); 
	        pw.write(filePath1);
	}
	
	 private String uploadFile(MultipartFile file, HttpServletRequest request) throws IOException {  
	        String fileName = file.getOriginalFilename();  
	        String path=request.getSession().getServletContext().getRealPath("images/");  
	        File tempFile = new File(path, new Date().getTime() + String.valueOf(fileName));  
	        System.out.println("tempFIle " + tempFile);
	        if (!tempFile.getParentFile().exists()) {  
	            tempFile.getParentFile().mkdir();  
	        }  
	        if (!tempFile.exists()) {  
	            tempFile.createNewFile();  
	        }  
	        file.transferTo(tempFile);  
	        String localIp=request.getLocalAddr();//锟斤拷取锟斤拷锟斤拷ip
			int localPort=request.getLocalPort();//锟斤拷取锟斤拷锟截的端匡拷
		
			String path1 = request.getSession().getServletContext().getContextPath();
			System.out.println("ip " + localIp + " port" + localPort + " path "+path1 );
			String result = "http://"+localIp+":"+localPort+path1+"/" + "images/" + tempFile.getName();
			System.out.println("result " + result);
	        return result;  
	    } 
	@RequestMapping(value = "/evaluate", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="评价",notes="评价")
	public ProtocolBean shopComment(
			@ApiParam(value="用户ID")
			@RequestParam int userId,
			@ApiParam(value="商品参数:[{\"shopId\":12,\"evaluateContent\":\"内容\",\"evaluateGrade(评论星级，几颗星)\"：1,\"evaluatePictureUrl\":\"图片地址（多个逗号隔开）\"}]")
			@RequestParam String shopParam
			){
		List<Comment> comments = JSON.parseArray(shopParam, Comment.class);
		return person.addComment(userId,comments);
	}
	
	@RequestMapping(value = "/reasonforreturn", method = RequestMethod.POST)
	@ResponseBody
	@ApiResponses({@ApiResponse(code=0,message="退货原因",response=ReturnReason.class)})
	@ApiOperation(value="退货原因",notes="退货原因")
	public ProtocolBean returnOrder(){
		return person.getReturnReason();
	}
	//返回的json加入注解
	@RequestMapping(value = "/detailsoftherefund", method = RequestMethod.POST)
	@ResponseBody
	@ApiResponses({@ApiResponse(response=ReturnDetail.class, code = 0, message = "返回订单详情"),
			@ApiResponse(response=ReturnDetail.class, code = 1, message = "返回订单详情")})
	@ApiOperation(value="退款详情",notes="退款详情")
	public ProtocolBean returnDetail(
			@ApiParam(value="用户ID")
			@RequestParam int userId,
			@ApiParam(value="商品ID")
			@RequestParam int shopId,
			@ApiParam(value="订单编号")
			@RequestParam String orderId
			){
		return person.getReturnDetail(userId, orderId, shopId);
	}
	
	@RequestMapping(value = "/personalcenter", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="个人中心",notes="个人中心")
	public ProtocolBean personCenter(
			@ApiParam(value="用户ID")
			@RequestParam int userId
			){
		System.out.println("userId " + userId);
		return person.personCenter(userId);
	}
	
	@RequestMapping(value = "/buyorderlist", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="我的订单",notes="我的订单")
	public ProtocolBean getMyOrder(
			@ApiParam(value="用户ID")
			@RequestParam int userId,
			@ApiParam(value="订单状态 0：全部 1：待付款 2：待发货 3待收货 4：待评价")
			@RequestParam int orderType,
			@ApiParam(value="页")
			@RequestParam int page, 
			@ApiParam(value="行")
			@RequestParam int rows
			){
		System.out.println("userId " + userId);
		return person.getMyOrder(userId, orderType, page, rows);
	}
	
	@RequestMapping(value = "/cancelorderform", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="取消、确认收货、删除订单",notes="取消、确认收货、删除订单")
	public ProtocolBean getMyOrder(
			@ApiParam(value="用户ID")
			@RequestParam int userId,
			@ApiParam(value="订单编号")
			@RequestParam String orderId,
			@ApiParam(value="状态 0：取消 1：确认 2：删除")
			@RequestParam int type
			){
		System.out.println("userId " + userId);
		return person.cancelDeleteOrder(userId, orderId, type);
	}
	
	@RequestMapping(value = "/lineitem", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="订单详情",notes="获取订单详情")
	public ProtocolBean getOrderDetail(
			@ApiParam(value="用户ID")
			@RequestParam int userId,
			@ApiParam(value="订单编号")
			@RequestParam String orderId
			){
		System.out.println("userId " + userId);
		return person.getOrderDetail(orderId, userId);
	}
	@RequestMapping(value = "/returnsforrefund", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="退款退货",notes="退款退货")
	public ProtocolBean getOrderDetail(
			@ApiParam(value="用户ID")
			@RequestParam int userId
			){
		System.out.println("userId " + userId);
		return person.getReturnList(userId);
	}
}
