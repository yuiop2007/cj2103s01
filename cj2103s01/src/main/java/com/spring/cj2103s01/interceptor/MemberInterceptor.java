package com.spring.cj2103s01.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MemberInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		
		int level = session.getAttribute("slevel") == null ? 99 : (int) session.getAttribute("slevel");
		if (level > 4) { // 비회원이면
			RequestDispatcher dispatcher = request.getRequestDispatcher("/member/login");
			dispatcher.forward(request, response);
		}

		return true;
	}
}
