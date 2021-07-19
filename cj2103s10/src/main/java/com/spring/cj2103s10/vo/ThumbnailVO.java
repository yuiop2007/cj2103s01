package com.spring.cj2103s10.vo;

public class ThumbnailVO {
	private int res;
	private String oFileName;
	private String tFileName;
	
	public int getRes() {
		return res;
	}
	public void setRes(int res) {
		this.res = res;
	}
	public String getoFileName() {
		return oFileName;
	}
	public void setoFileName(String oFileName) {
		this.oFileName = oFileName;
	}
	public String gettFileName() {
		return tFileName;
	}
	public void settFileName(String tFileName) {
		this.tFileName = tFileName;
	}
	@Override
	public String toString() {
		return "ThumbnailVO [res=" + res + ", oFileName=" + oFileName + ", tFileName=" + tFileName + "]";
	}
}
