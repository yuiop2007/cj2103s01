package com.spring.cj2103s10.service;

import org.springframework.web.multipart.MultipartFile;

import com.spring.cj2103s10.vo.MemberVO;

public interface MemberService {

	public MemberVO getIdCheck(String mid);

	public MemberVO getNicknameCheck(String nickName);

	public int setMemberInput(MultipartFile file, MemberVO vo);

}
