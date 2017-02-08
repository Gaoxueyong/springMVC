package com.sp.service;

import java.util.List;
import java.util.Map;

import com.sp.entity.SysProject;

/******************************************************************
 *    http://blog.csdn.net/fenfenguai  
 *    Package: com.sp.service
 *    Filename: SysProjectService.java
 *    Description: TODO(用一句话描述该文件做什么)
 *    @author: Gaoxueyong
 *    @version: 1.0.0
 *    Create at: 2017年2月7日 下午4:26:30
 *    Revision:
 *    2017年2月7日 下午4:26:30- first revision
 *
 *****************************************************************/
public interface SysProjectService {
	
	/**
	 * 
	 * @Description 新增项目信息
	 * @param record
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2017年2月7日 上午10:55:04
	 */
    int insertSysProject(SysProject sysProject);

    /**
     * 
     * @Description 删除项目信息
     * @param id
     * @return
     * @author: Gaoxueyong
     * Create at: 2017年2月7日 下午4:19:55
     */
    int deleteSysProjectByPrimaryKey(String id);
    
    /**
     * 
     * @Description 修改项目信息
     * @param record
     * @return
     * @author: Gaoxueyong
     * Create at: 2017年2月7日 下午4:21:07
     */
    int updateSysProjectByPrimaryKey(SysProject sysProject);
    
    /**
     * 
     * @Description 根据主键id获取对象信息
     * @param id
     * @return
     * @author: Gaoxueyong
     * Create at: 2017年2月7日 下午4:20:13
     */
    SysProject selectSysProjectByPrimaryKey(String id);
    
    /**
     * 
     * @Description 根据条件获取项目信息
     * @param paramerMap
     * @return
     * @author: Gaoxueyong
     * Create at: 2017年2月7日 下午4:44:31
     */
    List<SysProject> querySysProjectList(Map<String, Object> paramerMap);
}
