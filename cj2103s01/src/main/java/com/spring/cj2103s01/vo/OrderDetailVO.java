package com.spring.cj2103s01.vo;

public class OrderDetailVO {
	private int odId;
	private int oId;
	private String mId;
	private int pId;
	private String odOption;
	private int odCnt;
	private int odPrice;
	public int getOdId() {
		return odId;
	}
	public void setOdId(int odId) {
		this.odId = odId;
	}
	public int getoId() {
		return oId;
	}
	public void setoId(int oId) {
		this.oId = oId;
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
	public String getOdOption() {
		return odOption;
	}
	public void setOdOption(String odOption) {
		this.odOption = odOption;
	}
	public int getOdCnt() {
		return odCnt;
	}
	public void setOdCnt(int odCnt) {
		this.odCnt = odCnt;
	}
	public int getOdPrice() {
		return odPrice;
	}
	public void setOdPrice(int odPrice) {
		this.odPrice = odPrice;
	}
	@Override
	public String toString() {
		return "OrderDetailVO [odId=" + odId + ", oId=" + oId + ", mId=" + mId + ", pId=" + pId
				+ ", odOption=" + odOption + ", odCnt=" + odCnt + ", odPrice=" + odPrice + "]";
	}
	
	
}
