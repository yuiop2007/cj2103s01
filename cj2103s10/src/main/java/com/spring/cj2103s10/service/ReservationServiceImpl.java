package com.spring.cj2103s10.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.spring.cj2103s10.dao.ReservationDAO;
import com.spring.cj2103s10.vo.ReservationRoomVO;
import com.spring.cj2103s10.vo.ReservationStatusVO;
import com.spring.cj2103s10.vo.ReservationVO;

@Service
public class ReservationServiceImpl implements ReservationService {
  @Autowired
  ReservationDAO reservationDAO;

	@Override
	public void getReservation() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		
		//HttpSession session = request.getSession();
		//String mid = (String) session.getAttribute("smid");
				
		// 1.오늘 날짜를 저장
		Calendar calToday = Calendar.getInstance();
		int toYear = calToday.get(Calendar.YEAR);
		int toMonth = calToday.get(Calendar.MONTH);
		int toDay = calToday.get(Calendar.DATE);
		
		// 2.화면에 보여줄 해당년월 셋팅을 위한 부분
		Calendar calView = Calendar.getInstance();//화면에 표시할 날짜
		int yy = request.getParameter("yy") == null ? calView.get(Calendar.YEAR) : Integer.parseInt(request.getParameter("yy"));
		int mm = request.getParameter("mm") == null ? calView.get(Calendar.MONTH) : Integer.parseInt(request.getParameter("mm"));
		
		if( mm < 0 ) { // 1월에서 전월 버튼을 클릭시에 실행
			yy--;
			mm=11;
		}
		if( mm > 11) { // 12월에서 다음월 버튼을 클릭시에 실행함
			yy++;
			mm=0;
		}

		calView.set(yy,mm,1);//해당 년, 월의 첫날을 셋팅 
		
		int startWeek = calView.get(Calendar.DAY_OF_WEEK);//해당 년/월의 1일에 해당하는 요일값을 가져온다
		int lastDay = calView.getActualMaximum(Calendar.DAY_OF_MONTH);//해당월의 마지막일을 구한다
		
		// 3.화면에 보여줄 년월기준 전년도 다음년도를 위한 부분
		int preYear = yy; //전년도
		int preMonth = mm - 1; // 전월
		int nextYear = yy; //다음년도
		int nextMonth = mm + 1; //다음월
		
		if(preMonth == -1) { //1월에서 전월 버튼을 클릭시에 실행함
			preYear--;
			preMonth = 11;
		}
		
		if(nextMonth == 12) { //12월에서 다음월 버튼을 클릭시에 실행함
			nextYear++;
			nextMonth = 0;
		}
		Calendar calPre = Calendar.getInstance();//이전달력
		calPre.set(preYear, preMonth,1);//이전달력셋팅
		int preLastDay = calPre.getActualMaximum(Calendar.DAY_OF_MONTH);//이전달력의 마지막일을 구한다
		
		Calendar calNext = Calendar.getInstance();//다음달력
		calNext.set(nextYear, nextMonth,1);//다음달력셋팅
		int nextStartWeek = calNext.get(Calendar.DAY_OF_WEEK);//다음달력의 1일에 해당하는 요일값을 가져온다
		
		// 5. 해당사용자에 대한 해당월별 내역
		// 해당 sql쿼리 처리시 dataformat 비교형식에 맞춰주기 위해 아래와 같이 처리 > 현재 1월~9월은 2020-1 이런식으로 출력되므로 2020-01 이렇게 바꿔주어야한다
		// 일반 달력에서는, 처리하지 않았지만, 일정관리에서는 처리해줘야 한다. 
		String ym = "";
		int tmpMM = (mm+1);
		if(tmpMM >= 1 && tmpMM <=9) {
			ym = yy + "-" + "0" + (mm+1);
		}
		else {
			ym = yy + "-" + (mm+1);
		}
		
		// roomVos : 객실명,  statusVos : 각 객실별 예약현황 
		List<ReservationRoomVO> roomVos = reservationDAO.getReservationRoomList();
		List<ReservationStatusVO> statusVos = reservationDAO.getReservationStatusList(ym);
		
		/* ------  아래는  앞에서 처리된 값들을 모두 request객체에 담는다.  ------- */
		/*======================= setAttribute ===========================*/
		//오늘기준 달력
		request.setAttribute("toYear", toYear);		
		request.setAttribute("toMonth", toMonth);		
		request.setAttribute("toDay", toDay);		
		
		//화면에 보여줄 해당 달력
		request.setAttribute("yy", yy);
		request.setAttribute("mm", mm);
		request.setAttribute("startWeek", startWeek);
		request.setAttribute("lastDay", lastDay);
		
		//화면에 보여줄 해당 달력 기준 전년도,전년월,다음년도,다음월 달력
		request.setAttribute("preYear", preYear);
		request.setAttribute("preMonth", preMonth);
		request.setAttribute("preLastDay", preLastDay);
		request.setAttribute("nextYear", nextYear);
		request.setAttribute("nextMonth", nextMonth);
		request.setAttribute("nextStartWeek", nextStartWeek);
		
		// 해당월에 해당하는 vos담기
		//System.out.println("roomVos : " + roomVos);
		request.setAttribute("roomVos", roomVos);
		//System.out.println("statusVos : " + statusVos);
		request.setAttribute("statusVos", statusVos);
	}

	@Override
	public void reservationInput(ReservationStatusVO stVo, ReservationVO vo) {
		reservationDAO.reservationStatusInput(stVo);
		int stIdx = reservationDAO.getReservationStatusIdx();
		vo.setReservationStatusIdx(stIdx);
		reservationDAO.reservationInput(vo);
	}

	@Override
	public ReservationVO getReservationContent(int idx) {
		ReservationVO vo = reservationDAO.getReservationContent(idx);
		int stIdx = vo.getReservationStatusIdx();
		vo.setNickName(reservationDAO.getNickName(vo.getMid()));
		ReservationStatusVO stVo = reservationDAO.getReservationRoom(stIdx);
		vo.setRoom(stVo.getReservationRoom());
		vo.setReservationDate(stVo.getReservationDate());
		
		return vo;
	}

	@Override
	public List<ReservationVO> getReservationView(String mid) {
		return reservationDAO.getReservationView(mid);
	}

	@Override
	public boolean reservationCancelProcess(int idx) {
		//ReservationVo vo = reservationDao.getReservationContent(idx);
		ReservationVO vo = reservationDAO.getReservationContentIdxSearch(idx);
		int stIdx = vo.getReservationStatusIdx();
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strNow = sdf.format(now).substring(0,10);
		if(vo.getReservationDate().substring(0,10).compareTo(strNow) > 0) {  // 결과값이 양수면 예약일이 오늘 이후이고, 0이면 오늘이고, 음수이면 예약일이 오늘 이전으로 지났다.
			reservationDAO.reservationCancelProcess(idx);
			reservationDAO.reservationStatusCancelProcess(stIdx);
		  return true;
		}
		else {
			return false;
		}
	}

	@Override
	public List<ReservationVO> getReservationStatusView(int startNo, int pageSize, String room) {
		return reservationDAO.getReservationStatusView(startNo, pageSize, room);
	}
	/*
	@Override
	public List<ReservationVo> roomStatusSearch(int startNo, int pageSize, String room) {
		return reservationDao.roomStatusSearch(startNo, pageSize, room);
	}
  */
	@Override
	public List<ReservationRoomVO> getRoomInformation() {
		return reservationDAO.getRoomInformation();
	}

	@Override
	public List<ReservationRoomVO> getReservationRoomList() {
		return reservationDAO.getReservationRoomList();
	}

	@Override
	public void reservationUpdate(ReservationVO vo) {
		reservationDAO.reservationUpdate(vo);
	}

}
