package com.spring.cj2103s01.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.cj2103s01.vo.CouponVO;

public interface CouponDAO {

	public int totRecCnt();

	public List<CouponVO> getCouponList();

	public void setCouponInput(@Param("vo") CouponVO vo);

	public void cDeleteOk(@Param("cId") int cId);

	public List<CouponVO> getaCouponList();

	public List<CouponVO> getCouponListMid(@Param("mId") String mId);

	public int getCouponCnt(@Param("mId") String mId);

}
