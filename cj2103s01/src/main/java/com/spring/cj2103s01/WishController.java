package com.spring.cj2103s01;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.cj2103s01.pagenation.Pagenation;
import com.spring.cj2103s01.pagenation.PagenationVO;
import com.spring.cj2103s01.service.ProductService;
import com.spring.cj2103s01.service.WishService;
import com.spring.cj2103s01.vo.ProductVO;
import com.spring.cj2103s01.vo.WishVO;

@Controller
@RequestMapping("/wish")
public class WishController {
	String msgFlag = "";
	
	@Autowired
	WishService wishService;
	
	@Autowired
	Pagenation pagenation;
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/addWish", method = RequestMethod.GET)
	public String addWishGet(int pId, HttpSession session) {
		String mId = (String) session.getAttribute("smid");
		
		List<WishVO> vos = wishService.getWishList(mId);
		int res = wishService.addWish(vos, pId, mId);
		
		if(res==1) {
			msgFlag = "wishAddOk$pId=" + pId;
			return "redirect:/msg/" + msgFlag;
		}
		else {
			msgFlag = "wishAddNo";
			return "redirect:/msg/" + msgFlag;
		}
	}
	
	@RequestMapping(value = "/myWish", method = RequestMethod.GET)
	public String myWishGet(@RequestParam(name = "pag", defaultValue = "1", required = false) int pag,
			@RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize, Model model, HttpSession session) {
		if (pag < 1) {
			pag = 1;
		}
		
		String mId = (String) session.getAttribute("smid");

		PagenationVO pageVO = pagenation.pagenation(pag, pageSize, "mywish", mId , "");
		
		List<WishVO> vos = wishService.getWishList(mId);
		List<ProductVO> pvos = productService.getProductList(pageVO.getStartIndexNo(), pageSize);
		
		model.addAttribute("vos", vos);
		model.addAttribute("pvos", pvos);
		model.addAttribute("pageVO", pageVO);
		
		return "wishlist/myWish";
	}
	
	@RequestMapping(value = "/wishDelete", method = RequestMethod.GET)
	public String wishDeleteGet(int wId, HttpSession session) {
		String mId = (String)session.getAttribute("smid");
		
		if(mId.equals("")) {
			return "redirect:/member/login";
		}
		else {
			wishService.wishDelete(wId);
			msgFlag = "wishDeleteOk";
			return "redirect:/msg/" + msgFlag;
		}
	}
}
