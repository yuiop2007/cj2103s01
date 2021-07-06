package com.spring.cj2103s01.service;

import java.util.List;

import com.spring.cj2103s01.vo.CartVO;

public interface OrderService {

	public void setCart(String mId, int pId, String pOption, int pCnt, int pPrice);

	public List<CartVO> getCartList(String mId);

	public void setCartCntUpdate(String mId, int pId, String pOption, int pCnt, int pPrice);

	public int getCartCnt(String mId);

	public void cartDelete(int idx);

	public CartVO getIdxVo(int idx);

}
