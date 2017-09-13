package com.cn.hnust.pojo;

public class userMsg {
    private Integer userid;

    private String msg;

    private String msgtime;
  //状态 1：系统消息 2：金额提现 3：返利金额 4：商品
    private Integer messagePictureType;
    public Integer getMessagePictureType() {
		return messagePictureType;
	}

	public void setMessagePictureType(Integer messagePictureType) {
		this.messagePictureType = messagePictureType;
	}

	public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public String getMsgtime() {
        return msgtime;
    }

    public void setMsgtime(String msgtime) {
        this.msgtime = msgtime;
    }
}