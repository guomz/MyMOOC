package com.guomz.MyMOOC.entity;

public class CourseHistory {

	private Long userId;
	private Long courseId;
	private String chapter;
	private Double currentPos;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public String getChapter() {
		return chapter;
	}
	public void setChapter(String chapter) {
		this.chapter = chapter;
	}
	public Double getCurrentPos() {
		return currentPos;
	}
	public void setCurrentPos(Double currentPos) {
		this.currentPos = currentPos;
	}
	@Override
	public String toString() {
		return "CourseHistory [userId=" + userId + ", courseId=" + courseId + ", chapter=" + chapter + ", currentPos="
				+ currentPos + "]";
	}
	
}
