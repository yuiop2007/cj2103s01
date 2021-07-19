package com.spring.cj2103s10.vo;

public class ReservationRoomVO {
	private String room;
	private String content;
	private String available;
	
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	@Override
	public String toString() {
		return "ReservationRoomVo [room=" + room + ", content=" + content + ", available=" + available + "]";
	}
}
