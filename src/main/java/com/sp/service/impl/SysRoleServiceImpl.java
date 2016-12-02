package com.sp.service.impl;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sp.dao.SysRoleDao;
import com.sp.entity.SysRole;
import com.sp.service.SysRoleService;
import com.sp.utils.Page;
/**
 * 
 * @ClassName SysRoleServiceImpl
 * @Description 角色service实现
 * @author:Gaoxueyong
 * @Date 2016年12月2日 上午11:20:14
 * @version 1.0.0
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Resource
	private SysRoleDao sysRoleDao;
	
	/**
	 * 
	 * @Description 插入角色对象
	 * @param sysRole
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2016年12月2日 上午11:14:46
	 */
	@Override
	public int insertSysRole(SysRole sysRole) {
		return sysRoleDao.insertSysRole(sysRole);
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
	public int deleteSysRoleByPrimaryKeyReal(String id) {
		return sysRoleDao.deleteSysRoleByPrimaryKeyReal(id);
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
	public int deleteSysRoleByPrimaryKey(Map<String, Object> paramerMap) {
		return sysRoleDao.deleteSysRoleByPrimaryKey(paramerMap);
	}

	/**
     * 
     * @Description 更新角色对象
     * @param sysRole
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年12月2日 上午11:14:31
     */
	@Override
	public int updateSysRole(SysRole sysRole) {
		return sysRoleDao.updateSysRole(sysRole);
	}

	/**
     * 
     * @Description 根据主键ID获取角色对象
     * @param id
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年12月2日 上午11:13:52
     */
	@Override
	public SysRole selectSysRoleByPrimaryKey(String id) {
		return sysRoleDao.selectSysRoleByPrimaryKey(id);
	}

	/**
     * 
     * @Description 获取角色列表
     * @param paramerMap
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年11月21日 上午10:43:33
     */
	@Override
	public List<SysRole> getSysRoleList(Map<String, Object> paramerMap) {
		return sysRoleDao.getSysRoleList(paramerMap);
	}

	/**
     * 
     * @Description 获取角色列表
     * @param paramerMap
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年11月21日 上午10:43:33
     */
	@Override
	public Page<SysRole> getSysRoleListPage(Page<SysRole> page, Map<String, Object> paramerMap) {
		if(paramerMap.get("currentNo")!=null){
			page.setCurrentNo(Integer.parseInt(paramerMap.get("currentNo").toString()));
		} 
		
		if(paramerMap.get("pageSize")!=null){
			page.setPageSize(Integer.parseInt(paramerMap.get("pageSize").toString()));
		}
		 
		paramerMap.put("startSize",page.getStartSize());
		paramerMap.put("pageSize",page.getPageSize());
		page.setList(sysRoleDao.getSysRoleListPage(paramerMap));
		page.setTotalNum(sysRoleDao.getSysRoleListTotalNum(paramerMap));
		page.initialize();
		return page;
	}

	/**
     * 
     * @Description 获取角色列表总数
     * @param paramerMap
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年11月21日 上午10:43:38
     */
	@Override
	public int getSysRoleListTotalNum(Map<String, Object> paramerMap) {
		return sysRoleDao.getSysRoleListTotalNum(paramerMap);
	}

}
