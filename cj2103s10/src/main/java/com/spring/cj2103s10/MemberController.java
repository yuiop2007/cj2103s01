package com.spring.cj2103s10;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.cj2103s10.service.MemberService;
import com.spring.cj2103s10.vo.MemberVO;
import com.spring.cj2103s10.vo.NotifyVO;

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
	public String mLoginGet(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();  // 기존에 저장되어 있는 현재 사이트의 쿠키를 불러와서 배열로 저장한다.
		String mid = "";
		for(int i=0; i<cookies.length; i++) {
			if(cookies[i].getName().equals("cmid")) {
				mid = cookies[i].getValue();
				request.setAttribute("mid", mid);
				break;
			}
		}
		
		return "member/mLogin";
	}
	
	// 로그인 인증하기
	@RequestMapping(value = "/mLogin", method = RequestMethod.POST)
	public String mLoginPost(String mid, String pwd, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
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
			
			// 아이디에 대한 정보를 쿠키로 저장 유무 처리?
			String idCheck = request.getParameter("idCheck")==null ? "" : request.getParameter("idCheck");
			
			if(idCheck.equals("on")) {  // 쿠키 저장
				Cookie cookie = new Cookie("cmid", mid);
				cookie.setMaxAge(60*60*24*4);	// 쿠키의 만료시간을 4일로 정했다.(단위 '초')
				response.addCookie(cookie);
			}
			else {  // 저장된 cmid 쿠키명을 삭제처리
				Cookie[] cookies = request.getCookies();  // 기존에 저장되어 있는 현재 사이트의 쿠키를 불러와서 배열로 저장한다.
				for(int i=0; i<cookies.length; i++) {
					if(cookies[i].getName().equals("cmid")) {
						cookies[i].setMaxAge(0);	// 저장된 cmid 쿠키를 삭제한다.
						response.addCookie(cookies[i]);
						break;
					}
				}
			}
			
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
	
	@RequestMapping(value = "/mUpdateCheck", method = RequestMethod.POST)
	public String mUpdateCheckPost(String mid, String pwd) {
		MemberVO vo = memberService.getIdCheck(mid);
		
		if(vo != null && bCryptPasswordEncoder.matches(pwd, vo.getPwd()) && vo.getUserDel().equals("NO")) {
			msgFlag = "pwdCheckOk";
		}
		else {
			msgFlag = "pwdCheckNo";
		}
		
		return "redirect:/msg/" + msgFlag;
	}
	
	@RequestMapping(value = "/mUpdate", method = RequestMethod.GET)
	public String mUpdateGet(HttpSession session, Model model) {
		String mid = (String) session.getAttribute("smid");
		MemberVO vo = memberService.getIdCheck(mid);
		model.addAttribute("vo", vo);
		
		return "member/mUpdate";
	}
	
	@RequestMapping(value = "/mUpdate", method = RequestMethod.POST)
	public String mUpdatePost(MultipartFile mFile, MemberVO vo, HttpSession session, Model model) {
		String nickName = (String) session.getAttribute("snickname");
		
		// 닉네임 중복체크
		if(!nickName.equals(vo.getNickName())) {
			if(memberService.getNicknameCheck(vo.getNickName()) != null) {
				msgFlag = "nickCheckNo";
				return "redirect:/msg/" + msgFlag;
			}
		}
		
		// 비밀번호 암호화 처리
		vo.setPwd(bCryptPasswordEncoder.encode(vo.getPwd()));
		
		memberService.setMemberUpdate(mFile, vo);
		
		return "redirect:/msg/mUpdateOk";
	}
	
	// 회원 탈퇴처리
	@RequestMapping(value = "/mDelete", method = RequestMethod.GET)
	public String mDeleteGet(String mid) {
		memberService.mDelete(mid);
		
		return "redirect:/msg/mDeleteOk";
	}
	
	// 회원 아이디 찾기
	@RequestMapping(value = "/mIdSearch", method = RequestMethod.GET)
	public String mIdSearchGet() {
		return "member/mIdSearch";
	}
	
	// 회원 아이디 찾기(메일보내기위한 준비)
	@RequestMapping(value = "/mIdSearch", method = RequestMethod.POST)
	public String mIdSearchPost(String tel, String toMail) {
		MemberVO vo = memberService.getIdSearch(tel, toMail);
		if(vo != null) {
			String content = vo.getMid();
			
			//msgFlag = "pwdConfirmOk";
			return "redirect:/mail/idConfirmMailForm/"+toMail+"/"+content+"/";
		}
		else {
			msgFlag = "idConfirmNo";
			return "redirect:/msg/" + msgFlag;
		}
		
	}
	
	// 회원 비밀번호 찾기
	@RequestMapping(value = "/mPwdSearch", method = RequestMethod.GET)
	public String mPwdSearchGet() {
		return "member/mPwdSearch";
	}
	
	// 회원 비밀번호 찾기(메일보내기위한 준비)
	@RequestMapping(value = "/mPwdSearch", method = RequestMethod.POST)
	public String mPwdSearchPost(String mid, String toMail) {
		MemberVO vo = memberService.getPwdSearch(mid, toMail);
		String pwd = "";
		if(vo != null) {
			// 임시비밀번호를 발급한다.
			UUID uid = UUID.randomUUID();
			pwd = uid.toString().substring(0,8);
			memberService.setPwdChange(mid, bCryptPasswordEncoder.encode(pwd));
			
			String content = pwd;
			
			//msgFlag = "pwdConfirmOk";
			return "redirect:/mail/pwdConfirmMailForm/"+toMail+"/"+content+"/";
		}
		else {
			msgFlag = "pwdConfirmNo";
			return "redirect:/msg/" + msgFlag;
		}
	}
	
	@RequestMapping(value="/notify/mnList", method=RequestMethod.GET)
  public String mnListGet(Model model) {
  	List<NotifyVO> vos = memberService.getMNList();
  	model.addAttribute("vos", vos);
  	model.addAttribute("num", vos.size());
  	
  	return "member/mnList";
  }
	
	@RequestMapping(value="/notify/mnPopup", method=RequestMethod.GET)
	public String popupGet(int idx, Model model) {
		NotifyVO vo = memberService.getPopup(idx);
		model.addAttribute("vo", vo);
		
		return "member/mnPopup";
	}
	
	@RequestMapping(value="/notify/mNotifyView", method=RequestMethod.GET)
	public String mnNotifyViewGet(int idx, Model model) {
		NotifyVO vo = memberService.getPopup(idx);
		model.addAttribute("vo", vo);
		
		return "member/mNotifyView";
	}
}
