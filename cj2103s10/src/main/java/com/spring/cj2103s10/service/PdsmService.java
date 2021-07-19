package com.spring.cj2103s10.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.cj2103s10.vo.PdsmVO;

public interface PdsmService {

	public List<PdsmVO> getPmList(int startIndexNo, int pageSize, String part);

	public void pmInput(MultipartHttpServletRequest file, PdsmVO vo);

	public void setDownCheck(int idx);

	public PdsmVO getPmContent(int idx);

}
