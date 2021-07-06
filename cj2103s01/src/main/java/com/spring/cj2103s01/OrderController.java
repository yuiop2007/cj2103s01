package com.spring.cj2103s01;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.cj2103s01.pagenation.Pagenation;
import com.spring.cj2103s01.service.CouponService;
import com.spring.cj2103s01.service.MemberService;
import com.spring.cj2103s01.service.OrderService;
import com.spring.cj2103s01.service.ProductService;
import com.spring.cj2103s01.vo.CartVO;
import com.spring.cj2103s01.vo.CouponVO;
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
	
	@Autowired
	CouponService couponService;
	
	@ResponseBody
	@RequestMapping(value = "/cartOk", method = RequestMethod.GET)
	public String cartOkGet(int pId, String[] orderName, String[] orderNum, String[] orderPrice, HttpSession session, Model model) {
		int sw = 0;
		String mId = (String) session.getAttribute("smid");
		
		List<CartVO> vos = orderService.getCartList(mId);
		
		for(int i=0; i<orderName.length; i++) {
			if(!vos.isEmpty()) {
				for(CartVO vo : vos) {
					if(vo.getpId() == pId && vo.getpOption().equals(orderName[i])) {
						orderService.setCartCntUpdate(mId, pId, orderName[i], Integer.parseInt(orderNum[i]), Integer.parseInt(orderPrice[i]));
						sw = 1;
					}
				}
			}
			if(sw==0) {
				orderService.setCart(mId, pId, orderName[i], Integer.parseInt(orderNum[i]), Integer.parseInt(orderPrice[i]));
			}
			sw=0;
		}
		int cart = orderService.getCartCnt(mId);
		
		session.setAttribute("cart", cart);
		return "";
	}
	
	@RequestMapping(value = "/cartList", method = RequestMethod.GET)
	public String cartListGet(HttpSession session, Model model) {
		String mId = (String) session.getAttribute("smid");
		
		List<CartVO> vos = orderService.getCartList(mId);
		List<ProductVO> pvos = productService.getProductAllList();

		model.addAttribute("pvos", pvos);
		model.addAttribute("vos", vos);
		
		return "order/cartList";
	}
	
	@ResponseBody
	@RequestMapping(value = "/cartDelete", method = RequestMethod.POST)
	public String cartDeletePost(String delItems, HttpSession session) {
		String[] idxs = delItems.split("/");
		int cart = (int) session.getAttribute("cart");
		for (String idx : idxs) {
			orderService.cartDelete(Integer.parseInt(idx));
			cart = cart - 1;
		}
		
		session.setAttribute("cart", cart);
		return "";
	}
	
	@RequestMapping(value = "/orderInfo", method = RequestMethod.GET)
	public String orderInfoGet(String delItems, HttpSession session, Model model) {
		String mId = (String) session.getAttribute("smid");
		String[] idxs = delItems.split("/");
		int cart = (int) session.getAttribute("cart");
		
		
		int cpCnt = couponService.getCouponCnt(mId);
		List<CouponVO> cpvos = couponService.getCouponListMid(mId);
		MemberVO mvo = memberService.getIdCheck(mId);
		CartVO cartvo = new CartVO();
		List<CartVO> cvos = new ArrayList<CartVO>();
		List<ProductVO> pvos = productService.getProductAllList();
		
		for (String idx : idxs) {
			cartvo = orderService.getIdxVo(Integer.parseInt(idx));
			cvos.add(cartvo);
		}
		
		String add[] = mvo.getmAddress().split("[$]");
		String phone[] = mvo.getmPhone().split("-");
		String birth[] = mvo.getmBirth().split("-");
		
		try {
			for(int i=0; i<=add.length; i++) {
				model.addAttribute("add"+(i+1), add[i]);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			for(int i=0; i<=birth.length; i++) {
				model.addAttribute("birth"+(i+1), birth[i]);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		model.addAttribute("phone1", phone[0]);
		model.addAttribute("phone2", phone[1]);
		model.addAttribute("phone3", phone[2]);
		session.setAttribute("cart", cart);
		model.addAttribute("pvos", pvos);
		model.addAttribute("cvos", cvos);
		model.addAttribute("csize", cvos.size());
		model.addAttribute("cpvos", cpvos);
		model.addAttribute("cpCnt", cpCnt);
		model.addAttribute("mvo", mvo);
		
		return "order/orderInfo";
	}
}
