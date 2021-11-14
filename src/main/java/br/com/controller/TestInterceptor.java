package br.com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TestInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	        throws Exception {
		
		if (request.getSession().getAttribute("AcessoObjct") == null) {
			request.getRequestDispatcher("/AcessoNegado").forward(request, response);
			return false;
		} else {
			return true;
		}
		
	}
}
