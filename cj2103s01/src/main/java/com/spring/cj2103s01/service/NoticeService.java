package com.spring.cj2103s01.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spring.cj2103s01.vo.NoticeVO;

public interface NoticeService {
	
	public List<NoticeVO> getNoticeList(int startIndexNo, int pageSize);

	public int setNoticeInput(MultipartFile file, NoticeVO vo, String root);

	public List<NoticeVO> getNoticeSearchList(int startIndexNo, int pageSize, String search, String searchString);

	public void addReadNum(int idx);

	public NoticeVO getNoticeContent(int idx);

	public NoticeVO getIdCheck(int idx);

	public void noticeUpdateOk(NoticeVO vo);

	public void nDelete(int idx);

}
