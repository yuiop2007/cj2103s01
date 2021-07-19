package com.spring.cj2103s10.vo;

public class ReservationVO {
	private int idx;
	private int reservationStatusIdx;
	private String mid;
	private String person;
	private String content;
	
	private String nickName;				// 예약자 닉네임
	private String room;						// 예약한 방
	private String reservationDate;	// 예약한 날짜
	private String ymd;							// 출력날짜
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getReservationStatusIdx() {
		return reservationStatusIdx;
	}
	public void setReservationStatusIdx(int reservationStatusIdx) {
		this.reservationStatusIdx = reservationStatusIdx;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}
	public String getYmd() {
		return ymd;
	}
	public void setYmd(String ymd) {
		this.ymd = ymd;
	}
	@Override
	public String toString() {
		return "ReservationVO [idx=" + idx + ", reservationStatusIdx=" + reservationStatusIdx + ", mid=" + mid + ", person="
				+ person + ", content=" + content + ", nickName=" + nickName + ", room=" + room + ", reservationDate="
				+ reservationDate + ", ymd=" + ymd + "]";
	}
}
