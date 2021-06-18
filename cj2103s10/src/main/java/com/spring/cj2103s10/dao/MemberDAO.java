package com.spring.cj2103s10.dao;

import org.apache.ibatis.annotations.Param;

import com.spring.cj2103s10.vo.MemberVO;

public interface MemberDAO {

	public MemberVO getIdCheck(@Param("mid") String mid);

	public MemberVO getNicknameCheck(@Param("nickName") String nickName);

	public void setMemberInput(@Param("vo")  MemberVO vo);

}
