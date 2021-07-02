package com.spring.cj2103s10.service;

import java.util.List;

import com.spring.cj2103s10.vo.MemberVO;

public interface AdminService {

	public List<MemberVO> getMemberList(int startIndexNo, int pageSize);

	public List<MemberVO> getMemberListLevel(int startIndexNo, int pageSize, String level);

	public List<MemberVO> getMemberListMid(int startIndexNo, int pageSize, String mid);

	public void aMLevelCheck(String mid, String level);

	public MemberVO getAMInfor(String mid);

	public void memberDelete(int idx);

}
