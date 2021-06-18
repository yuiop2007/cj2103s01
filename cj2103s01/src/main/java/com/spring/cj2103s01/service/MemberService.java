package com.spring.cj2103s01.service;

import com.spring.cj2103s01.vo.MemberVO;


public interface MemberService {

	public void setMemberInput(MemberVO vo);

	public MemberVO getIdCheck(String mId);
	
}
