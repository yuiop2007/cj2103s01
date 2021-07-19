package com.spring.cj2103s10.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.cj2103s10.vo.MemberVO;
import com.spring.cj2103s10.vo.NotifyVO;
import com.spring.cj2103s10.vo.ReservationRoomVO;

public interface AdminDAO {

	public List<MemberVO> getMemberList(@Param("startIndexNo") int startIndexNo, @Param("pageSize") int pageSize);

	public List<MemberVO> getMemberListLevel(@Param("startIndexNo") int startIndexNo, @Param("pageSize") int pageSize, @Param("level") String level);

	public List<MemberVO> getMemberListMid(@Param("startIndexNo") int startIndexNo, @Param("pageSize") int pageSize, @Param("mid") String mid);

	public void aMLevelCheck(@Param("mid") String mid, @Param("level") String level);

	public MemberVO getAMInfor(@Param("mid") String mid);

	public void memberDelete(@Param("idx") int idx);

	public List<NotifyVO> getNotifyList();

	public void nInputOk(@Param("vo") NotifyVO vo);

	public NotifyVO nUpdate(@Param("idx") int idx);

	public void nUpdateOk(@Param("vo") NotifyVO vo);

	public void nDelete(@Param("idx") int idx);

	public void popupCheck(@Param("idx") int idx, @Param("popupSw") String popupSw);

	public List<NotifyVO> getNotifyPopup();

	public ReservationRoomVO getRoomSearch(@Param("room") String room);

	public List<ReservationRoomVO> roomList();

	public void roomInputOk(@Param("vo") ReservationRoomVO vo);

	public void roomDeleteOk(@Param("room") String room);

	public void setRoomModify(@Param("room") String room, @Param("available") String available);

}
