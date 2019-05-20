package com.guomz.MyMOOC.entity;

public class Plan {

	private Long planId;
	private Long userId;
	private String planName;
	private String groupName;
	private String planDetail;
	private String planDate;
	private String planTime;
	public Long getPlanId() {
		return planId;
	}
	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getPlanDetail() {
		return planDetail;
	}
	public void setPlanDetail(String planDetail) {
		this.planDetail = planDetail;
	}
	public String getPlanDate() {
		return planDate;
	}
	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}
	public String getPlanTime() {
		return planTime;
	}
	public void setPlanTime(String planTime) {
		this.planTime = planTime;
	}
	@Override
	public String toString() {
		return "Plan [planId=" + planId + ", userId=" + userId + ", planName=" + planName + ", groupName=" + groupName
				+ ", planDetail=" + planDetail + ", planDate=" + planDate + ", planTime=" + planTime + "]";
	}
	
}
