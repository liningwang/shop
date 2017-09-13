package com.cn.hnust.pojo;

import java.util.List;

public class HomePage {
	List<SlidPic> slideShowUrlList;
	List<ClassiFy> classifyList;
	List<Product> recommendationList;
	public List<SlidPic> getSlideShowUrlList() {
		return slideShowUrlList;
	}
	public void setSlideShowUrlList(List<SlidPic> slideShowUrlList) {
		this.slideShowUrlList = slideShowUrlList;
	}
	public List<ClassiFy> getClassifyList() {
		return classifyList;
	}
	public void setClassifyList(List<ClassiFy> classifyList) {
		this.classifyList = classifyList;
	}
	public List<Product> getRecommendationList() {
		return recommendationList;
	}
	public void setRecommendationList(List<Product> recommendationList) {
		this.recommendationList = recommendationList;
	}
	
}
