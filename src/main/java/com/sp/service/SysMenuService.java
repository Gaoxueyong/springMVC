package com.sp.service;

import java.util.List;
import java.util.Map;

import com.sp.entity.SysMenu;
import com.sp.utils.Page;
/**
 * 
 * @ClassName SysMenuService
 * @Description 菜单dao
 * @author:Gaoxueyong
 * @Date 2016年12月5日 上午11:37:10
 * @version 1.0.0
 */
public interface SysMenuService {
	
	/**
	 * 
	 * @Description 插入菜单对象
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
     * @Description 更新菜单对象
     * @param SysMenu
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年12月2日 上午11:14:31
     */
    int updateSysMenu(SysMenu SysMenu);
    
    /**
     * 
     * @Description 根据主键ID获取菜单对象
     * @param id
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年12月2日 上午11:13:52
     */
    SysMenu selectSysMenuByPrimaryKey(String id);
    
    /**
     * 
     * @Description 获取菜单列表
     * @param paramerMap
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年12月2日 上午11:17:50
     */
    List<SysMenu> getSysMenuList(Map<String, Object> paramerMap);
    
    /**
     * 
     * @Description 获取菜单列表
     * @param paramerMap
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年11月21日 上午10:43:33
     */
    public Page<SysMenu> getSysMenuListPage(Page<SysMenu> page,Map<String, Object> paramerMap);
    
    /**
     * 
     * @Description 获取菜单列表总数
     * @param paramerMap
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年11月21日 上午10:43:38
     */
    public int getSysMenuListTotalNum(Map<String, Object> paramerMap);
    
    /**
     * 
     * @Description 获取菜单树
     * @param paramerMap
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年12月5日 下午2:32:21
     */
    List<Map<String, Object>> getSysMenuTreeData(Map<String, Object> paramerMap);
    
    /**
     * 
     * @Description 根据角色id获取改角色的角色树  那些选中了 那些没选中
     * @param roleId
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年12月7日 下午5:46:11
     */
    List<Map<String, Object>> getSysMenuTreeDataChecked(Map<String, Object> paramerMap);
    
    /**
     * 
     * @Description 构造树结构列表
     * @param menuRoot
     * @param menuList
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年12月9日 下午3:35:18
     */
    List<SysMenu> getTreeList(List<SysMenu> menuRoot,List<SysMenu>menuList,List<SysMenu> resultList);
    
    /**
     * 
     * @Description 根据用户名账号获取菜单资源
     * @param paramerMap   account
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年12月27日 上午11:50:22
     */
    List<SysMenu> getSysMenuByAccount(Map<String, Object> paramerMap);
    
    /**
     * 
     * @Description 获取菜单列表 根据登录人权限
     * @author: Gaoxueyong
     * @param paramerMap
     * @return
     * Create at: 2017年1月3日 下午2:21:39
     */
    List<SysMenu> getSysMenuListByPermission(Map<String, Object> paramerMap);
}