package com.spring.cj2103s01.service;

import java.util.List;

import com.spring.cj2103s01.vo.QnaVO;

public interface QnaService {

	List<QnaVO> getQnaList(int startIndexNo, int pageSize);

	List<QnaVO> getQnaContentList(int pId, int startIndexNo, int qpageSize);

	int setQnaInput(QnaVO vo);

	void addReadNum(int qId);

	QnaVO getQnaContent(int qId);

	QnaVO getIdCheck(int qId);

	void qDelete(int qId);

	void qnaUpdateOk(QnaVO vo);

	List<QnaVO> getQnaSearchList(int startIndexNo, int pageSize, String search, String searchString);

	void setQnaUpdate(QnaVO vo);

	QnaVO getQnaLastVo();

	void qnaReplyOk(QnaVO vo);

	List<QnaVO> getQnaMemberList(String mId, int startIndexNo, int qpageSize);

	int qnaCheck(int qId);

}
 