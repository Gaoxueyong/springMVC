package com.sp.web;



import java.net.InetAddress;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sp.entity.SysUser;
import com.sp.service.SysUserService;


@Controller
public class LoginController {
	@Resource
	private SysUserService sysUserService;
	/**
	 * 
	 * @Description  登录方法
	 * @param request
	 * @param response
	 * @return
	 * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai
	 * Create at: 2016年11月8日 下午3:39:36
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request,HttpServletResponse response){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String url ="redirect:/login.jsp";
		String msg = "登录失败！";
		HttpSession session = request.getSession();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(true);  
		Subject subject = SecurityUtils.getSubject(); 
		//subject.getSession().setTimeout(1800000);
		try {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>4");
			subject.login(token);
			if(subject.isAuthenticated()){
				SysUser user = sysUserService.getSysUserByLoginName(username);
				String ip = InetAddress.getLocalHost().getHostAddress().toString(); // 获取服务器IP地址
				//用户信息保存在session里
				Date date = new Date();
				session.setAttribute("loginname", user.getName());
				session.setAttribute("loginid", user.getId());
				session.setAttribute("logindate", date);
				session.setAttribute("loginip", request.getRemoteAddr());
				user.setLoginDate(date);
				user.setLoginIp(ip);
				sysUserService.updateSysUserStatus(user);
				msg = "登录成功！";
				url =  "redirect:main/main";
			}else{
				subject.logout();
			}
		}catch (IncorrectCredentialsException e) {  
	        msg = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";  
	        //model.addAttribute("message", msg);  
	        System.out.println(msg);  
	    } catch (ExcessiveAttemptsException e) {  
	        msg = "登录失败次数过多";  
	        //model.addAttribute("message", msg);  
	        System.out.println(msg);  
	    } catch (LockedAccountException e) {  
	        msg = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";  
	        //model.addAttribute("message", msg);  
	        System.out.println(msg);  
	    } catch (DisabledAccountException e) {  
	        msg = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";  
	        //model.addAttribute("message", msg);  
	        System.out.println(msg);  
	    } catch (ExpiredCredentialsException e) {  
	        msg = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";  
	        //model.addAttribute("message", msg);  
	        System.out.println(msg);  
	    } catch (UnknownAccountException e) {  
	        msg = "帐号不存在. There is no user with username of " + token.getPrincipal();  
	        //model.addAttribute("message", msg);  
	        System.out.println(msg);  
	    } catch (UnauthorizedException e) {  
	        msg = "您没有得到相应的授权！" + e.getMessage();  
	        //model.addAttribute("message", msg);  
	        System.out.println(msg);  
	    }  finally{
	    	session.setAttribute("loginMessage", msg);
	    	return url;
		}
		
	}
	
}
