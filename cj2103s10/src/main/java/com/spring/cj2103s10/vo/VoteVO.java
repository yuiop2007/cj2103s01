package com.spring.cj2103s10.vo;

public class VoteVO {
  private int idx;
  private int personInforIdx;
  private int popularIdx;
  private int voteNum;
  
  private int voteSum;
  
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getPersonInforIdx() {
		return personInforIdx;
	}
	public void setPersonInforIdx(int personInforIdx) {
		this.personInforIdx = personInforIdx;
	}
	public int getPopularIdx() {
		return popularIdx;
	}
	public void setPopularIdx(int popularIdx) {
		this.popularIdx = popularIdx;
	}
	public int getVoteNum() {
		return voteNum;
	}
	public void setVoteNum(int voteNum) {
		this.voteNum = voteNum;
	}
	public int getVoteSum() {
		return voteSum;
	}
	public void setVoteSum(int voteSum) {
		this.voteSum = voteSum;
	}
	@Override
	public String toString() {
		return "VoteVo [idx=" + idx + ", personInforIdx=" + personInforIdx + ", popularIdx=" + popularIdx + ", voteNum="
				+ voteNum + ", voteSum=" + voteSum + "]";
	}
}
