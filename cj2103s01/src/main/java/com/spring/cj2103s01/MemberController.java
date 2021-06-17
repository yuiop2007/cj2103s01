package com.spring.cj2103s01;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.cj2103s01.service.MemberService;
import com.spring.cj2103s01.vo.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet() {
		
		return "member/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost() {
		
		return "redirect:/main/main";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String joinGet() {
		return "member/join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinPost(MemberVO vo, HttpServletRequest request) {
		vo.setmPwd(bCryptPasswordEncoder.encode(vo.getmPwd()));  // 비밀번호 암호화 하여 vo에 저장
		
		String add1 = request.getParameter("add1") == null ? "" : request.getParameter("add1");
		String add2 = request.getParameter("add2") == null ? "" : request.getParameter("add2");
		String add3 = request.getParameter("add3") == null ? "" : request.getParameter("add3");
		String mAddress = "";
		mAddress = add1 + " " + add2 + " " + add3;
		vo.setmAddress(mAddress);
		
		String tel1 = request.getParameter("tel1") == null ? "" : request.getParameter("tel1");
		String tel2 = request.getParameter("tel2") == null ? "" : request.getParameter("tel2");
		String tel3 = request.getParameter("tel3") == null ? "" : request.getParameter("tel3");
		String mPhone = tel1 + "-" + tel2 + "-" + tel3;
		vo.setmPhone(mPhone);
		
		String year = request.getParameter("birth_year") == null ? "" : request.getParameter("birth_year");
		String month = request.getParameter("birth_month") == null ? "" : request.getParameter("birth_month");
		String day = request.getParameter("birth_day") == null ? "" : request.getParameter("birth_day");
		String mBirth = "";
		vo.setmBirth(mBirth);

		memberService.setMemberInput(vo);
		
		return "redirect:/msg/mInputOk";
	}
}
