package com.spring.cj2103s01.vo;

public class QnaVO {
	private int qId;
	private int pId;
	private String qCate;
	private String qWriter;
	private String qTitle;
	private String qName;
	private String qPwd;
	private String qContent;
	private int qSecret;
	private String qRdate;
	private int qHit;
	private int originNo;
	private int groupNo;

	private int diffTime;
	private String oriContent;

	public int getqId() {
		return qId;
	}

	public void setqId(int qId) {
		this.qId = qId;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getqCate() {
		return qCate;
	}

	public void setqCate(String qCate) {
		this.qCate = qCate;
	}

	public String getqWriter() {
		return qWriter;
	}

	public void setqWriter(String qWriter) {
		this.qWriter = qWriter;
	}

	public String getqTitle() {
		return qTitle;
	}

	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}

	public String getqName() {
		return qName;
	}

	public void setqName(String qName) {
		this.qName = qName;
	}

	public String getqPwd() {
		return qPwd;
	}

	public void setqPwd(String qPwd) {
		this.qPwd = qPwd;
	}

	public String getqContent() {
		return qContent;
	}

	public void setqContent(String qContent) {
		this.qContent = qContent;
	}

	public int getqSecret() {
		return qSecret;
	}

	public void setqSecret(int qSecret) {
		this.qSecret = qSecret;
	}

	public String getqRdate() {
		return qRdate;
	}

	public void setqRdate(String qRdate) {
		this.qRdate = qRdate;
	}

	public int getqHit() {
		return qHit;
	}

	public void setqHit(int qHit) {
		this.qHit = qHit;
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

	public int getOriginNo() {
		return originNo;
	}

	public void setOriginNo(int originNo) {
		this.originNo = originNo;
	}

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	@Override
	public String toString() {
		return "QnaVO [qId=" + qId + ", pId=" + pId + ", qCate=" + qCate + ", qWriter=" + qWriter + ", qTitle=" + qTitle
				+ ", qName=" + qName + ", qPwd=" + qPwd + ", qContent=" + qContent + ", qSecret=" + qSecret
				+ ", qRdate=" + qRdate + ", qHit=" + qHit + ", originNo=" + originNo + ", groupNo=" + groupNo
				+ ", diffTime=" + diffTime + ", oriContent=" + oriContent + "]";
	}

	
	

}
