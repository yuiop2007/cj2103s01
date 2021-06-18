package com.spring.cj2103s10;

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
		String nickName = session.getAttribute("snickname")==null ? "" : (String) session.getAttribute("snickname");
		String strLevel = session.getAttribute("sStrLevel")==null ? "" : (String) session.getAttribute("sStrLevel");
		
		if(msgFlag.equals("guestInputOk")) {
			model.addAttribute("msg", "방명록에 글내용이 등록되었습니다.");
			model.addAttribute("url", "guest/gList");
		}
		else if(msgFlag.equals("guestDeleteOk")) {
			model.addAttribute("msg", "방명록 내역이 삭제되었습니다.");
			model.addAttribute("url", "guest/gList");
		}
		else if(msgFlag.equals("adminLoginOk")) {
			model.addAttribute("msg", "관리자 인증 Ok!!!");
			model.addAttribute("url", "guest/gList");
		}
		else if(msgFlag.equals("adminLoginNo")) {
			model.addAttribute("msg", "관리자 인증 실패~~~");
			model.addAttribute("url", "guest/adminLogin");
		}
		else if(msgFlag.equals("adminLogout")) {
			model.addAttribute("msg", "관리자 로그아웃");
			model.addAttribute("url", "guest/gList");
		}
		else if(msgFlag.equals("mJoinNo")) {
			model.addAttribute("msg", "가입실패~~");
			model.addAttribute("url", "member/mJoin");
		}
		else if(msgFlag.equals("mJoinOk")) {
			model.addAttribute("msg", "회원 가입을 환영합니다.");
			model.addAttribute("url", "member/mLogin");
		}
		else if(msgFlag.equals("mLoginOk")) {
			model.addAttribute("msg", nickName+"("+strLevel+")님 반갑습니다.");
			model.addAttribute("url", "main");
		}
		else if(msgFlag.equals("mLoginNo")) {
			model.addAttribute("msg", "로그인 실패~~~");
			model.addAttribute("url", "member/mLogin");
		}
		else if(msgFlag.equals("mLogoutOk")) {
			session.invalidate();
			model.addAttribute("msg", nickName + "님 로그아웃 되었습니다.");
			model.addAttribute("url", "main");
		}
		else if(msgFlag.equals("useNo")) {
			model.addAttribute("msg", "사용하실수 없습니다.");
			model.addAttribute("url", "main");
		}
		
		return "include/message";
	}
}
