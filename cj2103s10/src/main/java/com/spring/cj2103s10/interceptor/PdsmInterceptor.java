package com.spring.cj2103s10.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PdsmInterceptor extends HandlerInterceptorAdapter {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
  	HttpSession session = request.getSession();
  	int level = session.getAttribute("slevel")==null ? 99 : (int) session.getAttribute("slevel");
  	if(level > 2) {
  		RequestDispatcher dispatcher = request.getRequestDispatcher("/msg/useNo");
  		dispatcher.forward(request, response);
  	}
  	
  	return true;
  }
}
