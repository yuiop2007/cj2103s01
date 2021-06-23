package com.spring.cj2103s01.vo;

public class EventVO {
	private int eId;
	private String eWriter;
	private String eTitle;
	private String eName;
	private String ePwd;
	private String eContent;
	private String eFile;
	private String eRdate;
	private int eHit;
	private int diffTime;
	
	public int geteId() {
		return eId;
	}

	public void seteId(int eId) {
		this.eId = eId;
	}

	public String geteTitle() {
		return eTitle;
	}

	public void seteTitle(String eTitle) {
		this.eTitle = eTitle;
	}

	public String geteWriter() {
		return eWriter;
	}

	public void seteWriter(String eWriter) {
		this.eWriter = eWriter;
	}

	public String getePwd() {
		return ePwd;
	}

	public void setePwd(String ePwd) {
		this.ePwd = ePwd;
	}

	public String geteContent() {
		return eContent;
	}

	public void seteContent(String eContent) {
		this.eContent = eContent;
	}

	public String geteFile() {
		return eFile;
	}

	public void seteFile(String eFile) {
		this.eFile = eFile;
	}

	public String geteRdate() {
		return eRdate;
	}

	public void seteRdate(String eRdate) {
		this.eRdate = eRdate;
	}

	public int geteHit() {
		return eHit;
	}

	public void seteHit(int eHit) {
		this.eHit = eHit;
	}
	
	public int getDiffTime() {
		return diffTime;
	}

	public void setDiffTime(int diffTime) {
		this.diffTime = diffTime;
	}
	
	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	@Override
	public String toString() {
		return "EventVO [eId=" + eId + ", eTitle=" + eTitle + ", eWriter=" + eWriter + ", ePwd=" + ePwd + ", eContent="
				+ eContent + ", eFile=" + eFile + ", eRdate=" + eRdate + ", eHit=" + eHit + "]";
	}

}
