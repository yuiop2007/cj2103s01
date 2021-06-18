package com.spring.cj2103s01.service;

import java.util.List;

import com.spring.cj2103s01.vo.NoticeVO;

public interface NoticeService {
	public List<NoticeVO> getNoticeList(int startIndexNo, int pageSize);

	public int totRecCnt();
}
