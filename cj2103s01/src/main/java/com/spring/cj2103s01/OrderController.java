package com.spring.cj2103s01;



import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.cj2103s01.pagenation.Pagenation;
import com.spring.cj2103s01.pagenation.PagenationVO;
import com.spring.cj2103s01.service.MemberService;
import com.spring.cj2103s01.service.OrderService;
import com.spring.cj2103s01.service.ProductService;
import com.spring.cj2103s01.vo.CartVO;
import com.spring.cj2103s01.vo.MemberVO;
import com.spring.cj2103s01.vo.ProductVO;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	Pagenation pagenation;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	ProductService productService;
	
	@ResponseBody
	@RequestMapping(value = "/cartOk", method = RequestMethod.GET)
	public String cartOkGet(int pId, String[] orderName, String[] orderNum, String[] orderPrice, HttpSession session, Model model) {
		
		String mId = (String) session.getAttribute("smid");
		
		List<CartVO> vos = orderService.getCartList(mId);
		
		for(int i=0; i<orderName.length; i++) {
			for(CartVO vo : vos) {
				if(vo.getpId() == pId && vo.getpOption().equals(orderName[i])) {
					orderService.setCartCntUpdate(mId, pId, orderName[i]);
				}
				else {
					orderService.setCart(mId, pId, orderName[i], Integer.parseInt(orderNum[i]), Integer.parseInt(orderPrice[i]));
				}
			}
		}
		return "";
	}
	
	@RequestMapping(value = "/cartList", method = RequestMethod.GET)
	public String orderGet(HttpSession session, @RequestParam(name = "pag", defaultValue = "1", required = false) int pag,
			@RequestParam(name = "pageSize", defaultValue = "5", required = false) int pageSize, Model model) {
		String mId = (String) session.getAttribute("smid");
		
		PagenationVO pageVO = pagenation.pagenation(pag, pageSize, "cart", mId, "");
		List<CartVO> vos = orderService.getCartList(mId);
		List<ProductVO> pvos = productService.getProductAllList();

		model.addAttribute("pvos", pvos);
		model.addAttribute("vos", vos);
		model.addAttribute("pageVO", pageVO);
		
		return "order/cartList";
	}
}
