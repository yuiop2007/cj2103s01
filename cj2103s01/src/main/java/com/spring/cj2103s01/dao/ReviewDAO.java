package com.spring.cj2103s01.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.cj2103s01.vo.ReviewVO;

public interface ReviewDAO {

	int totRecCnt();

	int totSearchRecCnt(@Param("search") String search, @Param("searchString") String searchString);

	List<ReviewVO> getReviewList(@Param("startIndexNo") int startIndexNo, @Param("pageSize") int pageSize);

	List<ReviewVO> getReviewContentList(@Param("pId") int pId, @Param("startIndexNo") int startIndexNo, @Param("rpageSize") int rpageSize);

	int totpIdRecCnt(@Param("pId") int pId);

	void setReviewInput(@Param("vo") ReviewVO vo);

	ReviewVO getIdCheck(@Param("rId") int rId);

	void rDelete(@Param("rId") int rId);

	void addReadNum(@Param("rId") int rId);

	ReviewVO getReviewContent(@Param("rId") int rId);

	void reviewUpdateOk(@Param("vo") ReviewVO vo);

	List<ReviewVO> getReviewSearchList(@Param("startIndexNo") int startIndexNo, @Param("pageSize") int pageSize, @Param("search") String search,@Param("searchString") String searchString);

}
