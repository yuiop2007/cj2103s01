package com.spring.cj2103s10.vo;

public class PhotoVO {
	private int idx;
	private String name;
	private String part;
	private String title;
	private String content;
	private String thumbnail;
	private String wDate;
	private String hostIp;
	private int readNum;
	
	private String diffTime;  //  24시간에 따라서 '날짜/시간'으로 보여주기위한 필드 
	
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
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getwDate() {
		return wDate;
	}
	public void setwDate(String wDate) {
		this.wDate = wDate;
	}
	public String getHostIp() {
		return hostIp;
	}
	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}
	public int getReadNum() {
		return readNum;
	}
	public void setReadNum(int readNum) {
		this.readNum = readNum;
	}
	public String getDiffTime() {
		return diffTime;
	}
	public void setDiffTime(String diffTime) {
		this.diffTime = diffTime;
	}
	@Override
	public String toString() {
		return "PhotoVo [idx=" + idx + ", name=" + name + ", part=" + part + ", title=" + title + ", content=" + content
				+ ", thumbnail=" + thumbnail + ", wDate=" + wDate + ", hostIp=" + hostIp + ", readNum=" + readNum
				+ ", diffTime=" + diffTime + "]";
	}
}
