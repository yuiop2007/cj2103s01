package com.spring.cj2103s01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value = {"/","main"}, method = RequestMethod.GET)
	public String main() {
		
		return "main/main";
	}
	
	
	
	
}
