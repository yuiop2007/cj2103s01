package com.spring.cj2103s01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.cj2103s01.service.MemberService;
import com.spring.cj2103s01.vo.MemberVO;

@Controller
public class MemberController {
	String msgFlag = "";
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet() {
		
		return "member/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost(String mId, String mPwd, HttpSession session) {
		MemberVO vo = memberService.getIdCheck(mId);
		
		if(vo != null && bCryptPasswordEncoder.matches(mPwd, vo.getmPwd()) && vo.getmDrop().equals("NO")) {
			String strLevel = "";
			if(vo.getmLevel()==0) strLevel = "관리자";
			else if(vo.getmLevel()==1) strLevel = "특별회원";
			else if(vo.getmLevel()==2) strLevel = "우수회원";
			else if(vo.getmLevel()==3) strLevel = "정회원";
			else  strLevel = "준회원";
			
			session.setAttribute("smid", mId);
			session.setAttribute("slevel", vo.getmLevel());
			session.setAttribute("sStrLevel", strLevel);
			
			msgFlag = "mLoginOk";
		}
		else {
			msgFlag = "mLoginNo";
		}
		
		return "redirect:/msg/" + msgFlag;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String lougoutGet() {
		return "redirect:/msg/mLogoutOk";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String joinGet() {
		return "member/join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinPost(MemberVO vo, HttpServletRequest request) {
		// 아이디 중복체크
		if(memberService.getIdCheck(vo.getmId()) != null) {
			msgFlag = "mJoinNo";
			return "redirect:/msg/" + msgFlag;
		}
		
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
		mBirth = year + month + day;
		vo.setmBirth(mBirth);

		memberService.setMemberInput(vo);
		
		return "redirect:/msg/mJoinOk";
	}
	
	@ResponseBody
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public String idCheckPost(String mId) {
		String res = "0";
		MemberVO vo = memberService.getIdCheck(mId);
		if(vo != null) res = "1";
		
		return res;
	}
	
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public String mypageGet() {
		return "member/mypage";
	}
	
	@RequestMapping(value = "/mupdate", method = RequestMethod.GET)
	public String mupdateGet() {
		return "member/mupdate";
	}
	
	@RequestMapping(value = "/mupdate", method = RequestMethod.POST)
	public String mupdatePost() {
		return "member/mupdate";
	}
}
