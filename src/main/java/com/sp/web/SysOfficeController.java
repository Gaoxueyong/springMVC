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

import com.sp.entity.SysOffice;
import com.sp.service.SysOfficeService;
import com.sp.utils.DateUtils;
import com.sp.utils.Page;

import net.sf.json.JSONObject;

/**
 * 
 * @ClassName SysUserController
 * @Description 组织机构控制器
 * @author:Gaoxueyong
 * @Date 2016年11月14日 下午5:42:03
 * @version 1.0.0
 */
@Controller
@RequestMapping(value="/sys/office/")
public class SysOfficeController {
	private static Logger logger =Logger.getLogger(SysOfficeController.class);
	
	@Resource
	private SysOfficeService sysOfficeService;
	
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
	public String list(HttpServletRequest request,HttpServletResponse response,Model model,SysOffice sysOffice){
	
		Map<String, Object> paramerMap = new HashMap<String,Object>();
		paramerMap.put("currentNo", request.getParameter("currentNo"));
		paramerMap.put("pageSize", request.getParameter("pageSize"));
		paramerMap.put("name", request.getParameter("name"));
		paramerMap.put("delFlag","0");
		Page<SysOffice> page = sysOfficeService.getSysOfficListPage(new Page<SysOffice>(), paramerMap);
		//List<SysOffice> list = sysOfficeService.getSysOfficeList(paramerMap);
		model.addAttribute("page", page);
		model.addAttribute("sysOffice", sysOffice);
		//model.addAttribute("list",JSONObject.fromObject(list));
		//model.addAttribute("list",list);
		return "sys/office/sysOfficeList";
	}
	
	/**
	 * 
	 * @Description 组织机构树管理页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2016年11月21日 上午11:16:59
	 */
	@RequestMapping(value="sysOfficeIndex")
	public String sysOfficeIndex(HttpServletRequest request,HttpServletResponse response,Model model){
		
		
		 return "sys/office/sysOfficeIndex";
	}
	
	
	/**
	 * 
	 * @Description 机构选择
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2016年11月21日 上午11:16:59
	 */
	@RequestMapping(value="sysOfficeTree")
	public String sysOfficeTree(HttpServletRequest request,HttpServletResponse response,Model model){
		
		
		 return "sys/office/sysOfficeTree";
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
	@RequestMapping(value="sysOfficeTreeData")
	public void sysOfficeTreeData(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
		Map<String, Object> paramerMap = new HashMap<String,Object>();
		paramerMap.put("delFlag","0");
		List<Map<String, Object>> treeData = sysOfficeService.getSysOfficeTreeData(paramerMap);
		JSONObject json = new JSONObject();
		json.put("data", treeData);
		response.getWriter().write(json.toString());
	}
	
	/**
	 * 
	 * @Description 转至组织机构操作界面
	 * @param request
	 * @param response
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2016年11月15日 下午2:23:35
	 */
	@RequestMapping(value="sysOfficeForm")
	public String sysOfficeForm(HttpServletRequest request,HttpServletResponse response,Model model){
		String id = request.getParameter("id");
		String parentId = request.getParameter("parentId");
		SysOffice sysOffice = new SysOffice();
		if(!"".equals(id) && id!=null){//修改
			sysOffice = sysOfficeService.selectSysOfficeByPrimaryKey(id);
		}else if(!"".equals(parentId) && parentId!=null){
			SysOffice office = sysOfficeService.selectSysOfficeByPrimaryKey(parentId);
			if(office!=null){
				sysOffice.setParentName(office.getName());
				sysOffice.setParentId(office.getId());
				sysOffice.setParentIds(office.getParentId()+office.getId()+",");
			}
		}
		model.addAttribute("sysOffice", sysOffice);
		return "sys/office/sysOfficeForm";
	}
	
	/**
	 * 
	 * @Description 保存、修改组织机构信息
	 * @param request
	 * @param response
	 * @author: Gaoxueyong
	 * Create at: 2016年11月15日 下午2:22:30
	 * @throws IOException 
	 */
	@RequestMapping(value="saveSysOffice")
	public String saveSysOffice(HttpServletRequest request,HttpServletResponse response,SysOffice sysOffice,RedirectAttributes redirectAttributes) throws IOException{
		String message = "";
		//增加
		if(sysOffice.getId()==null || "".equals(sysOffice.getId())){
			message = "添加组织机构失败！";
			sysOffice.setId(DateUtils.format(new Date(), DateUtils.DATE_YYYYMMDDHHMISSSSS));
			sysOffice.setCreateBy(request.getSession().getAttribute("loginid").toString());
			sysOffice.setCreateDate(new Date());
			sysOffice.setDelFlag("0");
			sysOfficeService.insertSysOffice(sysOffice);
			message = "添加组织机构成功！";
		}else{
		//修改
			message = "更新组织机构失败！";
			SysOffice office = sysOfficeService.selectSysOfficeByPrimaryKey(sysOffice.getId());
			office.setParentId(sysOffice.getParentId());
			office.setParentIds(sysOffice.getParentIds());
			office.setName(sysOffice.getName());
			office.setAreaId(sysOffice.getAreaId());
			office.setCode(sysOffice.getCode());
			office.setAddress(sysOffice.getAddress());
			office.setZipCode(sysOffice.getZipCode());
			office.setRemarks(sysOffice.getRemarks());
			office.setUpdateBy(request.getSession().getAttribute("loginid").toString());
			office.setUpdateDate(new Date());
			sysOfficeService.updateSysOffice(office);
			message = "更新组织机构成功！";
		}
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/sys/office/list";
	}
	
	/**
	 * 
	 * @Description 保存、修改组织机构信息
	 * @param request
	 * @param response
	 * @author: Gaoxueyong
	 * Create at: 2016年11月15日 下午2:22:30
	 * @throws IOException 
	 */
	@RequestMapping(value="delSysOfficeById")
	public String delSysOfficeById(HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes) throws IOException{
		String message = "删除组织机构信息失败！";
		boolean success = false;
		String id = request.getParameter("id");
		if(!StringUtils.isEmpty(id)){
			Map<String, Object> paramerMap = new HashMap<String,Object>();
			paramerMap.put("id", id);
			int count = sysOfficeService.deleteSysOfficeAndChildrenById(paramerMap);
			if(count>0){
				message = "删除组织机构信息成功！";
				success = true;
			}
		}
		redirectAttributes.addFlashAttribute("message", message);
		redirectAttributes.addFlashAttribute("success", success);
		return "redirect:/sys/office/list";
	}
	
}
