package com.spring.cj2103s01;

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
import org.springframework.web.multipart.MultipartFile;

import com.spring.cj2103s01.pagenation.Pagenation;
import com.spring.cj2103s01.pagenation.PagenationVO;
import com.spring.cj2103s01.service.CouponService;
import com.spring.cj2103s01.service.FileService;
import com.spring.cj2103s01.service.MemberService;
import com.spring.cj2103s01.service.OrderService;
import com.spring.cj2103s01.service.ProductService;
import com.spring.cj2103s01.service.SlideService;
import com.spring.cj2103s01.vo.CouponVO;
import com.spring.cj2103s01.vo.MemberVO;
import com.spring.cj2103s01.vo.OrderDetailVO;
import com.spring.cj2103s01.vo.OrderVO;
import com.spring.cj2103s01.vo.ProductVO;
import com.spring.cj2103s01.vo.SlideVO;

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
	
	@Autowired
	FileService fileService;
	
	@Autowired
	SlideService slideService;

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminGet(Model model) {

		//모든 주문내역 날짜 체크 후 배송완료+주문상태의 일주일 이후 주문은 구매확정 처리
		List<OrderVO> vos = orderService.getBuyEndList(); // 일주일 지난 배송완료 + 주문상태 리스트
		for(OrderVO vo : vos) {
			orderService.setBuyEnd(vo); // 구매확정 처리
		}
		
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
		model.addAttribute("totSellMoney", orderService.totSellMoney()); //총 판매가격 배송완료+구매확정제품

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

		List<MemberVO> vos;
		if (!mId.equals("")) {
			vos = memberService.getMemberListMid(pageVO.getStartIndexNo(), pageSize, mId);
		} else {
			vos = memberService.getMemberList();
		}
		
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
	public String cDeleteGet(String cName, HttpSession session) {
		int slevel = (int) session.getAttribute("slevel");
		if(slevel==0) {
			couponService.cDeleteOk(cName);
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
				OrderVO ovo = orderService.getOrderInfo(Integer.parseInt(oId[i]));
				if(ovo.getoStatus().equals("입금전") && !oStatus[i].equals("입금전")) {
					memberService.updateMemberBuy(ovo.getmId());
					memberService.mPointInput(ovo.getmId(), ovo.getoSetPoint());
				}
				else if(!ovo.getoStatus().equals("입금전") && oStatus[i].equals("입금전")) {
					memberService.downPointBuy(ovo.getmId(), ovo.getoSetPoint());
				}
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
	
	@RequestMapping(value = "/fileDel", method = RequestMethod.GET)
	public String fileDelGet(HttpServletRequest request) {
		
		String uploadPath = request.getRealPath("/resources/ckeditor/images/");
		int fileCnt = fileService.imgDelete(uploadPath);
		
		msgFlag = "imgDeleteOk$"+fileCnt;
		
		return "redirect:/msg/" + msgFlag;
	}
	
	@RequestMapping(value = "/slideShow", method = RequestMethod.GET)
	public String slideShowGet(Model model) {
		
		List<SlideVO> vos = slideService.getSlideList();
		int sCnt = slideService.getSlideTotCnt();
		
		model.addAttribute("vos", vos);
		model.addAttribute("sCnt", sCnt);
		
		return "admin/slideShow";
	}
	
	@RequestMapping(value = "/sInput", method = RequestMethod.GET)
	public String sInputGet(Model model) {
		
		int sCnt = slideService.getSlideTotCnt();
		model.addAttribute("sCnt", sCnt);
		
		return "admin/sInput";
	}
	
	@RequestMapping(value = "/sInput", method = RequestMethod.POST)
	public String sInputPost(MultipartFile file, SlideVO vo) {
		String root = "sInput";
		int res = slideService.setSlideInput(file, vo, root);
		
		if (res == 1)
			msgFlag = "sInputOk";
		else
			msgFlag = "sInputNo";

		return "redirect:/msg/" + msgFlag;
	}
	
	@RequestMapping(value = "/sDelete", method = RequestMethod.GET)
	public String sDeleteGet(int sId, HttpServletRequest request) {
		
		//기존파일 삭제
		String uploadPath = request.getSession().getServletContext().getRealPath("/resources/slideshow/");
		String sImage = slideService.getImageSid(sId);
		fileService.fileDeleteCheck(uploadPath + sImage);
		
		slideService.sDelete(sId);

		return "redirect:/admin/slideShow";
	}
	
	@ResponseBody
	@RequestMapping(value = "/sNumCheck", method = RequestMethod.GET)
	public String sNumCheckGet(String Num, String delItems) {
		String[] sId = delItems.split("/");
		String[] sNum = Num.split("/");
		if(sId.length == sNum.length) {
			for (int i=0; i<sId.length; i++) {
				slideService.sNumUpdate(Integer.parseInt(sId[i]), Integer.parseInt(sNum[i]));
			}
		}else {
			return "false";
		}
		
		return "";
	}
	
	@RequestMapping(value = "/charts", method = RequestMethod.GET)
	public String chartsGet(Model model) {
		
		model.addAttribute("MadeSell", productService.MadeSellCnt()); // made 총 판매수
		model.addAttribute("OuterSell", productService.OuterSellCnt()); // outer
		model.addAttribute("TopSell", productService.TopSellCnt()); // top
		model.addAttribute("BottomSell", productService.BottomSellCnt()); // bottm
		model.addAttribute("AccSell", productService.AccSellCnt()); // acc

		
		model.addAttribute("NowMonth", orderService.nowMonth()); // 현재 년도와 월
		model.addAttribute("Pre1Month", orderService.pre1Month()); // 현재 년도와 -1월
		model.addAttribute("Pre2Month", orderService.pre2Month()); // 현재 년도와 -2월
		model.addAttribute("Pre3Month", orderService.pre3Month()); // 현재 년도와 -3월
		model.addAttribute("Pre4Month", orderService.pre4Month()); // 현재 년도와 -4월
		
		// 월별 총 판매가격
		model.addAttribute("NowMonthPrice", orderService.nowMonthPrice());
		model.addAttribute("Pre1MonthPrice", orderService.pre1MonthPrice());
		model.addAttribute("Pre2MonthPrice", orderService.pre2MonthPrice());
		model.addAttribute("Pre3MonthPrice", orderService.pre3MonthPrice());
		model.addAttribute("Pre4MonthPrice", orderService.pre4MonthPrice());

		// 월별 MADE 총 판매가격
		model.addAttribute("NowMadePrice", orderService.nowCatePrice("MADE"));
		model.addAttribute("Pre1MadePrice", orderService.pre1CatePrice("MADE"));
		model.addAttribute("Pre2MadePrice", orderService.pre2CatePrice("MADE"));
		model.addAttribute("Pre3MadePrice", orderService.pre3CatePrice("MADE"));
		model.addAttribute("Pre4MadePrice", orderService.pre4CatePrice("MADE"));
		
		// 월별 OUTER 총 판매가격
		model.addAttribute("NowOuterPrice", orderService.nowCatePrice("OUTER"));
		model.addAttribute("Pre1OuterPrice", orderService.pre1CatePrice("OUTER"));
		model.addAttribute("Pre2OuterPrice", orderService.pre2CatePrice("OUTER"));
		model.addAttribute("Pre3OuterPrice", orderService.pre3CatePrice("OUTER"));
		model.addAttribute("Pre4OuterPrice", orderService.pre4CatePrice("OUTER"));
		
		// 월별 TOP 총 판매가격
		model.addAttribute("NowTopPrice", orderService.nowCatePrice("TOP"));
		model.addAttribute("Pre1TopPrice", orderService.pre1CatePrice("TOP"));
		model.addAttribute("Pre2TopPrice", orderService.pre2CatePrice("TOP"));
		model.addAttribute("Pre3TopPrice", orderService.pre3CatePrice("TOP"));
		model.addAttribute("Pre4TopPrice", orderService.pre4CatePrice("TOP"));
		
		// 월별 BOTTOM 총 판매가격
		model.addAttribute("NowBottomPrice", orderService.nowCatePrice("BOTTOM"));
		model.addAttribute("Pre1BottomPrice", orderService.pre1CatePrice("BOTTOM"));
		model.addAttribute("Pre2BottomPrice", orderService.pre2CatePrice("BOTTOM"));
		model.addAttribute("Pre3BottomPrice", orderService.pre3CatePrice("BOTTOM"));
		model.addAttribute("Pre4BottomPrice", orderService.pre4CatePrice("BOTTOM"));
		
		// 월별 ACC 총 판매가격
		model.addAttribute("NowAccPrice", orderService.nowCatePrice("ACC"));
		model.addAttribute("Pre1AccPrice", orderService.pre1CatePrice("ACC"));
		model.addAttribute("Pre2AccPrice", orderService.pre2CatePrice("ACC"));
		model.addAttribute("Pre3AccPrice", orderService.pre3CatePrice("ACC"));
		model.addAttribute("Pre4AccPrice", orderService.pre4CatePrice("ACC"));
		
		
		
		return "admin/charts";
	}
}
