package com.spring.cj2103s01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.cj2103s01.dao.NoticeDAO;
import com.spring.cj2103s01.vo.NoticeVO;

@Service
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	NoticeDAO noticeDAO;
	
	@Override
	public List<NoticeVO> getNoticeList(int startIndexNo, int pageSize) {
		return noticeDAO.getNoticeList(startIndexNo, pageSize);
	}

	@Override
	public int totRecCnt() {
		return noticeDAO.totRecCnt();
	}
}
