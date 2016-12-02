package com.sp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @ClassName LoginInterceptor
 * @Description 登录拦截器
 * @author:Gaoxueyong
 * @Date 2016年11月8日 下午2:52:56
 * @version 1.0.0
 */
public class LoginInterceptor implements  HandlerInterceptor{

	/**
	 * 用户请求处理前进调用
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		//获取请求url
		String url = request.getRequestURI();
		if(url.indexOf("/login")>=0){
			return true;
		}
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("loginname");
		if(username!=null && !"".equals(username)){
			return true; 
		}
		//不符合要求的统一返回到登录界面
		//request.getRequestDispatcher("login.jsp").forward(request, response);
		
		response.sendRedirect(request.getContextPath()+"/login.jsp");
		return false;
	}
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
