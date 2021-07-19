package com.spring.cj2103s10;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.cj2103s10.service.MemberService;
import com.spring.cj2103s10.service.Study;
import com.spring.cj2103s10.vo.AreaVO;
import com.spring.cj2103s10.vo.CartTestVO;
import com.spring.cj2103s10.vo.MemberVO;
import com.spring.cj2103s10.vo.ThumbnailVO;

@Controller
@RequestMapping("/study")
public class StudyController {
	String msgFlag = "";
	
	@Autowired
	Study study;
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/woo")
	public String wooGet() {
		return "study/woo/woo";
	}
	
	@RequestMapping("/kakao")
	public String kakaoGet() {
		return "study/kakaomap/kakao";
	}
	
	@RequestMapping("/kakaoEx1")
	public String kakaoEx1Get() {
		return "study/kakaomap/kakaoEx1";
	}
	
	@RequestMapping("/kakaoEx2")
	public String kakaoEx2Get() {
		return "study/kakaomap/kakaoEx2";
	}
	
	@RequestMapping("/kakaoEx3")
	public String kakaoEx3Get(Model model, String address) {
		if(address == null) address = "사창사거리";
		model.addAttribute("address", address);
		return "study/kakaomap/kakaoEx3";
	}
	
	@RequestMapping(value="/kakaoEx4", method=RequestMethod.GET)
	public String kakaoEx4Get(Model model) {
		String[] address1s = study.getAddress1();
		
		double latitude = 36.63508797975421;
		double longitude = 127.45959376343134;
  	
		model.addAttribute("address1s", address1s);
		
		model.addAttribute("latitude", latitude);
		model.addAttribute("longitude", longitude);
		
		return "study/kakaomap/kakaoEx4";
	}
	
	@ResponseBody
	@RequestMapping(value="/kakaoEx4", method=RequestMethod.POST)
	public List<AreaVO> kakaoEx4Post(@RequestBody String address1) {
		List<AreaVO> vos = study.getAddress2(address1);
		
		return vos;
	}
	
	//@ResponseBody
	@RequestMapping(value="/kakaoEx4Search", method=RequestMethod.POST)
	public String kakaoEx4SearchPost(Model model, 
			@RequestParam(name="address1", defaultValue="충북", required=false) String address1,
			@RequestParam(name="address2", defaultValue="청주시", required=false) String address2) {
		
		AreaVO vo = study.getAddressSearch(address1, address2);
		
		model.addAttribute("address1", vo.getAddress1());
		model.addAttribute("address2", vo.getAddress2());
		model.addAttribute("latitude", vo.getLatitude());
		model.addAttribute("longitude", vo.getLongitude());
		
		return "study/kakaomap/kakaoEx4Search";
	}
	
	// 달력내역 가져오기
	@RequestMapping(value="/calendar", method=RequestMethod.GET)
	public String calendarGet() {
		study.getCalendar();
		return "study/calendar/calendar";
	}
	
	// 썸네일 이미지 처리
	@RequestMapping(value="/thumbnail", method=RequestMethod.GET)
	public String thumbnailGet() {
		return "study/thumbnail/thumbnail";
	}
	
	// 썸네일 이미지 파일 올리기 처리
	@RequestMapping(value="/thumbnail", method=RequestMethod.POST)
	public String thumbnailPost(MultipartFile file) throws Exception {
		ThumbnailVO vo = study.thumbnailCreate(file);
		
		if(vo.getRes() == 1) {
			//msgFlag = "thumbnailCreateOk$oFileName="+vo.getoFileName()+"&tFileName="+vo.gettFileName();
			msgFlag = "thumbnailCreateOk$oFileName="+URLEncoder.encode(vo.getoFileName(),"UTF-8")+"&tFileName="+URLEncoder.encode(vo.gettFileName(),"UTF-8");
		}
		else {
			msgFlag = "thumbnailCreateNo";
		}
		
		return "redirect:/msg/" + msgFlag;
	}
	
  //썸네일 이미지 보여주기
	@RequestMapping(value="/thumbnailView", method=RequestMethod.GET)
	public String thumbnailViewGet(String oFileName, String tFileName, Model model) {
		model.addAttribute("oFileName", oFileName);
		model.addAttribute("tFileName", tFileName);
		
		return "study/thumbnail/thumbnailView";
	}
	
	// '연습 장바구니' 물건 보여주기
	@RequestMapping(value="/cartTest/cartTestView", method=RequestMethod.GET)
	public String cartTestViewGet(Model model) {
		List<CartTestVO> vos = study.getCartTest();
		model.addAttribute("vos", vos);
		
		return "study/cartTest/cart";
	}
	
	// '연습 장바구니' 물건 보여주기
	@RequestMapping(value="/cartTest/order", method=RequestMethod.GET)
	public String cartTestOrderGet(String[] idxs, Model model, HttpSession session) {
		String mid = (String) session.getAttribute("smid");
		MemberVO memberVO = memberService.getIdCheck(mid);
		
		String idxsArr[] = new String[idxs.length];
		for(int i=0; i<idxsArr.length; i++) {
			idxsArr[i] = idxs[i].substring(3);
		}
		String idxsStr = String.join(",", idxsArr);
		
		List<CartTestVO> vos = study.getOrderList(idxsStr);
		
		// 주문번호 생성
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		UUID uid = UUID.randomUUID();
		String strUid = uid.toString().substring(0,8);
		String orderIdx = sdf.format(today).substring(2) + strUid;
		
		model.addAttribute("memberVO", memberVO);
		model.addAttribute("vos", vos);
		model.addAttribute("orderIdx", orderIdx);
		
		return "study/cartTest/order";
	}
}
