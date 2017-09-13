package com.cn.hnust.service;

import java.util.HashMap;
import java.util.Map;

import com.cn.hnust.pojo.ProtocolBean;

public class BaseService {
	public ProtocolBean genarateProtocol(String key,Object obj,String msg,int resultCode){
		Map<String,Object> map = new HashMap<>();
		if(key != null) {	
			map.put(key, obj);
		} 
		ProtocolBean bean = new ProtocolBean();
		bean.setData(map);
		bean.setMsg(msg);
		bean.setResultCode(resultCode);
		return bean;
	}
}
