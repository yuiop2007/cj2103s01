package com.spring.cj2103s10.vo;

public class VotePersonInforVO {
	private int idx;
	private String gender;
	private String age;
	private String job;
	private String area;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	@Override
	public String toString() {
		return "VotePersonInfor [idx=" + idx + ", gender=" + gender + ", age=" + age + ", job=" + job + ", area=" + area
				+ "]";
	}
}
