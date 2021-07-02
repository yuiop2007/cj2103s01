package com.spring.cj2103s10.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.cj2103s10.vo.MemberVO;

public interface AdminDAO {

	public List<MemberVO> getMemberList(@Param("startIndexNo") int startIndexNo, @Param("pageSize") int pageSize);

	public List<MemberVO> getMemberListLevel(@Param("startIndexNo") int startIndexNo, @Param("pageSize") int pageSize, @Param("level") String level);

	public List<MemberVO> getMemberListMid(@Param("startIndexNo") int startIndexNo, @Param("pageSize") int pageSize, @Param("mid") String mid);

	public void aMLevelCheck(@Param("mid") String mid, @Param("level") String level);

	public MemberVO getAMInfor(@Param("mid") String mid);

	public void memberDelete(@Param("idx") int idx);

}
