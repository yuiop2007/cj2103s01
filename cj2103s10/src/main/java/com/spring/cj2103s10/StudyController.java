package com.spring.cj2103s10;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.cj2103s10.service.Study;
import com.spring.cj2103s10.vo.AreaVO;

@Controller
@RequestMapping("/study")
public class StudyController {
	
	@Autowired
	Study study;
	
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
	
	
	
}
