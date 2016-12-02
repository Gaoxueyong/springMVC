package com.sp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sp.entity.SysUser;

/**
 * 
 * @ClassName SysUserDao
 * @Description 用户dao
 * @author:Gaoxueyong
 * @Date 2016年11月15日 上午9:51:26
 * @version 1.0.0
 */
@Repository
public interface SysUserDao {
    int deleteByPrimaryKey(int id);

    int insert(SysUser record);

    //int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(int id);

    //int updateByPrimaryKeySelective(SysUser record);

    /**
     * 
     * @Description 获取用户分页列表
     * @param paramerMap
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年11月15日 下午2:13:18
     */
    List<SysUser> getSysUserListPage(Map<String, Object> paramerMap);
    
    List<SysUser> getSysUserList(Map<String, Object> paramerMap);
    
    public void saveSysUser(SysUser sysUser);
	
	public void updateSysUser(SysUser sysUser);
	
	public void delSysUser(int id);
	
	public int getSysUserListTotalNum(Map<String, Object> paramerMap);
	
	public SysUser getSysUserByLoginName(String loginName);
}