package com.sp.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * 
 * @ClassName SysIconController
 * @Description  图标控制器
 * @author:Gaoxueyong
 * @Date 2016年12月2日 上午11:27:06
 * @version 1.0.0
 */
@Controller
@RequestMapping(value="/sys/icon/")
public class SysIconController {
	
	private static Logger logger =Logger.getLogger(SysIconController.class);
	 
	/**
	 * 
	 * @Description 图片选择页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2016年11月21日 上午11:16:59
	 */
	@RequestMapping(value="sysIconSelect")
	public String sysIconSelect(HttpServletRequest request,HttpServletResponse response,Model model){
		
		 return "sys/icon/sysIconSelect";
	}
 
}
