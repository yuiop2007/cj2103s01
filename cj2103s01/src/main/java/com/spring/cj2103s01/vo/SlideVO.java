package com.spring.cj2103s01.vo;

public class SlideVO {
	private int sId;
	private String sImage;
	private int sNum;

	public int getsId() {
		return sId;
	}

	public void setsId(int sId) {
		this.sId = sId;
	}

	public String getsImage() {
		return sImage;
	}

	public void setsImage(String sImage) {
		this.sImage = sImage;
	}

	public int getsNum() {
		return sNum;
	}

	public void setsNum(int sNum) {
		this.sNum = sNum;
	}

	@Override
	public String toString() {
		return "SlideVO [sId=" + sId + ", sImage=" + sImage + ", sNum=" + sNum + "]";
	}

}
