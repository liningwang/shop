package com.cn.hnust.pojo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class Address {
	@ApiModelProperty(value = "用户地址id", required = true)
    private Integer useraddreessid;
	@ApiModelProperty(value = "用户名字", required = true)
    private String username;
	@ApiModelProperty(value = "用户电话号码", required = true)
    private String usertelephone;
	@ApiModelProperty(value = "用户所在省", required = true)
    private String userprovince;
	@ApiModelProperty(value = "用户所在市", required = true)
    private String usercity;
	@ApiModelProperty(value = "用户地址所在区", required = true)
    private String userdistrict;
	@ApiModelProperty(value = "用户详细地址", required = true)
    private String userdetailedaddress;
	@ApiModelProperty(value = "是否设置为默认地址", required = true)
    private Integer useristype;
	@ApiModelProperty(value = "用户id", required = true)
    private Integer userid;

    public Integer getUseraddreessid() {
        return useraddreessid;
    }

    public void setUseraddreessid(Integer useraddreessid) {
        this.useraddreessid = useraddreessid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUsertelephone() {
        return usertelephone;
    }

    public void setUsertelephone(String usertelephone) {
        this.usertelephone = usertelephone == null ? null : usertelephone.trim();
    }

    public String getUserprovince() {
        return userprovince;
    }

    public void setUserprovince(String userprovince) {
        this.userprovince = userprovince == null ? null : userprovince.trim();
    }

    public String getUsercity() {
        return usercity;
    }

    public void setUsercity(String usercity) {
        this.usercity = usercity == null ? null : usercity.trim();
    }

    public String getUserdistrict() {
        return userdistrict;
    }

    public void setUserdistrict(String userdistrict) {
        this.userdistrict = userdistrict == null ? null : userdistrict.trim();
    }

    public String getUserdetailedaddress() {
        return userdetailedaddress;
    }

    public void setUserdetailedaddress(String userdetailedaddress) {
        this.userdetailedaddress = userdetailedaddress == null ? null : userdetailedaddress.trim();
    }

    public Integer getUseristype() {
        return useristype;
    }

    public void setUseristype(Integer useristype) {
        this.useristype = useristype;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}