package com.spring.cj2103s01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.cj2103s01.dao.CartDAO;
import com.spring.cj2103s01.dao.OrderDAO;
import com.spring.cj2103s01.vo.CartVO;
import com.spring.cj2103s01.vo.OrderDetailVO;
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

	@Override
	public int notPayCnt() {
		return orderDAO.notPayCnt();
	}

	@Override
	public int readyPayCnt() {
		return orderDAO.readyPayCnt();
	}

	@Override
	public int ingPayCnt() {
		return orderDAO.ingPayCnt();
	}

	@Override
	public int endPayCnt() {
		return orderDAO.endPayCnt();
	}

	@Override
	public int cancelCnt() {
		return orderDAO.cancelCnt();
	}

	@Override
	public int changeCnt() {
		return orderDAO.changeCnt();
	}

	@Override
	public int returnCnt() {
		return orderDAO.returnCnt();
	}

	@Override
	public int notPayCntMid(String mId) {
		return orderDAO.notPayCntMid(mId);
	}

	@Override
	public int readyPayCntMid(String mId) {
		return orderDAO.readyPayCntMid(mId);
	}

	@Override
	public int ingPayCntMid(String mId) {
		return orderDAO.ingPayCntMid(mId);
	}

	@Override
	public int endPayCntMid(String mId) {
		return orderDAO.endPayCntMid(mId);
	}

	@Override
	public int cancelCntMid(String mId) {
		return orderDAO.cancelCntMid(mId);
	}

	@Override
	public int changeCntMid(String mId) {
		return orderDAO.changeCntMid(mId);
	}

	@Override
	public int returnCntMid(String mId) {
		return orderDAO.returnCntMid(mId);
	}

	@Override
	public int getLastCartIdx(String mId) {
		return cartDAO.getLastCartIdx(mId);
	}

	@Override
	public List<OrderVO> getOrderListMid(String mId, int time) {
		return orderDAO.getOrderListMid(mId, time);
	}

	@Override
	public List<OrderDetailVO> getOrderDetailListMid(String mId) {
		return orderDAO.getOrderDetailListMid(mId);
	}

	@Override
	public List<OrderVO> getOrderListStatus(String mId, int time, String status) {
		return orderDAO.getOrderListStatus(mId, time, status);
	}

	@Override
	public List<OrderVO> getOrderListChange(String mId, int time, String change) {
		return orderDAO.getOrderListChange(mId, time, change);
	}

	@Override
	public List<OrderVO> getOrderAllList(int startIndexNo, int pageSize) {
		return orderDAO.getOrderAllList(startIndexNo, pageSize);
	}

	@Override
	public List<OrderVO> getAdminOlistStatus(String status,  int startIndexNo, int pageSize) {
		return orderDAO.getAdminOlistStatus(status, startIndexNo, pageSize);
	}

	@Override
	public List<OrderVO> getAdminOlistChange(String change,  int startIndexNo, int pageSize) {
		return orderDAO.getAdminOlistChagne(change, startIndexNo, pageSize);
	}

	@Override
	public List<OrderVO> getOrderSCList(String status, String change,  int startIndexNo, int pageSize) {
		return orderDAO.getOrderSCList(status, change, startIndexNo, pageSize);
	}

	@Override
	public List<OrderDetailVO> getOrderDetailList() {
		return orderDAO.getOrderDetailList();
	}

	@Override
	public void statusUpdate(String oId, String oStatus) {
		orderDAO.statusUpdate(oId, oStatus);
	}

	@Override
	public List<OrderVO> getMemberListMid(String mId, int startIndexNo, int pageSize) {
		return orderDAO.getMemberListMid(mId, startIndexNo, pageSize);
	}

	@Override
	public OrderVO getOrderContent(String mId, int oId) {
		return orderDAO.getOrderContent(mId, oId);
	}

	@Override
	public List<OrderDetailVO> getOrderDetailListOid(String mId, int oId) {
		return orderDAO.getOrderDetailListOid(mId, oId);
	}



}
