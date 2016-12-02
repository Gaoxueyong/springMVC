package com.sp.service;

import java.util.List;
import java.util.Map;

import com.sp.entity.SysUser;
/**
 * 
 * @ClassName SysUserService
 * @Description 用户service
 * @author:Gaoxueyong
 * @Date 2016年11月15日 上午9:51:10
 * @version 1.0.0
 */
import com.sp.utils.Page;
public interface SysUserService {

	public SysUser selectByPrimaryKey(int id);
	
	/**
	 * 
	 * @Description 
	 * @param paramerMap
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2016年11月15日 下午2:07:10
	 */
	public List<SysUser> getSysUserList(Map<String, Object> paramerMap);
	
	public void saveSysUser(SysUser sysUser);
	
	public void updateSysUser(SysUser sysUser);
	
	public void delSysUser(int id);
	
	public int getSysUserListTotalNum(Map<String, Object> paramerMap);
	
	public Page<SysUser> getSysUserListPage(Page<SysUser> page,Map<String, Object> paramerMap);
	
	public SysUser getSysUserByLoginName(String loginName);
	
}
