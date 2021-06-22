package com.spring.cj2103s10.vo;

public class BoardVO {
	private int idx;
	private String name;
	private String title;
	private String email;
	private String pwd;
	private String wDate;
	private int readNum;
	private String hostIp;
	private int good;
	private String content;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getwDate() {
		return wDate;
	}
	public void setwDate(String wDate) {
		this.wDate = wDate;
	}
	public int getReadNum() {
		return readNum;
	}
	public void setReadNum(int readNum) {
		this.readNum = readNum;
	}
	public String getHostIp() {
		return hostIp;
	}
	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "BoardVO [idx=" + idx + ", name=" + name + ", title=" + title + ", email=" + email + ", pwd=" + pwd
				+ ", wDate=" + wDate + ", readNum=" + readNum + ", hostIp=" + hostIp + ", good=" + good + ", content=" + content
				+ "]";
	}
}
