package com.sp.service;

import java.util.List;
import java.util.Map;

import com.sp.entity.SysRoleMenu;

/**
 * 
 * @ClassName SysRoleMenuService
 * @Description 角色菜单service
 * @author:Gaoxueyong
 * @Date 2016年12月7日 下午3:41:27
 * @version 1.0.0
 */
public interface SysRoleMenuService {
	
	int insertSysRoleMenu(SysRoleMenu sysRoleMenu);
	
	/**
	 * 
	 * @Description 删除角色菜单
	 * @param paramerMap  #{roleId} , #{menuId}
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2016年12月7日 下午3:35:10
	 */
    int deleteSysRoleMenuByPrimaryKey(Map<String, Object> paramerMap);
    
    /**
     * 
     * @Description 根据角色Id获取角色所包含的菜单
     * @param paramerMap roleId
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年12月7日 下午5:10:30
     */
    List<SysRoleMenu> getSysRoleMenuListByRoleId(Map<String, Object> paramerMap);
}
