package com.sp.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;




@Controller
@RequestMapping(value="/learn/" )
public class LeanConstroller {
	
	@RequestMapping("hello" )
	public ModelAndView welcome(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ModelAndView view = new ModelAndView("/learn/hello");
		view.addObject("date", new Date());
		view.addObject("name", "高学勇");
		view.getModel().put("name1", "ssss");
		Map<String, String> map = new HashMap<String,String>();
		map.put("ss", "111");
		view.addObject("usermap", map);
		return view; 
	}

	@RequestMapping("welcome" )
	public String welcome(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
		model.addAttribute("name", "sss");
		model.addAttribute("name1", "高学勇");
		model.addAttribute("date",new Date());
		return "learn/welcome"; 
	}
}
