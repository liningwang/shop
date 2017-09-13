package com.cn.hnust.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.common.Config;
import com.cn.hnust.common.HttpUtil;
import com.cn.hnust.pojo.ProtocolBean;
import com.cn.hnust.pojo.User;
import com.cn.hnust.pojo.UserInfo;
import com.cn.hnust.service.IUserInfoService;
import com.cn.hnust.service.IUserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import redis.clients.jedis.Jedis;

@Controller
@RequestMapping("/ykd")
public class UserController {
	public UserController() {
		System.out.println("UserController");
	}

	private String operation = "/industrySMS/sendSMS";
	@Resource
	private IUserService userService;
	@Resource
	private IUserInfoService userInfoService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "用户登录", httpMethod = "POST", response = ProtocolBean.class, notes = "实现用户登录操作")
	public ProtocolBean login(@ApiParam(required = true, name = "phone", value = "用户手机号") @RequestParam String phone,
			@ApiParam(required = true, name = "pass", value = "用户登录密码") @RequestParam String pass) {
		System.out.println("phone " + phone + " pass " + pass);
		UserInfo userInfo = userInfoService.login(phone, pass);
		ProtocolBean bean = new ProtocolBean();
		Map<String, Integer> map = new HashMap<>();
		if (userInfo != null) {
			map.put("userId", userInfo.getUserid());
			bean.setData(map);
			bean.setMsg("登录成功");
		} else {
			map.put("userId", null);
			bean.setData(map);
			bean.setMsg("登录失败");
		}

		return bean;
	}

	@RequestMapping(value = "/session", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "测试session功能", httpMethod = "GET", notes = "这个只是用来测试session功能的")
	public UserInfo sessionTest(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.setAttribute("data", "孤傲苍狼");
		// 转换后会生成类似于http://localhost:8080/Test/ykd/session;jsessionid=B490BC3B377164C169C1E373DE6931FC
		// 会添加一个jsessionid=B490BC3B377164C169C1E373DE6931FC，访问的时候在后面添加;jsession就可以再次获取到该session了，
		// 这样就不依赖于cookie了。
		String url = response.encodeURL(request.getRequestURI());
		System.out.println(url);
		if (session.isNew()) {
			System.out.println("session is new id " + session.getId());
		} else {
			System.out.println("session is old id " + session.getId() + " data " + session.getAttribute("data"));
		}
		// 设置了cookie最大时间后，关闭浏览器也再打开sessionId也不会丢失，就是让cookie持久化了(针对于浏览器，android客户端可能需要自己做了)。
		Cookie cookie = new Cookie("JSESSIONID", session.getId());
		cookie.setMaxAge(3600);
		response.addCookie(cookie);
		return new UserInfo();
	}

	@RequestMapping(value = "/loginthrid", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "用户第三方登录", httpMethod = "POST", response = ProtocolBean.class, notes = "实现用户第三方登录操作")
	public ProtocolBean login(@ApiParam(required = true, value = "用户唯一的openId") @RequestParam String openId,
			@ApiParam(required = true, value = "1 qq,2 微信") @RequestParam int type) {
		Jedis jedis = new Jedis("localhost");
		System.out.println("openId " + openId + " type " + type + " redis " + jedis.get("runoobkey"));
		UserInfo userInfo = userInfoService.loginThrid(openId);
		ProtocolBean bean = new ProtocolBean();
		Map<String, Integer> map = new HashMap<>();
		if (userInfo != null) {
			map.put("userId", userInfo.getUserid());
			bean.setData(map);
			bean.setMsg("登录成功");
			bean.setResultCode(0);
		} else {
			map.put("userId", null);
			bean.setData(map);
			bean.setMsg("登录失败");
			bean.setResultCode(1);
		}

		return bean;
	}

	private String getNumber(String phone) {
		Jedis jedis = new Jedis("localhost");
		System.out.println("连接成功");
		String number = jedis.get(phone);
		jedis.disconnect();
		System.out.println("断开连接 " + number);
		return number;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "注册用户", httpMethod = "POST", notes = "实现用户注册操作")
	public ProtocolBean register(
			@ApiParam(required = true, name = "phoneNumber", value = "手机号") @RequestParam String phoneNumber,
			@ApiParam(required = true, name = "passWord", value = "密码") @RequestParam String passWord,
			@ApiParam(required = true, name = "invitationCode", value = "邀请码") @RequestParam String invitationCode,
			@ApiParam(required = true, name = "validateCode", value = "手机验证码") @RequestParam String validateCode,
			@ApiParam(required = true, name = "type", value = "1 注册,2 忘记密码") @RequestParam String type) {
		System.out.println("validateCode " + validateCode + " type " + type);
		ProtocolBean bean = new ProtocolBean();
		UserInfo userInfo = new UserInfo();
		userInfo.setPhonenumber(phoneNumber);
		userInfo.setPassword(passWord);
		userInfo.setInvitationcode(invitationCode);

		String number = getNumber(phoneNumber);

		if (validateCode.equals(number)) {
			if ("1".equals(type)) {
				List<UserInfo> user = userInfoService.detectPhone(phoneNumber);
				if (user.size() == 0) {
					userInfoService.register(userInfo);
					// 获取插入成功数据的id
					System.out.println("1 " + userInfo.getUserid());
					bean.setResultCode(0);
					bean.setMsg("注册成功");
				} else {
					bean.setResultCode(1);
					bean.setMsg("手机号已经注册了");
				}
			} else if ("2".equals(type)) {
				int code = userInfoService.forgetPass(userInfo);
				System.out.println("2 " + code);
				bean.setResultCode(0);
				bean.setMsg("密码修改成功");
			}
		} else {
			bean.setResultCode(-1);
			bean.setMsg("验证码验证失败");
		}
		bean.setData(new HashMap<>());
		return bean;
	}

	// 对于required属性只有在@RequestParam中才起作用
	@RequestMapping(value = "/isbound", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "绑定账号", httpMethod = "POST", notes = "绑定用户账号")
	public ProtocolBean isbound(
			@ApiParam(required = true, name = "phoneNumber", value = "手机号") @RequestParam String phoneNumber,
			@ApiParam(required = true, name = "passWord", value = "密码") @RequestParam String passWord,
			@ApiParam(name = "invitationCode", value = "邀请码") @RequestParam(required = false) String invitationCode,
			@ApiParam(name = "validateCode", value = "手机验证码") @RequestParam(required = false) String validateCode,
			@ApiParam(required = true, name = "type", value = "0 未注册绑定新账号,1 已注册绑定已有账号") @RequestParam String type,
			@ApiParam(required = false, name = "openId", value = "qq或微信唯一对应的id号") @RequestParam(required = false) String openId,
			@ApiParam(required = false, name = "userId", value = "用户id") @RequestParam(required = false, defaultValue = "0") int userId) {

		ProtocolBean bean = new ProtocolBean();
		UserInfo userInfo = new UserInfo();
		userInfo.setPhonenumber(phoneNumber);
		userInfo.setPassword(passWord);
		userInfo.setInvitationcode(invitationCode);
		userInfo.setUserid(userId);
		userInfo.setOpenid(openId);
		System.out.println("validateCode " + validateCode);
		List<UserInfo> userOpenId = userInfoService.detectOpenId(openId);
		if(userOpenId.size() != 0) {
			bean.setResultCode(-1);
			bean.setMsg("openId不能重复绑定");
			return bean;
		}
		if ("0".equals(type)) {
			List<UserInfo> user = userInfoService.detectPhone(phoneNumber);
			System.out.println(user);
			if (user.size() == 0) {
				String number = getNumber(phoneNumber);
				if (validateCode.equals(number)) {
					int result = userInfoService.register(userInfo);
					if (result == 0) {
						bean.setResultCode(-1);
						bean.setMsg("绑定失败");
					} else {
						bean.setResultCode(0);
						bean.setMsg("绑定成功");
					}
				} else {
					bean.setResultCode(-1);
					bean.setMsg("验证码验证失败");
				}
			} else {
				bean.setResultCode(-1);
				bean.setMsg("手机号已经被注册");
			}
		} else if ("1".equals(type)) {
			int result = userInfoService.boundAccount(userInfo);
			System.out.println(result);
			if (result == 0) {
				bean.setResultCode(-1);
				bean.setMsg("绑定失败");
			} else {
				bean.setResultCode(0);
				bean.setMsg("绑定成功");
			}
		}

		bean.setData(new HashMap<>());

		return bean;
	}

	@RequestMapping(value = "/testRedis", method = RequestMethod.POST)
	@ApiOperation(value = "测试redis", httpMethod = "POST", notes = "测试redis用的")
	public void testRedis() {
		// 连接本地的 Redis 服务

		Jedis jedis = new Jedis("localhost");
		System.out.println("连接成功");
		// 设置 redis 字符串数据
		jedis.set("runoobkey", "www.runoob.com");
		// 获取存储的数据并输出

		jedis.expire("runoobkey", 45);
	}

	@RequestMapping(value = "/sendSMS", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "发送验证码", httpMethod = "POST", notes = "发送手机验证码")
	public ProtocolBean sendSMS(@ApiParam(required = false, value = "手机号") @RequestParam String phone) {

		// String number = productNum();
		String number = "111111";
		// String code = sendSmsCode(phone, number);
		String code = "00000";
		Jedis jedis = new Jedis("localhost");
		System.out.println("连接成功");
		jedis.set(phone, number);
		// 获取存储的数据并输出
		jedis.expire(phone, 60);
		jedis.disconnect();
		System.out.println("断开连接 " + number + " phone " + phone);

		ProtocolBean bean = new ProtocolBean();
		if (code.equals("00000")) {
			bean.setResultCode(0);
			bean.setMsg("验证码发送成功");
		} else {
			bean.setResultCode(-1);
			bean.setMsg("验证码发送失败");
		}
		bean.setData(new HashMap<>());
		return bean;
	}

	private String sendSmsCode(String phone, String number) {
		String accountSid = Config.ACCOUNT_SID;
		String to = phone;
		String smsContent = "【雨墨科技】验证码：" + number + "，打死都不要告诉别人哦！";
		String url = Config.BASE_URL + operation;
		String body = "accountSid=" + accountSid + "&to=" + to + "&smsContent=" + smsContent
				+ HttpUtil.createCommonParam();

		System.out.println("phone " + phone + " body " + body);
		// 提交请求
		String result = HttpUtil.post(url, body);
		System.out.println("result:" + result);

		JSONObject obj = new JSONObject(result);
		String code = obj.getString("respCode");
		return code;
	}

	private String productNum() {
		// 生成六位验证码
		String charValue = "";
		for (int i = 0; i < 6; i++) {
			char c = (char) (randomInt(0, 9) + '0');
			charValue += String.valueOf(c);
		}
		return charValue;
	}

	/**
	 * 生成随机数
	 * 
	 */
	private int randomInt(int from, int to) {
		Random r = new Random();
		return from + r.nextInt(to - from);
	}
	// @ResponseBody
	// @RequestMapping(value = "getUserById1", method = RequestMethod.GET,
	// produces = {"application/json; charset=utf-8","application/xml"})
	// @ApiOperation(value = "通过ID查询USER信息", httpMethod = "GET", notes = "暂无")
	// public String getUserById1(
	// @ApiParam(required = true, name = "id", value = "ID")
	// @RequestParam(value = "id") String id,HttpServletRequest request) {
	// User user = new User();
	//// user.setId(id);
	//// user.setName("测试人员");
	//// user.setAge(25);
	//// JSONObject object = JSONObject.fromObject(user);
	// return null;
	// }

	// @RequestMapping(value = "/uditer", method = RequestMethod.POST)
	// public void postUditetor(String editorValue,HttpServletResponse res){
	// System.out.println(editorValue);
	// try {
	// res.getWriter().write("aaaa");
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	// //model model.addAttribute("user", user);方法添加了
	// //了一个user参数，这个参数会传递给jsp，然后再jsp中 ${user.userName}获取这个类。
	// @RequestMapping("/showUser")
	// public String toIndex(HttpServletRequest request,Model model){
	// int userId = Integer.parseInt(request.getParameter("id"));
	// User user = this.userService.getUserById(userId);
	// model.addAttribute("user", user);
	// return "showUser";
	// }
	//
	// //@RequestBody 以json的形式作为参数,后面直接加一个封装类就行了。
	// //如果是form表单形式的参数可以直接写一个封装类
	// //也可以使用getAll(@RequestParam int id)这种方式传递单个参数。
	// @RequestMapping("/getAll")
	// @ResponseBody
	// public List<User> getAll(HttpServletRequest request,Model model){
	// return userService.getUserAll();
	// }

}
