package com.spring.cj2103s01.vo;

public class OrderVO {
	private int oId;
	private String mId;
	private String oRdate;
	private String oName;
	private String oPhone;
	private String oPost;
	private String oAddress;
	private String oEmail;
	private String oMessage;
	private int oPrice;
	private int oPayment; /* 1:현금입금(무통장) 2:카드 */
	private String oDelivery; // 0원무료배송 3000원일반배송등
	private String oStatus;

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

	public String getoMessage() {
		return oMessage;
	}

	public void setoMessage(String oMessage) {
		this.oMessage = oMessage;
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

	
	public String getoEmail() {
		return oEmail;
	}

	public void setoEmail(String oEmail) {
		this.oEmail = oEmail;
	}

	public String getoDelivery() {
		return oDelivery;
	}

	public void setoDelivery(String oDelivery) {
		this.oDelivery = oDelivery;
	}

	public String getoStatus() {
		return oStatus;
	}

	public void setoStatus(String oStatus) {
		this.oStatus = oStatus;
	}

	@Override
	public String toString() {
		return "OrderVO [oId=" + oId + ", mId=" + mId + ", oRdate=" + oRdate + ", oName=" + oName + ", oPhone=" + oPhone
				+ ", oPost=" + oPost + ", oAddress=" + oAddress + ", oEmail=" + oEmail + ", oMessage=" + oMessage
				+ ", oPrice=" + oPrice + ", oPayment=" + oPayment + ", oDelivery=" + oDelivery + ", oStatus=" + oStatus
				+ "]";
	}

	

}
