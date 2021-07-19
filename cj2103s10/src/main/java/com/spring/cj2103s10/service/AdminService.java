package com.spring.cj2103s10.service;

import java.util.List;

import com.spring.cj2103s10.vo.MemberVO;
import com.spring.cj2103s10.vo.NotifyVO;
import com.spring.cj2103s10.vo.ReservationRoomVO;

public interface AdminService {

	public List<MemberVO> getMemberList(int startIndexNo, int pageSize);

	public List<MemberVO> getMemberListLevel(int startIndexNo, int pageSize, String level);

	public List<MemberVO> getMemberListMid(int startIndexNo, int pageSize, String mid);

	public void aMLevelCheck(String mid, String level);

	public MemberVO getAMInfor(String mid);

	public void memberDelete(int idx);

	public List<NotifyVO> getNotifyList();

	public void nInputOk(NotifyVO vo);

	public NotifyVO nUpdate(int idx);

	public void nUpdateOk(NotifyVO vo);

	public void nDelete(int idx);

	public void popupCheck(int idx, String popupSw);

	public List<NotifyVO> getNotifyPopup();

	public ReservationRoomVO getRoomSearch(String room);

	public List<ReservationRoomVO> roomList();

	public void imgCheck(String content, String uploadPath, int position);

	public void roomInputOk(ReservationRoomVO vo);

	public void imgDelete(String uploadPath, String content);

	public void roomDeleteOk(String room);

	public void setRoomModify(String room, String available);

	public int imgDelete(String uploadPath);

}
