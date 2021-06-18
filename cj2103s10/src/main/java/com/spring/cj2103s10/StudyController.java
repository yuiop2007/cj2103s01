package com.spring.cj2103s10;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/study")
public class StudyController {
	
	@RequestMapping("/woo")
	public String wooGet() {
		return "study/woo/woo";
	}
	
	
}
