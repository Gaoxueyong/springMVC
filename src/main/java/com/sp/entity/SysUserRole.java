package com.sp.entity;
/**
 * 
 * @ClassName SysUserRole
 * @Description 用户角色
 * @author:Gaoxueyong  http://blog.csdn.net/fenfenguai
 * @Date 2016年12月7日 下午3:09:16
 * @version 1.0.0
 */
public class SysUserRole {
    private String userId;//用户编号

    private String roleId;//角色编号

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }
}