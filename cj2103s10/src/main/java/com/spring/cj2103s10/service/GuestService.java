package com.spring.cj2103s10.service;

import java.util.List;

import com.spring.cj2103s10.vo.GuestVO;

public interface GuestService {

	public List<GuestVO> getGuestList();

	public void setGuestInput(GuestVO vo);

}
