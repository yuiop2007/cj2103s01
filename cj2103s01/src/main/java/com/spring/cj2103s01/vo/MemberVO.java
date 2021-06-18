package com.spring.cj2103s01.vo;

public class MemberVO {
	private String mId;
	private String mPwd;
	private String mName;
	private String mPost;
	private String mAddress;
	private String mPhone;
	private String mEmail;
	private String mGender;
	private String mRdate;
	private String mBirth;
	private String mSolar;
	private int mLevel;
	private int mBuy;
	private int mDrop;
	private String mCmid;
	private int mPoint;
	
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmPwd() {
		return mPwd;
	}
	public void setmPwd(String mPwd) {
		this.mPwd = mPwd;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmPost() {
		return mPost;
	}
	public void setmPost(String mPost) {
		this.mPost = mPost;
	}
	public String getmAddress() {
		return mAddress;
	}
	public void setmAddress(String mAddress) {
		this.mAddress = mAddress;
	}
	public String getmPhone() {
		return mPhone;
	}
	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}
	public String getmEmail() {
		return mEmail;
	}
	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}
	public String getmGender() {
		return mGender;
	}
	public void setmGender(String mGender) {
		this.mGender = mGender;
	}
	public String getmRdate() {
		return mRdate;
	}
	public void setmRdate(String mRdate) {
		this.mRdate = mRdate;
	}
	public String getmBirth() {
		return mBirth;
	}
	public void setmBirth(String mBirth) {
		this.mBirth = mBirth;
	}
	public String getmSolar() {
		return mSolar;
	}
	public void setmSolar(String mSolar) {
		this.mSolar = mSolar;
	}
	public int getmLevel() {
		return mLevel;
	}
	public void setmLevel(int mLevel) {
		this.mLevel = mLevel;
	}
	public int getmBuy() {
		return mBuy;
	}
	public void setmBuy(int mBuy) {
		this.mBuy = mBuy;
	}
	public int getmDrop() {
		return mDrop;
	}
	public void setmDrop(int mDrop) {
		this.mDrop = mDrop;
	}
	public String getmCmid() {
		return mCmid;
	}
	public void setmCmid(String mCmid) {
		this.mCmid = mCmid;
	}
	public int getmPoint() {
		return mPoint;
	}
	public void setmPoint(int mPoint) {
		this.mPoint = mPoint;
	}
	@Override
	public String toString() {
		return "MemberVO [mId=" + mId + ", mPwd=" + mPwd + ", mName=" + mName + ", mPost=" + mPost + ", mAddress="
				+ mAddress + ", mPhone=" + mPhone + ", mEmail=" + mEmail + ", mGender=" + mGender + ", mRdate=" + mRdate
				+ ", mBirth=" + mBirth + ", mSolar=" + mSolar + ", mLevel=" + mLevel + ", mBuy=" + mBuy + ", mDrop="
				+ mDrop + ", mCmid=" + mCmid + ", mPoint=" + mPoint + "]";
	}

}