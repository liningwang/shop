package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.UserInfo;

public interface IUserInfoService {
	public UserInfo login(String phone,String password);
	public UserInfo loginThrid(String openId);
	public int register(UserInfo userInfo);
	int forgetPass(UserInfo record);
	public int boundAccount(UserInfo record);
	public List<UserInfo> detectPhone(String phone);
	public List<UserInfo> detectOpenId(String openid);
}
