package com.cn.hnust.pojo;

import java.util.List;

public class FirstResultMap {
	int stairId;
	String stairTitle;
	List<SecondResult> secondLevelList;
	public int getStairId() {
		return stairId;
	}
	public void setStairId(int stairId) {
		this.stairId = stairId;
	}
	public String getStairTitle() {
		return stairTitle;
	}
	public void setStairTitle(String stairTitle) {
		this.stairTitle = stairTitle;
	}
	public List<SecondResult> getSecondLevelList() {
		return secondLevelList;
	}
	public void setSecondLevelList(List<SecondResult> secondLevelList) {
		this.secondLevelList = secondLevelList;
	}
	
}
