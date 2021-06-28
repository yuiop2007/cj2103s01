package com.spring.cj2103s01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.cj2103s01.dao.ReviewDAO;
import com.spring.cj2103s01.vo.ReviewVO;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	ReviewDAO reviewDAO;

	@Override
	public List<ReviewVO> getReviewList(int startIndexNo, int pageSize) {
		return reviewDAO.getReviewList(startIndexNo, pageSize);
	}

	@Override
	public List<ReviewVO> getReviewContentList(int pId, int startIndexNo, int rpageSize) {
		return reviewDAO.getReviewContentList(pId, startIndexNo, rpageSize);
	}

	@Override
	public int setReviewInput(ReviewVO vo) {
		int res=0;
		reviewDAO.setReviewInput(vo);
		res = 1;
		return res;
	}

	@Override
	public ReviewVO getIdCheck(int rId) {
		return reviewDAO.getIdCheck(rId);
	}

	@Override
	public void rDelete(int rId) {
		reviewDAO.rDelete(rId);
	}

	@Override
	public void addReadNum(int rId) {
		reviewDAO.addReadNum(rId);
	}

	@Override
	public ReviewVO getReviewContent(int rId) {
		return reviewDAO.getReviewContent(rId);
	}

	@Override
	public void reviewUpdateOk(ReviewVO vo) {
		reviewDAO.reviewUpdateOk(vo);
	}

	@Override
	public List<ReviewVO> getReviewSearchList(int startIndexNo, int pageSize, String search, String searchString) {
		return reviewDAO.getReviewSearchList(startIndexNo, pageSize, search, searchString);
	}
}
