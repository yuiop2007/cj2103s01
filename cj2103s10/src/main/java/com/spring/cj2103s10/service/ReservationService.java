package com.spring.cj2103s10.service;

import java.util.List;

import com.spring.cj2103s10.vo.ReservationRoomVO;
import com.spring.cj2103s10.vo.ReservationStatusVO;
import com.spring.cj2103s10.vo.ReservationVO;

public interface ReservationService {

	public void getReservation();

	public void reservationInput(ReservationStatusVO stVo, ReservationVO vo);

	public ReservationVO getReservationContent(int idx);

	public List<ReservationVO> getReservationView(String mid);

	public boolean reservationCancelProcess(int idx);

	public List<ReservationVO> getReservationStatusView(int startNo, int pageSize, String room);

	// public List<ReservationVo> roomStatusSearch(int startNo, int pageSize, String room);
	
	public List<ReservationRoomVO> getRoomInformation();

	public List<ReservationRoomVO> getReservationRoomList();

	public void reservationUpdate(ReservationVO vo);

}
