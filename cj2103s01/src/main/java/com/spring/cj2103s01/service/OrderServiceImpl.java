package com.spring.cj2103s01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.cj2103s01.dao.CartDAO;
import com.spring.cj2103s01.dao.OrderDAO;
import com.spring.cj2103s01.vo.CartVO;

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
	public void setCartCntUpdate(String mId, int pId, String pOption) {
		cartDAO.setCartCntUpdate(mId, pId, pOption);
	}


}
