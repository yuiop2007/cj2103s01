package com.spring.cj2103s01.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.cj2103s01.vo.ProductVO;

public interface ProductDAO {

	public List<ProductVO> getpList(@Param("startIndexNo") int startIndexNo, @Param("pageSize") int pageSize);

	public int totRecCnt();

	public void setProductInput(@Param("vo") ProductVO vo);

}
