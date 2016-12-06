package com.sp.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @ClassName MainController
 * @Description 登录成功后跳转至主页面
 * @author:Gaoxueyong
 * @Date 2016年11月8日 下午3:46:24
 * @version 1.0.0
 */
@Controller
@RequestMapping(value="main")
public class MainController {
	
	@RequestMapping(value="main")
	public String systemMain(HttpServletRequest request,HttpServletResponse response,Model model){
		
		return "main/main";
	}
}
