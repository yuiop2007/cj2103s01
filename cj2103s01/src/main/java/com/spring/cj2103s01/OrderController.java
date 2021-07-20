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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.cj2103s01.pagenation.Pagenation;
import com.spring.cj2103s01.service.CouponService;
import com.spring.cj2103s01.service.MemberService;
import com.spring.cj2103s01.service.OrderService;
import com.spring.cj2103s01.service.ProductService;
import com.spring.cj2103s01.vo.CartVO;
import com.spring.cj2103s01.vo.CouponVO;
import com.spring.cj2103s01.vo.MemberVO;
import com.spring.cj2103s01.vo.OrderDetailVO;
import com.spring.cj2103s01.vo.OrderVO;
import com.spring.cj2103s01.vo.ProductVO;

@Controller
@RequestMapping("/order")
public class OrderController {
	String msgFlag = "";
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
	public String cartOkGet(int check, int pId, String[] orderName, String[] orderNum, String[] orderPrice, HttpSession session, Model model) {
		int sw = 0;
		String mId = (String) session.getAttribute("smid");
		String delItems = "";
		List<CartVO> vos = orderService.getCartList(mId);
		int cart = 0;
		
		//장바구니 등록
		if(check==2) {
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
			cart = orderService.getCartCnt(mId);
			session.setAttribute("cart", cart);
		}
		//바로구매
		else if(check==1) {
			for(int i=0; i<orderName.length; i++) {
				if(!vos.isEmpty()) {
					for(CartVO vo : vos) {
						if(vo.getpId() == pId && vo.getpOption().equals(orderName[i])) {
							orderService.setCartCntUpdate(mId, pId, orderName[i], Integer.parseInt(orderNum[i]), Integer.parseInt(orderPrice[i]));
							sw = 1;
							int idx = orderService.getCartIdx(mId, pId, orderName[i]);
							delItems += Integer.toString(idx) + "/";
						}
					}
				}
				if(sw==0) {
					orderService.setCart(mId, pId, orderName[i], Integer.parseInt(orderNum[i]), Integer.parseInt(orderPrice[i]));
					int idx = orderService.getLastCartIdx(mId);
					delItems += Integer.toString(idx) + "/";
				}
				sw=0;
			}
			cart = orderService.getCartCnt(mId);
			session.setAttribute("cart", cart);
			return delItems;
		}
		
		
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
			for(ProductVO vo : pvos) {
				if(vo.getpId()==cartvo.getpId()) {
					if(cartvo.getpCnt()>vo.getpStock()) {
						msgFlag = "StockNo";
						return "redirect:/msg/" + msgFlag;
					}
				}
			}
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
	public String orderInfoPost(OrderVO vo, HttpServletRequest request, String delItems, HttpSession session) {
		
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

		
		String mile = request.getParameter("mile");
		if(mile==null || mile.trim().equals("")) {
			mile = "0";
		}
		vo.setoUsePoint(Integer.parseInt(mile));
		
		String[] idxs = delItems.split("/");
		CartVO cartvo = new CartVO();
		int cart = orderService.getCartCnt(vo.getmId());
		
		if(vo.getoDelivery().equals("0")) {
			vo.setoDelivery("무료");
		}
		else {
			vo.setoDelivery("일반");
		}
		
		//무통장일때
		if(vo.getoPayment().equals("무통장")) {
			vo.setoStatus("입금전");
		}
		//카드일때(입금된거)
		else if(vo.getoPayment().equals("카드")) {
			vo.setoStatus("배송준비중");
		}
		
		//orderVO에 추가
		orderService.setInputOrder(vo);
		
		//회원 구매횟수 올리기(카드결제면올리고) + 적립시키기
		if(vo.getoStatus().equals("배송준비중")) {
			memberService.updateMemberBuy(vo.getmId());
			memberService.updateMemberPoint(vo.getmId(), vo.getoSetPoint());
		}
		//포인트 사용시 깎고 사용포인트에 추가
		if(Integer.parseInt(mile)>0) {
			memberService.setUpdatePoint(vo.getmId(), Integer.parseInt(mile));
		}
		//카트에 있던거 오더디테일로 옮기고 카트에서 삭제 + 상품 재고 줄이기
		int lastoId = orderService.getLastoId(vo.getmId());
		for (String idx : idxs) {
			cartvo = orderService.getIdxVo(Integer.parseInt(idx));
			productService.setStockUpdate(cartvo.getpId(), cartvo.getpCnt());
			int sale = productService.getSalePrice(cartvo.getpId());
			orderService.setOrderDetail(cartvo, lastoId, sale);
			orderService.cartDelete(Integer.parseInt(idx));
			cart = cart - 1;
		}
		
		session.setAttribute("cart", cart);
		msgFlag = "orderOk";
		return "redirect:/msg/" + msgFlag;
	}
	
	@RequestMapping(value = "/oList", method = RequestMethod.GET)
	public String oListGet(HttpSession session, Model model,
			@RequestParam(name = "time", defaultValue = "30", required = false) int time,
			@RequestParam(name = "status", defaultValue = "All", required = false) String status,
			@RequestParam(name = "change", defaultValue = "All", required = false) String change) {
		String mId = (String) session.getAttribute("smid");
		List<OrderVO> ovos = null;
		
		if(status.equals("notPay")) {
			model.addAttribute("status", status);
			status = "입금전";
			ovos = orderService.getOrderListStatus(mId, time, status);
		}
		else if(status.equals("readyDel")) {
			model.addAttribute("status", status);
			status = "배송준비중";
			ovos = orderService.getOrderListStatus(mId, time, status);
		}
		else if(status.equals("ingDel")) {
			model.addAttribute("status", status);
			status = "배송중";
			ovos = orderService.getOrderListStatus(mId, time, status);
		}
		else if(status.equals("endDel")) {
			model.addAttribute("status", status);
			status = "배송완료";
			ovos = orderService.getOrderListStatus(mId, time, status);
		}
		else {
			if(change.equals("cancel")) {
				model.addAttribute("change", change);
				change = "취소";
				ovos = orderService.getOrderListChange(mId, time, change);
			}
			else if(change.equals("change")) {
				model.addAttribute("change", change);
				change = "교환";
				ovos = orderService.getOrderListChange(mId, time, change);
			}
			else if(change.equals("return")) {
				model.addAttribute("change", change);
				change = "반품";
				ovos = orderService.getOrderListChange(mId, time, change);
			}
			else {
				ovos = orderService.getOrderListMid(mId, time);
				model.addAttribute("status", status);
				model.addAttribute("change", change);
			}
		}
		List<OrderDetailVO> odvos = orderService.getOrderDetailListMid(mId);
		List<ProductVO> pvos = productService.getProductAllList();
		
		
		model.addAttribute("time", time);
		model.addAttribute("ovos", ovos);
		model.addAttribute("odvos", odvos);
		model.addAttribute("pvos", pvos);

		return "order/oList";
	}
	
	@RequestMapping(value = "/oContent", method = RequestMethod.GET)
	public String oContentGet(int oId, HttpSession session, Model model) {
		String mId = (String) session.getAttribute("smid");
		
		OrderVO vo = orderService.getOrderContent(mId, oId);
		List<OrderDetailVO> odvos = orderService.getOrderDetailListOid(mId, oId);
		List<ProductVO> pvos = productService.getProductAllList();
		
		model.addAttribute("vo", vo);
		model.addAttribute("pvos", pvos);
		model.addAttribute("odvos", odvos);
		
		return "order/oContent";
	}
	
	@ResponseBody
	@RequestMapping(value = "/buyOk", method = RequestMethod.GET)
	public String buyOkGet(int oId) {
		orderService.setBuyUpdate(oId);
		
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value = "/returnOk", method = RequestMethod.GET)
	public String returnOkGet(int oId) {
		orderService.setReturnUpdate(oId);
		
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value = "/changeOk", method = RequestMethod.GET)
	public String changeOkGet(int oId) {
		orderService.setChangeUpdate(oId);
		
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value = "/cancelOk", method = RequestMethod.GET)
	public String cancelOkGet(int oId) {
		orderService.setCancelUpdate(oId);
		
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value = "/returnEnd", method = RequestMethod.GET)
	public String returnEndGet(String mId, int oId) {
		orderService.setReturnEndUpdate(oId);
		
		List<OrderDetailVO> vos = orderService.getOrderDetailListOid(mId, oId);
		for(OrderDetailVO vo : vos) {
			productService.setCancelStockUpdate(vo.getpId(), vo.getOdCnt());
		}
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value = "/changeEnd", method = RequestMethod.GET)
	public String changeEndGet(int oId) {
		orderService.setChangeEndUpdate(oId);
		
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value = "/cancelEnd", method = RequestMethod.GET)
	public String cancelEndGet(String mId, int oId) {
		orderService.setCancelEndUpdate(oId);
		OrderVO ovo = orderService.getOrderInfo(oId);
		memberService.upMemberPoint(mId, ovo.getoUsePoint());//구매시 사용한 포인트 재적립 + 사용포인트 다시 줄이기
		
		// 취소완료시 오더의 재고처리, 적립금삭감, 구매횟수 삭감
		// vos에 oId에 대한 모든 orderDetail 불러오기
		// 그 pId마다 재고다시 채우고 판매횟수에서 삭감
		List<OrderDetailVO> vos = orderService.getOrderDetailListOid(mId, oId);
		for(OrderDetailVO vo : vos) {
			productService.setCancelStockUpdate(vo.getpId(), vo.getOdCnt());
		}
		if(!ovo.getoStatus().equals("입금전")) {
			memberService.downPointBuy(mId, ovo.getoSetPoint()); // 구매횟수 + 적립금삭감
		}
		
		orderService.updateCancelStatus(oId); //배송완료처리
		return "";
	}
}
