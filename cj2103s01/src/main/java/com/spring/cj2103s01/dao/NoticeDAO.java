package com.spring.cj2103s01.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.cj2103s01.vo.NoticeVO;

public interface NoticeDAO {

	public List<NoticeVO> getNoticeList(@Param("startIndexNo") int startIndexNo, @Param("pageSize") int pageSize);

	public int totRecCnt();

	public void setNoticeInput(@Param("vo") NoticeVO vo);

	public List<NoticeVO> getNoticeSearchList(@Param("startIndexNo") int startIndexNo, @Param("pageSize") int pageSize, @Param("search") String search, @Param("searchString") String searchString);

	public int totSearchRecCnt(@Param("search") String search, @Param("searchString") String searchString);

	public void addReadNum(@Param("idx") int idx);

	public NoticeVO getNoticeContent(@Param("idx") int idx);

}
