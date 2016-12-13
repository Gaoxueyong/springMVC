package com.sp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sp.dao.SysAreaDao;
import com.sp.entity.SysArea;
import com.sp.entity.SysOffice;
import com.sp.service.SysAreaService;
import com.sp.utils.Page;
/**
 * 
 * @ClassName SysAreaServiceImpl
 * @Description 区域管理实现类
 * @author:Gaoxueyong
 * @Date 2016年11月22日 上午11:38:54
 * @version 1.0.0
 */
@Service
public class SysAreaServiceImpl implements SysAreaService {
	
	@Resource
	private SysAreaDao sysAreaDao;
	
	@Override
	public int insertSysArea(SysArea sysArea) {
		return sysAreaDao.insertSysArea(sysArea);
	}

	@Override
	public int deleteSysAreaByPrimaryKey(String id) {
		return sysAreaDao.deleteSysAreaByPrimaryKey(id);
	}

	@Override
	public int updateSysAreaByPrimaryKey(SysArea sysArea) {
		return sysAreaDao.updateSysAreaByPrimaryKey(sysArea);
	}

	@Override
	public SysArea selectSysAreaByPrimaryKey(String id) {
		return sysAreaDao.selectSysAreaByPrimaryKey(id);
	}

	@Override
	public Page<SysArea> getSysAreaListPage(Page<SysArea> page,Map<String, Object> paramerMap) {
		if(paramerMap.get("currentNo")!=null){
			page.setCurrentNo(Integer.parseInt(paramerMap.get("currentNo").toString()));
		}
		if(paramerMap.get("pageSize")!=null){
			page.setPageSize(Integer.parseInt(paramerMap.get("pageSize").toString()));
		}
		 
		paramerMap.put("startSize",page.getStartSize());
		paramerMap.put("pageSize",page.getPageSize());
		 
		page.setTotalNum(sysAreaDao.getSysAreaListTotalNum(paramerMap));
		page.setList(sysAreaDao.getSysAreaListPage(paramerMap));
		page.initialize();
		
		return page;
	}

	@Override
	public int getSysAreaListTotalNum(Map<String, Object> paramerMap) {
		return sysAreaDao.getSysAreaListTotalNum(paramerMap);
	}

	@Override
	public List<Map<String, Object>> getSysAreaTreeData(Map<String, Object> paramerMap) {
		return sysAreaDao.getSysAreaTreeData(paramerMap);
	}

	@Override
	public List<SysArea> getSysAreaList(Map<String, Object> paramerMap) {
		return sysAreaDao.getSysAreaList(paramerMap);
	}

    /**
     * 
     * @Description 根据主键ID删除其本身以及其下属区域
     * @param id
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年11月30日 上午10:49:46
     */
	@Override
	public int delChildrenById(Map<String, Object> paramerMap) {
		return sysAreaDao.delChildrenById(paramerMap);
	}
	
	 /**
     * 
     * @Description 获取树形结构列表
     * @param areaRootList
     * @param areaList
     * @param resultList
     * @return
     * @author: Gaoxueyong
     * Create at: 2016年12月13日 上午10:00:41
     */
	@Override
	public List<SysArea> getTreeList(List<SysArea> areaRootList, List<SysArea> areaList, List<SysArea> resultList) {
		return getAreaTreeList(areaRootList, areaList, null);
	}
	
	/**
	 * 
	 * @Description 获取根及其子节点
	 * @param rootList
	 * @param menuList
	 * @return
	 * @author: Gaoxueyong
	 * Create at: 2016年12月12日 上午11:47:51
	 */
	public List<SysArea> getAreaTreeList(List<SysArea> areaRootList,List<SysArea> areaList,Map<String, Object> containMap){
		List<SysArea> returnList = new ArrayList<SysArea>();
		if(areaList==null || areaList.size()==0){
			return returnList;
		}
		if(containMap==null){containMap=new HashMap<String,Object>();}
		for(SysArea root:areaRootList){
			//if(!containMap.containsKey(root.getId())){
				returnList.add(root);
				containMap.put(root.getId(), root.getId());
				for(SysArea child:areaList){
					if(!containMap.containsKey(child.getId())){
						if(root.getId().equals(child.getParentId())){
							returnList.add(child);
							containMap.put(child.getId(), child.getId());
						}
					}
				}
			//}
		}
		
		if(returnList!=null && returnList.size()==areaList.size()){
			return returnList;
		} 
		return getAreaTreeList(returnList, areaList,containMap);
	}

}
