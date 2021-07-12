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
		String sStrLevel = session.getAttribute("sStrLevel")==null ? "" : (String) session.getAttribute("sStrLevel");
		
		if(msgFlag.equals("mJoinOk")) {
			model.addAttribute("msg", "회원 가입을 환영합니다.");
			model.addAttribute("url", "member/login");
		}
		else if(msgFlag.equals("mJoinNo")) {
			model.addAttribute("msg", "가입실패");
			model.addAttribute("url", "member/join");
		}
		else if(msgFlag.equals("mLoginOk")) {
			if(sStrLevel.equals("관리자")) {
				model.addAttribute("msg", sStrLevel +"님 반갑습니다.");
				model.addAttribute("url", "admin/admin");
			}else {
				model.addAttribute("msg", sStrLevel +"님 반갑습니다.");
				model.addAttribute("url", "main");
			}
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
		else if(msgFlag.equals("pInputOk")) {
			model.addAttribute("msg", "상품이 등록되었습니다.");
			model.addAttribute("url", "product/pList");
		}
		else if(msgFlag.equals("pInputNo")) {
			model.addAttribute("msg", "상품등록에 실패하였습니다.");
			model.addAttribute("url", "product/pList");
		}
		else if(msgFlag.equals("nInputOk")) {
			model.addAttribute("msg", "게시물이 등록되었습니다.");
			model.addAttribute("url", "board/notice");
		}
		else if(msgFlag.equals("nInputNo")) {
			model.addAttribute("msg", "게시물 등록에 실패하였습니다.");
			model.addAttribute("url", "board/notice");
		}
		else if(msgFlag.equals("eInputOk")) {
			model.addAttribute("msg", "게시물이 등록되었습니다.");
			model.addAttribute("url", "board/event");
		}
		else if(msgFlag.equals("eInputNo")) {
			model.addAttribute("msg", "게시물 등록에 실패하였습니다.");
			model.addAttribute("url", "board/event");
		}
		else if(msgFlag.equals("rInputOk")) {
			model.addAttribute("msg", "게시물이 등록되었습니다.");
			model.addAttribute("url", "/member/mypage");
		}
		else if(msgFlag.equals("rInputNo")) {
			model.addAttribute("msg", "게시물 등록에 실패하였습니다.");
			model.addAttribute("url", "/member/mypage");
		}
		else if(msgFlag.equals("qInputOk")) {
			model.addAttribute("msg", "게시물이 등록되었습니다.");
			model.addAttribute("url", "/member/mypage");
		}
		else if(msgFlag.equals("qInputNo")) {
			model.addAttribute("msg", "게시물 등록에 실패하였습니다.");
			model.addAttribute("url", "/member/mypage");
		}
		else if(msgFlag.equals("cInputOk")) {
			model.addAttribute("msg", "쿠폰이 등록되었습니다.");
			model.addAttribute("url", "/admin/coupon");
		}
		else if(msgFlag.equals("cInputNo")) {
			model.addAttribute("msg", "쿠폰 등록에 실패하였습니다.");
			model.addAttribute("url", "/admin/cInput");
		}
		else if(msgFlag.equals("cDeleteOk")) {
			model.addAttribute("msg", "쿠폰이 삭제되었습니다.");
			model.addAttribute("url", "/admin/coupon");
		}
		else if(msgFlag.equals("cDeleteNo")) {
			model.addAttribute("msg", "쿠폰 삭제에 실패하였습니다.");
			model.addAttribute("url", "/admin/coupon");
		}
		else if(msgFlag.equals("wishAddNo")) {
			model.addAttribute("msg", "이미 Wish에 등록된 제품입니다.");
			model.addAttribute("url", "wish/myWish");
		}
		else if(msgFlag.equals("wishDeleteOk")) {
			model.addAttribute("msg", "삭제되었습니다.");
			model.addAttribute("url", "wish/myWish");
		}
		else if(msgFlag.equals("adminMemberDeleteOk")) {
			model.addAttribute("msg", "선택한 회원(들)을 삭제처리 하였습니다.");
			model.addAttribute("url", "admin/mList");
		}
		else if(msgFlag.equals("StockNo")) {
			model.addAttribute("msg", "재고가 부족합니다.");
			model.addAttribute("url", "order/cartList");
		}
	
		
		
		// 예) msgFlag = "boardPasswordNo?idx="+idx+"&pag="+pag+"&pageSize="+pageSize;
		else if(msgFlag.substring(0,15).equals("eventPasswordNo")) {
			model.addAttribute("msg", "비밀번호 오류입니다. 확인하세요.");
			model.addAttribute("url", "board/eContent?"+msgFlag.substring(16));
		}
		else if(msgFlag.substring(0,15).equals("eventPasswordOk")) {
			model.addAttribute("msg", "수정창으로 이동합니다.");
			model.addAttribute("url", "board/eUpdate?"+msgFlag.substring(16));
		}
		else if(msgFlag.substring(0,13).equals("eventDeleteOk")) {
			model.addAttribute("msg", "삭제되었습니다.");
			model.addAttribute("url", "board/event?"+msgFlag.substring(14));
		}
		else if(msgFlag.substring(0,13).equals("eventUpdateOk")) {
			model.addAttribute("msg", "게시글이 수정처리 되었습니다.");
			model.addAttribute("url", "board/eContent?"+msgFlag.substring(14));
		}
		else if(msgFlag.substring(0,16).equals("noticePasswordNo")) {
			model.addAttribute("msg", "비밀번호 오류입니다. 확인하세요.");
			model.addAttribute("url", "board/nContent?"+msgFlag.substring(17));
		}
		else if(msgFlag.substring(0,16).equals("noticePasswordOk")) {
			model.addAttribute("msg", "수정창으로 이동합니다.");
			model.addAttribute("url", "board/nUpdate?"+msgFlag.substring(17));
		}
		else if(msgFlag.substring(0,14).equals("noticeDeleteOk")) {
			model.addAttribute("msg", "삭제되었습니다.");
			model.addAttribute("url", "board/notice?"+msgFlag.substring(15));
		}
		else if(msgFlag.substring(0,14).equals("noticeUpdateOk")) {
			model.addAttribute("msg", "게시글이 수정처리 되었습니다.");
			model.addAttribute("url", "board/nContent?"+msgFlag.substring(15));
		}
		else if(msgFlag.substring(0,15).equals("productUpdateIn")) {
			model.addAttribute("msg", "수정창으로 이동합니다.");
			model.addAttribute("url", "product/pUpdate?"+msgFlag.substring(16));
		}
		else if(msgFlag.substring(0,15).equals("productDeleteOk")) {
			model.addAttribute("msg", "삭제되었습니다.");
			model.addAttribute("url", "product/pList?"+msgFlag.substring(16));
		}
		else if(msgFlag.substring(0,15).equals("productUpdateOk")) {
			model.addAttribute("msg", "게시글이 수정처리 되었습니다.");
			model.addAttribute("url", "product/pContent?"+msgFlag.substring(16));
		}
		else if(msgFlag.substring(0,15).equals("productUpdateNo")) {
			model.addAttribute("msg", "게시글 수정에 실패하였습니다.");
			model.addAttribute("url", "product/pContent?"+msgFlag.substring(16));
		}
		else if(msgFlag.substring(0,16).equals("reviewPasswordNo")) {
			model.addAttribute("msg", "비밀번호 오류입니다. 확인하세요.");
			model.addAttribute("url", "board/rContent?"+msgFlag.substring(17));
		}
		else if(msgFlag.substring(0,16).equals("reviewPasswordOk")) {
			model.addAttribute("msg", "수정창으로 이동합니다.");
			model.addAttribute("url", "board/rUpdate?"+msgFlag.substring(17));
		}
		else if(msgFlag.substring(0,14).equals("reviewDeleteOk")) {
			model.addAttribute("msg", "삭제되었습니다.");
			model.addAttribute("url", "product/pContent?"+msgFlag.substring(15));
		}
		else if(msgFlag.substring(0,14).equals("reviewUpdateOk")) {
			model.addAttribute("msg", "게시글이 수정처리 되었습니다.");
			model.addAttribute("url", "board/rContent?"+msgFlag.substring(15));
		}
		else if(msgFlag.substring(0,13).equals("qnaPasswordNo")) {
			model.addAttribute("msg", "비밀번호 오류입니다. 확인하세요.");
			model.addAttribute("url", "board/qContent?"+msgFlag.substring(14));
		}
		else if(msgFlag.substring(0,13).equals("qnaPasswordOk")) {
			model.addAttribute("msg", "수정창으로 이동합니다.");
			model.addAttribute("url", "board/qUpdate?"+msgFlag.substring(14));
		}
		else if(msgFlag.substring(0,11).equals("qnaDeleteOk")) {
			model.addAttribute("msg", "삭제되었습니다.");
			model.addAttribute("url", "product/pContent?"+msgFlag.substring(12));
		}
		else if(msgFlag.substring(0,11).equals("qnaUpdateOk")) {
			model.addAttribute("msg", "게시글이 수정처리 되었습니다.");
			model.addAttribute("url", "board/qContent?"+msgFlag.substring(12));
		}
		else if(msgFlag.substring(0,10).equals("qnaReplyOk")) {
			model.addAttribute("msg", "게시글을 등록하였습니다.");
			model.addAttribute("url", "board/qContent?"+msgFlag.substring(11));
		}
		else if(msgFlag.substring(0,9).equals("wishAddOk")) {
			model.addAttribute("msg", "추가하였습니다.");
			model.addAttribute("url", "product/pContent?"+msgFlag.substring(10));
		}
		return "include/message";
	}
}
