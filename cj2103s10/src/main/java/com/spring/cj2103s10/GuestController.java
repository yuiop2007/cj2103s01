package com.spring.cj2103s10;

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
import com.spring.cj2103s10.service.GuestService;
import com.spring.cj2103s10.vo.GuestVO;

@Controller
@RequestMapping("/guest")
public class GuestController {
  
	@Autowired
	GuestService guestService;
	
	@Autowired
	Pagination pagination;
	
	/*
	// 방명록 불러오기
	@RequestMapping(value = "/gList", method = RequestMethod.GET)
	public String gListGet(@RequestParam(name="pag", defaultValue="1", required=false) int pag,
			                   @RequestParam(name="pageSize", defaultValue="2", required=false) int pageSize,
			                   Model model) {
		
		// 페이징 처리를 위한 준비
		int totRecCnt = guestService.totRecCnt();
		int totPage = (totRecCnt % pageSize)==0 ? totRecCnt / pageSize : (int)(totRecCnt / pageSize) +1;
		int startIndexNo = (pag - 1) * pageSize;
	  int curScrStartNo = totRecCnt - startIndexNo;
	  int blockSize = 3;
		
		List<GuestVO> vos = guestService.getGuestList(startIndexNo, pageSize);
		
		model.addAttribute("vos", vos);
		
		// 페이징처리를 위한 저장소 저장하기
		model.addAttribute("pag", pag);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totPage", totPage);
		model.addAttribute("curScrStartNo", curScrStartNo);
		model.addAttribute("blockSize", blockSize);
		
		return "guest/gList";
	}
	*/
	
	// 방명록 불러오기(Pagination처리)
	@RequestMapping(value = "/gList", method = RequestMethod.GET)
	public String gListGet(@RequestParam(name="pag", defaultValue="1", required=false) int pag,
			@RequestParam(name="pageSize", defaultValue="2", required=false) int pageSize,
			Model model) {
		
		PaginationVO pageVo = pagination.pagination(pag, pageSize, "guest", "");
		
		List<GuestVO> vos = guestService.getGuestList(pageVo.getStartIndexNo(), pageSize);
		
		model.addAttribute("vos", vos);
		model.addAttribute("pageVo", pageVo);
		
		return "guest/gList";
	}
	
	// 방명록 작성하기 폼...
	@RequestMapping(value = "/gInput", method = RequestMethod.GET)
	public String gInputGet() {
		return "guest/gInput";
	}
	
	// 방명록 저장하기...
	@RequestMapping(value = "/gInput", method = RequestMethod.POST)
	public String gInputPost(GuestVO vo) {
		guestService.setGuestInput(vo);
		
		return "redirect:/msg/guestInputOk";
	}
	
	// 관리자 인증창 호출
	@RequestMapping(value = "/adminLogin", method = RequestMethod.GET)
	public String adminLoginGet() {
		return "guest/adminLogin";
	}
	
	// 관리자 인증하기
	@RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
	public String adminLoginPost(HttpSession session, String mid, String pwd) {
		if(mid.equals("admin") && pwd.equals("1234")) {
			session.setAttribute("smid", "admin");
			return "redirect:/msg/adminLoginOk";
		}
		else {
			return "redirect:/msg/adminLoginNo";
		}
	}
	
	// 관리자 로그아웃하기
	@RequestMapping(value = "/adminLogout", method = RequestMethod.GET)
	public String adminLogoutGet(HttpSession session) {
		session.removeAttribute("smid");
		return "redirect:/msg/adminLogout";
	}
	
}
