package com.spring.cj2103s10.vo;

public class ScheduleVO {
  private int idx;
  private String mid;
  private String scDate;
  private String memory;
  private String title;
  private String content;
  
  private String ymd;
  
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
	public String getScDate() {
		return scDate;
	}
	public void setScDate(String scDate) {
		this.scDate = scDate;
	}
	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory = memory;
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
	public String getYmd() {
		return ymd;
	}
	public void setYmd(String ymd) {
		this.ymd = ymd;
	}
	@Override
	public String toString() {
		return "ScheduleVO [idx=" + idx + ", mid=" + mid + ", scDate=" + scDate + ", memory=" + memory + ", title=" + title
				+ ", content=" + content + ", ymd=" + ymd + "]";
	}
}
