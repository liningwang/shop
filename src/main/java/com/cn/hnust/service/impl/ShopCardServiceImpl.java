package com.cn.hnust.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.cn.hnust.dao.AddressMapper;
import com.cn.hnust.dao.OrderProductMapper;
import com.cn.hnust.dao.ProductMapper;
import com.cn.hnust.dao.ShopOrderMapper;
import com.cn.hnust.dao.UserInfoMapper;
import com.cn.hnust.dao.UserProductMapper;
import com.cn.hnust.dao.userMsgMapper;
import com.cn.hnust.pojo.Address;
import com.cn.hnust.pojo.AdminOrderDetail;
import com.cn.hnust.pojo.ConfirmOrder;
import com.cn.hnust.pojo.OrderOuterParam;
import com.cn.hnust.pojo.OrderProduct;
import com.cn.hnust.pojo.Product;
import com.cn.hnust.pojo.ProductBean;
import com.cn.hnust.pojo.ProtocolBean;
import com.cn.hnust.pojo.ShopCard;
import com.cn.hnust.pojo.ShopOrder;
import com.cn.hnust.pojo.UserInfo;
import com.cn.hnust.pojo.UserProduct;
import com.cn.hnust.pojo.userMsg;
import com.cn.hnust.service.BaseService;
import com.cn.hnust.service.IShopCardService;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
@Service
public class ShopCardServiceImpl extends BaseService implements IShopCardService{

	public static final String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCPir1asRSw5AsmZp7wFo3cKcx8MA3ds+iji0IaUfLUzYGlSzwPCfiWPAN50xBd9L53AoMtQTj8F6EbIbiv8qr38SxI0EwQmuyUPnZIZt5MMyAcacFfIVfLSAl1zR1ObTSk+Awi9Ksh5/BYzhqPDQFVLART7lOv+mHfezcxFDPgnlQ3f0uM8nyC3ec/gDLPf/Bs4zOHSc3xPL41kRmnpchc8d8kcYzV43RGqDj1xtjygcTJDnjmT8exhI4pqGmMKcN5N1e7veMwVhQIU2ktU3d7z0GtM3KbsuGWL2d0LZimeiAa3FcvWH0CqC+nLweSGtphgVPLVGMP25LT8gVdSNRPAgMBAAECggEAE4c4g6IbwoUDz1GexeMLoaw+GZcgC81yFO3ZLGOBudnLjYVSwmkE3MuFXYmNbHrjfqVk2z9IVWORk3NfAPDuuZSiBbXw40FwdiqZRPhZvdwmvjbVC/ApVYqTXuds2UKaKudkH01SCubXF/3SNyv5xnCaliCSWhqiPhkxRQgOlrw+oJT7IKrjazSyuLQzFjhWuya06zWoUSiWMWv0PKFtOqn9p7NAmZIrdm9xfzeZadGUXteq2YStIOve15M+Dpfnrjc5dgcCuhxgZzovSC1IyWukrRSqfPWgaYrjmke/mYXpMztepnlQHhKFW82Ed/rcn7inLcnl7cIgM2lmmd/7oQKBgQDj03p2InqCfeYrlr8PUCMwvmBIlFhOQZ/ivIT7NmxWoUVbHgasMVFnTr7zEwCaUaXVr4ucAOlLZUR7OagPgRmyMQJEah9K+OZJO/PCYpuh/5OoYGsenQNvb4Ilvq3TL2GoydaMKJfobNAEb+0fr5VIESgGz6lMKJ8kCeKm6rzf5QKBgQChSv4uQyHWPbM6Lu4NCEeWz1EvkvhJ2v/9wVdMQCLD9n/UfHT5xXPuK9rNyp/d4lo0r7vHnWc2py69C7wy8Qzmug4JLRVxQBN+5OCOQtWqzGKzzzaYoNmObY8iF3wKzEVLS9aJKiCFNo7pObOZZkYK1PatKDL2/CMcDakYS4nYIwKBgQC6qcrsf9Ngl0a+8AQax84NYe4BiYnQlHQrkcpjCXQ6hmgM/8z3yHCp9Br/jdIwnjUBn5MDfrumypIRZGwOR/iFxyHUbB63jrcfyb8uxRw+3uhcTKN3sa3e374Crvg8z9V0NetTau8LwBuvhwUBsRresS/aZMqRW7cIEBFkMXAPAQKBgAgiSQk6N3WfbO9tOHINzdZsJBL7HWxUD/7TBj0BKv+o6a9ki12hOIR1T7Z7Fm7RIG1xw02AwZi++5trARWslFL6ZQTPcjpg2drXXf+unSnc3slklMNDsVT+b25vssC0pnXyoCIQrs0pADIAx+7fen7HWJ1I3rEX7AlZIwLnpE7zAoGAV5NWBNOhNrYCKVHzAOcgtuCYbEbftK0+qMn9WKgCfaVePeAQnd2FvzIFTVqp5TdDtprJDF8Q2HtWLzDnbesO/xUwZQg9B5TIjD07lAXW4P6tfVnxgh76GBMTbXzG+j4/+x8QsY+UhukADe+feuKC+8wSbo4jO0i9d1ahcWazVFo=";
	public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArJAH9xOIiBeamFAKHt+/58JBTno0SMNY7S5CG+weQGDhcVnDY37niuPR/OjKOtj59yZnwFijG9RMktblkLjzso8n/NyjR+ipwLHler6/6FQ5ZbmV6LPU6zauhlXIhBxPoFzBYkxGsjgqpJZbQOwXPm0J5TlQ7/WRZLblf9gTr++Izv6it/HPJQT1izxcwW3X87ak4y8vgyWFoz5nzU8MDcwtWV/OK14hl9fA+mIa350qNoKQy3mZeI6cTYx90eaOfOrwHclIOvQV41APFqa2poRA6wQBBrcPqdNRRON1CDrPsYQLFrTZCyJ48FRnwcljJAPIYuCdVI11AxIjViJjvwIDAQAB";
    protected static final String APP_KEY ="671487ea1e2e6bdc95c9c8a1";
    protected static final String MASTER_SECRET = "41487b01817b404b2383c64b";
	@Resource
	ProductMapper product;
	@Resource
	UserProductMapper userProduct;
	@Resource
	AddressMapper address;
	@Resource
	ShopOrderMapper orderMappper;
	@Resource
	OrderProductMapper orderProduct;
	@Resource
	UserInfoMapper user;
	@Resource
	userMsgMapper userMsgMapper;
	@Override
	public ProtocolBean getShopCard(int userId, int page, int rows) {
		// TODO Auto-generated method stub
		List<ShopCard> card = product.selectByUserIdLimit(userId, page, rows);
		Map<String,Object> map = new HashMap<>();
		map.put("shopCartList", card);
		ProtocolBean bean = new ProtocolBean();
		bean.setData(map);
		bean.setMsg("获取购物车成功");
		bean.setResultCode(0);
		return bean;
	}

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
	
	public ProtocolBean payOrder(String orderCode,Double money){
		ShopOrder order = orderMappper.getOrderByOrderNum(orderCode);
//		System.out.println(order + " order.getSumofSalesPrice() " + order.getSumofSalesPrice());
		if(order == null) {
			return genarateProtocol(null, null, "订单号已经失效", 1);
		}
		//		orderMappper.closeTimer(orderCode);
		//确认客户端和服务端的订单金额是否一致，防止由于各种原因订单金额变了。
		//如果直接用订单金额支付，可能会产生，一些人把数据库中的订单金额改变了，如果直接付款就不对了。所以
		//和客户端发送过来的金额做一个对比。
		if(money.compareTo(order.getSumofSalesPrice()) != 0) {
			return genarateProtocol(null, null, "订单金额和付款金额不一致，请确认", 1);
		}
		orderMappper.deleteTimer(orderCode);
//		String outtradeno = getOrderIdByUUId();
		String result = procutOrder(orderCode,money);
		ProtocolBean bean = new ProtocolBean();
		bean.setData(result);
		bean.setMsg("签名订单");
		bean.setResultCode(0);
		return bean;
	}
	private String procutOrder(String orderCode,Double money) {
		String result = null;
	//	String outtradeno = getOrderIdByUUId();
		// 实例化客户端
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do",
				"2016090900474503", APP_PRIVATE_KEY, "json", "utf-8", ALIPAY_PUBLIC_KEY, "RSA2");

		// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request1 = new AlipayTradeAppPayRequest();
		// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody("商品支付");
		model.setSubject("商品支付详情");
		model.setOutTradeNo(orderCode);
		model.setTimeoutExpress("30m");
		model.setTotalAmount(money+"");
		model.setProductCode("QUICK_MSECURITY_PAY");
		request1.setBizModel(model);
		request1.setNotifyUrl("http://114.215.46.63/Test/shopcard/notifyPay");

		try {
			// 这里和普通的接口调用不同，使用的是sdkExecute
			AlipayTradeAppPayResponse response1 = alipayClient.sdkExecute(request1);
			System.out.println(response1.getBody());// 就是orderString
			// 可以直接给客户端请求，无需再做处理。
			result = response1.getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ProtocolBean deleteProduct(int userId,String id) {
		// TODO Auto-generated method stub
		String[] ids = id.split(",");
		for(int i = 0;i < ids.length;i++) {
			System.out.println("userId" + userId +"id " + ids[i]);
			int result = userProduct.deleteByPrimaryKey(Integer.valueOf(ids[i]),userId);
			System.out.println("result "+result);
		}
		return genarateProtocol(null, null, "删除商品成功", 0);
	}
	@Override
	public ProtocolBean modifyProductCount(int id, int count) {
		// TODO Auto-generated method stub
		UserProduct pro = new UserProduct();
		pro.setId(id);
		pro.setShopnumber(count);
		userProduct.updateByPrimaryKeySelective(pro);
		return genarateProtocol(null, null, "商品修改成功", 0);
	}


	@Override
	public ProtocolBean addAddress(Address addr) {
		// TODO Auto-generated method stub
		address.insertSelective(addr);
		return genarateProtocol(null, null, "添加地址成功", 0);
	}


	@Override
	public ProtocolBean modifyAddress(Address addr) {
		// TODO Auto-generated method stub
		address.updateByPrimaryKeySelective(addr);
		return genarateProtocol(null, null, "修改地址成功", 0);
	}


	@Override
	public ProtocolBean getAllAddress(int userId, int page, int rows) {
		// TODO Auto-generated method stub
		int index = page * rows;
		List<Address> addrs = address.selectByUserId(userId, index, rows);
		
		return genarateProtocol("userLocationList",addrs,"获取地址成功",0);
	}


	@Override
	public ProtocolBean setAddress(int userId, int userAddreessId) {
		// TODO Auto-generated method stub
		Address addr = new Address();
		addr.setUseraddreessid(userAddreessId);
		addr.setUseristype(1);
		address.updateByPrimaryKeySelective(addr);
		return genarateProtocol(null, null, "设置默认地址成功", 0);
	}


	@Override
	public ProtocolBean deleteAddress(int userAddreessId) {
		// TODO Auto-generated method stub
		address.deleteByPrimaryKey(userAddreessId);
		return genarateProtocol(null, null, "删除地址成功", 0);
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ProtocolBean generateOrder(ShopOrder order,List<ProductBean> products) {
		// TODO Auto-generated method stub
		for(ProductBean bean : products) {
			Product pro = product.selectByPrimaryKey(bean.getProductId());
			System.out.println("generateOrder id " + pro.getShopid() + " shopCount " 
					+ pro.getShopCount() + " producntNum " + bean.getProductNum());
			if(pro.getShopCount() < bean.getProductNum()) {
				Map<String,Object> map = new HashMap<>();
				map.put("shopId", bean.getProductId());
				ProtocolBean aa = new ProtocolBean();
				aa.setData(map);
				aa.setMsg("库存不足");
				aa.setResultCode(0);
				return aa;
			}
		}
	
		List<Product> list = new ArrayList<>();
		OrderOuterParam param = computeTotalMoney(products, list);
		//下单的时候，校验客户端发过来的总金额和通过商品计算出来的总金额。
		//不能直接使用客户发过来的总金额，不安全，因为金额可能是伪造的
		//不能直接使用商品计算出来的价格，因为商品的价格可能会在后台进行改动，这个改动需要让用户知道,不然用户会看到确认订单的时候是一个金额数
		//当下单后这个金额数又变了。
		if(param.getTotalMoney().compareTo(order.getRentalprice()) != 0) {
			System.out.println(" total " + param.getTotalMoney() + " rentalPrice " + order.getRentalprice());
			return confirmOrder(order.getUserid(), products,1);
		}
		orderMappper.timerMy(order.getOrdernum());
		orderMappper.setTimer();
		order.setSumofSalesPrice(param.getTotalMoney());
		order.setExpressagePrices(param.getShopfreight());
		order.setShopTotalPrices(param.getTotalMoney() - param.getShopfreight());
		orderMappper.insertSelective(order);
		System.out.println("orderId " + order.getOrderid());
		OrderProduct orderP = new OrderProduct();
		for(ProductBean bean : products) {
			System.out.println(bean.toString());
			orderP.setOrderid(order.getOrderid());
			orderP.setProductnum(bean.getProductNum());
			orderP.setShopid(bean.getProductId());
			orderP.setOrdernum(order.getOrdernum());
			//更新库存
			product.updateShopCountByPrimaryKey(-(bean.getProductNum()), bean.getProductId());
			//更新销量
//			product.updateShopsalesvolumeByPrimaryKey(bean.getProductNum(),bean.getProductId());
			orderProduct.insertSelective(orderP);
		}
		ProtocolBean proB = new ProtocolBean();
		Map<String,Object> map = new HashMap<>();
		map.put("indentId", order.getOrdernum());
		proB.setData(map);
		proB.setMsg("下单成功");
		proB.setResultCode(0);
		return proB;
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ProtocolBean confirmOrder(int userId, List<ProductBean> products,int code) {
		// TODO Auto-generated method stub
		Address addr = address.selectDefaultByUserId(userId);
		List<Product> list = new ArrayList<>();
		//让用户确认订单价格等信息。
		OrderOuterParam param = computeTotalMoney(products, list);
		UserInfo info = user.selectByPrimaryKey(userId);
		Double money = info.getMoney();
		
		ConfirmOrder  map = new ConfirmOrder();
		map.setUserAddreessId(addr.getUseraddreessid());
		map.setUserName(addr.getUsername());
		map.setUserTelephone(addr.getUsertelephone());
		map.setUserProvince(addr.getUserprovince());
		map.setUserCity(addr.getUsercity());
		map.setUserDistrict(addr.getUserdistrict());
		map.setUserDetailedAddress(addr.getUserdetailedaddress());
		map.setShopRental(param.getTotalMoney());
		map.setShopFreight(param.getShopfreight());
		map.setShopDeduction(money);
		map.setShopMessage(list);
		
		ProtocolBean bean = new ProtocolBean();
		bean.setData(map);
		if(code == 1) {
			bean.setMsg("总额不匹配，或者商品价格有更改");
		} else {
			bean.setMsg("确认订单");
		}
		bean.setResultCode(code);
		
		return bean;
	}
	private OrderOuterParam computeTotalMoney(List<ProductBean> products,List<Product> outList){
		OrderOuterParam out = new OrderOuterParam();
		Double total = 0.0;
		Product pro = null;
		int totalCount = 0;
		for(ProductBean bean:products){
			System.out.println(bean.toString());
			int count = bean.getProductNum();
			totalCount += count;
			pro = product.selectByPrimaryKey(bean.getProductId());
			outList.add(pro);
			Double price = pro.getShopprice();
			Double proPrice = count * price;
			total += proPrice;
		}
		if(totalCount > 30) {
			total = total + pro.getShopfreight() * 2;
			out.setTotalMoney(total);
			out.setShopfreight(pro.getShopfreight() * 2);
		} else {
			total = total + pro.getShopfreight();
			out.setTotalMoney(total);
			out.setShopfreight(pro.getShopfreight());
		}
		
		return out;
	}

	@Override
	public ProtocolBean startTimer(String orderNum) {
		// TODO Auto-generated method stub
		orderMappper.timerMy(orderNum);
		orderMappper.setTimer();
		return genarateProtocol(null, null, "成功", 0);
	}

	@Override
	public ProtocolBean closeTimer(String orderNum) {
		// TODO Auto-generated method stub
//		orderMappper.closeTimer(orderNum);
		orderMappper.deleteTimer(orderNum);
		return genarateProtocol(null, null, "成功", 0);
	}
	
	private ProtocolBean sendPush(int userId,String content){
		ClientConfig clientConfig = ClientConfig.getInstance();
        final JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, clientConfig);
        PushPayload payload = null;
        if(userId != 0) {
        	payload = buildPushObject_android_and_ios(String.valueOf(userId),content);
        } else {
        	payload = buildPushObject_android_and_ios_all(content);
        }
		try {
            PushResult result = jpushClient.sendPush(payload);
            
            System.out.println("Got result - " + result);
            System.out.println(result);
            return genarateProtocol(null, null, "发送消息成功", 0);
            // 如果使用 NettyHttpClient，需要手动调用 close 方法退出进程
            // If uses NettyHttpClient, call close when finished sending request, otherwise process will not exit.
            // jpushClient.close();
        } catch (APIConnectionException e) {
        	System.out.println("Connection error. Should retry later. "+e);
        	System.out.println("Sendno: " + payload.getSendno());
        	 return genarateProtocol(null, null, "发送消息失败", 1);
        } catch (APIRequestException e) {
        	System.out.println("Error response from JPush server. Should review and fix it. "+ e);
        	System.out.println("HTTP Status: " + e.getStatus());
        	System.out.println("Error Code: " + e.getErrorCode());
        	System.out.println("Error Message: " + e.getErrorMessage());
        	System.out.println("Msg ID: " + e.getMsgId());
        	System.out.println("Sendno: " + payload.getSendno());
        	return genarateProtocol(null, null, "发送消息失败", 1);
        }
	}
    public PushPayload buildPushObject_android_and_ios(String userId,String content) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(userId))
                .setNotification(Notification.newBuilder()
                		.setAlert(content)
                		.addPlatformNotification(AndroidNotification.newBuilder()
                				.setTitle("有新消息！！！")
                				.addExtra("extra_key", "extra_value")
                				.setBuilderId(2)
                				.build())
                		.addPlatformNotification(IosNotification.newBuilder()
                				.incrBadge(1)
                				.addExtra("extra_key", "extra_value").build())
                		.build())
                .build();
    }
    public PushPayload buildPushObject_android_and_ios_all(String content) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                		.setAlert(content)
                		.addPlatformNotification(AndroidNotification.newBuilder()
                				.setTitle("有新消息！！！")
                				.addExtra("extra_key", "extra_value")
                				.setBuilderId(2)
                				.build())
                		.addPlatformNotification(IosNotification.newBuilder()
                				.incrBadge(1)
                				.addExtra("extra_key", "extra_value").build())
                		.build())
                .build();
    }

	@Override
	public ProtocolBean sendPushForUser(int userId,String content,int msgType) {
		// TODO Auto-generated method stub
		ProtocolBean bean = sendPush(userId,content);
		if(bean.getResultCode() == 0){
			com.cn.hnust.pojo.userMsg msg = new com.cn.hnust.pojo.userMsg();
			msg.setMsg(content);
			msg.setUserid(userId);
			msg.setMessagePictureType(msgType);
			userMsgMapper.insertSelective(msg);
		}
		return bean;
	}

	@Override
	public ProtocolBean getUserMsg(int userId) {
		// TODO Auto-generated method stub
		List<userMsg> list = userMsgMapper.selectByUserId(userId);
		ProtocolBean bean = new ProtocolBean();
		bean.setMsg("获取用户消息成功");
		Map<String,Object> map = new HashMap<>();
		map.put("messageList", list);
		bean.setData(map);
		bean.setResultCode(0);
		return bean;
	}

	@Override
	public ProtocolBean getCountUserMsg(int userId) {
		// TODO Auto-generated method stub
		int count = userMsgMapper.selectCountByUserId(userId);
		ProtocolBean bean = new ProtocolBean();
		bean.setMsg("获取消息数量成功");
		Map<String,Object> map = new HashMap<>();
		map.put("number", count);
		bean.setData(map);
		bean.setResultCode(0);
		return bean;
	}

	@Override
	public void inserdTrade(String trade_no,String out_trade_no) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void changeOrderType(int type, String orderNum) {
		// TODO Auto-generated method stub
		ShopOrder shop = new ShopOrder();
		shop.setOrdernum(orderNum);
		shop.setOrderType(type);
		orderMappper.updateByOrderNumSelective(shop);
	}

	//admin
	@Override
	public AdminOrderDetail getAdminOrderDetail(String orderNum) {
		// TODO Auto-generated method stub
		return orderMappper.getAdminOrderDetail(orderNum);
	}

	@Override
	public List<AdminOrderDetail> getAllAdminOrderDetail(int page,int rows) {
		// TODO Auto-generated method stub
		page = page * rows;
		System.out.println("service page "+page + " rows " + rows);
		return orderMappper.getAllAdminOrderDetail(page,rows);
	}

	@Override
	public int getOrderCount() {
		// TODO Auto-generated method stub
		return orderMappper.getCountOrder();
	}

	@Override
	public List<AdminOrderDetail> searchOrder(int orderType, String orderNum, String userName) {
		// TODO Auto-generated method stub
		return orderMappper.searchAdminOrderDetail(orderNum, orderType, userName);
	}

	@Override
	public ShopOrder getOrderByOrderNum(String orderNum) {
		// TODO Auto-generated method stub
		return orderMappper.getOrderByOrderNum(orderNum);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public int deleteByOrderNum(String orderNum) {
		// TODO Auto-generated method stub
		orderMappper.deleteByOrderNum(orderNum);
		int result = orderProduct.deleteByOrderNum(orderNum);
		return result;
	}

	@Override
	public int modifyProduct(Product shop) {
		// TODO Auto-generated method stub
		return product.updateByPrimaryKeySelective(shop);
	}

	@Override
	public int addProduct(Product shop) {
		// TODO Auto-generated method stub
		return product.insertSelective(shop);
	}

}
