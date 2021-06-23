package com.spring.cj2103s01.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spring.cj2103s01.vo.ProductVO;

public interface ProductService {

	public int setProductInput(MultipartFile file, ProductVO vo, String root);

	public List<ProductVO> getProductList(int startIndexNo, int pageSize);

}
