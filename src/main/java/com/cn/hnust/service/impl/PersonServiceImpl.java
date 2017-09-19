package com.cn.hnust.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.cn.hnust.dao.CommentMapper;
import com.cn.hnust.dao.OrderProductMapper;
import com.cn.hnust.dao.ProductMapper;
import com.cn.hnust.dao.ReturnOrderMapper;
import com.cn.hnust.dao.ReturnReasonMapper;
import com.cn.hnust.dao.ShopOrderMapper;
import com.cn.hnust.dao.UserInfoMapper;
import com.cn.hnust.pojo.AdminReturnDetail;
import com.cn.hnust.pojo.Comment;
import com.cn.hnust.pojo.MyOrder;
import com.cn.hnust.pojo.OrderDetail;
import com.cn.hnust.pojo.OrderProduct;
import com.cn.hnust.pojo.ProtocolBean;
import com.cn.hnust.pojo.ReturnDetail;
import com.cn.hnust.pojo.ReturnOrder;
import com.cn.hnust.pojo.ReturnProduct;
import com.cn.hnust.pojo.ReturnReason;
import com.cn.hnust.pojo.ShopOrder;
import com.cn.hnust.pojo.UserInfo;
import com.cn.hnust.pojo.UserPerson;
import com.cn.hnust.service.BaseService;
import com.cn.hnust.service.IPersonService;

@Service
public class PersonServiceImpl extends BaseService implements IPersonService {
	public static final String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCPir1asRSw5AsmZp7wFo3cKcx8MA3ds+iji0IaUfLUzYGlSzwPCfiWPAN50xBd9L53AoMtQTj8F6EbIbiv8qr38SxI0EwQmuyUPnZIZt5MMyAcacFfIVfLSAl1zR1ObTSk+Awi9Ksh5/BYzhqPDQFVLART7lOv+mHfezcxFDPgnlQ3f0uM8nyC3ec/gDLPf/Bs4zOHSc3xPL41kRmnpchc8d8kcYzV43RGqDj1xtjygcTJDnjmT8exhI4pqGmMKcN5N1e7veMwVhQIU2ktU3d7z0GtM3KbsuGWL2d0LZimeiAa3FcvWH0CqC+nLweSGtphgVPLVGMP25LT8gVdSNRPAgMBAAECggEAE4c4g6IbwoUDz1GexeMLoaw+GZcgC81yFO3ZLGOBudnLjYVSwmkE3MuFXYmNbHrjfqVk2z9IVWORk3NfAPDuuZSiBbXw40FwdiqZRPhZvdwmvjbVC/ApVYqTXuds2UKaKudkH01SCubXF/3SNyv5xnCaliCSWhqiPhkxRQgOlrw+oJT7IKrjazSyuLQzFjhWuya06zWoUSiWMWv0PKFtOqn9p7NAmZIrdm9xfzeZadGUXteq2YStIOve15M+Dpfnrjc5dgcCuhxgZzovSC1IyWukrRSqfPWgaYrjmke/mYXpMztepnlQHhKFW82Ed/rcn7inLcnl7cIgM2lmmd/7oQKBgQDj03p2InqCfeYrlr8PUCMwvmBIlFhOQZ/ivIT7NmxWoUVbHgasMVFnTr7zEwCaUaXVr4ucAOlLZUR7OagPgRmyMQJEah9K+OZJO/PCYpuh/5OoYGsenQNvb4Ilvq3TL2GoydaMKJfobNAEb+0fr5VIESgGz6lMKJ8kCeKm6rzf5QKBgQChSv4uQyHWPbM6Lu4NCEeWz1EvkvhJ2v/9wVdMQCLD9n/UfHT5xXPuK9rNyp/d4lo0r7vHnWc2py69C7wy8Qzmug4JLRVxQBN+5OCOQtWqzGKzzzaYoNmObY8iF3wKzEVLS9aJKiCFNo7pObOZZkYK1PatKDL2/CMcDakYS4nYIwKBgQC6qcrsf9Ngl0a+8AQax84NYe4BiYnQlHQrkcpjCXQ6hmgM/8z3yHCp9Br/jdIwnjUBn5MDfrumypIRZGwOR/iFxyHUbB63jrcfyb8uxRw+3uhcTKN3sa3e374Crvg8z9V0NetTau8LwBuvhwUBsRresS/aZMqRW7cIEBFkMXAPAQKBgAgiSQk6N3WfbO9tOHINzdZsJBL7HWxUD/7TBj0BKv+o6a9ki12hOIR1T7Z7Fm7RIG1xw02AwZi++5trARWslFL6ZQTPcjpg2drXXf+unSnc3slklMNDsVT+b25vssC0pnXyoCIQrs0pADIAx+7fen7HWJ1I3rEX7AlZIwLnpE7zAoGAV5NWBNOhNrYCKVHzAOcgtuCYbEbftK0+qMn9WKgCfaVePeAQnd2FvzIFTVqp5TdDtprJDF8Q2HtWLzDnbesO/xUwZQg9B5TIjD07lAXW4P6tfVnxgh76GBMTbXzG+j4/+x8QsY+UhukADe+feuKC+8wSbo4jO0i9d1ahcWazVFo=";
	public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArJAH9xOIiBeamFAKHt+/58JBTno0SMNY7S5CG+weQGDhcVnDY37niuPR/OjKOtj59yZnwFijG9RMktblkLjzso8n/NyjR+ipwLHler6/6FQ5ZbmV6LPU6zauhlXIhBxPoFzBYkxGsjgqpJZbQOwXPm0J5TlQ7/WRZLblf9gTr++Izv6it/HPJQT1izxcwW3X87ak4y8vgyWFoz5nzU8MDcwtWV/OK14hl9fA+mIa350qNoKQy3mZeI6cTYx90eaOfOrwHclIOvQV41APFqa2poRA6wQBBrcPqdNRRON1CDrPsYQLFrTZCyJ48FRnwcljJAPIYuCdVI11AxIjViJjvwIDAQAB";
	
	@Resource
	ReturnOrderMapper returnOrderMapper;
	@Resource
	ShopOrderMapper shopOrder;
	@Resource
	UserInfoMapper user;
	@Resource
	OrderProductMapper orderProduct;
	@Resource
	ReturnReasonMapper returnReason;
	@Resource
	CommentMapper comment;
	@Resource
	ProductMapper productMapper;
	private String getReturnNo() {
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);
		System.out.println("key " + key);
		Random r = new Random();
		key = key + r.nextInt();
		System.out.println("key + radom " + key);
		key = key.substring(0, 15);
		System.out.println("key " + key);
		return key;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ProtocolBean submitReturn(ReturnOrder returnOrder) {
		// TODO Auto-generated method stub
		returnOrder.setReturnnum(getReturnNo());
		int result1 = returnOrderMapper.insertSelective(returnOrder);
		int result = orderProduct.updateReturnShop(returnOrder.getOrdernum(), returnOrder.getShopid());
		if (result == 0 || result1 == 0) {
			return genarateProtocol(null, null, "申请退款失败", 1);
		} else {
			return genarateProtocol(null, null, "申请退款成功", 0);
		}
	}

	@Override
	public ProtocolBean personCenter(int userId) {
		// TODO Auto-generated method stub
		UserInfo userInfo = user.selectByPrimaryKey(userId);
		int unPay = shopOrder.computeUnPayCount(userId);
		int unComment = shopOrder.computeUnCommentCount(userId);
		int unRecieve = shopOrder.computeUnRecieveCount(userId);
		int unSend = shopOrder.computeUnSendCount(userId);
		int returnCount = returnOrderMapper.returnNum(userId);

		Map<String, Object> map = new HashMap<>();
		map.put("userHeadUrl", userInfo.getHeadportrait());
		map.put("userNickname", userInfo.getNickname());
		map.put("userGrade", userInfo.getUserGrade());
		map.put("userTelephone", userInfo.getPhonenumber());
		map.put("obligationNumber", unPay);
		map.put("overhangNumber", unSend);
		map.put("waitforreceivingNumber", unRecieve);
		map.put("waitevaluateNumber", unComment);
		map.put("refundNumber", returnCount);
		System.out.println("unPay " + unPay + " unComment " + unComment + " unRecieve " + unRecieve + " unSend "
				+ unSend + " returnCount " + returnCount);
		ProtocolBean bean = new ProtocolBean();
		bean.setData(map);
		bean.setMsg("获取个人信息成功");
		bean.setResultCode(0);
		return bean;
	}

	@Override
	public ProtocolBean getMyOrder(int userId, int orderType, int page, int rows) {
		// TODO Auto-generated method stub
		List<MyOrder> orders = shopOrder.selectOrderDetail(userId, orderType, page, rows);
		return genarateProtocol("orderList", orders, "获取我的订单", 0);
	}

	@Override
	public ProtocolBean cancelDeleteOrder(int userId, String orderId, int type) {
		// TODO Auto-generated method stub
		ProtocolBean bean = null;
		if (type == 0) {
			int result = shopOrder.deleteByOrderNumAndUserId(userId, orderId);
			if (result <= 0) {
				bean = genarateProtocol(null, null, "取消订单失败", 0);
			} else {
				bean = genarateProtocol(null, null, "取消订单成功", 0);
			}
		} else if (type == 1) {
			ShopOrder order = new ShopOrder();
			order.setOrdernum(orderId);
			order.setUserid(userId);
			order.setOrderType(4);
			int result = shopOrder.updateByOrderNumAndUserIdSelective(order);
			if (result <= 0) {
				bean = genarateProtocol(null, null, "确认订单失败", 0);
			} else {
				bean = genarateProtocol(null, null, "确认订单成功", 0);
			}
		} else if (type == 2) {
			int result = shopOrder.deleteByOrderNumAndUserId(userId, orderId);
			if (result <= 0) {
				bean = genarateProtocol(null, null, "删除订单失败", 0);
			} else {
				bean = genarateProtocol(null, null, "删除订单成功", 0);
			}
		}
		return bean;
	}

	@Override
	public ProtocolBean getOrderDetail(String orderNum, int userId) {
		// TODO Auto-generated method stub
		OrderDetail detail = shopOrder.getOrderDetail(orderNum, userId);
		ProtocolBean bean = new ProtocolBean();
		if (detail.getOrdernum() != null) {

			bean.setData(detail);
			bean.setMsg("获取订单成功");
			bean.setResultCode(0);
		} else {
			bean.setData(null);
			bean.setMsg("获取订单失败");
			bean.setResultCode(0);
		}
		return bean;
	}

	@Override
	public ProtocolBean getReturnList(int userId) {
		// TODO Auto-generated method stub
		List<ReturnProduct> product = returnOrderMapper.getReturnList(userId);
		for (ReturnProduct ret : product) {
			if (ret.getOrderType() != 1) {
				ret.setOrderType(2);
			}
		}
		return genarateProtocol("orderList", product, "获取退款退货列表成功", 0);
	}

	@Override
	public ProtocolBean getReturnDetail(int userId, String orderNum, int shopId) {
		// TODO Auto-generated method stub
		ReturnDetail detail = returnOrderMapper.returnDetail(userId, orderNum, shopId);
		ProtocolBean bean = new ProtocolBean();
		bean.setData(detail);
		bean.setMsg("获取退单详情成功");
		bean.setResultCode(0);
		return bean;
	}

	@Override
	public ProtocolBean getReturnReason() {
		// TODO Auto-generated method stub
		List<ReturnReason> list = returnReason.selectAll();
		return genarateProtocol("reasonList", list, "获取退货原因成功", 0);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ProtocolBean addComment(int userId, List<Comment> comments) {
		// TODO Auto-generated method stub
		for (Comment comm : comments) {
			comm.setUserid(userId);
			comment.insertSelective(comm);
		}
		return genarateProtocol(null, null, "评论成功", 0);
	}

	@Override
	public ProtocolBean getPersonInfo(int userId) {
		// TODO Auto-generated method stub
		UserPerson person = user.selectPersonByPrimaryKey(userId);
		ProtocolBean bean = new ProtocolBean();
		bean.setData(person);
		bean.setMsg("获取个人资料成功");
		bean.setResultCode(0);
		return bean;
	}

	@Override
	public ProtocolBean updatePersonInfo(UserPerson info) {
		// TODO Auto-generated method stub
		int result = user.updatePersonByPrimaryKeySelective(info);
		return genarateProtocol(null, null, "更新资料成功", 0);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ProtocolBean returnPay(String returnNo) {
		
		ReturnOrder returnOr = returnOrderMapper.selectByReturnNum(returnNo);
		HashMap<String, Object> returnOr1 = returnOrderMapper.selectMapByReturnNum(returnNo);
//		String orderNum = returnOr.getOrdernum();
//		Double money = returnOr.getRefundprice();
		//测试可以使用hashmap作为返回值
		String orderNum = (String) returnOr1.get("orderNum");
		Double money = (Double) returnOr1.get("refundPrice");
		Double shopTotal = (Double) returnOr1.get("shopTotalPrices");
		int shopId = (int) returnOr1.get("shopId");
		Double sumofSalesPrice = (Double) returnOr1.get("sumofSalesPrice");
		Double rentalPrice = (Double) returnOr1.get("rentalPrice");
		System.out.println("orderNum " + orderNum + " shopTotal " + shopTotal+
				" sumofSalesPrice " + sumofSalesPrice + " rentalPrice " + rentalPrice);
		
		if(Double.compare(shopTotal, money) < 0){
			return genarateProtocol(null, null, "所有的钱已经退款", -1);
		}
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do",
				"2016090900474503", APP_PRIVATE_KEY, "json", "utf-8", ALIPAY_PUBLIC_KEY, "RSA2");

		AlipayTradeRefundRequest request1 = new AlipayTradeRefundRequest();
		request1.setBizContent("{" + "\"out_trade_no\":\""+ orderNum +"\","
				+  "\"refund_amount\":" + money + ","
				+ "\"refund_reason\":\"正常退款\"," + "\"operator_id\":\"OP001\","
				+ "\"out_request_no\":\""+ returnNo + "\","
				+ "\"store_id\":\"NJ_S_001\"," + "\"terminal_id\":\"NJ_T_001\"" + "  }");
		AlipayTradeRefundResponse response = null;
		try {
			response = alipayClient.execute(request1);			
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("response " + response.getBody() + " code " + response.getCode());
		ProtocolBean bean = new ProtocolBean();
		if (response.isSuccess()) {
			System.out.println("调用成功");
			bean.setMsg("退款成功");
			bean.setResultCode(0);
			//改变中间表的cancelState为2
			orderProduct.acceptReturnShop(orderNum, shopId);
			productMapper.updateShopCountByPrimaryKey(returnOr.getReturnCount(), returnOr.getShopid());
			returnOr.setCancelState(2);
			returnOr.setOrderType(2);
			System.out.println(returnOr.toString());
			int result1 = returnOrderMapper.updateByPrimaryKeySelective(returnOr);
			System.out.println(result1);
			ShopOrder shop = new ShopOrder();
			//设置订单号，因为需按照订单号查询
			shop.setOrdernum(orderNum);
			//变成待发货
//			shop.setOrderType(2);
			//改变订单总金额
			Double price1 = rentalPrice - money;
			shop.setRentalprice(price1);
			//改变商品总金额（不包含邮费）
			Double price2 = shopTotal - money;
			shop.setShopTotalPrices(price2);
			//改变商品总金额其实和Rentalprice是一样的,懒得改了
			Double price3 = sumofSalesPrice - money;
			System.out.println("price1 " + price1 + " price2 " + price2 + " price3 " + price3);
			shop.setSumofSalesPrice(price3);
			System.out.println(shop.toString());
			int result = shopOrder.updateByOrderNumSelective(shop);
			System.out.println(result);
		} else {
			System.out.println("调用失败");
			bean.setMsg("退款失败");
			bean.setResultCode(1);
		}
		return bean;
	}

	
	@Override
	public List<AdminReturnDetail> returnList(int page,int rows) {
		// TODO Auto-generated method stub
		page = page * rows;
		return returnOrderMapper.returnDetailAll(page,rows);
	}

	@Override
	public AdminReturnDetail returnOrderByNum(String returnNum) {
		// TODO Auto-generated method stub
		return returnOrderMapper.returnDetailByReturnNum(returnNum);
	}

	@Override
	public int returnCount() {
		// TODO Auto-generated method stub
		return returnOrderMapper.returnAllCount();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int refusePay(String ordernum,int shopid,String returnNo) {
		int result1 = 0;
		// TODO Auto-generated method stub
		orderProduct.refuseReturnShop(ordernum, shopid);
		ReturnOrder returnOr = returnOrderMapper.selectByReturnNum(returnNo);
		returnOr.setCancelState(3);
		System.out.println(returnOr.toString());
		result1 = returnOrderMapper.updateByPrimaryKeySelective(returnOr);
		return result1;
	}
}
