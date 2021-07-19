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
		else if(msgFlag.equals("pwdCheckOk")) {
			model.addAttribute("msg", "비밀번호 인증 확인!");
			model.addAttribute("url", "member/mUpdate");
		}
		else if(msgFlag.equals("pwdCheckNo")) {
			model.addAttribute("msg", "비밀번호 인증 실패~~");
			model.addAttribute("url", "member/mUpdateCheck");
		}
		else if(msgFlag.equals("nickCheckNo")) {
			model.addAttribute("msg", "닉네임이 중복되었습니다.");
			model.addAttribute("url", "member/mUpdate");
		}
		else if(msgFlag.equals("mUpdateOk")) {
			model.addAttribute("msg", "회원 정보가 수정되었습니다.");
			model.addAttribute("url", "main");
		}
		else if(msgFlag.equals("mDeleteOk")) {
			session.invalidate();
			model.addAttribute("msg", "탈퇴 되었습니다.");
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
			model.addAttribute("url", "member/mLogin");
		}
		else if(msgFlag.equals("idConfirmNo")) {
			model.addAttribute("msg", "잘못된 정보입니다. 확인하세요.");
			model.addAttribute("url", "main");
		}
		else if(msgFlag.equals("idConfirmOk")) {
			model.addAttribute("msg", "검색된 아이디가 메일로 전송되었습니다.\\n메일을 확인하세요.");
			model.addAttribute("url", "member/mLogin");
		}
		else if(msgFlag.equals("bInputOk")) {
			model.addAttribute("msg", "게시글이 등록되었습니다.");
			model.addAttribute("url", "board/bList");
		}
		else if(msgFlag.equals("adminMemberDeleteOk")) {
			model.addAttribute("msg", "선택한 회원(들)을 삭제처리 하였습니다.");
			model.addAttribute("url", "admin/aMList");
		}
		else if(msgFlag.equals("nInputOk")) {
			model.addAttribute("msg", "공지사항이 등록되었습니다.");
			model.addAttribute("url", "admin/notify/nList");
		}
		else if(msgFlag.equals("nUpdateOk")) {
			model.addAttribute("msg", "공지사항이 수정되었습니다.");
			model.addAttribute("url", "admin/notify/nList");
		}
		else if(msgFlag.equals("pmInputOk")) {
			model.addAttribute("msg", "자료파일이 업로드 되었습니다.");
			model.addAttribute("url", "pdsm/pmList");
		}
		else if(msgFlag.equals("thumbnailCreateNo")) {
			model.addAttribute("msg", "썸네일 이미지 업로드 실패~~");
			model.addAttribute("url", "study/thumbnail");
		}
		else if(msgFlag.equals("photoInputOk")) {
			model.addAttribute("msg", "포토갤러리에 사진이 등록되었습니다.");
			model.addAttribute("url", "photo/photo");
		}
		else if(msgFlag.equals("photoDeleteOk")) {
			model.addAttribute("msg", "포토갤러리에 선택하신 내역이 삭제 되었습니다.");
			model.addAttribute("url", "photo/photo");
		}
		else if(msgFlag.equals("roomInputOk")) {
			model.addAttribute("msg", "객실정보가 등록되었습니다.");
			model.addAttribute("url", "admin/reservation/roomList");
		}
		else if(msgFlag.equals("roomDeleteOk")) {
			model.addAttribute("msg", "객실정보가 삭제되었습니다.");
			model.addAttribute("url", "admin/reservation/roomList");
		}
		else if(msgFlag.equals("reservationCancelOk")) {
			model.addAttribute("msg", "예약이 취소 되었습니다.");
			model.addAttribute("url", "reservation/reservationView");
		}
		else if(msgFlag.equals("reservationCancelNo")) {
			model.addAttribute("msg", "예약을 취소하실 수 없습니다.");
			model.addAttribute("url", "reservation/reservationView");
		}
		else if(msgFlag.equals("reservationUpdateNo")) {
			model.addAttribute("msg", "지난 예약일의 내역은 수정하실 수 없습니다.");
			model.addAttribute("url", "reservation/reservationView");
		}
		else if(msgFlag.equals("reservationUpdateOk")) {
			model.addAttribute("msg", "예약 내역이 수정되었습니다.");
			model.addAttribute("url", "reservation/reservationView");
		}
		else if(msgFlag.equals("aVoteInputOk")) {
			model.addAttribute("msg", "설문항목이 등록되었습니다.");
			model.addAttribute("url", "vote/aVoteList");
		}
		
		
		
		
		
		// 예) msgFlag = "boardPasswordNo?idx="+idx+"&pag="+pag+"&pageSize="+pageSize;
		else if(msgFlag.substring(0, 11).equals("imgDeleteOk")) {
			model.addAttribute("msg", "게시판의 임시 그림파일("+msgFlag.substring(12)+"개)이 모두 삭제되었습니다.");
			model.addAttribute("url", "admin/file/tempDelete");
		}
		else if(msgFlag.substring(0,13).equals("boardUpdateOk")) {
			model.addAttribute("msg", "게시글이 수정처리 되었습니다.");
			model.addAttribute("url", "board/bContent?"+msgFlag.substring(14));
		}
		else if(msgFlag.substring(0,15).equals("boardPasswordNo")) {
			model.addAttribute("msg", "비밀번호 오류입니다. 확인하세요.");
			model.addAttribute("url", "board/bContent?"+msgFlag.substring(16));
		}
		else if(msgFlag.substring(0,15).equals("boardPasswordOk")) {
			model.addAttribute("msg", "수정창으로 이동합니다.");
			model.addAttribute("url", "board/bUpdate?"+msgFlag.substring(16));
		}
		else if(msgFlag.substring(0,17).equals("thumbnailCreateOk")) {
			model.addAttribute("msg", "썸네일 이미지 업로드 성공!!!");
			model.addAttribute("url", "study/thumbnailView?"+msgFlag.substring(18));
		}
		else if(msgFlag.substring(0,18).equals("reservationInputOk")) {
			model.addAttribute("msg", "객실이 예약처리 되었습니다.");
			model.addAttribute("url", "reservation/reservation?"+msgFlag.substring(19));
		}
		
		
		return "include/message";
	}
}
