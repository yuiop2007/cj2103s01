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
			model.addAttribute("url", "member/login");
		}
		else if(msgFlag.equals("mJoinNo")) {
			model.addAttribute("msg", "가입실패");
			model.addAttribute("url", "member/join");
		}
		else if(msgFlag.equals("mLoginOk")) {
			model.addAttribute("msg", "회원님 반갑습니다.");
			model.addAttribute("url", "main");
		}
		else if(msgFlag.equals("mLoginNo")) {
			model.addAttribute("msg", "아이디 또는 비밀번호를 확인하세요.");
			model.addAttribute("url", "member/login");
		}
		else if(msgFlag.equals("mLoginDel")) {
			model.addAttribute("msg", "탈퇴 된 아이디입니다.");
			model.addAttribute("url", "member/login");
		}
		else if(msgFlag.equals("mLogoutOk")) {
			session.invalidate();
			model.addAttribute("msg", smid + "님 로그아웃 되었습니다.");
			model.addAttribute("url", "main");
		}
		else if(msgFlag.equals("mUpdateOk")) {
			model.addAttribute("msg", "정보가 수정되었습니다.");
			model.addAttribute("url", "member/mypage");
		}
		else if(msgFlag.equals("mDeleteOk")) {
			session.invalidate();
			model.addAttribute("msg", "회원탈퇴 되었습니다.");
			model.addAttribute("url", "main");
		}
		else if(msgFlag.equals("mailSendOk")) {
			model.addAttribute("msg", "성공적으로 메일이 전송되었습니다.");
			model.addAttribute("url", "main");
		}
		else if(msgFlag.equals("pwdConfirmNo")) {
			model.addAttribute("msg", "잘못된 정보입니다. 확인하세요.");
			model.addAttribute("url", "main");
		}
		else if(msgFlag.equals("pwdConfirmOk")) {
			model.addAttribute("msg", "임시비밀번호가 메일로 전송되었습니다.\\n메일을 확인하세요.");
			model.addAttribute("url", "member/login");
		}
		else if(msgFlag.equals("idConfirmNo")) {
			model.addAttribute("msg", "아이디가 존재하지 않습니다. 회원가입하세요.");
			model.addAttribute("url", "member/join");
		}
		else if(msgFlag.equals("idConfirmOk")) {
			model.addAttribute("msg", "아이디가 메일로 전송되었습니다.\\n메일을 확인하세요.");
			model.addAttribute("url", "member/join");
		}
			
		return "include/message";
	}
}
