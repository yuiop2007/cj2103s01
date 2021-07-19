package com.spring.cj2103s10.vo;

public class CartTestVO {
	private int idx;					// cart고유번호
	private String product;		// 상품명
	private String thumbImg;	// 상품이미지
	private int price;				// 구매가격
	private int su;						// 구매수량
	
	private int lastTotalPrice; // 구입(주문)한 모든 (품목가격*수량)의 총 합계
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getThumbImg() {
		return thumbImg;
	}
	public void setThumbImg(String thumbImg) {
		this.thumbImg = thumbImg;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSu() {
		return su;
	}
	public void setSu(int su) {
		this.su = su;
	}
	@Override
	public String toString() {
		return "CartTestVO [idx=" + idx + ", product=" + product + ", thumbImg=" + thumbImg + ", price=" + price + ", su="
				+ su + ", lastTotalPrice=" + lastTotalPrice + "]";
	}
	public int getLastTotalPrice() {
		return lastTotalPrice;
	}
	public void setLastTotalPrice(int lastTotalPrice) {
		this.lastTotalPrice = lastTotalPrice;
	}
}
