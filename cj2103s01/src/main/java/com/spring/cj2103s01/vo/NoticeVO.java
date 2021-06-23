package com.spring.cj2103s01.vo;

public class NoticeVO {
	private int nId;
	private String nWriter;
	private String nTitle;
	private String nName;
	private String nPwd;
	private String nContent;
	private String nFile;
	private String nRdate;
	private int nHit;
	private int diffTime;

	public int getnId() {
		return nId;
	}

	public void setnId(int nId) {
		this.nId = nId;
	}

	public String getnWriter() {
		return nWriter;
	}

	public void setnWriter(String nWriter) {
		this.nWriter = nWriter;
	}

	public String getnPwd() {
		return nPwd;
	}

	public void setnPwd(String nPwd) {
		this.nPwd = nPwd;
	}

	public String getnTitle() {
		return nTitle;
	}

	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
	}

	public String getnContent() {
		return nContent;
	}

	public void setnContent(String nContent) {
		this.nContent = nContent;
	}

	public String getnFile() {
		return nFile;
	}

	public void setnFile(String nFile) {
		this.nFile = nFile;
	}

	public String getnRdate() {
		return nRdate;
	}

	public void setnRdate(String nRdate) {
		this.nRdate = nRdate;
	}

	public int getnHit() {
		return nHit;
	}

	public void setnHit(int nHit) {
		this.nHit = nHit;
	}

	public int getDiffTime() {
		return diffTime;
	}

	public void setDiffTime(int diffTime) {
		this.diffTime = diffTime;
	}

	public String getnName() {
		return nName;
	}

	public void setnName(String nName) {
		this.nName = nName;
	}

	@Override
	public String toString() {
		return "NoticeVO [nId=" + nId + ", nWriter=" + nWriter + ", nPwd=" + nPwd + ", nTitle=" + nTitle + ", nContent="
				+ nContent + ", nFile=" + nFile + ", nRdate=" + nRdate + ", nHit=" + nHit + "]";
	}

}
