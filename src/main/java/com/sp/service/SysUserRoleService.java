package com.sp.service;

import java.util.List;
import java.util.Map;

import com.sp.entity.SysUserRole;

/**
 * 
 * @ClassName SysUserRoleService
 * @Description 用户角色service
 * @author:Gaoxueyong  http://blog.csdn.net/fenfenguai
 * @Date 2016年12月7日 下午3:36:28
 * @version 1.0.0
 */
public interface SysUserRoleService {

	   int insertSysUserRole(SysUserRole sysUserRole);
	    
	    /**
	     * 
	     * @Description 删除用户角色
	     * @param paramerMap  userId , roleId
	     * @return
	     * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai
	     * Create at: 2016年12月7日 下午3:33:52
	     */
	    int deleteSysUserRoleByPrimary(Map<String, Object> paramerMap);
	    
	    /**
	     * 
	     * @Description 获取用户角色列表
	     * @param paramerMap
	     * @return
	     * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai
	     * Create at: 2016年12月9日 上午9:39:11
	     */
	    List<SysUserRole> getSysUserRoleList(Map<String, Object> paramerMap);
}
