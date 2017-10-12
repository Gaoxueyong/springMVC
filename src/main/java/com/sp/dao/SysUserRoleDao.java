package com.sp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sp.entity.SysUserRole;
/**
 * 
 * @ClassName SysUserRoleDao
 * @Description 用户角色Dao
 * @author:Gaoxueyong  http://blog.csdn.net/fenfenguai
 * @Date 2016年12月7日 下午3:17:16
 * @version 1.0.0
 */
@Repository
public interface SysUserRoleDao {

    int insertSysUserRole(SysUserRole sysUserRole);
    
    /**
     * 
     * @Description 删除用户角色
     * @param paramerMap   #{userId} , #{roleId}
     * @return
     * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai
     * Create at: 2016年12月7日 下午3:33:52
     */
    int deleteSysUserRoleByPrimary(Map<String, Object> paramerMap);
    
    /**
     * 
     * @Description 根据用户id删除用户角色信息
     * @param userId
     * @return
     * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai
     * Create at: 2016年12月8日 下午3:13:42
     */
    int deleteSysUserRoleByUserId(String userId);
    
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