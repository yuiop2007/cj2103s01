package com.spring.cj2103s01.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spring.cj2103s01.vo.EventVO;

public interface EventService {

	public List<EventVO> getEventList(int startIndexNo, int pageSize);
	
	public int setEventInput(MultipartFile file, EventVO vo, String root);

	public List<EventVO> getEventSearchList(int startIndexNo, int pageSize, String search, String searchString);
	
	public void addReadNum(int idx);

	public EventVO getEventContent(int idx);
}
