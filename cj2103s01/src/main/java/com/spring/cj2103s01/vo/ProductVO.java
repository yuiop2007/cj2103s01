package com.spring.cj2103s01.vo;

public class ProductVO {
	private int pId;
	private String pName;
	private String pCate;
	private int pPrice;
	private int pSale;
	private int pStock;
	private String pImage;
	private String pContent;
	private String pDelivery;
	private String pRdate;
	private int pHit;
	private int pSell;

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpCate() {
		return pCate;
	}

	public void setpCate(String pCate) {
		this.pCate = pCate;
	}

	public int getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	public int getpSale() {
		return pSale;
	}

	public void setpSale(int pSale) {
		this.pSale = pSale;
	}

	public int getpStock() {
		return pStock;
	}

	public void setpStock(int pStock) {
		this.pStock = pStock;
	}

	public String getpImage() {
		return pImage;
	}

	public void setpImage(String pImage) {
		this.pImage = pImage;
	}

	public String getpContent() {
		return pContent;
	}

	public void setpContent(String pContent) {
		this.pContent = pContent;
	}

	public String getpDelivery() {
		return pDelivery;
	}

	public void setpDelivery(String pDelivery) {
		this.pDelivery = pDelivery;
	}

	public String getpRdate() {
		return pRdate;
	}

	public void setpRdate(String pRdate) {
		this.pRdate = pRdate;
	}

	public int getpHit() {
		return pHit;
	}

	public void setpHit(int pHit) {
		this.pHit = pHit;
	}

	public int getpSell() {
		return pSell;
	}

	public void setpSell(int pSell) {
		this.pSell = pSell;
	}

	@Override
	public String toString() {
		return "ProductVO [pId=" + pId + ", pName=" + pName + ", pCate=" + pCate + ", pPrice=" + pPrice + ", pSale="
				+ pSale + ", pStock=" + pStock + ", pImage=" + pImage + ", pContent=" + pContent + ", pDelivery="
				+ pDelivery + ", pRdate=" + pRdate + ", pHit=" + pHit + ", pSell=" + pSell + "]";
	}

}
