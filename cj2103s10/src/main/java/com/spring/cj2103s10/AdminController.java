package com.spring.cj2103s10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.cj2103s10.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
  String msgFlag = "";
  
  @Autowired
  AdminService adminService;
	
  @RequestMapping(value="/aCheck", method=RequestMethod.GET)
  public String aCheckGet() {
  	return "admin/aMain";
  }
  
  @RequestMapping(value="/aMenu", method=RequestMethod.GET)
  public String aMenuGet() {
  	return "admin/aMenu";
  }
  
  @RequestMapping(value="/aContent", method=RequestMethod.GET)
  public String aContent() {
  	return "admin/aContent";
  }
}
