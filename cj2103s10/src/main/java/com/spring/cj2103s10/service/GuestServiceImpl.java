package com.spring.cj2103s10.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.cj2103s10.dao.GuestDAO;
import com.spring.cj2103s10.vo.GuestVO;

@Service
public class GuestServiceImpl implements GuestService {

	@Autowired
	GuestDAO guestDAO;

	@Override
	public List<GuestVO> getGuestList() {
		return guestDAO.getGuestList();
	}

	@Override
	public void setGuestInput(GuestVO vo) {
		guestDAO.setGuestInput(vo);
	}
	
}
