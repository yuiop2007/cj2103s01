package com.spring.cj2103s01.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.cj2103s01.vo.EventVO;

public interface EventDAO {

	public List<EventVO> getEventList(@Param("startIndexNo") int startIndexNo, @Param("pageSize") int pageSize);

	public int totRecCnt();

	public void setEventInput(@Param("vo") EventVO vo);

	public int totSearchRecCnt(@Param("search") String search, @Param("searchString") String searchString);

	public List<EventVO> getEventSearchList(@Param("startIndexNo") int startIndexNo, @Param("pageSize") int pageSize, @Param("search") String search, @Param("searchString") String searchString);

	public void addReadNum(@Param("idx") int idx);

	public EventVO getEventContent(@Param("idx") int idx);

}
