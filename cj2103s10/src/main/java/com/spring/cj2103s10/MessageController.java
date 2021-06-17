package com.spring.cj2103s10;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MessageController {
	
	@RequestMapping(value = "/msg/{msgFlag}", method = RequestMethod.GET)
	public String msgGet(@PathVariable String msgFlag, Model model) {
		if(msgFlag.equals("guestInputOk")) {
			model.addAttribute("msg", "방명록에 글내용이 등록되었습니다.");
			model.addAttribute("url", "/guest/gList");
		}
		else if(msgFlag.equals("guestDeleteOk")) {
			model.addAttribute("msg", "방명록 내역이 삭제되었습니다.");
			model.addAttribute("url", "/guest/gList");
		}
		else if(msgFlag.equals("adminLoginOk")) {
			model.addAttribute("msg", "관리자 인증 Ok!!!");
			model.addAttribute("url", "/guest/gList");
		}
		else if(msgFlag.equals("adminLoginNo")) {
			model.addAttribute("msg", "관리자 인증 실패~~~");
			model.addAttribute("url", "/guest/adminLogin");
		}
		
		return "include/message";
	}
}
