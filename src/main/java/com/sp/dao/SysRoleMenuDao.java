package com.sp.dao;

import java.util.List;
import java.util.Map;

import com.sp.entity.SysRoleMenu;
/**
 * 
 * @ClassName SysRoleMenuDao
 * @Description 角色菜单Dao
 * @author:Gaoxueyong
 * @Date 2016年12月7日 下午3:17:51
 * @version 1.0.0
 */
public interface SysRoleMenuDao {
	
	int insertSysRoleMenu(SysRoleMenu sysRoleMenu);
	
	/**
	 * 
	 * @Description 删除角色菜单
	 * @param paramerMap  roleId , menuId
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2016年12月7日 下午3:35:10
	 */
    int deleteSysRoleMenuByPrimaryKey(Map<String, Object> paramerMap);
    
    /**
     * 
     * @Description 根据角色id进行删除
     * @param roleId
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年12月7日 下午4:24:19
     */
    int deleteSysRoleMenuByRoleId(String roleId);
    
    /**
     * 
     * @Description 根据角色Id获取角色所包含的菜单
     * @param paramerMap  roleId
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年12月7日 下午5:10:30
     */
    List<SysRoleMenu> getSysRoleMenuListByRoleId(Map<String, Object> paramerMap);
}