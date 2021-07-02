package com.spring.cj2103s10.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.cj2103s10.dao.AdminDAO;
import com.spring.cj2103s10.vo.MemberVO;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDAO adminDAO;

	@Override
	public List<MemberVO> getMemberList(int startIndexNo, int pageSize) {
		return adminDAO.getMemberList(startIndexNo, pageSize);
	}

	@Override
	public List<MemberVO> getMemberListLevel(int startIndexNo, int pageSize, String level) {
		return adminDAO.getMemberListLevel(startIndexNo, pageSize, level);
	}

	@Override
	public List<MemberVO> getMemberListMid(int startIndexNo, int pageSize, String mid) {
		return adminDAO.getMemberListMid(startIndexNo, pageSize, mid);
	}

	@Override
	public void aMLevelCheck(String mid, String level) {
		adminDAO.aMLevelCheck(mid, level);
	}

	@Override
	public MemberVO getAMInfor(String mid) {
		return adminDAO.getAMInfor(mid);
	}

	@Override
	public void memberDelete(int idx) {
		adminDAO.memberDelete(idx);
	}
	
}
