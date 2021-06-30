package com.spring.cj2103s01.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;

import com.spring.cj2103s01.vo.ProductVO;

public interface ProductDAO {

	public int totRecCnt();

	public void setProductInput(@Param("vo") ProductVO vo);

	public List<ProductVO> getProductList(@Param("startIndexNo") int startIndexNo, @Param("pageSize") int pageSize);

	public List<ProductVO> getProductSearchList(@Param("startIndexNo") int startIndexNo, @Param("pageSize") int pageSize, @Param("search") String search, @Param("searchString") String searchString);

	public int totSearchRecCnt(@Param("search") String search, @Param("searchString") String searchString);

	public void addReadNum(@Param("pId") int pId);

	public ProductVO getProductContent(@Param("pId") int pId);

	public ProductVO getIdCheck(@Param("pId") int pId);

	public void productUpdateOk(@Param("vo") ProductVO vo);

	public void pDelete(@Param("pId") int pId);

	public int totProductCnt();

}
