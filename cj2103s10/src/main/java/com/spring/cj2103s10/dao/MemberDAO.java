package com.spring.cj2103s10.dao;

import org.apache.ibatis.annotations.Param;

import com.spring.cj2103s10.vo.MemberVO;

public interface MemberDAO {

	public MemberVO getIdCheck(@Param("mid") String mid);

	public MemberVO getNicknameCheck(@Param("nickName") String nickName);

	public void setMemberInput(@Param("vo")  MemberVO vo);

	public void setMemberUpdate(@Param("vo")  MemberVO vo);

	public void mDelete(@Param("mid") String mid);

	public MemberVO getPwdSearch(@Param("mid") String mid, @Param("toMail") String toMail);

	public void setPwdChange(@Param("mid") String mid, @Param("encode") String encode);

	public MemberVO getIdSearch(@Param("tel") String tel, @Param("toMail") String toMail);

	public int totRecCnt();

	public int totRecCntLevel(@Param("searchString") String searchString);

	public int totRecCntMid(@Param("partValue") String partValue);

}
