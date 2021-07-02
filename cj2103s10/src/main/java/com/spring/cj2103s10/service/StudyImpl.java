package com.spring.cj2103s10.service;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.spring.cj2103s10.dao.StudyDAO;
import com.spring.cj2103s10.vo.AreaVO;

@Service
public class StudyImpl implements Study {

	@Autowired
	StudyDAO studyDAO;

	@Override
	public String[] getAddress1() {
		return studyDAO.getAddress1();
	}

	@Override
	public List<AreaVO> getAddress2(String address1) {
		return studyDAO.getAddress2(address1);
	}

	@Override
	public AreaVO getAddressSearch(String address1, String address2) {
		return studyDAO.getAddressSearch(address1, address2);
	}

	@Override
	public void getCalendar() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		
		// 오늘 날짜 저장
		Calendar calToday = Calendar.getInstance();
		int toYear = calToday.get(Calendar.YEAR);
		int toMonth = calToday.get(Calendar.MONTH);
		int toDay = calToday.get(Calendar.DATE);
				
		// 화면에 보여줄 해당년월을 셋팅하는 부분
		Calendar calView = Calendar.getInstance();
		int yy = request.getParameter("yy")==null ? calView.get(Calendar.YEAR) : Integer.parseInt(request.getParameter("yy"));
	  int mm = request.getParameter("mm")==null ? calView.get(Calendar.MONTH) : Integer.parseInt(request.getParameter("mm"));
	  
	  if(mm < 0) { // 1월에서 전월 버튼을 클릭시에 실행
	  	yy--;
	  	mm = 11;
	  }
	  if(mm > 11) { // 12월에서 다음월 버튼을 클릭시에 실행
	  	yy++;
	  	mm = 0;
	  }
	  calView.set(yy, mm, 1);
	  
	  int startWeek = calView.get(Calendar.DAY_OF_WEEK);  // 해당 '년/월'의 1일에 해당하는 요일값을 가져온다.
	  int lastDay = calView.getActualMaximum(Calendar.DAY_OF_MONTH);  // 해당월의 마지막일자를 구한다.
	  
	  // 화면에 보여줄 년월기준 전년도/다음년도를 구하기 위한 부분
	  int prevYear = yy;  			// 전년도
	  int prevMonth = (mm) - 1; // 이전월
	  int nextYear = yy;  			// 다음년도
	  int nextMonth = (mm) + 1; // 다음월
	  
	  if(prevMonth == -1) {  // 1월에서 전월 버튼을 클릭시에 실행..
	  	prevYear--;
	  	prevMonth = 11;
	  }
	  
	  if(nextMonth == 12) {  // 12월에서 다음월 버튼을 클릭시에 실행..
	  	nextYear++;
	  	nextMonth = 0;
	  }
	  
	  // 이전달력과 다음달력처리부분
	  Calendar calPre = Calendar.getInstance();  // 이전달력
	  calPre.set(prevYear, prevMonth, 1);  // 이전 달력 셋팅
	  int preLastDay = calPre.getActualMaximum(Calendar.DAY_OF_MONTH);  // 해당월의 마지막일자를 구한다.
	  
	  Calendar calNext = Calendar.getInstance();  // 다음달력
	  calNext.set(nextYear, nextMonth, 1);  // 다음 달력 셋팅
	  int nextStartWeek = calNext.get(Calendar.DAY_OF_WEEK);  // 다음달의 1일에 해당하는 요일값을 가져온다.
	  
	  // 아래는 sql 쿼리작업에서 dataformat의 비교형식을 맞춰주기위해 날짜형식을 변환하였다.
	  // 해당월의 일자를 처리시에 값이 2021-1  -> 2021-01   , 2021-10 은 변환 불필요.
	  String ym = "";
	  int tmpMM = (mm + 1);
	  if(tmpMM >= 1 && tmpMM <= 9) {
	  	ym = yy + "-0" + (mm + 1);
	  }
	  else {
	  	ym = yy + "-" + (mm + 1);
	  }
	  
	  /* ---------  아래는  앞에서 처리된 값들을 모두 request객체에 담는다.  -----------------  */
	  
	  // 오늘기준 달력...
	  request.setAttribute("toYear", toYear);
	  request.setAttribute("toMonth", toMonth);
	  request.setAttribute("toDay", toDay);
	  
	  // 화면에 보여줄 해당 달력...
	  request.setAttribute("yy", yy);
	  request.setAttribute("mm", mm);
	  request.setAttribute("startWeek", startWeek);
	  request.setAttribute("lastDay", lastDay);
	  
	  // 화면에 보여줄 해당 달력 기준의 전년도, 전월, 다음년도, 다음월 ...
	  request.setAttribute("preYear", prevYear);
		request.setAttribute("preMonth", prevMonth);
		request.setAttribute("preLastDay", preLastDay);
		request.setAttribute("nextYear", nextYear);
		request.setAttribute("nextMonth", nextMonth);
		request.setAttribute("nextStartWeek", nextStartWeek);
	}
}
