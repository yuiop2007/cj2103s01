package com.spring.cj2103s01.service;

import java.util.List;

import com.spring.cj2103s01.vo.CouponVO;

public interface CouponService {

	public List<CouponVO> getCouponList();

	public int setCouponInput(CouponVO vo);

	public void cDeleteOk(int cId);

	public List<CouponVO> getaCouponList();

}
