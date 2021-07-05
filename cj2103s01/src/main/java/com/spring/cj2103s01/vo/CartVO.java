package com.spring.cj2103s01.vo;

public class CartVO {
	private int cartId;
	private String mId;
	private int pId;
	private String pOption;
	private int pCnt;
	private int pPrice;

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
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

	public String getpOption() {
		return pOption;
	}

	public void setpOption(String pOption) {
		this.pOption = pOption;
	}

	public int getpCnt() {
		return pCnt;
	}

	public void setpCnt(int pCnt) {
		this.pCnt = pCnt;
	}

	public int getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	@Override
	public String toString() {
		return "CartVO [cartId=" + cartId + ", mId=" + mId + ", pId=" + pId + ", pOption=" + pOption + ", pCnt=" + pCnt
				+ ", pPrice=" + pPrice + "]";
	}

}
