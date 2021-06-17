package com.spring.cj2103s01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.cj2103s01.dao.MemberDAO;
import com.spring.cj2103s01.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDAO memberDAO;

	@Override
	public void setMemberInput(MemberVO vo) {
		memberDAO.setMemberInput(vo);
	}
	
}
