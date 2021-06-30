package com.spring.cj2103s01.vo;

public class WishVO {
	private int wId;
	private String mId;
	private int pId;
	
	public int getwId() {
		return wId;
	}
	public void setwId(int wId) {
		this.wId = wId;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	@Override
	public String toString() {
		return "WishVO [wId=" + wId + ", mId=" + mId + ", pId=" + pId + "]";
	}
	
	
}
