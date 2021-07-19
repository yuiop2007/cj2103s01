package com.spring.cj2103s10.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spring.cj2103s10.vo.MemberVO;
import com.spring.cj2103s10.vo.NotifyVO;

public interface MemberService {

	public MemberVO getIdCheck(String mid);

	public MemberVO getNicknameCheck(String nickName);

	public int setMemberInput(MultipartFile file, MemberVO vo);

	public void setMemberUpdate(MultipartFile mFile, MemberVO vo);

	public void mDelete(String mid);

	public MemberVO getPwdSearch(String mid, String toMail);

	public void setPwdChange(String mid, String encode);

	public MemberVO getIdSearch(String tel, String toMail);

	public List<NotifyVO> getMNList();

	public NotifyVO getPopup(int idx);

}
