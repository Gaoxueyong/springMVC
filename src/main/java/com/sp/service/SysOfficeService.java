package com.sp.service;
import java.util.List;
import java.util.Map;

import com.sp.entity.SysOffice;
import com.sp.utils.Page;

/**
 * 
 * @ClassName SysOfficeService
 * @Description 机构管理service
 * @author:Gaoxueyong
 * @Date 2016年11月18日 下午5:43:56
 * @version 1.0.0
 */
public interface SysOfficeService {
	
	/**
	 * 
	 * @Description 根据主键id获取SysOffice对象
	 * @param id
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2016年11月21日 上午10:29:52
	 */
	SysOffice selectSysOfficeByPrimaryKey(String id);
	
	/**
	 * 
	 * @Description   插入SysOffice对象 
	 * @param sysOffice
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2016年11月21日 上午10:31:29
	 */
	int insertSysOffice(SysOffice sysOffice);
	
	/**
	 * 
	 * @Description 修改SysOffice对象 
	 * @param sysOffice
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2016年11月21日 上午10:32:42
	 */
	int updateSysOffice(SysOffice sysOffice);
	
	/**
	 * 
	 * @Description 删除SysOffice对象
	 * @param id
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2016年11月21日 上午10:34:26
	 */
    int deleteSysOfficeByPrimaryKey(String id);
    /**
     * 
     * @Description 获取组织机构列表
     * @param page
     * @param paramerMap
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年11月21日 上午10:43:33
     */
    Page<SysOffice> getSysOfficListPage(Page<SysOffice> page,Map<String, Object> paramerMap);
   /**
    * 
    * @Description 获取组织机构列表总数
    * @param paramerMap
    * @return
    * @author: Gaoxueyong
    * Create at: 2016年11月21日 上午10:50:59
    */
    public int getSysOfficListTotalNum(Map<String, Object> paramerMap);
    
    /**
     * 
     * @Description 获取组织机构树据
     * @param paramerMap
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年11月21日 上午11:20:15
     */
    List<Map<String, Object>> getSysOfficeTreeData(Map<String, Object> paramerMap);
    
    /**
     * 
     * @Description 获取组织机构列表
     * @param paramerMap
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年11月21日 上午11:20:15
     */
    List<SysOffice> getSysOfficeList(Map<String, Object> paramerMap);

    /**
     * 
     * @Description 删除目标机构及其下属机构
     * @param paramerMap
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年11月30日 下午3:49:18
     */
    int deleteSysOfficeAndChildrenById(Map<String, Object> paramerMap);
}
