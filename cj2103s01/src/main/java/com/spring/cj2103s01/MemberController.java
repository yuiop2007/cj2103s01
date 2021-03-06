package com.spring.cj2103s01;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.cj2103s01.pagenation.Pagenation;
import com.spring.cj2103s01.pagenation.PagenationVO;
import com.spring.cj2103s01.service.CouponService;
import com.spring.cj2103s01.service.MemberService;
import com.spring.cj2103s01.service.OrderService;
import com.spring.cj2103s01.service.QnaService;
import com.spring.cj2103s01.service.ReviewService;
import com.spring.cj2103s01.vo.MemberVO;
import com.spring.cj2103s01.vo.QnaVO;
import com.spring.cj2103s01.vo.ReviewVO;

@Controller
@RequestMapping("/member")
public class MemberController {
	String msgFlag = "";
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	CouponService couponService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	Pagenation pagenation;
	
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	QnaService qnaService;
	
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
			else  strLevel = "회원";
			
			int cart = orderService.getCartCnt(mId);
			
			session.setAttribute("cart", cart);
			session.setAttribute("smid", mId);
			session.setAttribute("sname", vo.getmName());
			session.setAttribute("slevel", vo.getmLevel());
			session.setAttribute("sStrLevel", strLevel);
			
			msgFlag = "mLoginOk";
			
		}
		else if(vo != null && bCryptPasswordEncoder.matches(mPwd, vo.getmPwd()) && vo.getmDrop().equals("YES")){
			msgFlag = "mLoginDel";
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
	public String joinPost(MemberVO vo, HttpServletRequest request, Model model) {
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
		mAddress = add1 + "$" + add2 + "$" + add3;
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
		mBirth = year + "-" + month + "-" + day;
		vo.setmBirth(mBirth);
		
		List<MemberVO> vos = memberService.getMemberList();
		for(MemberVO mvo : vos) {
			if(mvo.getmId().equals(vo.getmCmid()))
			vo.setmPoint(4000);
		}

		memberService.setMemberInput(vo);
		
		//가입쿠폰발급
		couponService.setJoinCoupon(vo.getmId());
		
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
	public String mypageGet(HttpSession session, Model model) {
		String mId = (String) session.getAttribute("smid");
		MemberVO vo = memberService.getIdCheck(mId);
		int couponCnt = couponService.getCouponCnt(mId);
		
		model.addAttribute("notPayCnt", orderService.notPayCntMid(mId)); // 입금전 주문 갯수
		model.addAttribute("delReadyCnt", orderService.readyPayCntMid(mId)); // 배송준비중 갯수
		model.addAttribute("delingCnt", orderService.ingPayCntMid(mId)); // 배송중 갯수
		model.addAttribute("delEndCnt", orderService.endPayCntMid(mId)); // 배송완료 주문 갯수
		model.addAttribute("cancelCnt", orderService.cancelCntMid(mId)); // 취소 갯수
		model.addAttribute("changeCnt", orderService.changeCntMid(mId)); // 교환 갯수
		model.addAttribute("returnCnt", orderService.returnCntMid(mId)); // 반품 갯수
		
		
		
		model.addAttribute("vo", vo);
		model.addAttribute("couponCnt", couponCnt);
		return "member/mypage";
	}
	
	@RequestMapping(value = "/mupdate", method = RequestMethod.GET)
	public String mUpdateGet(HttpSession session, Model model) {
		String mId = (String) session.getAttribute("smid");
		MemberVO vo = memberService.getIdCheck(mId);
		
		String add[] = vo.getmAddress().split("[$]");
		String phone[] = vo.getmPhone().split("-");
		String birth[] = vo.getmBirth().split("-");
		
		try {
			for(int i=0; i<=add.length; i++) {
				model.addAttribute("add"+(i+1), add[i]);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			for(int i=0; i<=birth.length; i++) {
				model.addAttribute("birth"+(i+1), birth[i]);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		model.addAttribute("vo", vo);
		model.addAttribute("phone1", phone[0]);
		model.addAttribute("phone2", phone[1]);
		model.addAttribute("phone3", phone[2]);
		
		return "member/mupdate";
	}
	
	@RequestMapping(value = "/mupdate", method = RequestMethod.POST)
	public String mUpdatePost(String mId, MemberVO voc, HttpServletRequest request) {
		MemberVO vo = memberService.getIdCheck(mId);
		
		vo.setmPwd(bCryptPasswordEncoder.encode(voc.getmPwd()));  // 비밀번호 암호화 하여 vo에 저장
		
		vo.setmName(voc.getmName());
		vo.setmEmail(voc.getmEmail());
		vo.setmGender(voc.getmGender());
		vo.setmSolar(voc.getmSolar());
		
		String add1 = request.getParameter("add1") == null ? "" : request.getParameter("add1");
		String add2 = request.getParameter("add2") == null ? "" : request.getParameter("add2");
		String add3 = request.getParameter("add3") == null ? "" : request.getParameter("add3");
		String mAddress = "";
		mAddress = add1 + "$" + add2 + "$" + add3;
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
		mBirth = year + "-" + month + "-" + day;
		vo.setmBirth(mBirth);
		
		memberService.setMemberUpdate(vo);
		
		return "redirect:/msg/mUpdateOk";
	}
	
	// 회원 탈퇴처리
	@RequestMapping(value = "/mDelete", method = RequestMethod.GET)
	public String mDeleteGet(String mId) {
		memberService.mDelete(mId);
		
		return "redirect:/msg/mDeleteOk";
	}
	
	// 회원 비밀번호 찾기
	@RequestMapping(value = "/mPwdSearch", method = RequestMethod.GET)
	public String mPwdSearchGet() {
		return "member/mPwdSearch";
	}
	
	// 회원 비밀번호 찾기(메일보내기위한 준비)
	@RequestMapping(value = "/mPwdSearch", method = RequestMethod.POST)
	public String mPwdSearchPost(String mId, String mEmail) {
		MemberVO vo = memberService.getPwdSearch(mId, mEmail);
		String pwd = "";
		if(vo != null) {
			// 임시비밀번호를 발급한다.
			UUID uid = UUID.randomUUID();
			pwd = uid.toString().substring(0,8);
			memberService.setPwdChange(mId, bCryptPasswordEncoder.encode(pwd));
			
			String content = pwd;
			
			//msgFlag = "pwdConfirmOk";
			return "redirect:/mail/pwdConfirmMailForm/"+mEmail+"/"+content+"/";
		}
		else {
			msgFlag = "pwdConfirmNo";
			return "redirect:/msg/" + msgFlag;
		}
		
	}
		
	// 회원 아이디 찾기
	@RequestMapping(value = "/mIdSearch", method = RequestMethod.GET)
	public String mIdSearchGet() {
		return "member/mIdSearch";
	}
	
	// 회원 아이디 찾기(메일보내기위한 준비)
	@RequestMapping(value = "/mIdSearch", method = RequestMethod.POST)
	public String mIdSearchPost(String mName, String mEmail) {
		MemberVO vo = memberService.getNameSearch(mName, mEmail);
		if(vo != null) {
			
			String content = vo.getmId();
			
			return "redirect:/mail/idConfirmMailForm/"+mEmail+"/"+content+"/";
		}
		else {
			msgFlag = "idConfirmNo";
			return "redirect:/msg/" + msgFlag;
		}
		
	}
	
	@RequestMapping(value = "/myBoard", method = RequestMethod.GET)
	public String myBoardGet(Model model, HttpSession session,
			@RequestParam(name = "rpag", defaultValue = "1", required = false) int rpag,
			@RequestParam(name = "rpageSize", defaultValue = "5", required = false) int rpageSize,
			@RequestParam(name = "qpag", defaultValue = "1", required = false) int qpag,
			@RequestParam(name = "qpageSize", defaultValue = "5", required = false) int qpageSize) {
		if (rpag < 1) {
			rpag = 1;
		}
		String mId = (String) session.getAttribute("smid");
		
		PagenationVO rpageVO = pagenation.pagenation(rpag, rpageSize, "mreview", mId , "");
		
		List<ReviewVO> rvos = reviewService.getReviewMemberList(mId ,rpageVO.getStartIndexNo(), rpageSize);

		model.addAttribute("rvos", rvos);
		model.addAttribute("rpageVO", rpageVO);
		
		PagenationVO qpageVO = pagenation.pagenation(qpag, qpageSize, "mqna", mId , "");
		
		List<QnaVO> qvos = qnaService.getQnaMemberList(mId ,qpageVO.getStartIndexNo(), qpageSize);
		
		model.addAttribute("qvos", qvos);
		model.addAttribute("qpageVO", qpageVO);
		
		return "member/myBoard";
	}
}
