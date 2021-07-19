package com.spring.cj2103s10.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.cj2103s10.vo.AreaVO;
import com.spring.cj2103s10.vo.CartTestVO;

public interface StudyDAO {

	public String[] getAddress1();

	public List<AreaVO> getAddress2(@Param("address1") String address1);

	public AreaVO getAddressSearch(@Param("address1") String address1, @Param("address2") String address2);

	public List<CartTestVO> getOrderList(@Param("idxsStr") String idxsStr);

	public List<CartTestVO> getCartTest();

}
