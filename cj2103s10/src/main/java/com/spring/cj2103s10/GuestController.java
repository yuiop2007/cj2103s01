package com.spring.cj2103s10;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.cj2103s10.service.GuestService;
import com.spring.cj2103s10.vo.GuestVO;

@Controller
@RequestMapping("/guest")
public class GuestController {
  
	@Autowired
	GuestService guestService;
	
	
	// 방명록 불러오기
	@RequestMapping(value = "/gList", method = RequestMethod.GET)
	public String gListGet(Model model) {
		
		List<GuestVO> vos = guestService.getGuestList();
		
		model.addAttribute("vos", vos);
		
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
	
}
