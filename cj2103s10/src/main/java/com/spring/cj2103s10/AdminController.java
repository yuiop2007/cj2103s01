package com.spring.cj2103s10;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.cj2103s10.pagination.Pagination;
import com.spring.cj2103s10.pagination.PaginationVO;
import com.spring.cj2103s10.service.AdminService;
import com.spring.cj2103s10.vo.MemberVO;

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
}
