package com.spring.cj2103s01.vo;

public class OrderVO {
	private int oId;
	private String mId;
	private int cId;
	private String oRdate;
	private String oName;
	private String oPhone;
	private String oPost;
	private String oAddress;
	private String oMassage;
	private int oPrice;
	private int oPayment; /* 1:현금입금(무통장) 2:카드 */
	private int oDelivery; // 무료배송 일반배송등

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

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getoRdate() {
		return oRdate;
	}

	public void setoRdate(String oRdate) {
		this.oRdate = oRdate;
	}

	public String getoName() {
		return oName;
	}

	public void setoName(String oName) {
		this.oName = oName;
	}

	public String getoPhone() {
		return oPhone;
	}

	public void setoPhone(String oPhone) {
		this.oPhone = oPhone;
	}

	public String getoPost() {
		return oPost;
	}

	public void setoPost(String oPost) {
		this.oPost = oPost;
	}

	public String getoAddress() {
		return oAddress;
	}

	public void setoAddress(String oAddress) {
		this.oAddress = oAddress;
	}

	public String getoMassage() {
		return oMassage;
	}

	public void setoMassage(String oMassage) {
		this.oMassage = oMassage;
	}

	public int getoPrice() {
		return oPrice;
	}

	public void setoPrice(int oPrice) {
		this.oPrice = oPrice;
	}

	public int getoPayment() {
		return oPayment;
	}

	public void setoPayment(int oPayment) {
		this.oPayment = oPayment;
	}

	public int getoDelivery() {
		return oDelivery;
	}

	public void setoDelivery(int oDelivery) {
		this.oDelivery = oDelivery;
	}

	@Override
	public String toString() {
		return "OrderVO [oId=" + oId + ", mId=" + mId + ", cId=" + cId + ", oRdate=" + oRdate + ", oName=" + oName
				+ ", oPhone=" + oPhone + ", oPost=" + oPost + ", oAddress=" + oAddress + ", oMassage=" + oMassage
				+ ", oPrice=" + oPrice + ", oPayment=" + oPayment + ", oDelivery=" + oDelivery + "]";
	}

}
