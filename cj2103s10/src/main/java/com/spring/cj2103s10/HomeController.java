package com.spring.cj2103s10;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@RequestMapping(value = {"/","/h","/main"}, method = RequestMethod.GET)
	public String home() {
		
		return "main/main";
	}
	
}
