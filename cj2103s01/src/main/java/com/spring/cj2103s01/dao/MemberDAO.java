package com.spring.cj2103s01.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.cj2103s01.vo.MemberVO;

public interface MemberDAO {

	public void setMemberInput(@Param("vo") MemberVO vo);

	public MemberVO getIdCheck(@Param("mId") String mId);

	public void setMemberUpdate(@Param("vo") MemberVO vo);
 
	public void mDelete(@Param("mId") String mId);

	public MemberVO getPwdSearch(@Param("mId") String mId, @Param("mEmail") String mEmail);

	public void setPwdChange(@Param("mId") String mId, @Param("encode") String encode);

	public MemberVO getNameSearch(@Param("mName") String mName, @Param("mEmail") String mEmail);

	public List<MemberVO> getMemberList();

	public int totMemberCnt();

	public int dropMemberCnt();

	public int totSearchRecCnt(String search, String searchString);


}
