package com.spring.cj2103s01.vo;

public class MailVO {
	private String toMail;
	private String title;
	private String content;

	public String getToMail() {
		return toMail;
	}

	public void setToMail(String toMail) {
		this.toMail = toMail;
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

	@Override
	public String toString() {
		return "MailVO [toMail=" + toMail + ", title=" + title + ", content=" + content + "]";
	}

}
