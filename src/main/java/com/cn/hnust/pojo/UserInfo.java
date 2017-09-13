package com.cn.hnust.pojo;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
public class UserInfo {
    private Integer userid;
    @ApiModelProperty(value="昵称")
    private String nickname;

    private String password;
    @ApiModelProperty(value="我自己的邀请码")
    private String mycode;
    @ApiModelProperty(value="手机号")
    private String phonenumber;

    private String identity;
    @ApiModelProperty(value="未读消息数量")
    private Integer myusercount;

    private Double money;
    @ApiModelProperty(value="性别 0：男 1：女")
    private Integer sextype;

    private String regitime;
   
    private String invitationcode;
    @ApiModelProperty(value="图片数据，用base64加密后的图片数据")
    private String headportrait;

    private String openid;
    
    private Integer userGrade;
    @ApiModelProperty(value="分享状态 0：已分享 1：未分享")
    private Integer shareType;
    @ApiModelProperty(value="分享链接")
    private String shareLink;
    @ApiModelProperty(value="抵扣金额")
    private Double deduction;
    public Integer getUserGrade() {
		return userGrade;
	}

	public void setUserGrade(Integer userGrade) {
		this.userGrade = userGrade;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getMycode() {
        return mycode;
    }

    public void setMycode(String mycode) {
        this.mycode = mycode == null ? null : mycode.trim();
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber == null ? null : phonenumber.trim();
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity == null ? null : identity.trim();
    }

    public Integer getMyusercount() {
        return myusercount;
    }

    public void setMyusercount(Integer myusercount) {
        this.myusercount = myusercount;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getSextype() {
        return sextype;
    }

    public void setSextype(Integer sextype) {
        this.sextype = sextype;
    }

    public String getRegitime() {
        return regitime;
    }

    public void setRegitime(String regitime) {
        this.regitime = regitime;
    }

    public String getInvitationcode() {
        return invitationcode;
    }

    public void setInvitationcode(String invitationcode) {
        this.invitationcode = invitationcode == null ? null : invitationcode.trim();
    }

    public String getHeadportrait() {
        return headportrait;
    }

    public void setHeadportrait(String headportrait) {
        this.headportrait = headportrait == null ? null : headportrait.trim();
    }
}