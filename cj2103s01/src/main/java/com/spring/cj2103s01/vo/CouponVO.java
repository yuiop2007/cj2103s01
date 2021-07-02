package com.spring.cj2103s01.vo;

public class CouponVO {
	private int cId;
	private String mId;
	private int cmCate;
	private String cpCate;
	private String cName;
	private int cDiscount;
	private int cPrice;
	private String cRdate;
	private String cEnddate;
	
	private int diffTime;

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public int getCmCate() {
		return cmCate;
	}

	public void setCmCate(int cmCate) {
		this.cmCate = cmCate;
	}

	public String getCpCate() {
		return cpCate;
	}

	public void setCpCate(String cpCate) {
		this.cpCate = cpCate;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public int getcDiscount() {
		return cDiscount;
	}

	public void setcDiscount(int cDiscount) {
		this.cDiscount = cDiscount;
	}

	public int getcPrice() {
		return cPrice;
	}

	public void setcPrice(int cPrice) {
		this.cPrice = cPrice;
	}

	public String getcRdate() {
		return cRdate;
	}

	public void setcRdate(String cRdate) {
		this.cRdate = cRdate;
	}

	public String getcEnddate() {
		return cEnddate;
	}

	public void setcEnddate(String cEnddate) {
		this.cEnddate = cEnddate;
	}

	public int getDiffTime() {
		return diffTime;
	}

	public void setDiffTime(int diffTime) {
		this.diffTime = diffTime;
	}
	

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	@Override
	public String toString() {
		return "CouponVO [cId=" + cId + ", cmCate=" + cmCate + ", cpCate=" + cpCate + ", cName=" + cName
				+ ", cDiscount=" + cDiscount + ", cPrice=" + cPrice + ", cRdate=" + cRdate + ", cEnddate=" + cEnddate
				+ ", diffTime=" + diffTime + "]";
	}

	
	
}
