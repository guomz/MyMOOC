package com.guomz.MyMOOC.entity;

public class Event {

	private Long eventId;
	private Long userId;
	private Long roomId;
	private String eventName;
	private String eventDetail;
	private String eventDate;
	private String groupName;
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventDetail() {
		return eventDetail;
	}
	public void setEventDetail(String eventDetail) {
		this.eventDetail = eventDetail;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", userId=" + userId + ", roomId=" + roomId + ", eventName=" + eventName
				+ ", eventDetail=" + eventDetail + ", eventDate=" + eventDate + ", groupName=" + groupName + "]";
	}
	
}
