package com.spring.cj2103s01.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spring.cj2103s01.vo.NoticeVO;

public interface NoticeService {
	public List<NoticeVO> getNoticeList(int startIndexNo, int pageSize);

	public int totRecCnt();

	public int setNoticeInput(MultipartFile file, NoticeVO vo, String root);

}
