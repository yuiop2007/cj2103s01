package com.spring.cj2103s01.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spring.cj2103s01.vo.SlideVO;

public interface SlideService {

	public List<SlideVO> getSlideList();

	public int getSlideTotCnt();

	public int setSlideInput(MultipartFile file, SlideVO vo, String root);

	public String getImageSid(int sId);

	public void sDelete(int sId);

	public void sNumUpdate(int sId, int sNum);

	public List<SlideVO> getSlideMainList();

}
