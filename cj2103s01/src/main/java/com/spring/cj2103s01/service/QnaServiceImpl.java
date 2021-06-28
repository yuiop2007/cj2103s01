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
	
}
