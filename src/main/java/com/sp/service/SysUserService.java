package com.sp.service;

import java.util.List;
import java.util.Map;

import com.sp.entity.SysUser;
/**
 * 
 * @ClassName SysUserService
 * @Description 用户service
 * @author:Gaoxueyong  http://blog.csdn.net/fenfenguai
 * @Date 2016年11月15日 上午9:51:10
 * @version 1.0.0
 */
import com.sp.utils.Page;
public interface SysUserService {

	int insertSysUser(SysUser sysUser);

	 /**
     * 
     * @Description 逻辑删除
     * @param paramerMap
     * @return
     * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai
     * Create at: 2016年12月8日 下午3:19:48
     */
    int deleteSysUserByPrimaryKey(Map<String, Object> paramerMap);
    
    /**
     * 
     * @Description 物理删除
     * @param id
     * @return
     * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai
     * Create at: 2016年12月8日 下午3:19:57
     */
    int deleteSysUserByPrimaryKeyReal(String id);

	int updateSysUser(SysUser sysUser);
	
	/**
	 * 
	 * @Description 更新状态  登录成功后进行
	 * @param sysUser
	 * @return
	 * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai
	 * Create at: 2016年12月8日 下午3:39:11
	 */
	int updateSysUserStatus(SysUser sysUser);

	SysUser selectSysUserByPrimaryKey(String id);

	/**
	 * 
	 * @Description 获取用户列表
	 * @param paramerMap
	 * @return
	 * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai Create at: 2016年12月8日 下午3:06:51
	 */
	List<SysUser> getSysUserList(Map<String, Object> paramerMap);

	/**
	 * 
	 * @Description 获取用户分页列表
	 * @param paramerMap
	 * @return
	 * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai Create at: 2016年11月15日 下午2:13:18
	 */
	public Page<SysUser> getSysUserListPage(Page<SysUser> page, Map<String, Object> paramerMap);

	/**
	 * 
	 * @Description 获取用户列表总数
	 * @param paramerMap
	 * @return
	 * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai Create at: 2016年12月8日 下午3:06:13
	 */
	public int getSysUserListTotalNum(Map<String, Object> paramerMap);

	/**
	 * 
	 * @Description 根据登录名获取用户对象
	 * @param loginName
	 * @return
	 * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai Create at: 2016年12月8日 下午3:06:32
	 */
	public SysUser getSysUserByLoginName(String loginName);
}
