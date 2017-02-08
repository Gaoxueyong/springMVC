package com.sp.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.sp.dao.SysProjectDao;
import com.sp.entity.SysProject;
import com.sp.service.SysProjectService;

/******************************************************************
 *    http://blog.csdn.net/fenfenguai  
 *    Package: com.sp.service.impl
 *    Filename: SysProjectServiceImpl.java
 *    Description: 项目信息操作实现类
 *    @author: Gaoxueyong
 *    @version: 1.0.0
 *    Create at: 2017年2月7日 下午4:27:44
 *    Revision:
 *    2017年2月7日 下午4:27:44- first revision
 *
 *****************************************************************/
public class SysProjectServiceImpl implements SysProjectService {

	@Resource
	private SysProjectDao sysProjectDao;
	/**
	 * @Description 新增项目信息
	 * @param sysProject
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2017年2月7日 下午4:27:44
	 */
	@Override
	public int insertSysProject(SysProject sysProject) {
		return sysProjectDao.insertSysProject(sysProject);
	}

	/**
	 * @Description 删除系统项目对象根据主键id
	 * @param id
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2017年2月7日 下午4:27:44
	 */
	@Override
	public int deleteSysProjectByPrimaryKey(String id) {
		return sysProjectDao.deleteSysProjectByPrimaryKey(id);
	}

	/**
	 * @Description 更新系统项目信息
	 * @param sysProject
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2017年2月7日 下午4:27:44
	 */
	@Override
	public int updateSysProjectByPrimaryKey(SysProject sysProject) {
		return sysProjectDao.updateSysProjectByPrimaryKey(sysProject);
	}

	/**
	 * @Description 根据主键id获取项目信息
	 * @param id
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2017年2月7日 下午4:27:44
	 */
	@Override
	public SysProject selectSysProjectByPrimaryKey(String id) {
		return sysProjectDao.selectSysProjectByPrimaryKey(id);
	}

	/**
     * 
     * @Description 根据条件获取项目信息
     * @param paramerMap
     * @return
     * @author: Gaoxueyong
     * Create at: 2017年2月7日 下午4:44:31
     */
	@Override
	public List<SysProject> querySysProjectList(Map<String, Object> paramerMap) {
		return sysProjectDao.querySysProjectList(paramerMap);
	}

}
