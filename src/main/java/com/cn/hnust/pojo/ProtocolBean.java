package com.cn.hnust.pojo;

public class ProtocolBean {
	int resultCode;
	String msg;
	Object data;
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "ProtocolBean [resultCode=" + resultCode + ", msg=" + msg + ", data=" + data + "]";
	}
	
}
