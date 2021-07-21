package com.spring.cj2103s01.service;

import java.util.List;

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
	public void cDeleteOk(String cName) {
		couponDAO.cDeleteOk(cName);
	}

	@Override
	public List<CouponVO> getaCouponList() {
		return couponDAO.getaCouponList();
	}

	@Override
	public List<CouponVO> getCouponListMid(String mId) {
		return couponDAO.getCouponListMid(mId);
	}

	@Override
	public int getCouponCnt(String mId) {
		return couponDAO.getCouponCnt(mId);
	}

	@Override
	public void setJoinCoupon(String mId) {
		couponDAO.setJoinCoupon(mId);
	}
}
