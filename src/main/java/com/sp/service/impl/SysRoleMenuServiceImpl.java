package com.sp.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sp.dao.SysRoleMenuDao;
import com.sp.entity.SysRoleMenu;
import com.sp.service.SysRoleMenuService;

@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService{
	@Resource
	private SysRoleMenuDao sysRoleMenuDao;
	@Override
	public int insertSysRoleMenu(SysRoleMenu sysRoleMenu) {
		return sysRoleMenuDao.insertSysRoleMenu(sysRoleMenu);
	}

	/**
	 * 
	 * @Description 删除角色菜单
	 * @param paramerMap  roleId , menuId
	 * @return
	 * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai
	 * Create at: 2016年12月7日 下午3:35:10
	 */
	@Override
	public int deleteSysRoleMenuByPrimaryKey(Map<String, Object> paramerMap) {
		return sysRoleMenuDao.deleteSysRoleMenuByPrimaryKey(paramerMap);
	}

	/**
     * 
     * @Description 根据角色Id获取角色所包含的菜单
     * @param paramerMap   roleId
     * @return
     * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai
     * Create at: 2016年12月7日 下午5:10:30
     */
	@Override
	public List<SysRoleMenu> getSysRoleMenuListByRoleId(Map<String, Object> paramerMap) {
		return sysRoleMenuDao.getSysRoleMenuListByRoleId(paramerMap);
	}

}
