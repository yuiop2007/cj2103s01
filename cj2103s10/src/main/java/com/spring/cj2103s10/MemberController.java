package com.spring.cj2103s10;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.cj2103s10.service.MemberService;
import com.spring.cj2103s10.vo.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberController {
	String msgFlag = "";
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	// 로그인폼 호출
	@RequestMapping(value = "/mLogin", method = RequestMethod.GET)
	public String mLoginGet() {
		return "member/mLogin";
	}
	
	// 로그인 인증하기
	@RequestMapping(value = "/mLogin", method = RequestMethod.POST)
	public String mLoginPost(String mid, String pwd, HttpSession session) {
		MemberVO vo = memberService.getIdCheck(mid);
		
		//pwd = bCryptPasswordEncoder.encode(pwd);
		
		if(vo != null && bCryptPasswordEncoder.matches(pwd, vo.getPwd()) && vo.getUserDel().equals("NO")) {
			String strLevel = "";
			if(vo.getLevel()==0) strLevel = "관리자";
			else if(vo.getLevel()==1) strLevel = "특별회원";
			else if(vo.getLevel()==2) strLevel = "우수회원";
			else if(vo.getLevel()==3) strLevel = "정회원";
			else  strLevel = "준회원";
			
			session.setAttribute("smid", mid);
			session.setAttribute("snickname", vo.getNickName());
			session.setAttribute("slevel", vo.getLevel());
			session.setAttribute("sStrLevel", strLevel);
			
			msgFlag = "mLoginOk";
		}
		else {
			msgFlag = "mLoginNo";
		}
		
		return "redirect:/msg/" + msgFlag;
	}
	
	@RequestMapping(value = "/mLogout", method = RequestMethod.GET)
	public String mLogoutGet() {
		return "redirect:/msg/mLogoutOk";
	}
	
	// 회원가입폼 호출
	@RequestMapping(value = "/mJoin", method = RequestMethod.GET)
	public String mJoinGet() {
		return "member/mJoin";
	}
	
	// 회원가입처리
	@RequestMapping(value = "/mJoin", method = RequestMethod.POST)
	public String mJoinPost(MultipartFile file, MemberVO vo) {
		// 아이디 중복체크
		if(memberService.getIdCheck(vo.getMid()) != null) {
			msgFlag = "mJoinNo";
			return "redirect:/msg/" + msgFlag;
		}
		// 닉네임 중복체크
		if(memberService.getNicknameCheck(vo.getNickName()) != null) {
			msgFlag = "mJoinNo";
			return "redirect:/msg/" + msgFlag;
		}
		
		// 비밀번호 암호화 처리
		vo.setPwd(bCryptPasswordEncoder.encode(vo.getPwd()));
		
		int res = memberService.setMemberInput(file, vo);
		
		if(res == 1) msgFlag = "mJoinOk";
		else msgFlag = "mJoinNo";
		
		return "redirect:/msg/" + msgFlag;
	}
	
	// ID중복체크(aJax처리)
	@ResponseBody
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public String idCheckPost(String mid) {
		String res = "0";
		MemberVO vo = memberService.getIdCheck(mid);
		if(vo != null) res = "1";
		
		return res;
	}
	
	// 닉네임중복체크(aJax처리)
	@ResponseBody
	@RequestMapping(value = "/nickCheck", method = RequestMethod.POST)
	public String nickCheckPost(String nickName) {
		String res = "0";
		MemberVO vo = memberService.getNicknameCheck(nickName);
		if(vo != null) res = "1";
		
		return res;
	}
	
	@RequestMapping(value = "/mUpdateCheck", method = RequestMethod.GET)
	public String mUpdateCheckGet() {
		return "member/mUpdateCheck";
	}
}
