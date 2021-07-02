package com.spring.cj2103s01.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.cj2103s01.dao.CouponDAO;
import com.spring.cj2103s01.vo.CouponVO;

@Service
public class CouponServiceImpl implements CouponService{

	@Autowired
	CouponDAO couponDAO;

	@Override
	public List<CouponVO> getCouponList() {
		return couponDAO.getCouponList();
	}

	@Override
	public int setCouponInput(CouponVO vo) {
		int res = 0;

		if(vo != null) {
			couponDAO.setCouponInput(vo);
			res = 1;
		}

		return res;
	}

	@Override
	public void cDeleteOk(int cId) {
		couponDAO.cDeleteOk(cId);
	}

	@Override
	public List<CouponVO> getaCouponList() {
		return couponDAO.getaCouponList();
	}
}
