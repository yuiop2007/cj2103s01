package com.spring.cj2103s01.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.cj2103s01.vo.CartVO;

public interface CartDAO {

	public void setCart(@Param("mId") String mId,@Param("pId") int pId,@Param("pOption") String pOption,@Param("pCnt") int pCnt,@Param("pPrice") int pPrice);

	public int totRecCntMid(@Param("search") String search);

	public List<CartVO> getCartList(@Param("mId") String mId);

	public void setCartCntUpdate(@Param("mId")String mId,@Param("pId") int pId,@Param("pOption") String pOption,@Param("pCnt") int pCnt,@Param("pPrice") int pPrice);

	public int getCartCnt(@Param("mId") String mId);

	public void cartDelete(@Param("idx") int idx);

	public CartVO getIdxVo(@Param("idx") int idx);

	public int getLastCartIdx(@Param("mId") String mId);

}
