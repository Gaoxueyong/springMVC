package com.sp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sp.entity.SysOffice;
/**
 * 
 * @ClassName SysOfficeDao
 * @Description 组织机构dao
 * @author:Gaoxueyong  http://blog.csdn.net/fenfenguai
 * @Date 2016年11月21日 上午10:26:43
 * @version 1.0.0
 */
@Repository
public interface SysOfficeDao {
	
	/**
	 * 
	 * @Description 根据主键id获取SysOffice对象
	 * @param id
	 * @return
	 * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai
	 * Create at: 2016年11月21日 上午10:29:52
	 */
	SysOffice selectSysOfficeByPrimaryKey(String id);
	
	/**
	 * 
	 * @Description   插入SysOffice对象 
	 * @param sysOffice
	 * @return
	 * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai
	 * Create at: 2016年11月21日 上午10:31:29
	 */
	int insertSysOffice(SysOffice sysOffice);
	
	/**
	 * 
	 * @Description 修改SysOffice对象 
	 * @param sysOffice
	 * @return
	 * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai
	 * Create at: 2016年11月21日 上午10:32:42
	 */
	int updateSysOffice(SysOffice sysOffice);
	
	/**
	 * 
	 * @Description 删除SysOffice对象
	 * @param id
	 * @return
	 * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai
	 * Create at: 2016年11月21日 上午10:34:26
	 */
    int deleteSysOfficeByPrimaryKey(String id);
    /**
     * 
     * @Description 获取组织机构列表
     * @param paramerMap
     * @return
     * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai
     * Create at: 2016年11月21日 上午10:43:33
     */
    List<SysOffice> getSysOfficListPage(Map<String, Object> paramerMap);
    /**
     * 
     * @Description 获取组织机构列表总数
     * @param paramerMap
     * @return
     * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai
     * Create at: 2016年11月21日 上午10:43:38
     */
    public int getSysOfficListTotalNum(Map<String, Object> paramerMap);
    
    /**
     * 
     * @Description 获取组织机构树据
     * @param paramerMap
     * @return
     * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai
     * Create at: 2016年11月21日 上午11:20:15
     */
    List<Map<String, Object>> getSysOfficeTreeData(Map<String, Object> paramerMap);
    
    /**
     * 
     * @Description 获取组织机构列表
     * @param paramerMap
     * @return
     * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai
     * Create at: 2016年11月21日 上午11:20:15
     */
    List<SysOffice> getSysOfficeList(Map<String, Object> paramerMap);
    
    /**
     * 
     * @Description 删除目标机构及其下属机构
     * @param paramerMap
     * @return
     * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai
     * Create at: 2016年11月30日 下午3:49:18
     */
    int deleteSysOfficeAndChildrenById(Map<String, Object> paramerMap);
}