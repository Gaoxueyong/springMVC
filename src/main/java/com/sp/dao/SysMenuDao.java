package com.sp.dao;

import java.util.List;
import java.util.Map;

import com.sp.entity.SysMenu;
/**
 * 
 * @ClassName SysMenuDao
 * @Description 菜单dao
 * @author:Gaoxueyong
 * @Date 2016年12月5日 上午11:37:10
 * @version 1.0.0
 */
public interface SysMenuDao {
	
	/**
	 * 
	 * @Description 插入角色对象
	 * @param SysMenu
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2016年12月2日 上午11:14:46
	 */
	int insertSysMenu(SysMenu SysMenu);
	
	/**
	 * 
	 * @Description 删除(物理删除)
	 * @param id
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2016年12月2日 上午11:08:20
	 */
    int deleteSysMenuByPrimaryKeyReal(String id);

    /**
     * 
     * @Description 删除(逻辑删除)
     * @param paramerMap
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年12月2日 上午11:08:48
     */
    int deleteSysMenuByPrimaryKey(Map<String, Object> paramerMap);
    
    /**
     * 
     * @Description 更新角色对象
     * @param SysMenu
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年12月2日 上午11:14:31
     */
    int updateSysMenu(SysMenu SysMenu);
    
    /**
     * 
     * @Description 根据主键ID获取角色对象
     * @param id
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年12月2日 上午11:13:52
     */
    SysMenu selectSysMenuByPrimaryKey(String id);
    
    /**
     * 
     * @Description 获取角色列表
     * @param paramerMap
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年12月2日 上午11:17:50
     */
    List<SysMenu> getSysMenuList(Map<String, Object> paramerMap);
    
    /**
     * 
     * @Description 获取角色列表
     * @param paramerMap
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年11月21日 上午10:43:33
     */
    List<SysMenu> getSysMenuListPage(Map<String, Object> paramerMap);
    
    /**
     * 
     * @Description 获取角色列表总数
     * @param paramerMap
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年11月21日 上午10:43:38
     */
    public int getSysMenuListTotalNum(Map<String, Object> paramerMap);
    
    /**
     * 
     * @Description 获取组织树
     * @param paramerMap
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年12月5日 下午2:33:43
     */
    List<Map<String, Object>> getSysMenuTreeData(Map<String, Object> paramerMap);
}