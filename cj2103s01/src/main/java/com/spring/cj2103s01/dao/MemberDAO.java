package com.spring.cj2103s01.dao;

import org.apache.ibatis.annotations.Param;

import com.spring.cj2103s01.vo.MemberVO;

public interface MemberDAO {

	public void setMemberInput(@Param("vo") MemberVO vo);
	
}
