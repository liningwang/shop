package com.cn.hnust.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cn.hnust.dao.UserInfoMapper;
import com.cn.hnust.pojo.UserInfo;
import com.cn.hnust.service.IUserInfoService;
@Service
public class UserInfoServiceImpl implements IUserInfoService{
	@Resource
	private UserInfoMapper userInfo;

	@Override
	public UserInfo login(String phone, String password) {
		// TODO Auto-generated method stub
		return userInfo.login(phone, password);
	}

	//事务测试
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class) 
	public int register(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return this.userInfo.insertSelective(userInfo);
	}

	@Override
	public int forgetPass(UserInfo record) {
		// TODO Auto-generated method stub
		return userInfo.updateByPhoneNumberSelective(record);
	}

	@Override
	public int boundAccount(UserInfo record) {
		// TODO Auto-generated method stub
		return userInfo.updateByIdAndPassSelective(record);
	}
	@Override
	public List<UserInfo> detectPhone(String phone) {
		// TODO Auto-generated method stub
		return userInfo.selectByPhoneNumber(phone);
	}

	@Override
	public UserInfo loginThrid(String openId) {
		// TODO Auto-generated method stub
		return userInfo.loginThrid(openId);
	}

	@Override
	public List<UserInfo> detectOpenId(String openid) {
		// TODO Auto-generated method stub
		List<UserInfo> users = userInfo.selectByOpenId(openid);
		return users;
	}

	
}
