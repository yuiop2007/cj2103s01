package com.spring.cj2103s01;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.cj2103s01.pagenation.Pagenation;
import com.spring.cj2103s01.pagenation.PagenationVO;
import com.spring.cj2103s01.service.NoticeService;
import com.spring.cj2103s01.vo.NoticeVO;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	NoticeService noticeService;
	
	@Autowired
	Pagenation pagenation;
	
	@RequestMapping(value = "/notice", method = RequestMethod.GET)
	public String noticeGet(@RequestParam(name="pag", defaultValue="1", required=false) int pag,
			@RequestParam(name="pageSize", defaultValue="10", required=false) int pageSize,
			Model model) {
		if(pag<1) {
			pag=1;
		}
		
		PagenationVO pageVo = pagenation.pagenation(pag, pageSize, "notice", "");
		
		List<NoticeVO> vos = noticeService.getNoticeList(pageVo.getStartIndexNo(), pageSize);
		
		model.addAttribute("vos", vos);
		model.addAttribute("pageVo", pageVo);
		
		return "board/notice";
	}
	
	@RequestMapping(value = "/qna", method = RequestMethod.GET)
	public String qnaGet() {
		
		return "board/qna";
	}
	
	@RequestMapping(value = "/event", method = RequestMethod.GET)
	public String eventGet() {
		
		return "board/event";
	}
}
