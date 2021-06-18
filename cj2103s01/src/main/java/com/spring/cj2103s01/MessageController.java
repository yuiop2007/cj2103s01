package com.spring.cj2103s01;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MessageController {
	
	@RequestMapping(value = "/msg/{msgFlag}", method = RequestMethod.GET)
	public String msgGet(@PathVariable String msgFlag, Model model, HttpSession session) {
		String smid = session.getAttribute("smid")==null ? "" : (String) session.getAttribute("smid");
		
		if(msgFlag.equals("mJoinOk")) {
			model.addAttribute("msg", "회원 가입을 환영합니다.");
			model.addAttribute("url", "login");
		}
		else if(msgFlag.equals("mJoinNo")) {
			model.addAttribute("msg", "가입실패");
			model.addAttribute("url", "join");
		}
		else if(msgFlag.equals("mLoginOk")) {
			model.addAttribute("msg", "회원님 반갑습니다.");
			model.addAttribute("url", "main");
		}
		else if(msgFlag.equals("mLogoutOk")) {
			session.invalidate();
			model.addAttribute("msg", smid + "님 로그아웃 되었습니다.");
			model.addAttribute("url", "main");
		}
			
		return "include/message";
	}
}
