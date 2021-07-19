package com.spring.cj2103s10;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.cj2103s10.pagination.Pagination;
import com.spring.cj2103s10.pagination.PaginationVO;
import com.spring.cj2103s10.service.AdminService;
import com.spring.cj2103s10.service.MemberService;
import com.spring.cj2103s10.service.ReservationService;
import com.spring.cj2103s10.vo.MemberVO;
import com.spring.cj2103s10.vo.ReservationRoomVO;
import com.spring.cj2103s10.vo.ReservationStatusVO;
import com.spring.cj2103s10.vo.ReservationVO;

@Controller
@RequestMapping("/reservation")
public class ReservationController {
	String msgFlag = "";
	
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	Pagination pagination;

	// 예약현황 달력을 보여주는 컨트롤러(달력처리는 서비스객체에서 수행했다.
	@RequestMapping(value="/reservation", method=RequestMethod.GET)
	public String reservationGet() {
		reservationService.getReservation();
		
		return "reservation/reservation";
	}
	
	// 달력에 표시된 객실을 클릭하면 해당 객실의 정보를 출력시켜줄수 있게 한다.
	@RequestMapping(value="/roomInformation", method=RequestMethod.GET)
	public String roomInformationGet(String room, Model model) {
		ReservationRoomVO vo = adminService.getRoomSearch(room);				// 방이름을 가지고 검색해서 현재방의 정보를 가져온다.
		model.addAttribute("vo", vo);
		
		return "admin/reservation/roomView";												// 현재 객실의 정보를 DB에서 가져와서 roomView.jsp에 뿌릴 준비를 하고 있다.
	}
	
	// 예약현황 입력하기위한 입력폼 출력하기(현재폼에서 객실정보를 보기위해 ...)
	@RequestMapping(value="/reservationInput", method=RequestMethod.GET)
	public String reservationInputGet(String ymd, String room, Model model) {
		ReservationRoomVO vo = adminService.getRoomSearch(room);
		model.addAttribute("ymd", ymd);
		model.addAttribute("room", room);
		model.addAttribute("vo", vo);
		
		return "reservation/reservationInput";
	}
	
	// 예약내역 저장하기
	@RequestMapping(value="/reservationInput", method=RequestMethod.POST)
	public String reservationInputPost(ReservationStatusVO stVo, ReservationVO vo) {
		reservationService.reservationInput(stVo, vo);
		String[] ymd = vo.getYmd().split("-");
		String yy = ymd[0];
		String mm = (Integer.parseInt(ymd[1])-1)+"";
		msgFlag = "reservationInputOk$yy="+yy+"&mm="+mm;
		
		return "redirect:/msg/" + msgFlag;
	}
	
	// 예약내역 전체 확인하기
	@RequestMapping(value="/reservationView", method=RequestMethod.GET)
	public String reservationViewGet(HttpSession session, Model model) {
		String mid = (String) session.getAttribute("smid");
		List<ReservationVO> vos = reservationService.getReservationView(mid);
		model.addAttribute("vos", vos);
		
		return "reservation/reservationView";
	}
	
	// 개별 예약내역 상세보기
	@RequestMapping(value="/reservationContent", method=RequestMethod.GET)
	public String reservationContentGet(int idx, String ymd, Model model) {
		ReservationVO vo = reservationService.getReservationContent(idx);
		model.addAttribute("ymd", ymd);
		model.addAttribute("vo", vo);
		
		return "reservation/reservationContent";
	}
	
	// 관리자가 개별 예약내역 상세보기
	@RequestMapping(value="/reservationAdminContent", method=RequestMethod.GET)
	public String reservationAdminContentGet(int idx, String ymd, Model model) {
		ReservationVO vo = reservationService.getReservationContent(idx);
		model.addAttribute("ymd", ymd);
		model.addAttribute("vo", vo);
		
		return "admin/reservation/reservationAdminContent";
	}
	
	// 관리자가 예약현황 전체 보기(관리자가 지정한 객실에 대하여만 예약된 전체자료 보여주기 포함)
	@RequestMapping(value="/reservationStatusViewList", method=RequestMethod.GET)
	public String reservationStatusViewGet(Model model,
			@RequestParam(name="room", defaultValue="전체", required=false) String room,
			@RequestParam(name="pag", defaultValue="1", required=false) int pag,
			@RequestParam(name="pageSize", defaultValue="10", required=false) int pageSize) {
		
		PaginationVO pageVO = pagination.pagination(pag, pageSize, "reservation", room, "");
		
		List<ReservationRoomVO> roomVos = reservationService.getReservationRoomList();
		List<ReservationVO> vos = reservationService.getReservationStatusView(pageVO.getStartIndexNo(),pageSize,room);
		
		model.addAttribute("vos", vos);
		model.addAttribute("roomVos", roomVos);
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("room", room);
		
		return "admin/reservation/reservationStatusViewList";
	}
	
	// 예약 취소하기..
	@RequestMapping(value="/reservationCancel", method=RequestMethod.GET)
	public String reservationCancelGet(int idx) {
		boolean res = reservationService.reservationCancelProcess(idx);
		if(res) msgFlag = "reservationCancelOk";
		else msgFlag = "reservationCancelNo";
		
		return "redirect:/msg/" + msgFlag;
	}
	
	// 상세내역 수정창 보여주기
	@RequestMapping(value="/reservationUpdate", method=RequestMethod.GET)
	public String reservationUpdateGet(int idx, String ymd, Model model) {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strNow = sdf.format(now).substring(0,10);
		if(ymd.compareTo(strNow) > 0) {  // 결과값이 양수면 예약일이 오늘 이후이기에 수정할수 있게하고, 그렇지 않으면 수정할 수 없게 처리한다.
			ReservationVO vo = reservationService.getReservationContent(idx);
			model.addAttribute("ymd", ymd);
			model.addAttribute("vo", vo);
			return "reservation/reservationUpdate";
		}
		else {
			msgFlag = "reservationUpdateNo";
			return "redirect/msg/" + msgFlag;
		}
	}
	
	
	// 상세내역에서 예약내역 수정하기
	@RequestMapping(value="/reservationUpdate", method=RequestMethod.POST)
	public String reservationUpdatePost(ReservationVO vo) {
		reservationService.reservationUpdate(vo);
		msgFlag = "reservationUpdateOk";
		
		return "redirect:/msg/" + msgFlag;
	}
	
	// 회원정보보기
	@RequestMapping(value="/memberView", method=RequestMethod.GET)
	public String memberViewGet(String mid, Model model) {
		MemberVO vo = memberService.getIdCheck(mid);
		model.addAttribute("vo", vo);
		
		return "reservation/memberView";
	}
}
