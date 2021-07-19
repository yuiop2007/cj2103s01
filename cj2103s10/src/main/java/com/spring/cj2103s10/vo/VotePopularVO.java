package com.spring.cj2103s10.vo;

public class VotePopularVO {
	private int idx;
	private String part;
	private String title;
	private String subTitle;
	private String realImg;
	private String thumbImg;
	private String progress;
	
	private int voteCnt;  // 투표에 참여한 사람수
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
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
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getRealImg() {
		return realImg;
	}
	public void setRealImg(String realImg) {
		this.realImg = realImg;
	}
	public String getThumbImg() {
		return thumbImg;
	}
	public void setThumbImg(String thumbImg) {
		this.thumbImg = thumbImg;
	}
	public int getVoteCnt() {
		return voteCnt;
	}
	public void setVoteCnt(int voteCnt) {
		this.voteCnt = voteCnt;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	@Override
	public String toString() {
		return "VotePopularVo [idx=" + idx + ", part=" + part + ", title=" + title + ", subTitle=" + subTitle + ", realImg="
				+ realImg + ", thumbImg=" + thumbImg + ", progress=" + progress + ", voteCnt=" + voteCnt + "]";
	}
}
