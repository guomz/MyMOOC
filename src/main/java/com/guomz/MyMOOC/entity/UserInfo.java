package com.guomz.MyMOOC.entity;

import java.util.List;

public class UserInfo {

	private Long userId;
	private String openid;
	private String nickName;
	private String avatarUrl;
	private List<Course> courseList;
	private List<Article> articleList;
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public List<Course> getCourseList() {
		return courseList;
	}
	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}
	public List<Article> getArticleList() {
		return articleList;
	}
	public void setArticleList(List<Article> articleList) {
		this.articleList = articleList;
	}
	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", openid=" + openid + ", nickName=" + nickName + ", avatarUrl="
				+ avatarUrl + ", courseList=" + courseList + ", articleList=" + articleList + "]";
	}
}
