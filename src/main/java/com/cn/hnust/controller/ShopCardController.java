package com.cn.hnust.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.cn.hnust.pojo.Address;
import com.cn.hnust.pojo.ProductBean;
import com.cn.hnust.pojo.ProtocolBean;
import com.cn.hnust.pojo.ShopOrder;
import com.cn.hnust.service.IShopCardService;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/shopcard")
public class ShopCardController extends BaseController {
	public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArJAH9xOIiBeamFAKHt+/58JBTno0SMNY7S5CG+weQGDhcVnDY37niuPR/OjKOtj59yZnwFijG9RMktblkLjzso8n/NyjR+ipwLHler6/6FQ5ZbmV6LPU6zauhlXIhBxPoFzBYkxGsjgqpJZbQOwXPm0J5TlQ7/WRZLblf9gTr++Izv6it/HPJQT1izxcwW3X87ak4y8vgyWFoz5nzU8MDcwtWV/OK14hl9fA+mIa350qNoKQy3mZeI6cTYx90eaOfOrwHclIOvQV41APFqa2poRA6wQBBrcPqdNRRON1CDrPsYQLFrTZCyJ48FRnwcljJAPIYuCdVI11AxIjViJjvwIDAQAB";
	@Resource
	IShopCardService card;

	private String getOrderIdByUUId() {
		int machineId = 1;// 最大支持1-9个集群机器部署
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {// 有可能是负数
			hashCodeV = -hashCodeV;
		}
		// 0 代表前面补充0
		// 4 代表长度为4
		// d 代表参数为正数型
		return machineId + String.format("%015d", hashCodeV);
	}

	@RequestMapping(value = "/edityourcart", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "编辑购物车", notes = "编辑购物车列表")
	public ProtocolBean editeCard(@ApiParam(value = "用户id") @RequestParam int userId,
			@ApiParam(value = "商品ID") @RequestParam int ProductId,
			@ApiParam(value = "商品数量") @RequestParam int ProductNum) {
		return card.modifyProductCount(ProductId, ProductNum);
	}

	@RequestMapping(value = "/sendpush", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "推送消息", notes = "推送消息")
	public ProtocolBean sendPush(@ApiParam(value = "用户id") @RequestParam int userId,
			@ApiParam(value = "消息内容") @RequestParam String msg,
			@ApiParam(value = "状态 1：系统消息 2：金额提现 3：返利金额 4：商品") @RequestParam int msgType) {
		return card.sendPushForUser(userId, msg, msgType);
	}

	@RequestMapping(value = "/systemMessages", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "系统消息", notes = "系统消息")
	public ProtocolBean getSystemMsg(@ApiParam(value = "用户id") @RequestParam int userId) {
		return card.getUserMsg(userId);
	}

	@RequestMapping(value = "/numberofmessage", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "消息数量", notes = "消息数量")
	public ProtocolBean getMsgCount(@ApiParam(value = "用户id") @RequestParam int userId) {
		return card.getCountUserMsg(userId);
	}

	@RequestMapping(value = "/startTimer", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "开启定时器", notes = "测试定时器")
	public ProtocolBean startTimer(@ApiParam(value = "订单编号") @RequestParam String orderNum) {
		return card.startTimer(orderNum);
	}

	@RequestMapping(value = "/closeTimer", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "测试关闭定时器", notes = "测试关闭定时器")
	public ProtocolBean closeTimer(@ApiParam(value = "订单编号") @RequestParam String orderCode) {
		return card.closeTimer(orderCode);
	}

	@RequestMapping(value = "/notifyPay", method = RequestMethod.POST)
	public void notifyPay(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("doPost**********************");
		Map<String, String> params = new HashMap<String, String>();
		String out_trade_no = request.getParameter("out_trade_no");
		String trade_no = request.getParameter("trade_no");
		System.out.println("doPost out_trade_no " + out_trade_no + " trade_no " + trade_no);
		Map requestParams = request.getParameterMap();
		System.out.println("doPost map " + requestParams.toString());
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			System.out.println("name " + name + ",valueStr " + valueStr);
			// 乱码解决，这段代码在出现乱码时使用。
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		// 切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
		// boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String
		// publicKey, String charset, String sign_type)
		boolean flag = false;
		try {
			flag = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, "utf-8", "RSA2");
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (flag) {			
				card.changeOrderType(2, out_trade_no);
				response.getWriter().write("success");
			} else {
				response.getWriter().write("failure");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/payment", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "支付", notes = "支付")
	public ProtocolBean payMoney(@ApiParam(value = "状态 1:支付宝支付 2:微信支付 3:帐户余额支付") @RequestParam int payType,
			@ApiParam(value = "订单编号") @RequestParam String orderCode,
			@ApiParam(value = "支付金额") @RequestParam Double payMoney,
			@ApiParam(value = "支付密码") @RequestParam Double payPassword) {
		System.out.println("money " + payMoney);
		return card.payOrder(orderCode, payMoney);
	}

	// 如果不添加@RequestParam,直接写一个封装类和拆开写封装类里面的属性值效果是一样的.
	// 如果想要这个参数封装类能够展现到swagger接口文档中，必须添加@ModelAttribute，然后再封装类的
	// 属性前面添加@ApiModelProperty(value = "用户地址id", required = true)解释描述每个属性
	// 单个参数如果不加@RequestParam参数也是无法显示到swagger接口文档中的
	@RequestMapping(value = "/addoreditaddress", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "编辑地址", notes = "编辑用户地址")
	public ProtocolBean modifyAddress(@ModelAttribute Address addr,
			@ApiParam(value = "状态 0：编辑 1：新增") @RequestParam int type) {
		ProtocolBean bean = null;
		if (type == 1) {
			bean = card.addAddress(addr);
		} else if (type == 0) {
			bean = card.modifyAddress(addr);
		}
		return bean;
	}

	@RequestMapping(value = "/confirmanorder", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "确认订单", notes = "确认订单")
	public ProtocolBean confirmanOrder(@ApiParam(value = "用户ID") @RequestParam int userId,
			@ApiParam(value = "商品参数:[{\"ProductId\":12,\"ProductNum\":123}]") @RequestParam String shopParameter) {
		List<ProductBean> list = JSON.parseArray(shopParameter, ProductBean.class);
		System.out.println(list.toString());
		return card.confirmOrder(userId, list, 0);
	}

	@RequestMapping(value = "/generateindent", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "立即下单", notes = "立即下单")
	public ProtocolBean generateOrder(@ApiParam(value = "用户ID") @RequestParam int userId,
			@ApiParam(value = "订单商品详情，value类型[{\"ProductId\":12,\"ProductNum\":123}]") @RequestParam String indentDetails,
			@ApiParam(value = "用户地址ID") @RequestParam int userAddreessId,
			@ApiParam(value = "总额") @RequestParam String rentalPrice,
			@ApiParam(value = "买家留言") @RequestParam String buyerMessage) {
		String orderNum = getOrderIdByUUId();
		System.out.println(orderNum);
		ShopOrder order = new ShopOrder();
		order.setBuyermessage(buyerMessage);
		order.setOrdernum(orderNum);
		order.setRentalprice(Double.valueOf(rentalPrice));
		order.setUseraddreessid(userAddreessId);
		order.setUserid(userId);
		List<ProductBean> products = JSON.parseArray(indentDetails, ProductBean.class);
		System.out.println(products.toString());
		return card.generateOrder(order, products);
	}

	@RequestMapping(value = "/takelocationlist", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "获取用户地址", notes = "通过用户id获取用户地址")
	public ProtocolBean getAddress(@ApiParam(value = "userID") @RequestParam int userId,
			@ApiParam(value = "页数") @RequestParam int page, @ApiParam(value = "每页有多少行") @RequestParam int rows) {
		String id = getOrderIdByUUId();
		System.out.println(id);
		return card.getAllAddress(userId, page, rows);
	}

	@RequestMapping(value = "/deletelocation", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "删除、设置地址", notes = "删除、设置地址")
	public ProtocolBean editAddress(@ApiParam(value = "userID") @RequestParam int userId,
			@ApiParam(value = "地址ID") @RequestParam int userAddreessId,
			@ApiParam(value = "状态 0：删除 1：设置成默认") @RequestParam int userAddreessType) {
		ProtocolBean bean = null;
		if (userAddreessType == 1) {
			bean = card.setAddress(userId, userAddreessId);
		} else if (userAddreessType == 0) {
			bean = card.deleteAddress(userAddreessId);
		}
		return bean;
	}

	@RequestMapping(value = "/deleteyourcart", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "删除购物车商品", notes = "删除购物车商品")
	public ProtocolBean deleteCard(@ApiParam(value = "userID 用户id") @RequestParam int userId,
			@ApiParam(value = "商品ID（多个逗号隔开）") @RequestParam String ProductId) {
		return card.deleteProduct(userId,ProductId);
	}

	@RequestMapping(value = "/shoppingcartlist", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "获取购物车", notes = "获取购物车列表")
	public ProtocolBean getFirstPage(@ApiParam(value = "用户id") @RequestParam int userId,
			@ApiParam(value = "页数") @RequestParam int page, @ApiParam(value = "每页所显示的行数") @RequestParam int rows) {
		System.out.println("page " + page + " rows " + rows);
		return card.getShopCard(userId, page, rows);
	}
}
