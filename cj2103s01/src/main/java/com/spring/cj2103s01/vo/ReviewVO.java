package com.spring.cj2103s01.vo;

public class ReviewVO {
	private int rId;
	private int pId;
	private int rRating;
	private String rWriter;
	private String rTitle;
	private String rName;
	private String rPwd;
	private String rContent;
	private String rRdate;
	private int rHit;

	private int diffTime;
	private String oriContent;

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public int getrRating() {
		return rRating;
	}

	public void setrRating(int rRating) {
		this.rRating = rRating;
	}

	public String getrWriter() {
		return rWriter;
	}

	public void setrWriter(String rWriter) {
		this.rWriter = rWriter;
	}

	public String getrTitle() {
		return rTitle;
	}

	public void setrTitle(String rTitle) {
		this.rTitle = rTitle;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	public String getrPwd() {
		return rPwd;
	}

	public void setrPwd(String rPwd) {
		this.rPwd = rPwd;
	}

	public String getrContent() {
		return rContent;
	}

	public void setrContent(String rContent) {
		this.rContent = rContent;
	}

	public String getrRdate() {
		return rRdate;
	}

	public void setrRdate(String rRdate) {
		this.rRdate = rRdate;
	}

	public int getrHit() {
		return rHit;
	}

	public void setrHit(int rHit) {
		this.rHit = rHit;
	}

	public int getDiffTime() {
		return diffTime;
	}

	public void setDiffTime(int diffTime) {
		this.diffTime = diffTime;
	}

	public String getOriContent() {
		return oriContent;
	}

	public void setOriContent(String oriContent) {
		this.oriContent = oriContent;
	}

	@Override
	public String toString() {
		return "ReviewVO [rId=" + rId + ", pId=" + pId + ", rRating=" + rRating + ", rWriter=" + rWriter + ", rTitle="
				+ rTitle + ", rName=" + rName + ", rPwd=" + rPwd + ", rContent=" + rContent + ", rRdate=" + rRdate
				+ ", rHit=" + rHit + ", diffTime=" + diffTime + ", oriContent=" + oriContent + "]";
	}

}
