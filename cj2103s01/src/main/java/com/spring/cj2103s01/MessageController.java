package com.spring.cj2103s01;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MessageController {
	
	@RequestMapping(value = "/msg/{msgFlag}", method = RequestMethod.GET)
	public String msgGet(@PathVariable String msgFlag, Model model) {
		if(msgFlag.equals("mInputOk")) {
			model.addAttribute("msg", "사용자가 등록되었습니다.");
			model.addAttribute("url", "member/login");
		}
//		else if(msgFlag.equals("uUpdateOk")) {
//			model.addAttribute("msg", "사용자 정보가 수정되었습니다.");
//			model.addAttribute("url", "/user/uList");
//		}
//		else if(msgFlag.equals("uDeleteOk")) {
//			model.addAttribute("msg", "사용자 정보가 삭제되었습니다.");
//			model.addAttribute("url", "/user/uList");
//		}
		
		return "include/message";
	}
}
