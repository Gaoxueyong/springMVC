package com.sp.service.impl;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sp.dao.SysMenuDao;
import com.sp.entity.SysMenu;
import com.sp.entity.SysRole;
import com.sp.service.SysMenuService;
import com.sp.utils.Page;
/**
 * 
 * @ClassName SysMenuServiceImpl
 * @Description 菜单service实现
 * @author:Gaoxueyong
 * @Date 2016年12月2日 上午11:20:14
 * @version 1.0.0
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

	@Resource
	private SysMenuDao sysMenuDao;
	
	/**
	 * 
	 * @Description 插入菜单对象
	 * @param SysMenu
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2016年12月2日 上午11:14:46
	 */
	@Override
	public int insertSysMenu(SysMenu sysMenu) {
		return sysMenuDao.insertSysMenu(sysMenu);
	}

	/**
	 * 
	 * @Description 删除(物理删除)
	 * @param id
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2016年12月2日 上午11:08:20
	 */
	@Override
	public int deleteSysMenuByPrimaryKeyReal(String id) {
		return sysMenuDao.deleteSysMenuByPrimaryKeyReal(id);
	}

	 /**
     * 
     * @Description 删除(逻辑删除)
     * @param id
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年12月2日 上午11:08:48
     */
	@Override
	public int deleteSysMenuByPrimaryKey(Map<String, Object> paramerMap) {
		return sysMenuDao.deleteSysMenuByPrimaryKey(paramerMap);
	}

	/**
     * 
     * @Description 更新菜单对象
     * @param SysMenu
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年12月2日 上午11:14:31
     */
	@Override
	public int updateSysMenu(SysMenu SysMenu) {
		return sysMenuDao.updateSysMenu(SysMenu);
	}

	/**
     * 
     * @Description 根据主键ID获取菜单对象
     * @param id
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年12月2日 上午11:13:52
     */
	@Override
	public SysMenu selectSysMenuByPrimaryKey(String id) {
		return sysMenuDao.selectSysMenuByPrimaryKey(id);
	}

	/**
     * 
     * @Description 获取菜单列表
     * @param paramerMap
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年11月21日 上午10:43:33
     */
	@Override
	public List<SysMenu> getSysMenuList(Map<String, Object> paramerMap) {
		return sysMenuDao.getSysMenuList(paramerMap);
	}

	/**
     * 
     * @Description 获取菜单列表
     * @param paramerMap
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年11月21日 上午10:43:33
     */
	@Override
	public Page<SysMenu> getSysMenuListPage(Page<SysMenu> page, Map<String, Object> paramerMap) {
		if(paramerMap.get("currentNo")!=null){
			page.setCurrentNo(Integer.parseInt(paramerMap.get("currentNo").toString()));
		} 
		
		if(paramerMap.get("pageSize")!=null){
			page.setPageSize(Integer.parseInt(paramerMap.get("pageSize").toString()));
		}
		 
		paramerMap.put("startSize",page.getStartSize());
		paramerMap.put("pageSize",page.getPageSize());
		page.setList(sysMenuDao.getSysMenuListPage(paramerMap));
		page.setTotalNum(sysMenuDao.getSysMenuListTotalNum(paramerMap));
		page.initialize();
		return page;
	}


	/**
     * 
     * @Description 获取菜单列表总数
     * @param paramerMap
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年11月21日 上午10:43:38
     */
	@Override
	public int getSysMenuListTotalNum(Map<String, Object> paramerMap) {
		return sysMenuDao.getSysMenuListTotalNum(paramerMap);
	}

	/**
     * 
     * @Description 获取菜单树
     * @param paramerMap
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年12月5日 下午2:32:21
     */
	@Override
	public List<Map<String, Object>> getSysMenuTreeData(Map<String, Object> paramerMap) {
		return sysMenuDao.getSysMenuTreeData(paramerMap);
	}


	
}
