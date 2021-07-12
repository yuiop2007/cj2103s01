package com.spring.cj2103s01.service;

import java.util.List;

import com.spring.cj2103s01.vo.MemberVO;


public interface MemberService {

	public void setMemberInput(MemberVO vo);

	public MemberVO getIdCheck(String mId);

	public void setMemberUpdate(MemberVO vo);

	public void mDelete(String mId);

	public MemberVO getPwdSearch(String mId, String mEmail);

	public void setPwdChange(String mId, String encode);

	public MemberVO getNameSearch(String mName, String mEmail);

	public List<MemberVO> getMemberList();

	public int totMemberCnt();

	public int dropMemberCnt();

	public List<MemberVO> getMemberListLevel(int startIndexNo, int pageSize, String mLevel);

	public List<MemberVO> getMemberListMid(int startIndexNo, int pageSize, String mId);

	public void mLevelCheck(String mId, String mLevel);

	public void memberDelete(String idx);

	public void mPointInput(String idx, int point);

	public List<MemberVO> getCouponMember(int cmCate);

	public void updateMemberBuy(String mId);

	public void setUpdatePoint(String mId, int mile);

	public int totSellCnt();

	public void updateMemberPoint(String mId, int point);

	public void updateMemberBuyDown(String mId);
	
}
