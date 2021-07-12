package com.spring.cj2103s01.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spring.cj2103s01.vo.ProductVO;

public interface ProductService {

	public int setProductInput(MultipartFile file, ProductVO vo, String root);

	public List<ProductVO> getProductList(int startIndexNo, int pageSize);

	public List<ProductVO> getProductSearchList(int startIndexNo, int pageSize, String search, String searchString);

	public void addReadNum(int pId);

	public ProductVO getProductContent(int pId);

	public ProductVO getIdCheck(int pId);

	public int productUpdateOk(MultipartFile file, ProductVO vo, String root);

	public void pDelete(int pId);

	public int totProductCnt();

	public void productDelete(int idx);

	public List<ProductVO> getProductAllList();

	public void setStockUpdate(int pId, int Cnt);

	public void setCancelStockUpdate(int pId, int Cnt);

}
