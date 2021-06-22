package com.spring.cj2103s01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminGet() {
		return "admin/admin";
	}
	
}
