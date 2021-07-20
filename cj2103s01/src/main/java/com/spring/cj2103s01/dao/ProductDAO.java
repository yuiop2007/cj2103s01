package com.spring.cj2103s01.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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

	public void productDelete(@Param("idx") int idx);

	public List<ProductVO> getProductAllList();

	public void setStockUpdate(@Param("pId") int pId,@Param("Cnt") int Cnt);

	public void setCancelStockUpdate(@Param("pId") int pId,@Param("Cnt") int cnt);

	public String getImagePid(@Param("pId") int pId);

	public List<ProductVO> getEventProductList(@Param("startIndexNo") int startIndexNo, @Param("pageSize") int pageSize);

	public int MadeSellCnt();

	public int OuterSellCnt();

	public int TopSellCnt();

	public int BottomSellCnt();

	public int AccSellCnt();

	public int getSalePrice(@Param("pId") int pId);

}
