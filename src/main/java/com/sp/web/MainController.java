package com.sp.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sp.entity.SysMenu;
import com.sp.service.SysMenuService;

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
	
	@Resource
	private SysMenuService sysMenuService;
	
	@RequestMapping(value="main")
	public String systemMain(HttpServletRequest request,HttpServletResponse response,Model model){
		
		Map<String,Object> paramerMap = new HashMap<String,Object>();
		paramerMap.put("delFlag","0");//删除标记 默认为0  删除为1
		paramerMap.put("isShow","1");//是否在菜单中显示 默认为1  不显示为0 
		paramerMap.put("parentId","1");//一级菜单
		paramerMap.put("orderField","sort");//一级菜单
		paramerMap.put("orderSeq","asc");//一级菜单${orderField} ${orderSeq}
		List<SysMenu> menuListFirst = sysMenuService.getSysMenuList(paramerMap);
		model.addAttribute("menuListFirst", menuListFirst);
		return "main/main";
	}
	
	@RequestMapping(value = "menuTree")
	public String menuTree(HttpServletRequest request,HttpServletResponse response,Model model){
		
		Map<String,Object> paramerMap = new HashMap<String,Object>();
		paramerMap.put("delFlag","0");//删除标记 默认为0  删除为1
		paramerMap.put("isShow","1");//是否在菜单中显示 默认为1  不显示为0 
		paramerMap.put("childrens",request.getParameter("parentId"));//parentId
		paramerMap.put("orderField","sort");//一级菜单
		paramerMap.put("orderSeq","asc");//一级菜单${orderField} ${orderSeq}
		List<SysMenu> menuList = sysMenuService.getSysMenuList(paramerMap);
		model.addAttribute("menuList", menuList);
		model.addAttribute("parentId", request.getParameter("parentId"));
		return "main/menuTree";
	}
}
