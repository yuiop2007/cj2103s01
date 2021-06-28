package com.spring.cj2103s01.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.cj2103s01.vo.QnaVO;

public interface QnaDAO {

	int totRecCnt();

	int totSearchRecCnt(@Param("search") String search, @Param("searchString") String searchString);

	List<QnaVO> getQnaList(@Param("startIndexNo") int startIndexNo,@Param("pageSize") int pageSize);

	List<QnaVO> getQnaContentList(@Param("pId") int pId,@Param("startIndexNo") int startIndexNo,@Param("qpageSize") int qpageSize);

	void setQnaInput(@Param("vo") QnaVO vo);

	void addReadNum(@Param("qId") int qId);

	QnaVO getQnaContent(@Param("qId") int qId);


}
