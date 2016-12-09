package com.sp.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sp.dao.SysUserRoleDao;
import com.sp.entity.SysUserRole;
import com.sp.service.SysUserRoleService;
/**
 * 
 * @ClassName SysUserRoleServiceImpl
 * @Description 用户角色serviceImpl
 * @author:Gaoxueyong
 * @Date 2016年12月7日 下午3:37:21
 * @version 1.0.0
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {
	
	@Resource
	private SysUserRoleDao sysUserRoleDao;
	@Override
	public int insertSysUserRole(SysUserRole sysUserRole) {
		return sysUserRoleDao.insertSysUserRole(sysUserRole);
	}


    /**
     * 
     * @Description 删除用户角色
     * @param paramerMap   userId , roleId
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年12月7日 下午3:33:52
     */
	@Override
	public int deleteSysUserRoleByPrimary(Map<String, Object> paramerMap) {
		return sysUserRoleDao.deleteSysUserRoleByPrimary(paramerMap);
	}

    /**
     * 
     * @Description 获取用户角色列表
     * @param paramerMap
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年12月9日 上午9:39:11
     */
	@Override
	public List<SysUserRole> getSysUserRoleList(Map<String, Object> paramerMap) {
		return sysUserRoleDao.getSysUserRoleList(paramerMap);
	}

}
