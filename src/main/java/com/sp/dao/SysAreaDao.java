package com.sp.dao;

import java.util.List;
import java.util.Map;

import com.sp.entity.SysArea;
/**
 * 
 * @ClassName SysAreaDao
 * @Description 区域dao
 * @author:Gaoxueyong  http://blog.csdn.net/fenfenguai
 * @Date 2016年11月22日 上午11:19:14
 * @version 1.0.0
 */
public interface SysAreaDao {
	
	int insertSysArea(SysArea sysArea);
	
    int deleteSysAreaByPrimaryKey(String id);

    int updateSysAreaByPrimaryKey(SysArea sysArea);

    SysArea selectSysAreaByPrimaryKey(String id);
    
    /**
     * 
     * @Description 获取区域列表
     * @param paramerMap
     * @return
     * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai
     * Create at: 2016年11月21日 上午10:43:33
     */
    List<SysArea> getSysAreaListPage(Map<String, Object> paramerMap);
    /**
     * 
     * @Description 获取区域列表总数
     * @param paramerMap
     * @return
     * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai
     * Create at: 2016年11月21日 上午10:43:38
     */
    public int getSysAreaListTotalNum(Map<String, Object> paramerMap);
    
    /**
     * 
     * @Description 获取区域树据
     * @param paramerMap
     * @return
     * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai
     * Create at: 2016年11月21日 上午11:20:15
     */
    List<Map<String, Object>> getSysAreaTreeData(Map<String, Object> paramerMap);
    
    /**
     * 
     * @Description 获取区域列表
     * @param paramerMap
     * @return
     * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai
     * Create at: 2016年11月21日 上午11:20:15
     */
    List<SysArea> getSysAreaList(Map<String, Object> paramerMap);
    /**
     * 
     * @Description 根据主键ID删除其本身以及其下属区域
     * @param paramerMap
     * @return
     * @author: Gaoxueyong  http://blog.csdn.net/fenfenguai
     * Create at: 2016年11月30日 上午10:49:46
     */
    int delChildrenById(Map<String, Object> paramerMap);
}