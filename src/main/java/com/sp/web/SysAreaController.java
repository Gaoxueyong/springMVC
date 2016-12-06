package com.sp.web;

import java.io.IOException;
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

import com.sp.entity.SysArea;
import com.sp.service.SysAreaService;
import com.sp.utils.DateUtils;
import com.sp.utils.Page;

import net.sf.json.JSONObject;

/**
 * 
 * @ClassName SysUserController
 * @Description 区域管理控制器
 * @author:Gaoxueyong
 * @Date 2016年11月14日 下午5:42:03
 * @version 1.0.0
 */
@Controller
@RequestMapping(value="/sys/area/")
public class SysAreaController {
	private static Logger logger =Logger.getLogger(SysAreaController.class);
	
	@Resource
	private SysAreaService sysAreaService;
	
	/**
	 * 
	 * @Description 
	 * @param request
	 * @param response
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2016年11月15日 下午2:21:27
	 */
	@RequestMapping(value="list")
	public String list(HttpServletRequest request,HttpServletResponse response,Model model,SysArea sysArea){
	
		Map<String, Object> paramerMap = new HashMap<String,Object>();
		paramerMap.put("currentNo", request.getParameter("currentNo"));
		paramerMap.put("pageSize", request.getParameter("pageSize"));
		paramerMap.put("name", request.getParameter("name"));
		paramerMap.put("childrens",sysArea.getParentId());
		paramerMap.put("orderField", "sort");
		paramerMap.put("orderSeq", "asc");
		paramerMap.put("delFlag", "0");
		Page<SysArea> page = sysAreaService.getSysAreaListPage(new Page<SysArea>(), paramerMap);
		//List<SysArea> list = SysAreaService.getSysAreaList(paramerMap);
		model.addAttribute("page", page);
		model.addAttribute("sysArea", sysArea);
		//model.addAttribute("list",JSONObject.fromObject(list));
		//model.addAttribute("list",list);
		return "sys/area/sysAreaList";
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
	@RequestMapping(value="sysAreaIndex")
	public String sysAreaIndex(HttpServletRequest request,HttpServletResponse response,Model model){
		
		
		 return "sys/area/sysAreaIndex";
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
	@RequestMapping(value="sysAreaTree")
	public String sysAreaTree(HttpServletRequest request,HttpServletResponse response,Model model){
		
		
		 return "sys/area/sysAreaTree";
	}
	
	
	/**
	 * 
	 * @Description 加载机构数据
	 * @param request
	 * @param response
	 * @param model
	 * @author: Gaoxueyong
	 * Create at: 2016年11月21日 上午11:17:55
	 * @throws IOException 
	 */
	@RequestMapping(value="sysAreaTreeData")
	public void sysAreaTreeData(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
		Map<String, Object> paramerMap = new HashMap<String,Object>();
		paramerMap.put("delFlag", "0");
		List<Map<String, Object>> treeData = sysAreaService.getSysAreaTreeData(paramerMap);
		JSONObject json = new JSONObject();
		json.put("data", treeData);
		response.getWriter().write(json.toString());
	}
	
	/**
	 * 
	 * @Description 转至区域管理操作界面
	 * @param request
	 * @param response
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2016年11月15日 下午2:23:35
	 */
	@RequestMapping(value="sysAreaForm")
	public String sysAreaForm(HttpServletRequest request,HttpServletResponse response,Model model){
		String id = request.getParameter("id");
		String parentId = request.getParameter("parentId");
		
		SysArea sysArea = new SysArea();
		if(!"".equals(id) && id!=null){
			//修改
			sysArea = sysAreaService.selectSysAreaByPrimaryKey(id);
		}else if(!"".equals(parentId) && parentId!=null){
			//添加
			SysArea area = sysAreaService.selectSysAreaByPrimaryKey(parentId);
			if(area!=null){
				sysArea.setParentId(area.getId());
				sysArea.setParentIds(area.getParentIds()+area.getId()+",");
				sysArea.setParentName(area.getName());
			}
		}
		model.addAttribute("sysArea", sysArea);
		model.addAttribute("loginFlag", "1");//绑定默认值
		//model.addAttribute("list", list);
		return "sys/area/sysAreaForm";
	}
	
	/**
	 * 
	 * @Description 保存、修改区域管理信息
	 * @param request
	 * @param response
	 * @author: Gaoxueyong
	 * Create at: 2016年11月15日 下午2:22:30
	 * @throws IOException 
	 */
	@RequestMapping(value="saveSysArea")
	public String saveSysArea(HttpServletRequest request,HttpServletResponse response,SysArea sysArea,RedirectAttributes redirectAttributes) throws IOException{
		String message = "";
		//增加
		if(sysArea.getId()==null ||"".equals(sysArea.getId())){
			message = "添加区域管理失败！";
			sysArea.setId(DateUtils.format(new Date(), DateUtils.DATE_YYYYMMDDHHMISSSSS));
			sysArea.setCreateBy("1");
			sysArea.setCreateDate(new Date());
			sysArea.setUpdateBy("1");
			sysArea.setUpdateDate(new Date());
			sysArea.setDelFlag("0");
			sysArea.setCreateBy(request.getSession().getAttribute("loginid").toString());
			sysArea.setCreateDate(new Date());
			sysAreaService.insertSysArea(sysArea);
			message = "添加区域管理成功！";
		}else{
		//修改
			message = "更新区域管理失败！";
			SysArea area = sysAreaService.selectSysAreaByPrimaryKey(sysArea.getId());
			if(!"".equals(area.getParentName())&&area.getParentName()!=null){
				area.setParentId(sysArea.getParentId());
				area.setParentIds(sysArea.getParentIds());
			}
			area.setCode(sysArea.getCode());
			area.setName(sysArea.getName());
			area.setSort(sysArea.getSort());
			area.setRemarks(sysArea.getRemarks());
			area.setUpdateBy(request.getSession().getAttribute("loginid").toString());
			area.setUpdateDate(new Date());
			sysAreaService.updateSysAreaByPrimaryKey(area);
			message = "更新区域管理成功！";
		}
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/sys/area/list";
	}
	
	/**
	 * 
	 * @Description 保存、修改区域管理信息
	 * @param request
	 * @param response
	 * @author: Gaoxueyong
	 * Create at: 2016年11月15日 下午2:22:30
	 * @throws IOException 
	 */
	@RequestMapping(value="delSysAreaById")
	public String delSysAreaById(HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes) throws IOException{
		String message = "删除区域管理信息失败！";
		boolean success = false;
		String id = request.getParameter("id");
		if(!StringUtils.isEmpty(id)){
			/*int count = sysAreaService.deleteSysAreaByPrimaryKey(id);
			if(count==1){
				message = "删除区域管理信息成功！";
				success = true;
			}*/
			Map<String, Object> paramerMap = new HashMap<String,Object>();
			paramerMap.put("id", id);
			int count = sysAreaService.delChildrenById(paramerMap);
			if(count>0){
				message = "删除区域管理信息成功！";
				success = true;
			}
		}
		redirectAttributes.addFlashAttribute("message", message);
		redirectAttributes.addFlashAttribute("success", success);
		return "redirect:/sys/area/list";
	}
	
}
