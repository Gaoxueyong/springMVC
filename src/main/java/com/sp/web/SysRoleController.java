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

import com.sp.entity.SysRole;
import com.sp.service.SysRoleService;
import com.sp.utils.DateUtils;
import com.sp.utils.Page;


/**
 * 
 * @ClassName SysRoleController
 * @Description  角色控制器
 * @author:Gaoxueyong
 * @Date 2016年12月2日 上午11:27:06
 * @version 1.0.0
 */
@Controller
@RequestMapping(value="/sys/role/")
public class SysRoleController {
	
	private static Logger logger =Logger.getLogger(SysRoleController.class);
	
	@Resource
	private SysRoleService sysRoleService;
	
	/**
	 * 
	 * @Description 获取角色列表
	 * @param request
	 * @param response
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2016年11月15日 下午2:21:27
	 */
	@RequestMapping(value="list")
	public String list(HttpServletRequest request,HttpServletResponse response,Model model,SysRole sysRole){
	
		Map<String, Object> paramerMap = new HashMap<String,Object>();
		paramerMap.put("currentNo", request.getParameter("currentNo"));
		paramerMap.put("pageSize", request.getParameter("pageSize"));
		paramerMap.put("delFlag","0");
		paramerMap.put("name", request.getParameter("name"));
		Page<SysRole> page = sysRoleService.getSysRoleListPage(new Page<SysRole>(), paramerMap);
		model.addAttribute("page", page);
		model.addAttribute("sysRole", sysRole);
		return "sys/role/sysRoleList";
	}
	
	/**
	 * 
	 * @Description 转至角色操作界面
	 * @param request
	 * @param response
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2016年11月15日 下午2:23:35
	 */
	@RequestMapping(value="sysRoleForm")
	public String sysRoleForm(HttpServletRequest request,HttpServletResponse response,Model model){
		String id = request.getParameter("id");
		SysRole sysRole  = new SysRole();
		if(!"".equals(id) && id!=null){
			sysRole = sysRoleService.selectSysRoleByPrimaryKey(id);
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
		model.addAttribute("list", list);
		model.addAttribute("sysRole", sysRole);
		return "sys/role/sysRoleForm";
	}
	
	/**
	 * 
	 * @Description 保存、修改角色信息
	 * @param request
	 * @param response
	 * @author: Gaoxueyong
	 * Create at: 2016年11月15日 下午2:22:30
	 * @throws IOException 
	 */
	@RequestMapping(value="saveSysRole")
	public String saveSysRole(HttpServletRequest request,HttpServletResponse response,SysRole sysRole,RedirectAttributes redirectAttributes) throws IOException{
		String message = "";
		//增加
		if(sysRole.getId()==null || "".equals(sysRole.getId())){
			message = "添加角色失败！";
			sysRole.setId(DateUtils.format(new Date(), DateUtils.DATE_YYYYMMDDHHMISSSSS));
			sysRole.setCreateBy(request.getSession().getAttribute("loginid").toString());
			sysRole.setCreateDate(new Date());
			sysRole.setDelFlag("0");
			sysRoleService.insertSysRole(sysRole);
			message = "添加角色成功！";
		}else{
		//修改
			message = "更新角色失败！";
			SysRole role = sysRoleService.selectSysRoleByPrimaryKey(sysRole.getId());
			role.setName(sysRole.getName());
			role.setEnname(sysRole.getEnname());
			role.setUseable(sysRole.getUseable());
			role.setRemarks(sysRole.getRemarks());
			role.setRoleMenus(sysRole.getRoleMenus());//设置角色资源信息
			role.setUpdateBy(request.getSession().getAttribute("loginid").toString());
			role.setUpdateDate(new Date());
			sysRoleService.updateSysRole(role);
			message = "更新角色成功！";
		}
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/sys/role/list";
	}
	
	/**
	 * 
	 * @Description 保存、修改角色信息
	 * @param request
	 * @param response
	 * @author: Gaoxueyong
	 * Create at: 2016年11月15日 下午2:22:30
	 * @throws IOException 
	 */
	@RequestMapping(value="delSysRoleById")
	public String delSysRoleById(HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes) throws IOException{
		String message = "删除角色信息失败！";
		boolean success = false;
		String id = request.getParameter("id");
		if(!StringUtils.isEmpty(id)){
			Map<String, Object> paramerMap = new HashMap<String,Object>();
			paramerMap.put("id",id);
			paramerMap.put("delFlag","1");
			int count = sysRoleService.deleteSysRoleByPrimaryKey(paramerMap);
			if(count>0){
				message = "删除角色信息成功！";
				success = true;
			}
		}
		redirectAttributes.addFlashAttribute("message", message);
		redirectAttributes.addFlashAttribute("success", success);
		return "redirect:/sys/role/list";
	}
	
}
