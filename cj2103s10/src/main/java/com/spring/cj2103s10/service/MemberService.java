package com.spring.cj2103s10.service;

import org.springframework.web.multipart.MultipartFile;

import com.spring.cj2103s10.vo.MemberVO;

public interface MemberService {

	public MemberVO getIdCheck(String mid);

	public MemberVO getNicknameCheck(String nickName);

	public int setMemberInput(MultipartFile file, MemberVO vo);

	public void setMemberUpdate(MultipartFile mFile, MemberVO vo);

	public void mDelete(String mid);

	public MemberVO getPwdSearch(String mid, String toMail);

	public void setPwdChange(String mid, String encode);

	public MemberVO getIdSearch(String tel, String toMail);

}
