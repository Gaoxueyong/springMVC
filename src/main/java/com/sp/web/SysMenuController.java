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

import com.sp.entity.SysMenu;
import com.sp.service.SysMenuService;
import com.sp.utils.DateUtils;
import com.sp.utils.Page;

import net.sf.json.JSONObject;

/**
 * 
 * @ClassName SysMenuController
 * @Description  菜单控制器
 * @author:Gaoxueyong
 * @Date 2016年12月2日 上午11:27:06
 * @version 1.0.0
 */
@Controller
@RequestMapping(value="/sys/menu/")
public class SysMenuController {
	
	private static Logger logger =Logger.getLogger(SysMenuController.class);
	
	@Resource
	private SysMenuService sysMenuService;
	
	/**
	 * 
	 * @Description 获取菜单列表
	 * @param request
	 * @param response
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2016年11月15日 下午2:21:27
	 */
	@RequestMapping(value="list")
	public String list(HttpServletRequest request,HttpServletResponse response,Model model,SysMenu sysMenu){
	
		Map<String, Object> paramerMap = new HashMap<String,Object>();
		paramerMap.put("currentNo", request.getParameter("currentNo"));
		paramerMap.put("pageSize", request.getParameter("pageSize"));
		paramerMap.put("delFlag","0");
		paramerMap.put("name", request.getParameter("name"));
		paramerMap.put("childrens", sysMenu.getParentId());
		Page<SysMenu> page = sysMenuService.getSysMenuListPage(new Page<SysMenu>(), paramerMap);
		model.addAttribute("page", page);
		model.addAttribute("sysMenu", sysMenu);
		return "sys/menu/sysMenuList";
	}
	
	/**
	 * 
	 * @Description 区域管理树管理页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2016年11月21日 上午11:16:59
	 */
	@RequestMapping(value="sysMenuIndex")
	public String sysMenuIndex(HttpServletRequest request,HttpServletResponse response,Model model){
		
		 return "sys/menu/sysMenuIndex";
	}
	
	

	/**
	 * 
	 * @Description 区域数选择
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2016年11月21日 上午11:16:59
	 */
	@RequestMapping(value="sysMenuTree")
	public String sysMenuTree(HttpServletRequest request,HttpServletResponse response,Model model){
		
		
		 return "sys/menu/sysMenuTree";
	}
	
	
	/**
	 * 
	 * @Description 加载菜单数据
	 * @param request
	 * @param response
	 * @param model
	 * @author: Gaoxueyong
	 * Create at: 2016年11月21日 上午11:17:55
	 * @throws IOException 
	 */
	@RequestMapping(value="sysMenuTreeData")
	public void sysMenuTreeData(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
		Map<String, Object> paramerMap = new HashMap<String,Object>();
		paramerMap.put("delFlag", "0");
		List<Map<String, Object>> treeData = sysMenuService.getSysMenuTreeData(paramerMap);
		JSONObject json = new JSONObject();
		json.put("data", treeData);
		response.getWriter().write(json.toString());
	}
	
	/**
	 * 
	 * @Description 转至菜单操作界面
	 * @param request
	 * @param response
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2016年11月15日 下午2:23:35
	 */
	@RequestMapping(value="sysMenuForm")
	public String form(HttpServletRequest request,HttpServletResponse response,Model model,SysMenu sysMenu){
		if(!"".equals(sysMenu.getId()) && sysMenu.getId()!=null){
			sysMenu = sysMenuService.selectSysMenuByPrimaryKey(sysMenu.getId());
		}else if(!"".equals(sysMenu.getParentId())&& sysMenu.getParentId()!=null){
			SysMenu menu = sysMenuService.selectSysMenuByPrimaryKey(sysMenu.getParentId());
			if(menu!=null){
				sysMenu.setParentId(menu.getId());
				sysMenu.setParentIds(menu.getParentIds()+menu.getId()+",");
				sysMenu.setParentName(menu.getName());
			}
		}
		Map<String, String> map = new HashMap<String,String>();
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		map.put("value", "1");
		map.put("lable", "是");
		list.add(map);
		map = new HashMap<String,String>();
		map.put("value", "0");
		map.put("lable", "否");
		list.add(map);
		model.addAttribute("list", list);
		model.addAttribute("sysMenu", sysMenu);
		return "sys/menu/sysMenuForm";
	}
	
	/**
	 * 
	 * @Description 保存、修改菜单信息
	 * @param request
	 * @param response
	 * @author: Gaoxueyong
	 * Create at: 2016年11月15日 下午2:22:30
	 * @throws IOException 
	 */
	@RequestMapping(value="saveSysMenu")
	public String saveSysMenu(HttpServletRequest request,HttpServletResponse response,SysMenu sysMenu,RedirectAttributes redirectAttributes) throws IOException{
		String message = "";
		//增加
		if(sysMenu.getId()==null || "".equals(sysMenu.getId())){
			message = "添加菜单失败！";
			sysMenu.setId(DateUtils.format(new Date(), DateUtils.DATE_YYYYMMDDHHMISSSSS));
			sysMenu.setCreateBy(request.getSession().getAttribute("loginid").toString());
			sysMenu.setCreateDate(new Date());
			sysMenu.setDelFlag("0");
			sysMenuService.insertSysMenu(sysMenu);
			message = "添加菜单成功！";
		}else{
		//修改
			message = "更新菜单失败！";
			SysMenu menu = sysMenuService.selectSysMenuByPrimaryKey(sysMenu.getId());
	        menu.setParentId(sysMenu.getParentId());
	        menu.setParentIds(sysMenu.getParentIds());
	        menu.setName(sysMenu.getName());
	        menu.setSort(sysMenu.getSort());
	        menu.setHref(sysMenu.getHref());
	        //menu.setTarget(sysMenu.getTarget());
	        menu.setIcon(sysMenu.getIcon());
	        menu.setIsShow(sysMenu.getIsShow());
	        menu.setPermission(sysMenu.getPermission());
			menu.setRemarks(sysMenu.getRemarks());
			menu.setUpdateBy(request.getSession().getAttribute("loginid").toString());
			menu.setUpdateDate(new Date());
			sysMenuService.updateSysMenu(menu);
			message = "更新菜单成功！";
		}
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/sys/menu/list";
	}
	
	/**
	 * 
	 * @Description 保存、修改菜单信息
	 * @param request
	 * @param response
	 * @author: Gaoxueyong
	 * Create at: 2016年11月15日 下午2:22:30
	 * @throws IOException 
	 */
	@RequestMapping(value="delSysMenuById")
	public String delSysMenuById(HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes) throws IOException{
		String message = "删除菜单信息失败！";
		boolean success = false;
		String id = request.getParameter("id");
		if(!StringUtils.isEmpty(id)){
			SysMenu sysMenu = sysMenuService.selectSysMenuByPrimaryKey(id);
			if(sysMenu!=null){
				Map<String, Object> paramerMap = new HashMap<String,Object>();
				paramerMap.put("id", sysMenu.getId());
				paramerMap.put("delFlag","1");
				int count = sysMenuService.deleteSysMenuByPrimaryKey(paramerMap);
				if(count>0){
					message = "删除菜单信息成功！";
					success = true;
				}
			}
		}
		redirectAttributes.addFlashAttribute("message", message);
		redirectAttributes.addFlashAttribute("success", success);
		return "redirect:/sys/menu/list";
	}
	
}
