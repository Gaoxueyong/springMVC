package com.sp.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sp.dao.SysUserDao;
import com.sp.dao.SysUserRoleDao;
import com.sp.entity.SysRole;
import com.sp.entity.SysUser;
import com.sp.entity.SysUserRole;
import com.sp.service.SysUserService;
import com.sp.utils.Page;

/**
 * 
 * @ClassName SysUserServiceImpl
 * @Description 用户serv实现
 * @author:Gaoxueyong
 * @Date 2016年12月8日 下午3:33:28
 * @version 1.0.0
 */
@Service
public class SysUserServiceImpl implements SysUserService{

	@Resource
	private SysUserDao sysUserDao;
	@Resource
	private SysUserRoleDao sysUserRoleDao;
	
	@Override
	public int insertSysUser(SysUser sysUser) {
		//插入用户角色
		if(sysUser.getRoleStr()!=null && !"".equals(sysUser.getRoleStr())){
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setUserId(sysUser.getId());
			String[] roleStr = sysUser.getRoleStr().split(",");
			for(String roleId:roleStr){
				sysUserRole.setRoleId(roleId);
				sysUserRoleDao.insertSysUserRole(sysUserRole);
				
			}
		}
		return sysUserDao.insertSysUser(sysUser);
	}
	/**
     * 
     * @Description 逻辑删除
     * @param paramerMap
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年12月8日 下午3:19:48
     */
	@Override
	public int deleteSysUserByPrimaryKey(Map<String, Object> paramerMap) {
		//先根据用户id删除用户角色 
		sysUserRoleDao.deleteSysUserRoleByUserId(paramerMap.get("id").toString());
		return sysUserDao.deleteSysUserByPrimaryKey(paramerMap);
	}
	
	/**
     * 
     * @Description 物理删除
     * @param id
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年12月8日 下午3:19:57
     */
	@Override
	public int deleteSysUserByPrimaryKeyReal(String id) {
		//先根据用户id删除用户角色 
		sysUserRoleDao.deleteSysUserRoleByUserId(id);
		return sysUserDao.deleteSysUserByPrimaryKeyReal(id);
	}
	@Override
	public int updateSysUser(SysUser sysUser) {
		//先根据用户id删除用户角色 
		sysUserRoleDao.deleteSysUserRoleByUserId(sysUser.getId());
		
		//插入用户角色
		if(sysUser.getRoleStr()!=null && !"".equals(sysUser.getRoleStr())){
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setUserId(sysUser.getId());
			String[] roleStr = sysUser.getRoleStr().split(",");
			for(String roleId:roleStr){
				if(!"".equals(roleId)){
					sysUserRole.setRoleId(roleId);
					sysUserRoleDao.insertSysUserRole(sysUserRole);
				}
				
			}
		}
		return sysUserDao.updateSysUser(sysUser);
	}
	@Override
	public SysUser selectSysUserByPrimaryKey(String id) {
		return sysUserDao.selectSysUserByPrimaryKey(id);
	}
	
	/**
	 * 
	 * @Description 获取用户列表
	 * @param paramerMap
	 * @return
	 * @author: Gaoxueyong Create at: 2016年12月8日 下午3:06:51
	 */
	@Override
	public List<SysUser> getSysUserList(Map<String, Object> paramerMap) {
		return sysUserDao.getSysUserList(paramerMap);
	}
	
	/**
	 * 
	 * @Description 获取用户分页列表
	 * @param paramerMap
	 * @return
	 * @author: Gaoxueyong Create at: 2016年11月15日 下午2:13:18
	 */
	@Override
	public Page<SysUser> getSysUserListPage(Page<SysUser> page, Map<String, Object> paramerMap) {
		if(paramerMap.get("currentNo")!=null){
			page.setCurrentNo(Integer.parseInt(paramerMap.get("currentNo").toString()));
		}
		if(paramerMap.get("pageSize")!=null){
			page.setPageSize(Integer.parseInt(paramerMap.get("pageSize").toString()));
		}
		 
		paramerMap.put("startSize",page.getStartSize());
		paramerMap.put("pageSize",page.getPageSize());
		 
		page.setTotalNum(sysUserDao.getSysUserListTotalNum(paramerMap));
		page.setList(sysUserDao.getSysUserListPage(paramerMap));
		page.initialize();
		return page;
	}
	
	/**
	 * 
	 * @Description 获取用户列表总数
	 * @param paramerMap
	 * @return
	 * @author: Gaoxueyong Create at: 2016年12月8日 下午3:06:13
	 */
	@Override
	public int getSysUserListTotalNum(Map<String, Object> paramerMap) {
		return sysUserDao.getSysUserListTotalNum(paramerMap);
	}
	
	/**
	 * 
	 * @Description 根据登录名获取用户对象
	 * @param loginName
	 * @return
	 * @author: Gaoxueyong Create at: 2016年12月8日 下午3:06:32
	 */
	@Override
	public SysUser getSysUserByLoginName(String loginName) {
		return sysUserDao.getSysUserByLoginName(loginName);
	}
	
	/**
	 * 
	 * @Description 更新状态  登录成功后进行
	 * @param sysUser
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2016年12月8日 下午3:39:11
	 */
	@Override
	public int updateSysUserStatus(SysUser sysUser) {
		return sysUserDao.updateSysUserStatus(sysUser);
	}
}
