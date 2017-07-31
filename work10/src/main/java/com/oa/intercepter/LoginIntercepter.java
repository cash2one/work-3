package com.oa.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.oa.entity.User;

public class LoginIntercepter  implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2) throws Exception {
		String url = request.getRequestURI();
		if (url.contains("login")||url.contains("toLogin")||url.contains("static")) {
			return true;
		}
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("u");
		if (user!=null) {
			return true;
		}
		request.getRequestDispatcher("/WEB-INF/application/login.ftl").forward(request, arg1);
		return false;
	}
	
	

}
