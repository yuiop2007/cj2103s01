package com.spring.cj2103s01.service;

import java.util.List;

import com.spring.cj2103s01.vo.ReviewVO;

public interface ReviewService {

	List<ReviewVO> getReviewList(int startIndexNo, int pageSize);

	List<ReviewVO> getReviewContentList(int pId, int startIndexNo, int rpageSize);

	int setReviewInput(ReviewVO vo);

	ReviewVO getIdCheck(int rId);

	void rDelete(int rId);

	void addReadNum(int rId);

	ReviewVO getReviewContent(int rId);

	void reviewUpdateOk(ReviewVO vo);

	List<ReviewVO> getReviewSearchList(int startIndexNo, int pageSize, String search, String searchString);

	List<ReviewVO> getReviewMemberList(String mId, int startIndexNo, int rpageSize);


}
