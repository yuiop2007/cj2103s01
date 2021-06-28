package com.spring.cj2103s10.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.cj2103s10.vo.AreaVO;

public interface StudyDAO {

	public String[] getAddress1();

	public List<AreaVO> getAddress2(@Param("address1") String address1);

}
