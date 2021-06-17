package com.spring.cj2103s10.pagination;

public class PaginationVO {
	private int pag;
	private int pageSize;
	private int totRecCnt;
	private int totPage;
	private int startIndexNo;
	private int curScrStartNo;
	private int blockSize;
	
	public int getPag() {
		return pag;
	}
	public void setPag(int pag) {
		this.pag = pag;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotRecCnt() {
		return totRecCnt;
	}
	public void setTotRecCnt(int totRecCnt) {
		this.totRecCnt = totRecCnt;
	}
	public int getTotPage() {
		return totPage;
	}
	public void setTotPage(int totPage) {
		this.totPage = totPage;
	}
	public int getStartIndexNo() {
		return startIndexNo;
	}
	public void setStartIndexNo(int startIndexNo) {
		this.startIndexNo = startIndexNo;
	}
	public int getCurScrStartNo() {
		return curScrStartNo;
	}
	public void setCurScrStartNo(int curScrStartNo) {
		this.curScrStartNo = curScrStartNo;
	}
	public int getBlockSize() {
		return blockSize;
	}
	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}
	@Override
	public String toString() {
		return "PaginationVO [pag=" + pag + ", pageSize=" + pageSize + ", totRecCnt=" + totRecCnt + ", totPage=" + totPage
				+ ", startIndexNo=" + startIndexNo + ", curScrStartNo=" + curScrStartNo + ", blockSize=" + blockSize + "]";
	}
}
