package com.spring.cj2103s10;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.cj2103s10.pagination.Pagination;
import com.spring.cj2103s10.pagination.PaginationVO;
import com.spring.cj2103s10.service.VoteService;
import com.spring.cj2103s10.vo.VotePersonInforVO;
import com.spring.cj2103s10.vo.VotePopularVO;
import com.spring.cj2103s10.vo.VoteVO;

@Controller
@RequestMapping("/vote")
public class VoteController {
	String msgFlag = "";
	
	@Autowired
	VoteService voteService;
	
	@Autowired
	Pagination pagination;
	
	@RequestMapping(value="/aVoteInput", method=RequestMethod.GET)
	public String aVoteInputGet() {
		return "admin/vote/voteInput";
	}
	
	@RequestMapping(value="/aVoteInput", method=RequestMethod.POST)  // 멀티파일을 위한 객체 : MultipartHttpServletRequest객체를 사용한다.
  public String aVoteInputPost(MultipartHttpServletRequest file, VotePopularVO vo) {
  	voteService.aVotePopularInputOk(file, vo);
  	msgFlag = "aVoteInputOk";
  	
  	return "redirect:/msg/" + msgFlag;
  }
	
	@RequestMapping(value="/aVoteInforInsert", method=RequestMethod.GET)
	public String aVoteInforInsertGet(String mid, Model model) {
		if(mid != null) {  // 기존회원이면 memberShip테이블에서 gender를 가져온다.
			String gender = voteService.memberSearch(mid);
			model.addAttribute("gender", gender);
		}
		return "admin/vote/voteInforInsert";
	}
	
	@RequestMapping(value="/aVoteInforInsert", method=RequestMethod.POST)
	public String aVoteInforInsertPost(VotePersonInforVO personVo, HttpSession session) {
		int votePersonInforIdx = voteService.getMaxVotePersonInfor()==0 ? 1 : voteService.getMaxVotePersonInfor() + 1;
		session.setAttribute("sVotePersonInforIdx", votePersonInforIdx);
		personVo.setIdx(votePersonInforIdx);
		voteService.aVoteInforInsert(personVo);
		return "redirect:/admin/aVoteList";
	}
	
	@RequestMapping(value="/aVoteList", method=RequestMethod.GET)
	public String aVoteListGet(HttpServletRequest request, Model model,
			@RequestParam(name="pag", defaultValue="1", required=false) int pag,
			@RequestParam(name="pageSize", defaultValue="5", required=false) int pageSize) {
		
		PaginationVO pageVO = pagination.pagination(pag, pageSize, "vote", "", "");
		
		List<VotePopularVO> vos = voteService.aVotePopularList(pageVO.getStartIndexNo(), pageSize);
		
		model.addAttribute("vos", vos);
		model.addAttribute("pageVO", pageVO);
		
		return "admin/vote/voteList";
	}
	
	@ResponseBody
	@RequestMapping(value="/votePopularDelete", method=RequestMethod.GET)
	public int votePopularDeleteGet(int idx, String realImg, String thumbImg) {
		int res = voteService.votePopularDelete(idx, realImg, thumbImg);
		return res;
	}
  
	@RequestMapping(value="/voteInforInsert", method=RequestMethod.GET)
	public String voteInforInsertGet() {
		return "vote/voteInforInsert";
	}
	
	@RequestMapping(value="/voteInforInsert", method=RequestMethod.POST)
	public String voteInforInsertPost(VotePersonInforVO personVo, HttpSession session) {
		int votePersonInforIdx = voteService.getMaxVotePersonInfor()==0 ? 1 : voteService.getMaxVotePersonInfor() + 1;
		session.setAttribute("sVotePersonInforIdx", votePersonInforIdx);
		personVo.setIdx(votePersonInforIdx);
		voteService.aVoteInforInsert(personVo);
		return "redirect:/vote/voteList";
	}
	
	@RequestMapping(value="/voteList", method=RequestMethod.GET)
	public String voteListGet(HttpServletRequest request, Model model,
			@RequestParam(name="pag", defaultValue="1", required=false) int pag,
			@RequestParam(name="pageSize", defaultValue="5", required=false) int pageSize) {
		
		PaginationVO pageVO = pagination.pagination(pag, pageSize, "vote", "", "");
		
		List<VotePopularVO> vos = voteService.aVotePopularList(pageVO.getStartIndexNo(), pageSize);
		
		model.addAttribute("vos", vos);
		model.addAttribute("pageVO", pageVO);
		
		return "vote/voteList";
	}
	
	@RequestMapping(value="/votePopularView", method=RequestMethod.GET)
	@ResponseBody
	public VotePopularVO votePopularViewGet(int idx) {
		VotePopularVO vo = voteService.getVotePopularView(idx);
		return vo;
	}
	
	@RequestMapping(value="/voteSelectInsert", method=RequestMethod.GET)
	@ResponseBody
	public int voteSelectInsertGet( int votePersonInforIdx, int idx, int sel) {  // votePersonInforIdx:설문한사람의 고유번호(personInforIdx로변환), idx:설문지번호(popularIdx로변환), sel:선택한 설문지항목번호(voteNum로변환)
		voteService.voteSelectInsert(votePersonInforIdx, idx, sel);
		return 1;
	}
	
	@ResponseBody
	@RequestMapping(value="/voteResultView", method=RequestMethod.GET)
	public HashMap<String, Object> voteResultViewGet(int idx) {  // votePersonInforIdx:설문한사람의 고유번호(personInforIdx로변환), idx:설문지번호(popularIdx로변환), sel:선택한 설문지항목번호(voteNum로변환)
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<VoteVO> vos = voteService.voteResultView(idx);  // vos로 값을 넘길때는 HashMap을 사용한다. vo는 그냥 자체로 넘길수 있다.
		
		result.put("vos", vos);
		
		VotePopularVO pVo = voteService.getVotePopularView(vos.get(0).getPopularIdx());
		result.put("pVo", pVo);
		
		return result;
	}
	
	@RequestMapping(value="/chartAnalysis", method=RequestMethod.GET)
	public String chartAnalysisGet(HttpServletRequest request, Model model) {
		int idx = Integer.parseInt(request.getParameter("idx"));
		String chartFlag = request.getParameter("chartFlag");
    List<VoteVO> vos = voteService.voteResultView(idx);  // 투표정보
    VotePopularVO pVo = voteService.getVotePopularView(vos.get(0).getPopularIdx());  // 투표주제
    int voteTotCnt = voteService.getVoteTotCnt(idx);  // 총 투표 인원수
    
    model.addAttribute("vos", vos);
    model.addAttribute("voteTotCnt", voteTotCnt);
    model.addAttribute("pVo", pVo);
    model.addAttribute("chartFlag", chartFlag);
    
    if(chartFlag.equals("pie")) {
		  return "vote/analysisPieChart";
    }
    else {
    	return "vote/analysisBarChart";
    }
	}
	
	@ResponseBody
	@RequestMapping(value="/voteProgress", method=RequestMethod.GET)
	public String voteProgressGet(int idx, String progress) {
		voteService.setProgress(idx, progress);
		return "";
	}
}
