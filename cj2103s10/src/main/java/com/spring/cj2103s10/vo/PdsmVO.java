package com.spring.cj2103s10.vo;

public class PdsmVO {
	private int idx;
	private String mid;
	private String nickName;
	private String fName;
	private String rfName;
	private String title;
	private String part;
	private String pwd;
	private String fDate;
	private int fSize;
	private int downNum;
	private String openSw;
	private String content;
	
	// 멀티파일에 대한 정보를 저장하기 위한 필드 2개
	private String fNames;
	private String rfNames;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getRfName() {
		return rfName;
	}
	public void setRfName(String rfName) {
		this.rfName = rfName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getfDate() {
		return fDate;
	}
	public void setfDate(String fDate) {
		this.fDate = fDate;
	}
	public int getfSize() {
		return fSize;
	}
	public void setfSize(int fSize) {
		this.fSize = fSize;
	}
	public int getDownNum() {
		return downNum;
	}
	public void setDownNum(int downNum) {
		this.downNum = downNum;
	}
	public String getOpenSw() {
		return openSw;
	}
	public void setOpenSw(String openSw) {
		this.openSw = openSw;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getfNames() {
		return fNames;
	}
	public void setfNames(String fNames) {
		this.fNames = fNames;
	}
	public String getRfNames() {
		return rfNames;
	}
	public void setRfNames(String rfNames) {
		this.rfNames = rfNames;
	}
	@Override
	public String toString() {
		return "PdsmVO [idx=" + idx + ", mid=" + mid + ", nickName=" + nickName + ", fName=" + fName + ", rfName=" + rfName
				+ ", title=" + title + ", part=" + part + ", pwd=" + pwd + ", fDate=" + fDate + ", fSize=" + fSize
				+ ", downNum=" + downNum + ", openSw=" + openSw + ", content=" + content + ", fNames=" + fNames + ", rfNames="
				+ rfNames + "]";
	}
}
