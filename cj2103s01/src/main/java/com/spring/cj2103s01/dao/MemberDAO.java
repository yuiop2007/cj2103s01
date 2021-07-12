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

	public List<MemberVO> getMemberListLevel(@Param("startIndexNo") int startIndexNo,@Param("pageSize") int pageSize,@Param("mLevel") String mLevel);

	public int totRecCntMid(@Param("search") String search);

	public int totRecCntLevel(@Param("searchString") String searchString);

	public List<MemberVO> getMemberListMid(@Param("startIndexNo") int startIndexNo,@Param("pageSize") int pageSize,@Param("mId") String mId);

	public void mLevelCheck(@Param("mId") String mId,@Param("mLevel") String mLevel);

	public void memberDelete(@Param("idx") String idx);

	public void mPointInput(@Param("idx") String idx,@Param("point") int point);

	public List<MemberVO> getCouponMember(@Param("cmCate") int cmCate);

	public void updateMemberBuy(@Param("mId") String mId);

	public void setUpdatePoint(@Param("mId") String mId, @Param("mile") int mile);

	public int totSellCnt();

	public void updateMemberPoint(@Param("mId") String mId, @Param("point") int point);

	public void updateMemberBuyDown(@Param("mId") String mId);

}
