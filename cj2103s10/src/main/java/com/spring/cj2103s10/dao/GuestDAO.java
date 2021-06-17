package com.spring.cj2103s10.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.cj2103s10.vo.GuestVO;

public interface GuestDAO {

	public List<GuestVO> getGuestList();

	public void setGuestInput(@Param("vo") GuestVO vo);

}
