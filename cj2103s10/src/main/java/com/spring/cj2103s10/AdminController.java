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
  
  // ????????? ????????? ??????????????????
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
  
	/* ?????????, '???????????????'??? ????????? ????????????~  */
	
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
	
	/* '???????????????'??? ??????????????????  */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/reservation/roomInputOk", method=RequestMethod.POST)
	public String roomInputOkPost(ReservationRoomVO vo) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String uploadPath = request.getRealPath("/resources/ckeditor/images/reservation/");  // ckeditor??? ????????? ???????????? ??????????????? ??????
		
		// 54??? ????????? ????????? ???????????? ??? ?????? : src="/cj2103s10/resources/ckeditor/images/reservation/????????????.jsp"
		adminService.imgCheck(vo.getContent(), uploadPath, 54); // ???????????? ???????????? 'src'????????? ????????????

		// ????????? ??????????????? src????????? ?????????????????? DB??? ???????????? ??????????????? src????????? ??????????????????.
		vo.setContent(vo.getContent().replace("/resources/ckeditor/images/reservation/", "/resources/ckeditor/images/reservation/src/")); // ????????? ????????? ???????????? ??????
		
		// <p>~</p>????????? ????????? ????????? ?????????(ckeditor?????? ????????? ??????????????? ?????????????????? ????????? ????????? ??????????????? <p>~</p>??????????????? ????????? ????????????.
		String content = "";
		String tempContent = vo.getContent();
		while(true) {
			content += tempContent.substring(tempContent.indexOf("<p>"),tempContent.indexOf("</p>")+4);
			tempContent = tempContent.substring(tempContent.indexOf("</p>")+4);
			if(tempContent.indexOf("<p>")== -1) break;
		}
		vo.setContent(content);
		
		adminService.roomInputOk(vo); // ??? ????????? vo??? DB??? ????????????.
		
		return "redirect:/msg/roomInputOk";
	}
	
	// ?????? ????????????
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/reservation/roomDelete", method=RequestMethod.GET)
	public String roomListGet(String room, HttpServletRequest request) {
		ReservationRoomVO vo = adminService.getRoomSearch(room);
		
  	//?????? ????????? ????????? ??????????????? ????????????
		String uploadPath = request.getRealPath("/resources/ckeditor/images/reservation/src/");
		adminService.imgDelete(uploadPath, vo.getContent());
		
		//DB?????? ?????? ???????????? ????????????
		adminService.roomDeleteOk(room);
		return "redirect:/msg/roomDeleteOk";
	}
	
	// ?????? ????????????
	@RequestMapping(value="/reservation/roomView", method=RequestMethod.GET)
	public String roomViewGet(String room, Model model) {
		ReservationRoomVO vo = adminService.getRoomSearch(room);
		model.addAttribute("vo", vo);
		return "admin/reservation/roomView";
	}
	
	// ?????? ???????????? ????????????
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
		// board???????????? ????????? 'ckeditor/images'????????? ?????? ?????????????????? ?????????????????????.
		String uploadPath = request.getRealPath("/resources/ckeditor/images/");
		int fileCnt = adminService.imgDelete(uploadPath);
		
		msgFlag = "imgDeleteOk$"+(fileCnt-2);
		
		return "redirect:/msg/" + msgFlag;
	}
}
