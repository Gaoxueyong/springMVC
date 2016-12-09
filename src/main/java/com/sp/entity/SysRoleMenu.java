package com.sp.entity;
/**
 * 
 * @ClassName SysRoleMenu
 * @Description 角色菜单
 * @author:Gaoxueyong
 * @Date 2016年12月7日 下午3:08:58
 * @version 1.0.0
 */
public class SysRoleMenu {
    private String roleId;//角色编号

    private String menuId;//菜单编号
    
    

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }
}