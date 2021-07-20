package com.spring.cj2103s01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.cj2103s01.dao.QnaDAO;
import com.spring.cj2103s01.vo.QnaVO;

@Service
public class QnaServiceImpl implements QnaService {
	
	@Autowired
	QnaDAO qnaDAO;

	@Override
	public List<QnaVO> getQnaList(int startIndexNo, int pageSize) {
		return qnaDAO.getQnaList(startIndexNo, pageSize);
	}

	@Override
	public List<QnaVO> getQnaContentList(int pId, int startIndexNo, int qpageSize) {
		return qnaDAO.getQnaContentList(pId, startIndexNo, qpageSize);
	}

	@Override
	public int setQnaInput(QnaVO vo) {
		int res=0;
		qnaDAO.setQnaInput(vo);
		res = 1;
		return res;
	}

	@Override
	public void addReadNum(int qId) {
		qnaDAO.addReadNum(qId);
	}

	@Override
	public QnaVO getQnaContent(int qId) {
		return qnaDAO.getQnaContent(qId);
	}

	@Override
	public QnaVO getIdCheck(int qId) {
		return qnaDAO.getIdCheck(qId);
	}

	@Override
	public void qDelete(int qId) {
		qnaDAO.qDelete(qId);
	}

	@Override
	public void qnaUpdateOk(QnaVO vo) {
		qnaDAO.qnaUpdateOk(vo);
	}

	@Override
	public List<QnaVO> getQnaSearchList(int startIndexNo, int pageSize, String search, String searchString) {
		return qnaDAO.getQnaSearchList(startIndexNo, pageSize, search, searchString);
	}

	@Override
	public void setQnaUpdate(QnaVO vo) {
		qnaDAO.setQnaUpdate(vo);
	}

	@Override
	public QnaVO getQnaLastVo() {
		return qnaDAO.getQnaLastVo();
	}

	@Override
	public void qnaReplyOk(QnaVO vo) {
		qnaDAO.qnaReplyOk(vo);
	}

	@Override
	public List<QnaVO> getQnaMemberList(String mId, int startIndexNo, int qpageSize) {
		return qnaDAO.getQnaMemberList(mId, startIndexNo, qpageSize);
	}

	@Override
	public int qnaCheck(int qId) {
		return qnaDAO.qnaCheck(qId);
	}
	
}
