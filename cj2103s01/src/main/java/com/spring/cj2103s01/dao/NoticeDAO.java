package com.spring.cj2103s01.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.cj2103s01.vo.NoticeVO;

public interface NoticeDAO {

	public List<NoticeVO> getNoticeList(@Param("startIndexNo") int startIndexNo, @Param("pageSize") int pageSize);

	public int totRecCnt();

}
