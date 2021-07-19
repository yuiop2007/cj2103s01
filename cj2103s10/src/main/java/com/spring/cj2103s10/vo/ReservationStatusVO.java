package com.spring.cj2103s10.vo;

public class ReservationStatusVO {
	private int idx;
	private String reservationDate;
	private String reservationRoom;
	private String available;   // 현재 사용하지 않음....
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}
	public String getReservationRoom() {
		return reservationRoom;
	}
	public void setReservationRoom(String reservationRoom) {
		this.reservationRoom = reservationRoom;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	@Override
	public String toString() {
		return "ReservationStatusVo [idx=" + idx + ", reservationDate=" + reservationDate + ", reservationRoom="
				+ reservationRoom + ", available=" + available + "]";
	}
}
