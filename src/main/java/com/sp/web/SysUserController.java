package com.sp.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sp.entity.SysUser;
import com.sp.service.SysUserService;
import com.sp.utils.Page;

/**
 * 
 * @ClassName SysUserController
 * @Description 用户控制器
 * @author:Gaoxueyong
 * @Date 2016年11月14日 下午5:42:03
 * @version 1.0.0
 */
@Controller
@RequestMapping(value="/sys/user/")
public class SysUserController {
	
	private static Logger logger =Logger.getLogger(SysUserController.class);
	
	@Resource
	private SysUserService sysUserService;
	
	/**
	 * 
	 * @Description 获取用户列表
	 * @param request
	 * @param response
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2016年11月15日 下午2:21:27
	 */
	@RequestMapping(value="list")
	public String systemMain(HttpServletRequest request,HttpServletResponse response,Model model,SysUser sysUser){
	
		Map<String, Object> paramerMap = new HashMap<String,Object>();
		paramerMap.put("currentNo", request.getParameter("currentNo"));
		paramerMap.put("pageSize", request.getParameter("pageSize"));
		paramerMap.put("name", request.getParameter("name"));
		Page<SysUser> page = sysUserService.getSysUserListPage(new Page<SysUser>(), paramerMap);
		model.addAttribute("page", page);
		model.addAttribute("sysUser", sysUser);
		return "sys/user/sysUserList";
	}
	
	/**
	 * 
	 * @Description 转至用户操作界面
	 * @param request
	 * @param response
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2016年11月15日 下午2:23:35
	 */
	@RequestMapping(value="manage")
	public String manageSysUser(HttpServletRequest request,HttpServletResponse response,Model model){
		String id = request.getParameter("id");
		SysUser sysUser = new SysUser();
		if(!"".equals(id) && id!=null){
			sysUser = sysUserService.selectByPrimaryKey(Integer.parseInt(id));
		}
		Map<String, String> map = new HashMap<String,String>();
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		map.put("value", "1");
		map.put("lable", "是");
		list.add(map);
		map = new HashMap<String,String>();
		map.put("value", "2");
		map.put("lable", "否");
		list.add(map);
		model.addAttribute("sysUser", sysUser);
		model.addAttribute("loginFlag", "1");//绑定默认值
		model.addAttribute("list", list);
		return "sys/user/sysUserManage";
	}
	
	/**
	 * 
	 * @Description 保存、修改用户信息
	 * @param request
	 * @param response
	 * @author: Gaoxueyong
	 * Create at: 2016年11月15日 下午2:22:30
	 * @throws IOException 
	 */
	@RequestMapping(value="saveSysUser")
	public String saveSysUser(HttpServletRequest request,HttpServletResponse response,SysUser sysUser,RedirectAttributes redirectAttributes) throws IOException{
		String message = "";
		//增加
		if(sysUser.getId()==null){
			message = "添加用户失败！";
			sysUser.setCreateBy(request.getSession().getAttribute("loginid").toString());
			sysUser.setCreateDate(new Date());
			sysUser.setDelFlag("0");
			sysUserService.saveSysUser(sysUser);
			message = "添加用户成功！";
		}else{
		//修改
			message = "更新用户失败！";
			SysUser user = sysUserService.selectByPrimaryKey(sysUser.getId());
			user.setName(sysUser.getName());
			user.setUpdateBy(request.getAttribute("loginid").toString());
			user.setUpdateDate(new Date());
			sysUserService.updateSysUser(user);
			message = "更新用户成功！";
		}
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/sys/user/list";
	}
	
	/**
	 * 
	 * @Description 保存、修改用户信息
	 * @param request
	 * @param response
	 * @author: Gaoxueyong
	 * Create at: 2016年11月15日 下午2:22:30
	 * @throws IOException 
	 */
	@RequestMapping(value="delSysUserById")
	public String delSysUserById(HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes) throws IOException{
		String message = "删除用户信息失败！";
		boolean success = false;
		String id = request.getParameter("id");
		if(!StringUtils.isEmpty(id)){
			SysUser user = sysUserService.selectByPrimaryKey(Integer.parseInt(id));
			if(user!=null){
				sysUserService.delSysUser(Integer.parseInt(id));
				message = "删除用户信息成功！";
				success = true;
			}
		}
		redirectAttributes.addFlashAttribute("message", message);
		redirectAttributes.addFlashAttribute("success", success);
		return "redirect:/sys/user/list";
	}
	
}
