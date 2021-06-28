package com.spring.cj2103s10;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		
		//if(address2 == null) address = "";
		
		//double latitude = 36.63508797975421;
		//double longitude = 127.45959376343134;
		
		//model.addAttribute("address", address);
		//model.addAttribute("address1s", address1s);
		
		//model.addAttribute("latitude", latitude);
		//model.addAttribute("longitude", longitude);
		return vos;
	}
	
	/*
	@RequestMapping("/kakaoEx4")
	public String kakaoEx4Get(Model model, String address) {
		if(address == null) address = "충북";
		double latitude = 0.0;
		double longitude = 0.0;
		
		if(address.equals("서울")) {
			latitude = 37.56682420267543;
			longitude = 126.978652258823;
		}
		else if(address.equals("인천")) {
			latitude = 37.45602200632166;
			longitude = 126.70506586217154;
		}
		else if(address.equals("강원")) {
			latitude = 37.59016781354798;
			longitude = 128.41113164659401;
		}
		else if(address.equals("충북")) {
			latitude = 36.635400453513675;
			longitude = 127.49082473882677;
		}
		else if(address.equals("충남")) {
			latitude = 36.486733289649955;
			longitude = 127.06063183885762;
		}
		else if(address.equals("경북")) {
			latitude = 36.43769791344115;
			longitude = 128.61943659685062;
		}
		else if(address.equals("경남")) {
			latitude = 35.45637271308668;
			longitude = 128.5107087340775;
		}
		else if(address.equals("전북")) {
			latitude = 35.822254829570674;
			longitude = 127.10899059168042;
		}
		else if(address.equals("전남")) {
			latitude = 34.99567623833813;
			longitude = 126.95821146112398;
		}
		else if(address.equals("제주")) {
			latitude = 33.361697752542355;
			longitude = 126.52939954030747;
		}
		else {
			latitude = 36.63508797975421;
			longitude = 127.45959376343134;
		}
  	  
		model.addAttribute("latitude", latitude);
		model.addAttribute("longitude", longitude);
		return "study/kakaomap/kakaoEx4";
	}
	*/
	
}
