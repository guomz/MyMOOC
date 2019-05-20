package com.guomz.MyMOOC.entity;

public class Teacher {

	private Long teacherId;
	private String teacherName;
	private String teacherDesc;
	private String avatarUrl;
	private Long userId;
	public Long getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getTeacherDesc() {
		return teacherDesc;
	}
	public void setTeacherDesc(String teacherDesc) {
		this.teacherDesc = teacherDesc;
	}
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
	@Override
	public String toString() {
		return "Teacher [teacherId=" + teacherId + ", teacherName=" + teacherName + ", teacherDesc=" + teacherDesc
				+ ", avatarUrl=" + avatarUrl + ", userId=" + userId + "]";
	}
	
}
