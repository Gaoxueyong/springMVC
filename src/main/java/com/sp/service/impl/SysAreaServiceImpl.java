package com.sp.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sp.dao.SysAreaDao;
import com.sp.entity.SysArea;
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

}
