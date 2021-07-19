package com.spring.cj2103s10.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.cj2103s10.vo.ReservationRoomVO;
import com.spring.cj2103s10.vo.ReservationStatusVO;
import com.spring.cj2103s10.vo.ReservationVO;

public interface ReservationDAO {

	public List<ReservationRoomVO> getReservationRoomList();

	public List<ReservationStatusVO> getReservationStatusList(@Param("ym") String ym);

	public void reservationStatusInput(@Param("stVo") ReservationStatusVO stVo);

	public int getReservationStatusIdx();

	public void reservationInput(@Param("vo") ReservationVO vo);

	public ReservationVO getReservationContent(@Param("idx") int idx);

	public String getNickName(@Param("mid") String mid);

	public ReservationStatusVO getReservationRoom(@Param("reservationStatusIdx") int reservationStatusIdx);

	public List<ReservationVO> getReservationView(@Param("mid") String mid);

	public void reservationCancelProcess(@Param("idx") int idx);

	public void reservationStatusCancelProcess(@Param("stIdx") int stIdx);

	public ReservationVO getReservationContentIdxSearch(@Param("idx") int idx);

	public List<ReservationVO> getReservationStatusView(@Param("startNo") int startNo, @Param("pageSize") int pageSize, @Param("room") String room);
	
	// public List<ReservationVo> roomStatusSearch(@Param("startNo") int startNo, @Param("pageSize") int pageSize, @Param("room") String room);

	public List<ReservationRoomVO> getRoomInformation();

	public void reservationUpdate(@Param("vo") ReservationVO vo);

	public int totRecCnt(@Param("room") String room);

}
