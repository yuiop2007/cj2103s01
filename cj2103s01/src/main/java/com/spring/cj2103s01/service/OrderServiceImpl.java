package com.spring.cj2103s01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.cj2103s01.dao.CartDAO;
import com.spring.cj2103s01.dao.OrderDAO;
import com.spring.cj2103s01.vo.CartVO;
import com.spring.cj2103s01.vo.OrderVO;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderDAO orderDAO;
	
	@Autowired
	CartDAO cartDAO;

	@Override
	public void setCart(String mId, int pId, String pOption, int pCnt, int pPrice) {
		cartDAO.setCart(mId, pId, pOption, pCnt, pPrice);
	}

	@Override
	public List<CartVO> getCartList(String mId) {
		return cartDAO.getCartList(mId);
	}

	@Override
	public void setCartCntUpdate(String mId, int pId, String pOption, int pCnt, int pPrice) {
		cartDAO.setCartCntUpdate(mId, pId, pOption, pCnt, pPrice);
	}

	@Override
	public int getCartCnt(String mId) {
		return cartDAO.getCartCnt(mId);
	}

	@Override
	public void cartDelete(int idx) {
		cartDAO.cartDelete(idx);
	}

	@Override
	public CartVO getIdxVo(int idx) {
		return cartDAO.getIdxVo(idx);
	}

	@Override
	public void setInputOrder(OrderVO vo) {
		orderDAO.setInputOrder(vo);
	}

	@Override
	public int getLastoId(String mId) {
		return orderDAO.getLastoId(mId);
	}

	@Override
	public void setOrderDetail(CartVO cartvo, int lastoId) {
		orderDAO.setOrderDetail(cartvo, lastoId);
	}


}
