package com.cn.hnust.controller;

import java.io.UnsupportedEncodingException;

public class BaseController {
	public String encordingUtf8(String param){
		String result="";
		try {
			result = new String(param.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
