package com.cn.hnust.pojo;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class UserPerson {
	@ApiModelProperty(value="用户id")
	private Integer userid;
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	@ApiModelProperty(value="昵称")
	private String nickname;
	@ApiModelProperty(value="手机号不需要填写",hidden=true,required=false)
	private String phonenumber;
	@ApiModelProperty(value="性别 0：男 1：女")
	private Integer sextype;
	@ApiModelProperty(value="用户头像地址")
	private String headportrait;
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public Integer getSextype() {
		return sextype;
	}
	public void setSextype(Integer sextype) {
		this.sextype = sextype;
	}
	public String getHeadportrait() {
		return headportrait;
	}
	public void setHeadportrait(String headportrait) {
		this.headportrait = headportrait;
	}
	
}