package com.spring.cj2103s01;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.spring.cj2103s01.vo.OrderVO;
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
		model.addAttribute("pvos", pvos);
		model.addAttribute("cvos", cvos);
		model.addAttribute("csize", cvos.size());
		model.addAttribute("cpvos", cpvos);
		model.addAttribute("cpCnt", cpCnt);
		model.addAttribute("mvo", mvo);
		model.addAttribute("delItems", delItems);
		return "order/orderInfo";
	}
	
	@RequestMapping(value = "/orderInfo", method = RequestMethod.POST)
	public String orderInfoPost(OrderVO vo, HttpServletRequest request, String delItems) {
		
		String add1 = request.getParameter("add1") == null ? "" : request.getParameter("add1");
		String add2 = request.getParameter("add2") == null ? "" : request.getParameter("add2");
		String add3 = request.getParameter("add3") == null ? "" : request.getParameter("add3");
		String mAddress = "";
		mAddress = add1 + " " + add2 + " " + add3;
		vo.setoAddress(mAddress);
		
		String tel1 = request.getParameter("tel1") == null ? "" : request.getParameter("tel1");
		String tel2 = request.getParameter("tel2") == null ? "" : request.getParameter("tel2");
		String tel3 = request.getParameter("tel3") == null ? "" : request.getParameter("tel3");
		String mPhone = tel1 + "-" + tel2 + "-" + tel3;
		vo.setoPhone(mPhone);
		
		String message = request.getParameter("oMessage") == null ? "" : request.getParameter("oMessage");
		vo.setoMessage(message);
		
		String mile = request.getParameter("mile") == null ? "0" : request.getParameter("mile");
		
		String[] idxs = delItems.split("/");
		System.out.println(delItems);
		CartVO cartvo = new CartVO();
		
		if(vo.getoDelivery().equals("0")) {
			vo.setoDelivery("무료");
		}
		else {
			vo.setoDelivery("일반");
		}
		
		//무통장일때
		if(vo.getoPayment()==1) {
			vo.setoStatus("입금전");
		}
		//카드일때(입금된거)
		else if(vo.getoPayment()==2) {
			vo.setoStatus("배송준비중");
		}
		
//		//orderVO에 추가
//		orderService.setInputOrder(vo);
//		
//		//회원 구매횟수 올리기(카드결제면올리고)
//		if(vo.getoStatus().equals("배송준비중")) {
//			memberService.updateMemberBuy(vo.getmId());
//		}
//		//포인트 사용시 깎고 사용포인트에 추가
//		if(Integer.parseInt(mile)>0) {
//			memberService.setUpdatePoint(vo.getmId(), Integer.parseInt(mile));
//		}
		//카트에 있던거 오더디테일로 옮기고 카트에서 삭제
		int lastoId = orderService.getLastoId(vo.getmId());
		for (String idx : idxs) {
			cartvo = orderService.getIdxVo(Integer.parseInt(idx));
			orderService.setOrderDetail(cartvo, lastoId);
			orderService.cartDelete(Integer.parseInt(idx));
		}
		
		
		return "redirect:/main";
	}
}
