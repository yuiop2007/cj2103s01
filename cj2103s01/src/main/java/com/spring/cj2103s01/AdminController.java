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
import com.spring.cj2103s01.service.CouponService;
import com.spring.cj2103s01.service.MemberService;
import com.spring.cj2103s01.service.OrderService;
import com.spring.cj2103s01.service.ProductService;
import com.spring.cj2103s01.vo.CouponVO;
import com.spring.cj2103s01.vo.MemberVO;
import com.spring.cj2103s01.vo.OrderDetailVO;
import com.spring.cj2103s01.vo.OrderVO;
import com.spring.cj2103s01.vo.ProductVO;

@Controller
@RequestMapping("/admin")
public class AdminController {
	String msgFlag ="";
	
	@Autowired
	MemberService memberService;

	@Autowired
	ProductService productService;

	@Autowired
	Pagenation pagenation;
	
	@Autowired
	CouponService couponService;
	
	@Autowired
	OrderService orderService;

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminGet(Model model) {


		model.addAttribute("totMember", memberService.totMemberCnt()); // 총회원수
		model.addAttribute("dropMember", memberService.dropMemberCnt()); // 탈퇴회원수
		model.addAttribute("totProduct", productService.totProductCnt()); // 총 상품수
		model.addAttribute("notPayCnt", orderService.notPayCnt()); // 입금전 주문 갯수
		model.addAttribute("delReadyCnt", orderService.readyPayCnt()); // 배송준비중 갯수
		model.addAttribute("delingCnt", orderService.ingPayCnt()); // 배송중 갯수
		model.addAttribute("delEndCnt", orderService.endPayCnt()); // 배송완료 주문 갯수
		model.addAttribute("cancelCnt", orderService.cancelCnt()); // 취소 갯수
		model.addAttribute("changeCnt", orderService.changeCnt()); // 교환 갯수
		model.addAttribute("returnCnt", orderService.returnCnt()); // 반품 갯수
		model.addAttribute("totSell", memberService.totSellCnt()); // 총 판매 갯수

		return "admin/admin";
	}

	@RequestMapping(value = "/mList", method = RequestMethod.GET)
	public String mListGet(@RequestParam(name = "pag", defaultValue = "1", required = false) int pag,
			@RequestParam(name = "pageSize", defaultValue = "20", required = false) int pageSize, Model model) {
		if (pag < 1) {
			pag = 1;
		}

		PagenationVO pageVO = pagenation.pagenation(pag, pageSize, "mList", "", "");

		List<MemberVO> vos = memberService.getMemberList();

		model.addAttribute("vos", vos);
		model.addAttribute("pageVO", pageVO);

		return "admin/mList";
	}

	@RequestMapping(value = "/mListLevel", method = RequestMethod.GET)
	public String mListLevelGet(Model model, String mLevel,
			@RequestParam(name = "pag", defaultValue = "1", required = false) int pag,
			@RequestParam(name = "pageSize", defaultValue = "20", required = false) int pageSize) {

		PagenationVO pageVO = pagenation.pagenation(pag, pageSize, "mList", "", mLevel);

		List<MemberVO> vos;
		if (!mLevel.equals("")) {
			vos = memberService.getMemberListLevel(pageVO.getStartIndexNo(), pageSize, mLevel);
		} else {
			vos = memberService.getMemberList();
		}

		model.addAttribute("vos", vos);
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("stringLevel", mLevel);

		return "admin/mList";
	}

	@RequestMapping(value = "/mSearch", method = RequestMethod.GET)
	public String mSearchGet(Model model, String mId,
			@RequestParam(name = "pag", defaultValue = "1", required = false) int pag,
			@RequestParam(name = "pageSize", defaultValue = "20", required = false) int pageSize) {

		PagenationVO pageVO = pagenation.pagenation(pag, pageSize, "mList", mId, "");

		List<MemberVO> vos = memberService.getMemberListMid(pageVO.getStartIndexNo(), pageSize, mId);

		model.addAttribute("vos", vos);
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("mId", mId);

		return "admin/mList";
	}

	@ResponseBody
	@RequestMapping(value = "/mLevelCheck", method = RequestMethod.GET)
	public String mLevelCheckGet(String mId, String mLevel) {
		memberService.mLevelCheck(mId, mLevel);
		return "";
	}

	// 선택한 회원들 삭제처리하기
	@RequestMapping(value = "/mList", method = RequestMethod.POST)
	public String mListPost(String delItems) {
		String[] idxs = delItems.split("/");
		for (String idx : idxs) {
			memberService.memberDelete(idx);
		}

		return "redirect:/msg/adminMemberDeleteOk";
	}
	
	@ResponseBody
	@RequestMapping(value = "/mPointCheck", method = RequestMethod.POST)
	public String mPointCheckPost(String delItems, String point) {
		String[] idxs = delItems.split("/");
		for (String idx : idxs) {
			memberService.mPointInput(idx, Integer.parseInt(point));
		}
		
		return "";
	}
	
	@RequestMapping(value="/mInfor", method=RequestMethod.GET)
	public String mInforGet(Model model, String mId) {
		MemberVO vo = memberService.getIdCheck(mId);

		model.addAttribute("vo", vo);
		return "admin/mInfor";
	}
	
	@RequestMapping(value = "/coupon", method = RequestMethod.GET)
	public String couponGet(@RequestParam(name = "pag", defaultValue = "1", required = false) int pag,
			@RequestParam(name = "pageSize", defaultValue = "20", required = false) int pageSize, Model model, HttpSession session) {
		if (pag < 1) {
			pag = 1;
		}
		
		PagenationVO pageVO = pagenation.pagenation(pag, pageSize, "coupon", "" , "");
		
		List<CouponVO> vos = couponService.getCouponList();
		List<CouponVO> avos = couponService.getaCouponList();
		
		model.addAttribute("vos", vos);
		model.addAttribute("avos", avos);
		model.addAttribute("pageVO", pageVO);
		
		return "admin/coupon";
	}
	
	@RequestMapping(value = "/cInput", method = RequestMethod.GET)
	public String cInputGet() {
		return "admin/cInput";
	}
	
	@RequestMapping(value = "/cInput", method = RequestMethod.POST)
	public String cInputPost(CouponVO vo) {
		int res = 0;
		List<MemberVO> vos = null;
		if(vo.getCmCate()<5) {
			vos = memberService.getCouponMember(vo.getCmCate());
		}
		else {
			vos = memberService.getMemberList();
		}
		
		for(MemberVO mvo : vos) {
			vo.setmId(mvo.getmId());
			res = couponService.setCouponInput(vo);
			if(res == 0) {
				msgFlag = "cInputNo";
			}
		}
		
		if (res == 1)
			msgFlag = "cInputOk";
		else
			msgFlag = "cInputNo";

		return "redirect:/msg/" + msgFlag;
	}
	
	@RequestMapping(value = "/cDelete", method = RequestMethod.GET)
	public String cDeleteGet(int cId, HttpSession session) {
		String smid = (String) session.getAttribute("smid");
		int slevel = (int) session.getAttribute("slevel");
		if(smid.equals("yuiop2007")&&slevel==0) {
			couponService.cDeleteOk(cId);
			msgFlag = "cDeleteOk";
		}
		else {
			msgFlag = "cDeleteNo";
		}
		
		return "redirect:/msg/" + msgFlag;
	}

	@RequestMapping(value = "/oaList", method = RequestMethod.GET)
	public String oaListGet(HttpSession session, Model model,
			@RequestParam(name = "status", defaultValue = "All", required = false) String status,
			@RequestParam(name = "change", defaultValue = "All", required = false) String change,
			@RequestParam(name = "pag", defaultValue = "1", required = false) int pag,
			@RequestParam(name = "pageSize", defaultValue = "30", required = false) int pageSize) {
		if (pag < 1) {
			pag = 1;
		}
		
		List<OrderVO> ovos = null;
		PagenationVO pageVO = pagenation.pagenation(pag, pageSize, "oaList", "", "");
		
		if(change.equals("All") && !status.equals("All")) {
			pageVO = pagenation.pagenation(pag, pageSize, "oaList", "", status);
			if(status.equals("입금전")) {
				model.addAttribute("status", status);
				model.addAttribute("change", change);
				ovos = orderService.getAdminOlistStatus(status, pageVO.getStartIndexNo(), pageSize);
			}
			else if(status.equals("배송준비중")) {
				model.addAttribute("status", status);
				model.addAttribute("change", change);
				ovos = orderService.getAdminOlistStatus(status, pageVO.getStartIndexNo(), pageSize);
			}
			else if(status.equals("배송중")) {
				model.addAttribute("status", status);
				model.addAttribute("change", change);
				ovos = orderService.getAdminOlistStatus(status, pageVO.getStartIndexNo(), pageSize);
			}
			else if(status.equals("배송완료")) {
				model.addAttribute("status", status);
				model.addAttribute("change", change);
				ovos = orderService.getAdminOlistStatus(status, pageVO.getStartIndexNo(), pageSize);
			}
			else {
				ovos = orderService.getOrderAllList(pageVO.getStartIndexNo(), pageSize);
				model.addAttribute("status", status);
				model.addAttribute("change", change);
			}
		}
		else if(!change.equals("All") && status.equals("All")) {
			pageVO = pagenation.pagenation(pag, pageSize, "oaList", change, "");
			if(change.equals("취소")) {
				model.addAttribute("status", status);
				model.addAttribute("change", change);
				ovos = orderService.getAdminOlistChange(change, pageVO.getStartIndexNo(), pageSize);
			}
			else if(change.equals("교환")) {
				model.addAttribute("status", status);
				model.addAttribute("change", change);
				ovos = orderService.getAdminOlistChange(change, pageVO.getStartIndexNo(), pageSize);
			}
			else if(change.equals("반품")) {
				model.addAttribute("status", status);
				model.addAttribute("change", change);
				ovos = orderService.getAdminOlistChange(change, pageVO.getStartIndexNo(), pageSize);
			}
			else if(change.equals("구매확정")) {
				model.addAttribute("status", status);
				model.addAttribute("change", change);
				ovos = orderService.getAdminOlistChange(change, pageVO.getStartIndexNo(), pageSize);
			}
			else {
				ovos = orderService.getOrderAllList(pageVO.getStartIndexNo(), pageSize);
				model.addAttribute("status", status);
				model.addAttribute("change", change);
			}
		}
		else if(!change.equals("All") && !status.equals("All")) {
			pageVO = pagenation.pagenation(pag, pageSize, "oaList", change, status);
			ovos = orderService.getOrderSCList(status, change, pageVO.getStartIndexNo(), pageSize);
			model.addAttribute("status", status);
			model.addAttribute("change", change);
		}
		else {
			ovos = orderService.getOrderAllList(pageVO.getStartIndexNo(), pageSize);
			model.addAttribute("status", status);
			model.addAttribute("change", change);
		}
			
		
		List<OrderDetailVO> odvos = orderService.getOrderDetailList();
		List<ProductVO> pvos = productService.getProductAllList();
		
		model.addAttribute("ovos", ovos);
		model.addAttribute("odvos", odvos);
		model.addAttribute("pvos", pvos);
		model.addAttribute("pageVO", pageVO);
		
		return "admin/oaList";
	}
	
	@ResponseBody
	@RequestMapping(value = "/oStatusCheck", method = RequestMethod.GET)
	public String oStatusCheckGet(String status, String delItems) {
		String[] oId = delItems.split("/");
		String[] oStatus = status.split("/");
		if(oId.length == oStatus.length) {
			for (int i=0; i<oId.length; i++) {
				orderService.statusUpdate(oId[i], oStatus[i]);
			}
		}else {
			return "false";
		}
		
		return "";
	}
	
	@RequestMapping(value = "/oSearch", method = RequestMethod.GET)
	public String oSearchGet(Model model, String mId,
			@RequestParam(name = "pag", defaultValue = "1", required = false) int pag,
			@RequestParam(name = "pageSize", defaultValue = "30", required = false) int pageSize) {
		
		PagenationVO pageVO = pagenation.pagenation(pag, pageSize, "oaList", "mId", mId);
		
		List<OrderVO> ovos = orderService.getMemberListMid(mId, pageVO.getStartIndexNo(), pageSize);
		List<OrderDetailVO> odvos = orderService.getOrderDetailListMid(mId);
		List<ProductVO> pvos = productService.getProductAllList();
		
		model.addAttribute("ovos", ovos);
		model.addAttribute("odvos", odvos);
		model.addAttribute("pvos", pvos);
		model.addAttribute("pageVO", pageVO);

		return "admin/oaList";
	}
	
	@RequestMapping(value = "/oInfo", method = RequestMethod.GET)
	public String oInfoGet(String mId, int oId, HttpSession session, Model model) {
		
		OrderVO vo = orderService.getOrderContent(mId, oId);
		List<OrderDetailVO> odvos = orderService.getOrderDetailListOid(mId, oId);
		List<ProductVO> pvos = productService.getProductAllList();
		
		model.addAttribute("vo", vo);
		model.addAttribute("pvos", pvos);
		model.addAttribute("odvos", odvos);
		
		return "admin/oInfo";
	}
}
