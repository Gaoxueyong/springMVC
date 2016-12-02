package com.sp.service;

import java.util.List;
import java.util.Map;


import com.sp.entity.SysArea;
import com.sp.utils.Page;
/**
 * 
 * @ClassName SysAreaService
 * @Description  区域管理service
 * @author:Gaoxueyong
 * @Date 2016年11月22日 上午11:36:38
 * @version 1.0.0
 */
public interface SysAreaService {
	int insertSysArea(SysArea sysArea);
	
    int deleteSysAreaByPrimaryKey(String id);

    int updateSysAreaByPrimaryKey(SysArea sysArea);

    SysArea selectSysAreaByPrimaryKey(String id);
    
    /**
     * 
     * @Description 获取区域列表
     * @param paramerMap
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年11月21日 上午10:43:33
     */
    Page<SysArea> getSysAreaListPage(Page<SysArea> page,Map<String, Object> paramerMap);
    /**
     * 
     * @Description 获取区域列表总数
     * @param paramerMap
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年11月21日 上午10:43:38
     */
    public int getSysAreaListTotalNum(Map<String, Object> paramerMap);
    
    /**
     * 
     * @Description 获取区域树据
     * @param paramerMap
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年11月21日 上午11:20:15
     */
    List<Map<String, Object>> getSysAreaTreeData(Map<String, Object> paramerMap);
    
    /**
     * 
     * @Description 获取区域列表
     * @param paramerMap
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年11月21日 上午11:20:15
     */
    List<SysArea> getSysAreaList(Map<String, Object> paramerMap);
    
    /**
     * 
     * @Description 根据主键ID删除其本身以及其下属区域
     * @param paramerMap
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年11月30日 上午10:49:46
     */
    int delChildrenById(Map<String, Object> paramerMap);

}
