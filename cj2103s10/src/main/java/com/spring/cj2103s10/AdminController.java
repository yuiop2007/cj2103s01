package com.spring.cj2103s10;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.spring.cj2103s10.pagination.Pagination;
import com.spring.cj2103s10.pagination.PaginationVO;
import com.spring.cj2103s10.service.AdminService;
import com.spring.cj2103s10.vo.MemberVO;
import com.spring.cj2103s10.vo.NotifyVO;
import com.spring.cj2103s10.vo.ReservationRoomVO;

@Controller
@RequestMapping("/admin")
public class AdminController {
  String msgFlag = "";
  
  @Autowired
  AdminService adminService;
  
  @Autowired
  Pagination pagination;
	
  @RequestMapping(value="/aCheck", method=RequestMethod.GET)
  public String aCheckGet() {
  	return "admin/aMain";
  }
  
  @RequestMapping(value="/aMenu", method=RequestMethod.GET)
  public String aMenuGet() {
  	return "admin/aMenu";
  }
  
  @RequestMapping(value="/aContent", method=RequestMethod.GET)
  public String aContentGet() {
  	return "admin/aContent";
  }
  
  @RequestMapping(value="/aOut", method=RequestMethod.GET)
  public String aOutGet() {
  	return "redirect:/";
  }
  
  @RequestMapping(value="/aMList", method=RequestMethod.GET)
  public String aMListGet(Model model,
  		@RequestParam(name="pag", defaultValue="1", required=false) int pag,
			@RequestParam(name="pageSize", defaultValue="5", required=false) int pageSize) {
  	
  	PaginationVO pageVO = pagination.pagination(pag, pageSize, "member", "", "");
  	
  	List<MemberVO> vos = adminService.getMemberList(pageVO.getStartIndexNo(), pageSize);
  	
  	model.addAttribute("vos", vos);
  	model.addAttribute("pageVO", pageVO);
  	
  	return "admin/member/aMList";
  }
  
  // 선택한 회원들 삭제처리하기
  @RequestMapping(value="/aMList", method=RequestMethod.POST)
  public String aMListPost(String delItems) {
  	String[] idxs = delItems.split("/");
  	
  	for(String idx : idxs) {
  		adminService.memberDelete(Integer.parseInt(idx));
  	}
  	
  	return "redirect:/msg/adminMemberDeleteOk";
  }
  
  @RequestMapping(value="/aMListLevel", method=RequestMethod.GET)
  public String aMListLevelGet(Model model, String level,
  		@RequestParam(name="pag", defaultValue="1", required=false) int pag,
  		@RequestParam(name="pageSize", defaultValue="5", required=false) int pageSize) {
  	
  	PaginationVO pageVO = pagination.pagination(pag, pageSize, "member", "", level);
  	
  	List<MemberVO> vos;
  	if(!level.equals("")) {
  	  vos = adminService.getMemberListLevel(pageVO.getStartIndexNo(), pageSize, level);
  	}
  	else {
  		vos = adminService.getMemberList(pageVO.getStartIndexNo(), pageSize);
  	}
  	
  	model.addAttribute("vos", vos);
  	model.addAttribute("pageVO", pageVO);
  	model.addAttribute("stringLevel", level);
  	
  	return "admin/member/aMList";
  }
  
  @RequestMapping(value="/aSearch", method=RequestMethod.GET)
  public String aSearchGet(Model model, String mid,
  		@RequestParam(name="pag", defaultValue="1", required=false) int pag,
  		@RequestParam(name="pageSize", defaultValue="5", required=false) int pageSize) {
  	
  	PaginationVO pageVO = pagination.pagination(pag, pageSize, "member", mid, "");
  	
  	List<MemberVO> vos = adminService.getMemberListMid(pageVO.getStartIndexNo(), pageSize, mid);
  	
  	model.addAttribute("vos", vos);
  	model.addAttribute("pageVO", pageVO);
  	model.addAttribute("mid", mid);
  	
  	return "admin/member/aMList";
  }
  
  @ResponseBody
  @RequestMapping(value="/aMLevelCheck", method=RequestMethod.GET)
  public String aMLevelCheckGet(String mid, String level) {
  	adminService.aMLevelCheck(mid, level);
  	return "";
  }
  
  @RequestMapping(value="/aMInfor", method=RequestMethod.GET)
  public String aMInforGet(Model model, String mid) {
  	MemberVO vo = adminService.getAMInfor(mid);
  	System.out.println("vo : " + vo);
  	model.addAttribute("vo", vo);
  	return "admin/member/aMInfor";
  }
  
  @RequestMapping(value="/notify/nList", method=RequestMethod.GET)
  public String nListGet(Model model) {
  	List<NotifyVO> vos = adminService.getNotifyList();
  	model.addAttribute("vos", vos);
  	model.addAttribute("num", vos.size());
  	
  	return "admin/notify/nList";
  }
  
  @RequestMapping(value="/notify/nInput", method=RequestMethod.GET)
  public String nInputGet() {
  	return "admin/notify/nInput";
  }
  
  @RequestMapping(value="/notify/nInput", method=RequestMethod.POST)
  public String nInputPost(NotifyVO vo) {
  	adminService.nInputOk(vo);
  	
  	return "redirect:/msg/nInputOk";
  }
  
  @RequestMapping(value="/notify/nUpdate", method=RequestMethod.GET)
  public String nUpdateGet(int idx, Model model) {
  	NotifyVO vo = adminService.nUpdate(idx);
  	model.addAttribute("vo", vo);
  	
  	return "admin/notify/nUpdate";
  }
  
  @RequestMapping(value="/notify/nUpdate", method=RequestMethod.POST)
  public String nUpdatePost(NotifyVO vo) {
  	adminService.nUpdateOk(vo);
  	
  	return "redirect:/msg/nUpdateOk";
  }
  
  @ResponseBody
  @RequestMapping(value="/notify/nDelete", method=RequestMethod.GET)
  public String nDeleteGet(int idx) {
  	adminService.nDelete(idx);
  	
  	return "";
  }
  
  @ResponseBody
  @RequestMapping(value="/notify/popupCheck", method=RequestMethod.GET)
  public String popupCheck(int idx, String popupSw) {
  	adminService.popupCheck(idx, popupSw);
  	
  	return "";
  }
  
	/* 아래로, '예약시스템'에 관련된 메소드들~  */
	
	@RequestMapping(value="/reservation/roomList", method=RequestMethod.GET)
	public String roomListGet(Model model) {
		List<ReservationRoomVO> vos = adminService.roomList();
		model.addAttribute("vos", vos);
		model.addAttribute("roomCnt", vos.size());
		return "admin/reservation/roomList";
	}
	
	@RequestMapping(value="/reservation/roomAvailable", method=RequestMethod.GET)
	public String roomAvailableGet(Model model) {
		List<ReservationRoomVO> vos = adminService.roomList();
		model.addAttribute("vos", vos);
		model.addAttribute("roomCnt", vos.size());
		return "admin/reservation/roomAvailable";
	}
	
	/* '예약시스템'에 객실등록하기  */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/reservation/roomInputOk", method=RequestMethod.POST)
	public String roomInputOkPost(ReservationRoomVO vo) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String uploadPath = request.getRealPath("/resources/ckeditor/images/reservation/");  // ckeditor를 통해서 저장되는 그림파일의 경로
		
		// 54는 이미지 파일이 시작하는 첫 위치 : src="/cj2103s10/resources/ckeditor/images/reservation/그림파일.jsp"
		adminService.imgCheck(vo.getContent(), uploadPath, 54); // 이미지를 발췌해서 'src'폴더로 복사처리

		// 파일이 정상적으로 src폴더에 복사되었으면 DB에 저장되는 실제경로를 src폴더로 변경시켜준다.
		vo.setContent(vo.getContent().replace("/resources/ckeditor/images/reservation/", "/resources/ckeditor/images/reservation/src/")); // 실제로 서버에 저장되는 경로
		
		// <p>~</p>태그의 내용만 모아서 붙이기(ckeditor안의 줄바꿈 특수문자가 브라우저에서 인식을 못하고 충돌하기에 <p>~</p>태그내용만 모아서 처리했다.
		String content = "";
		String tempContent = vo.getContent();
		while(true) {
			content += tempContent.substring(tempContent.indexOf("<p>"),tempContent.indexOf("</p>")+4);
			tempContent = tempContent.substring(tempContent.indexOf("</p>")+4);
			if(tempContent.indexOf("<p>")== -1) break;
		}
		vo.setContent(content);
		
		adminService.roomInputOk(vo); // 잘 정비된 vo를 DB에 저장한다.
		
		return "redirect:/msg/roomInputOk";
	}
	
	// 객실 삭제하기
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/reservation/roomDelete", method=RequestMethod.GET)
	public String roomListGet(String room, HttpServletRequest request) {
		ReservationRoomVO vo = adminService.getRoomSearch(room);
		
  	//실제 서버에 저장된 그림파일을 삭제처리
		String uploadPath = request.getRealPath("/resources/ckeditor/images/reservation/src/");
		adminService.imgDelete(uploadPath, vo.getContent());
		
		//DB에서 실제 게시글을 삭제처리
		adminService.roomDeleteOk(room);
		return "redirect:/msg/roomDeleteOk";
	}
	
	// 객실 보여주기
	@RequestMapping(value="/reservation/roomView", method=RequestMethod.GET)
	public String roomViewGet(String room, Model model) {
		ReservationRoomVO vo = adminService.getRoomSearch(room);
		model.addAttribute("vo", vo);
		return "admin/reservation/roomView";
	}
	
	// 객실 사용여부 변경하기
	@ResponseBody
	@RequestMapping(value="/reservation/roomModify", method=RequestMethod.GET)
	public String roomModifyGet(String room, String available) {
		adminService.setRoomModify(room, available);
		return "";
	}
  
	@RequestMapping(value="/file/tempDelete", method=RequestMethod.GET)
	public String tempDeleteGet() {
		return "admin/file/tempDelete";
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/file/boardTempDelete", method=RequestMethod.GET)
	public String boardTempDeleteGet(HttpServletRequest request) {
		// board작업시에 생성된 'ckeditor/images'폴더의 모든 그림파일들을 삭제처리시킨다.
		String uploadPath = request.getRealPath("/resources/ckeditor/images/");
		int fileCnt = adminService.imgDelete(uploadPath);
		
		msgFlag = "imgDeleteOk$"+(fileCnt-2);
		
		return "redirect:/msg/" + msgFlag;
	}
}
