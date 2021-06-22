package com.spring.cj2103s01.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spring.cj2103s01.vo.ProductVO;

public interface ProductService {

	public List<ProductVO> getpList(int startIndexNo, int pageSize);

	public int totRecCnt();

	public int setProductInput(MultipartFile file, ProductVO vo);

	public void imgCheck(String getpContent, String uploadPath, int i);

}
