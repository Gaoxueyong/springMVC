package com.sp.web;


import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sp.entity.SysUser;
import com.sp.service.SysUserService;


@Controller
public class LoginController {
	private static Logger logger =Logger.getLogger(SysUserController.class);
	@Resource
	private SysUserService sysUserService;
	/**
	 * 
	 * @Description  登录方法
	 * @param request
	 * @param response
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2016年11月8日 下午3:39:36
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request,HttpServletResponse response){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String url ="redirect:/login.jsp";
		String loginMessage = "登录失败！";
		HttpSession session = request.getSession();
		try {
			//**************************************************************
			/*System.out.println("request.getLocalAddr()>>>>>"+request.getLocalAddr());
			System.out.println("request.getRemoteHost()>>>>>"+request.getRemoteHost());
			System.out.println("request.getRemoteAddr()>>>>>"+request.getRemoteAddr());*/
			
			
			//**************************************************************
			
			SysUser user = sysUserService.getSysUserByLoginName(username);
			boolean loginFlag = false;
			if(user!=null && !"".equals(password)){
				if(user.getPassword().equals(password)){
					//用户信息保存在session里
					Date date = new Date();
					session.setAttribute("loginname", user.getName());
					session.setAttribute("loginid", user.getId());
					session.setAttribute("logindate", date);
					session.setAttribute("loginip", request.getRemoteAddr());
					user.setLoginDate(date);
					user.setLoginIp(request.getLocalAddr());
					sysUserService.updateSysUserStatus(user);
					loginMessage = "登录成功！";
					loginFlag = true;
				}else{
					loginMessage = "登录名或密码错误，登录失败！";
					
				}
			}else{
				loginMessage = "登录名或密码错误，登录失败！";
			}
			if(loginFlag){
				url =  "redirect:main/main";
			}
		} catch (Exception e) {
			loginMessage = "登录名或密码错误，登录失败！";
			logger.info(loginMessage+e.getMessage());
			e.printStackTrace();
		}finally{
			session.setAttribute("loginMessage", loginMessage);
			logger.info(loginMessage);
			return url;
		}
		
	}
	
	/**
	 * 
	 * @Description 退出系统
	 * @param request
	 * @param response
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2016年11月8日 下午3:45:14
	 */
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		//清除Session  
        session.invalidate(); 
        return "redirect:/login.jsp";
	}
}
