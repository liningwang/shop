package com.cn.hnust.pojo;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class ReturnReason {
	@ApiModelProperty(value="退货原因id")
    private Integer salesreturnid;
	@ApiModelProperty(value="退货原因描述")
    private String returnreason;

    public Integer getSalesreturnid() {
        return salesreturnid;
    }

    public void setSalesreturnid(Integer salesreturnid) {
        this.salesreturnid = salesreturnid;
    }

    public String getReturnreason() {
        return returnreason;
    }

    public void setReturnreason(String returnreason) {
        this.returnreason = returnreason == null ? null : returnreason.trim();
    }
}