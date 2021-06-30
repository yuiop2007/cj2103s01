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
import com.spring.cj2103s01.service.MemberService;
import com.spring.cj2103s01.service.ProductService;
import com.spring.cj2103s01.vo.MemberVO;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	Pagenation pagenation;
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminGet(Model model) {
		
		int totMember = memberService.totMemberCnt();
		int dropMember = memberService.dropMemberCnt();
		int totProduct = productService.totProductCnt();
		
		model.addAttribute("totMember", totMember);
		model.addAttribute("dropMember", dropMember);
		model.addAttribute("totProduct", totProduct);
		
		return "admin/admin";
	}
	
	@RequestMapping(value = "/mList", method = RequestMethod.GET)
	public String mListGet(@RequestParam(name = "pag", defaultValue = "1", required = false) int pag,
			@RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize, Model model) {
		if (pag < 1) {
			pag = 1;
		}

		PagenationVO pageVO = pagenation.pagenation(pag, pageSize, "mList", "", "");
		
		List<MemberVO> vos = memberService.getMemberList();
		
		model.addAttribute("vos", vos);
		model.addAttribute("pageVO", pageVO);
		
		return "admin/mList";
	}
	
}
