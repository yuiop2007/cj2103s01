package com.spring.cj2103s01.service;

import java.util.List;

import com.spring.cj2103s01.vo.CartVO;
import com.spring.cj2103s01.vo.OrderDetailVO;
import com.spring.cj2103s01.vo.OrderVO;

public interface OrderService {

	public void setCart(String mId, int pId, String pOption, int pCnt, int pPrice);

	public List<CartVO> getCartList(String mId);

	public void setCartCntUpdate(String mId, int pId, String pOption, int pCnt, int pPrice);

	public int getCartCnt(String mId);

	public void cartDelete(int idx);

	public CartVO getIdxVo(int idx);

	public void setInputOrder(OrderVO vo);

	public int getLastoId(String mId);

	public void setOrderDetail(CartVO cartvo, int lastoId);

	public int notPayCnt();

	public int readyPayCnt();

	public int ingPayCnt();

	public int endPayCnt();

	public int cancelCnt();

	public int changeCnt();

	public int returnCnt();

	public int notPayCntMid(String mId);

	public int readyPayCntMid(String mId);

	public int ingPayCntMid(String mId);

	public int endPayCntMid(String mId);

	public int cancelCntMid(String mId);

	public int changeCntMid(String mId);

	public int returnCntMid(String mId);

	public int getLastCartIdx(String mId);

	public List<OrderVO> getOrderListMid(String mId, int time);

	public List<OrderDetailVO> getOrderDetailListMid(String mId);

}
