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
	
}
