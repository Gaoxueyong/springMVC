package com.sp.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sp.dao.SysUserDao;
import com.sp.entity.SysUser;
import com.sp.service.SysUserService;
import com.sp.utils.Page;


@Service
public class SysUserServiceImpl implements SysUserService{

	@Resource
	private SysUserDao sysUserDao;
	
	public SysUserDao getSysUserDao() {
		return sysUserDao;
	}

	public void setSysUserDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}

	@Override
	public SysUser selectByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		return sysUserDao.selectByPrimaryKey(id);
	}

	@Override
	public List<SysUser> getSysUserList(Map<String, Object> paramerMap) {
		// TODO Auto-generated method stub
		return sysUserDao.getSysUserList(paramerMap);
	}

	@Override
	public void saveSysUser(SysUser sysUser) {
		sysUserDao.saveSysUser(sysUser);
	}

	@Override
	public void updateSysUser(SysUser sysUser) {
		sysUserDao.updateSysUser(sysUser);
	}

	@Override
	public void delSysUser(int id) {
		sysUserDao.deleteByPrimaryKey(id);
	}

	@Override
	public int getSysUserListTotalNum(Map<String, Object> paramerMap) {
		return sysUserDao.getSysUserListTotalNum(paramerMap);
	}

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

	@Override
	public SysUser getSysUserByLoginName(String loginName) {
		return sysUserDao.getSysUserByLoginName(loginName);
	}

}
